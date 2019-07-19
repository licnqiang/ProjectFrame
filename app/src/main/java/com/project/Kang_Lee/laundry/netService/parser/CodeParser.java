
package com.project.Kang_Lee.laundry.netService.parser;


import com.google.gson.Gson;
import com.project.Kang_Lee.laundry.netService.control.NetControl;
import com.project.Kang_Lee.laundry.netService.data.BaseReseponseInfo;
import com.project.Kang_Lee.laundry.netService.http.HttpConnector;
import com.project.Kang_Lee.laundry.util.Log;
import com.project.Kang_Lee.laundry.constant.UrlContant;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 获取验证码解析层
 * @data on  2019/2/14 11:03
 * @describe 发送请求，解析获取验证码返回数据
 */
public class CodeParser extends BaseParser {

    private BaseReseponseInfo baseReseponseInfo=new BaseReseponseInfo();

    private String url = UrlContant.get_code;


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
        EventBus.getDefault().post(baseReseponseInfo);
    }

}
