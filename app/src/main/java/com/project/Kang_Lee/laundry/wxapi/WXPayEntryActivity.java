package com.project.Kang_Lee.laundry.wxapi;

import android.content.Intent;
import android.os.Bundle;
import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.common.BaseActivity;
import com.project.Kang_Lee.laundry.common.BaseApplication;
import com.project.Kang_Lee.laundry.util.ToastUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * 微信支付结果界面
 * @author hfk
 * create at 2016/5/3 10:42
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final String TAG= "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    private static WXCallBack wxCallBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, BaseApplication.App_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pay_result;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    /**
     * 结果回调方法
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        int code = resp.errCode;
        //支付成功
        if (code == 0){
            ToastUtil.show(this,"支付成功");
        }
        if (code == -1){
            //错误
            ToastUtil.show(this,"支付错误");
        }
        if (code == -2){
            //用户取消
            ToastUtil.show(this,"用户取消");
        }
    }
    public static void setWXCallBack(WXCallBack callBack){
        wxCallBack=callBack;
    }

    public interface WXCallBack {
        void msgCallBask(int msg);
    }

}