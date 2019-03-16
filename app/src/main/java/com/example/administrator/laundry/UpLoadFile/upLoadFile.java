package com.example.administrator.laundry.UpLoadFile;

import android.util.Log;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class upLoadFile {

    public interface ResultCallBack {
        void succeed(List<String> str);

        void faild();
    }

    /**
     * 文件上传
     *
     * @param paths
     */
    public static void uploadFile(List<String> paths, final ResultCallBack resultCallBack) {
        final StringBuffer sbServerPath = new StringBuffer();
        List<MultipartBody.Part> body = UpLoadFileNet.filesToMultipartBodyParts(paths);
        final FileUploadService service = UpLoadFileNet.buildRetrofit().create(FileUploadService.class);
        Call<BaseResponse> call = service.uploadFilesWithParts(body);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (!response.isSuccessful()) {
                    resultCallBack.faild();
                    return;
                }
                BaseResponse fileInfo = response.body();

                if(null!=fileInfo) {
                    resultCallBack.succeed(fileInfo.imgData);
                }else {
                    resultCallBack.faild();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("-----------","---文件上传失败-------"+t.getMessage());
                resultCallBack.faild();
            }
        });
    }
}
