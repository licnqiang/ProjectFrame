package com.project.Kang_Lee.laundry.util.WXbean;

import android.text.TextUtils;

import com.project.Kang_Lee.laundry.base.BaseApplication;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2017/5/9.
 */

public class WxInfo {

    private String appId;                         //appid
    private String partnerId;                     //商户号
    private String prepayId;                      //预支付订单号
    private String packageValue;                  //扩展字段
    private String nonceStr;                      //随机字符串
    private String timeStamp;                     //时间戳
    private String sign;                          //签名

    public WxInfo() {
    }

    public WxInfo(String info) throws JSONException {

        if (TextUtils.isEmpty(info)) {

            return;
        }

            JSONObject jsonObject = new JSONObject(info);  //String转jsonObject

            appId=jsonObject.optString("appid");

            BaseApplication.App_ID=appId;       //保存appid,在微信支付回调页面使用

            partnerId=jsonObject.optString("partnerid");
            prepayId=jsonObject.optString("prepayid");
            packageValue=jsonObject.optString("package");
            nonceStr=jsonObject.optString("noncestr");
            timeStamp=jsonObject.optString("timestamp");
            sign=jsonObject.optString("sign");

    }

    public String getAppId() {
        return appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSign() {
        return sign;
    }
}
