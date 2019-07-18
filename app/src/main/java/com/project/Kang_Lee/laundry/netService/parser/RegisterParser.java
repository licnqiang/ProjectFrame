
package com.project.Kang_Lee.laundry.netService.parser;


import com.project.Kang_Lee.laundry.netService.control.NetControl;
import com.project.Kang_Lee.laundry.netService.data.BaseReseponseInfo;
import com.project.Kang_Lee.laundry.netService.http.HttpConnector;
import com.project.Kang_Lee.laundry.constant.UrlContant;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 注册解析层
 * @data on  2019/2/14 11:03
 * @describe 发送请求，解析注册返回数据
 */
public class RegisterParser extends BaseParser {

    private BaseReseponseInfo baseReseponseInfo=new BaseReseponseInfo();

    private String url = UrlContant.register;

    private NetControl.GetResultListenerCallback listener;

    public RegisterParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {


        this.listener = listener;

//        setTest(true);
//
//        setTestFileName("Common.txt");

        setParameters(mHashMap);

        setUrlBody(url);

        setRequestMethod(HttpConnector.METHOD_POST);

        setReturnInfo(baseReseponseInfo);
    }


    @Override
    protected void parser() {
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
