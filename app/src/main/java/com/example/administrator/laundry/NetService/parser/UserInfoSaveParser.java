
package com.example.administrator.laundry.NetService.parser;


import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.http.HttpConnector;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 个人信息保存
 * @data on  2019/2/14 11:03
 * @describe
 */
public class UserInfoSaveParser extends BaseParser {

    private BaseReseponseInfo baseReseponseInfo=new BaseReseponseInfo();

    private String url = "setMyInfo";


    private NetControl.GetResultListenerCallback listener;

    public UserInfoSaveParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {


        this.listener = listener;

//        setTest(true);
//
//        setTestFileName("user.txt");

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
