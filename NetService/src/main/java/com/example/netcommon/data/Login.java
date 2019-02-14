package com.example.netcommon.data;

/**
 *@author lq
 *@fileName 登录响应数据
 *@data on  2019/2/14 11:01
 *@describe TODO
 */

public class Login extends BaseReseponseInfo{


    /**
     * code : 0
     * msg : 请求成功
     * data : {"id":2,"userRealName":"王医生"}
     */
    public String code;
    public String msg;
    public DataBean data;

    public static class DataBean {
        /**
         * id : 2
         * userRealName : 王医生
         */

        public String id;
        public String userRealName;
        public String userMobile;
        public String openId;
    }
}
