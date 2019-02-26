package com.example.administrator.laundry.NetService.data;

import java.io.Serializable;

/**
 * @author lq
 * @fileName http返回信息
 * @data on  2019/2/14 10:27
 * @describe 响应数据的基类
 */
public class BaseReseponseInfo implements Serializable {
    public final static int SUCCESS = 200;//连接成功
    //    请求失败
    public final static int ERROR_CODE = 201;
    //    验证码错误
    public final static int CODE_PASSWORD_ERRO = 202;
    //    手机号码格式不正确
    public final static int CODE_PHONE_ERRO = 203;
    //    用户不存在
    public final static int CODE_NOUSER_ERRO = 204;
    //    该用户已注册
    public final static int CODE_EXIST_ERRO = 205;
    //    请重新登录
    public final static int CODE_TOKEN_ERRO = 210;
    //    用户名或密码错误
    public final static int CODE_VERIFICATION_ERRO = 212;
    //    数据库语言执行错误
    public final static int CODE_EXECUTSQL_ERRO = 215;
    //    服务器内部错误
    public final static int CODE_SERVICE_ERROR = 217;
    //    Cookie错误
    public final static int CODE_COOKIE_ERROR = 220;
    //    非法请求
    public final static int CODE_PUPPET_ERROR = 4000;
    //    参数为空
    public final static int CODE_PAREMATER_ERROR = 500;
    //    请求参数为空
    public final static int CODE_PAREMATERNO_ERROR = 400;


    String info;// 内容

    int flag;// 标签

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setFlag(String flag) {
        this.flag = Integer.parseInt(flag);
    }
}
