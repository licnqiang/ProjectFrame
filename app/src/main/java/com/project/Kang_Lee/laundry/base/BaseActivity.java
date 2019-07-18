package com.project.Kang_Lee.laundry.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.project.Kang_Lee.laundry.netService.data.BaseReseponseInfo;
import com.project.Kang_Lee.laundry.netService.util.Log;
import com.project.Kang_Lee.laundry.base.event.NetworkChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.putActivityInfoToMap(this);
        EventBus.getDefault().register(this);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
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
        EventBus.getDefault().unregister(this);
        BaseApplication.removeActivityInfoFromMap(this);
    }

    public void toActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    /**
     * 网络监听回调
     *
     * @author wangyj
     * @time 2018/8/14 14:17
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventNetworkChange(NetworkChangeEvent event) {
//        if(!event.isConnectNetwork){
//            Toast.makeText(this,
//                    "网络断开", Toast.LENGTH_SHORT).show();
//        }
    }


    /**
     * 所有请求错误回掉
     * 统一处理所有的网络错误
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventNetworkChange(BaseReseponseInfo event) {
        RequestOnErro(event);
    }


    private void RequestOnErro(Object o){
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
