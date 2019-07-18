package com.project.Kang_Lee.laundry.database;

import android.content.Context;
import com.project.Kang_Lee.laundry.constant.FileConstant;
import com.raizlabs.android.dbflow.config.FlowManager;
import java.io.File;



/**
 * Created by lq on 2018/7/3.
 * 初始化数据库
 */

public class InitDBUtil {
    public static void initDB(Context context,String userId) {

        String dbName = FileConstant.getDBPath(userId);

        FlowManager.init(new DatabaseContext(context, new File(dbName), false));
    }
}
