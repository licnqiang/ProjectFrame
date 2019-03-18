
package com.example.administrator.laundry.NetService.parser;


import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.http.HttpConnector;

import java.util.HashMap;


/**
 * @author lq
 * @fileName 收藏
 * @data on  2019/2/14 11:03
 * @describe
 */
public class CollectParser extends BaseParser {

    private BaseReseponseInfo baseReseponseInfo=new BaseReseponseInfo();

    private String url = "collect";


    private NetControl.GetResultListenerCallback listener;

    public CollectParser(NetControl.GetResultListenerCallback listener, HashMap<String, String> mHashMap) {


        this.listener = listener;

//        setTest(true);
////
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
        listener.onErro(baseReseponseInfo);
    }

}
