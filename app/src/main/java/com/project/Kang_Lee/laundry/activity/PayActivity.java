package com.project.Kang_Lee.laundry.activity;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.alipay.sdk.app.PayTask;
import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.base.BaseActivity;
import com.project.Kang_Lee.laundry.util.PayResult;
import com.project.Kang_Lee.laundry.util.WXbean.WxInfo;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.OnClick;

public class PayActivity extends BaseActivity {
    // 判断resultStatus 为“9000”则代表支付成功
    public final static String SUCCEED = "9000";
    // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
    public final static String CONFIRM = "8000";
    private static final int SDK_PAY_FLAG = 1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, SUCCEED)) {

                    } else {
                        if (TextUtils.equals(resultStatus, CONFIRM)) {

                        } else {
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_alipay, R.id.btn_wxpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_alipay:
                WXpay(new WxInfo());
                break;
            case R.id.btn_wxpay:
                Alipay("");
                break;
        }
    }



    /**
     * 原生支付宝支付
     */
    private void Alipay(final String payInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                //調用支付接口，獲取支付結果
                String result = alipay.pay(payInfo, true);
                //获取支付结果
                if (result != null) {
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                } else {
                    return;
                }
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 原生微信支付
     *
     * @param
     */
    private void WXpay(WxInfo wxInfo) {
        IWXAPI api = WXAPIFactory.createWXAPI(PayActivity.this, wxInfo.getAppId());
        api.registerApp(wxInfo.getAppId());
        //微信接收支付参数的容器
        PayReq payRequest = new PayReq();
        payRequest.appId = wxInfo.getAppId();                           //appid
        payRequest.partnerId = wxInfo.getPartnerId();                   //商户号
        payRequest.prepayId = wxInfo.getPrepayId();                     //预支付订单号
        payRequest.packageValue = wxInfo.getPackageValue();             //扩展字段
        payRequest.nonceStr = wxInfo.getNonceStr();                     //随机字符串
        payRequest.timeStamp = wxInfo.getTimeStamp();                   //时间戳
        payRequest.sign = wxInfo.getSign();                             //签名
        //支付
        api.sendReq(payRequest);
    }
}
