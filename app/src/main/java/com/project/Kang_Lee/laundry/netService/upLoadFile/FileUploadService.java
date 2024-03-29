package com.project.Kang_Lee.laundry.netService.upLoadFile;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * @author lq
 * @time 2018/11/29
 */
public interface FileUploadService {
    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST("/dl_laundry/upload")
    Call<BaseResponse> uploadFilesWithParts(@Part() List<MultipartBody.Part> parts);
}
