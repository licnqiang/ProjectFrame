
package com.example.netcommon.parser;


import com.example.netcommon.control.NetControl;
import com.example.netcommon.data.Login;
import com.example.netcommon.http.HttpConnector;
import com.example.netcommon.util.Log;
import com.google.gson.Gson;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 登录解析层
 * @data on  2019/2/14 11:03
 * @describe 发送请求，解析登录返回数据
 */
public class LoginParser extends BaseParser {

    private Login mInfo;

    private String url = "/DorLogin/resLogin";


    private NetControl.GetResultListenerCallback listener;

    public LoginParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {

        this.listener = listener;

      setTest(true);

      setTestFileName("LoginParser.txt");

        setParameters(mHashMap);

        setUrlBody(url);

        setRequestMethod(HttpConnector.METHOD_POST);

        setReturnInfo(mInfo);
    }


    @Override
    protected void parser() {
        try {
            mInfo = new Gson().fromJson(mJson.toString(), Login.class);
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
        listener.onErro(mInfo);

    }

}
