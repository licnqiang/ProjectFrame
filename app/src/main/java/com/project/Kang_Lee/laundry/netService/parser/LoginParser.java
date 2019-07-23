
package com.project.Kang_Lee.laundry.netService.parser;


import com.project.Kang_Lee.laundry.netService.control.NetControl;
import com.project.Kang_Lee.laundry.netService.data.LoginInfo;
import com.project.Kang_Lee.laundry.netService.http.HttpConnector;
import com.project.Kang_Lee.laundry.util.Log;
import com.project.Kang_Lee.laundry.constant.UrlContant;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 登录解析层
 * @data on  2019/2/14 11:03
 * @describe 发送请求，解析登录返回数据
 */
public class LoginParser extends BaseParser {

    private LoginInfo mInfo;

    private String url = UrlContant.login;


    private NetControl.GetResultListenerCallback listener;

    public LoginParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {


        this.listener = listener;
//
//        setTest(true);
//
//        setTestFileName("LoginParser.txt");

        setParameters(mHashMap);

        setUrlBody(url);

        setRequestMethod(HttpConnector.METHOD_POST);

        setReturnInfo(mInfo);
    }


    @Override
    protected void parser() {
        try {
            Log.e("-----------","-------------"+new Gson().toJson(mJson));
            mInfo = new Gson().fromJson(mJson.toString(), LoginInfo.class);
        } catch (Exception e) {
            Log.e(TAG, CLASS_NAME + "--e==" + e);
        }
    }

    @Override
    protected void Success() {
        listener.onFinished(mInfo);
    }

    @Override
    protected void Error() {
        EventBus.getDefault().post(mInfo);
    }

}
