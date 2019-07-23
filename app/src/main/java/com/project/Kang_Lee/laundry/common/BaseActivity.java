package com.project.Kang_Lee.laundry.common;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hb.dialog.dialog.LoadingDialog;
import com.project.Kang_Lee.laundry.netService.data.BaseReseponseInfo;
import com.project.Kang_Lee.laundry.util.DialogUtils;
import com.project.Kang_Lee.laundry.util.Log;
import com.project.Kang_Lee.laundry.common.event.NetworkChangeEvent;
import com.project.Kang_Lee.laundry.util.NetUtil;
import com.project.Kang_Lee.laundry.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder mBind;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全部禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BaseApplication.putActivityInfoToMap(this);
        EventBus.getDefault().register(this);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        initLoadingDialog();
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mBind) {
            mBind.unbind();
        }
        dismiss();
        EventBus.getDefault().unregister(this);
        BaseApplication.removeActivityInfoFromMap(this);
    }

    protected void initLoadingDialog(){
        if (null==loadingDialog) {
            loadingDialog = new LoadingDialog(this);
        }
    }

    public void showLoadingDialog(String message,boolean cancelable){
        loadingDialog.setMessage(message);
        loadingDialog.setCancelable(cancelable);
        loadingDialog.show();
    }

    public void dismiss() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    /**
     * 跳转界面
     */
    public void toActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    /**
     * 网络监听回调
     * @time 2018/8/14 14:17
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventNetworkChange(NetworkChangeEvent event) {
        Log.i("netType", "netType:" + event.networkType);
        if (!NetUtil.isNetConnect(event.networkType)) {
            ToastUtil.show(this, "网络异常");
        } else {
            ToastUtil.show(this, "网络恢复");
        }
    }




    /**
     * 所有请求错误回掉
     * 统一处理所有的网络错误
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventNetworkChange(BaseReseponseInfo event) {
        RequestOnErro(event);
    }

    private void RequestOnErro(Object o) {
        if (o != null) {
            BaseReseponseInfo mBaseReseponseInfo = (BaseReseponseInfo) o;
            int code = mBaseReseponseInfo.getFlag();
            String msg = mBaseReseponseInfo.getInfo();
            if (msg != null && msg.length() > 0) {
                Log.e("TAG-code", code + "");
                Log.e("TAG-msg", msg);
                Toast.makeText(
                        BaseApplication.ApplicationContext,
                        msg + " code:" + code,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,
                    "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
        }
    }


}
