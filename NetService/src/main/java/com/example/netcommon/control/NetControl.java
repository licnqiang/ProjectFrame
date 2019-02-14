package com.example.netcommon.control;

import com.example.netcommon.parser.LoginParser;

import java.util.HashMap;

/**
 * @author lq
 * @fileName 网络控制层
 * @data on  2019/2/14 10:09
 * @describe 分发网络请求到各个解析层
 */
public class NetControl {


    public interface GetResultListenerCallback {
        /**
         * 请求成功
         *
         * @param o 成功回调响应数据
         */
        void onFinished(Object o);

        /**
         * 请求失败
         *
         * @param o 成功回调响应数据
         */
        void onErro(Object o);

    }


    /**
     * 登陆
     */
    public static void Login(final GetResultListenerCallback listener,
                             final HashMap<String, String> mHashMap) {

        LoginParser mParser = new LoginParser(listener, mHashMap);
        mParser.start();
    }

}
