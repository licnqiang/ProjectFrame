
package com.example.administrator.laundry.NetService.parser;


import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.Login;
import com.example.administrator.laundry.NetService.http.HttpConnector;
import com.example.administrator.laundry.NetService.util.Log;
import com.google.gson.Gson;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 获取验证码解析层
 * @data on  2019/2/14 11:03
 * @describe 发送请求，解析获取验证码返回数据
 */
public class CodeParser extends BaseParser {

    private BaseReseponseInfo baseReseponseInfo=new BaseReseponseInfo();

    private String url = "/DorLogin/resLogin";


    private NetControl.GetResultListenerCallback listener;

    public CodeParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {


        this.listener = listener;

//        setTest(true);
//
//        setTestFileName("LoginParser.txt");

        setParameters(mHashMap);

        setUrlBody(url);

        setRequestMethod(HttpConnector.METHOD_POST);

        setReturnInfo(baseReseponseInfo);
    }


    @Override
    protected void parser() {
        try {
        } catch (Exception e) {
            Log.e(TAG, CLASS_NAME + "--e==" + e);
        }
    }

    @Override
    protected void Success() {
        listener.onFinished(baseReseponseInfo);
    }

    @Override
    protected void Error() {
        listener.onErro(baseReseponseInfo);
    }

}
