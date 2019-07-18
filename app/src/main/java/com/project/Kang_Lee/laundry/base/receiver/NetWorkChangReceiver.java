package com.project.Kang_Lee.laundry.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.project.Kang_Lee.laundry.base.event.NetworkChangeEvent;
import org.greenrobot.eventbus.EventBus;


/**
 * 网络连接监听
 *
 * @author wangyj
 * @time 2018/8/14 14:04
 */
public class NetWorkChangReceiver extends BroadcastReceiver {

    /**
     * 获取连接类型
     *
     * @param type
     * @return
     */
    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "移动网络数据";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }

    /**
     * 判断网络是否连接
     *
     * @author wangyj
     * @time 2018/8/14 14:03
     */
    private boolean isConnection(Context context) {
        //获取联网状态的NetworkInfo对象
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Log.i("TAG", getConnectionType(info.getType()) + "连上");
                        return true;
                    }
                } else {
                    Log.i("TAG", getConnectionType(info.getType()) + "断开");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnect = isConnection(context);
        EventBus.getDefault().post(new NetworkChangeEvent(isConnect));
    }

}