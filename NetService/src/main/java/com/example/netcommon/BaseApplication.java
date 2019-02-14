package com.example.netcommon;

import android.app.Application;
import android.content.Context;

/**
 * @author lq
 * @fileName BaseApplication
 * @data on  2019/2/14 10:45
 * @describe TODO
 */
public class BaseApplication extends Application {

    public static Context ApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContext = getApplicationContext();
    }
}
