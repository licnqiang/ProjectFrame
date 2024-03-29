package com.project.Kang_Lee.laundry.netService.control;

import com.project.Kang_Lee.laundry.netService.parser.ChangePswParser;
import com.project.Kang_Lee.laundry.netService.parser.CodeParser;
import com.project.Kang_Lee.laundry.netService.parser.ForgetPswParser;
import com.project.Kang_Lee.laundry.netService.parser.LoginParser;
import com.project.Kang_Lee.laundry.netService.parser.RegisterParser;

import java.util.HashMap;

/**
 * @author lq
 * @fileName 网络控制层
 * @data on  2019/2/14 10:09
 * @describe 分发网络请求到各个解析层
 */
public class NetControl {


    public interface GetResultListenerCallback {
        void onFinished(Object o);

    }


    /**
     * 登陆
     */
    public static void Login(final GetResultListenerCallback listener,
                             final HashMap<String, String> mHashMap) {

        LoginParser mParser = new LoginParser(listener, mHashMap);
        mParser.start();
    }

    /**
     * 获取验证码
     */
    public static void GetCode(final GetResultListenerCallback listener,
                               final HashMap<String, String> mHashMap) {

        CodeParser mParser = new CodeParser(listener, mHashMap);
        mParser.start();
    }

    /**
     * 注册
     */
    public static void Register(final GetResultListenerCallback listener,
                                final HashMap<String, String> mHashMap) {

        RegisterParser mParser = new RegisterParser(listener, mHashMap);
        mParser.start();
    }


    /**
     * 忘记密码
     */
    public static void ForgetPsw(final GetResultListenerCallback listener,
                                 final HashMap<String, String> mHashMap) {

        ForgetPswParser mParser = new ForgetPswParser(listener, mHashMap);
        mParser.start();
    }


    /**
     * 修改密码
     */
    public static void changpsw(final GetResultListenerCallback listener,
                                   final HashMap<String, String> mHashMap) {

        ChangePswParser mParser = new ChangePswParser(listener, mHashMap);
        mParser.start();
    }


}
