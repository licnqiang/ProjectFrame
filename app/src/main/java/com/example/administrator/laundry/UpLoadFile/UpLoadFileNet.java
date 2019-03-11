package com.example.administrator.laundry.UpLoadFile;


import com.example.administrator.laundry.NetService.http.UrlConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lq
 * @time 2018/11/29
 */
public class UpLoadFileNet {

    private static RequestBody requestBody;

    public static MultipartBody filesToMultipartBody(List<String> paths) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (String path : paths) {
            File file = new File(path);
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }


    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> paths) {
        File file;
        List<MultipartBody.Part> parts = new ArrayList<>(paths.size());
        for (String path : paths) {
            file = new File(path);
            if (null == file || !file.isFile()) {
                return null;
            }
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();

            /* 依扩展名的类型决定MimeType 这里只用了pdf word 图片更类型可查http://www.cnblogs.com/hibr     aincol/archive/2010/09/16/1828502.html*/
            if (end.equals("jpg") || end.endsWith("JPG") || end.endsWith("PNG") || end.endsWith("png") || end.endsWith("jpeg") || end.endsWith("JPEG")) {
//                file = PictureUtils.scal(path);
                requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            } else if (end.equals("mp3")) {
                requestBody = RequestBody.create(MediaType.parse("audio/mpeg"), file);
            } else if (end.equals("mp4") || end.endsWith("3gp")) {
                requestBody = RequestBody.create(MediaType.parse("video/mp4"), file);
            }
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    private static Retrofit retrofit;

    public synchronized static Retrofit buildRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging).retryOnConnectionFailure(true)
                    .build();
            retrofit = new Retrofit.Builder().client(client)
                    .baseUrl(UrlConfig.URL_PREFIX)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
        }
        return retrofit;
    }

}
