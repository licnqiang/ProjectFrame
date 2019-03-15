
package com.example.administrator.laundry.NetService.parser;


import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.http.HttpConnector;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 忘记密码解析层
 * @data on  2019/2/14 11:03
 * @describe 发送请求，解析忘记密码返回数据
 */
public class ChangePswParser extends BaseParser {

    private BaseReseponseInfo baseReseponseInfo=new BaseReseponseInfo();

    private String url = "setUserPwd";


    private NetControl.GetResultListenerCallback listener;

    public ChangePswParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {


        this.listener = listener;

//        setTest(true);
//
        setTestFileName("Common.txt");

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
        listener.onErro(baseReseponseInfo);
    }

}
