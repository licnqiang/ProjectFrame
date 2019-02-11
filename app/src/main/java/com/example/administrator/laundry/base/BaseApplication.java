package com.example.administrator.laundry.base;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.administrator.laundry.util.SpHelper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseApplication extends Application {
    public static Map<String, Activity> activityMap = new HashMap<String, Activity>();        //管理activity 的容器
    private static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        SpHelper.init(getApplicationContext());
    }

    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    //收集创建的Activity
    public static void putActivityInfoToMap(Activity activity) {
        if (activity != null) {
            String activityName = activity.getClass().getSimpleName();
            Log.i("info", "putActivity--->" + activityName);

            activityMap.put(activityName, activity);
        }
    }

    //移除activity
    public static void removeActivityInfoFromMap(Activity activity) {
        if (activity != null) {
            String activityName = activity.getClass().getSimpleName();
            Log.i("info", "removeActivity--->" + activityName);
            if (activityMap.containsKey(activityName)) {
                activityMap.remove(activityName);
            }
        }
    }
    //关闭所有界面
    public static void closeAllActivityByMap() {
        if (!activityMap.isEmpty()) {
            Collection<Activity> activities = activityMap.values();
            Iterator<Activity> it = activities.iterator();
            while (it.hasNext()) {
                Activity activity = it.next();
                String activityName = activity.getClass().getSimpleName();

                Log.i("info", "removeActivity--->" + activityName);

                activity.finish();
            }
        }

    }


}
