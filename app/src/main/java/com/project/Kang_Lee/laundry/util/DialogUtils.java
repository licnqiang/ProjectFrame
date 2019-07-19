package com.project.Kang_Lee.laundry.util;

import android.content.Context;
import android.content.DialogInterface;


/**
 * Created by sen.luo on 2018/6/25.
 */

public class DialogUtils {

    /**
     * 只有确认按钮的Dialog
     * @param context
     * @param message
     * @param negativeClickListener
     */
    public static void onlyConfirmDialog(Context context, String message, DialogInterface.OnClickListener negativeClickListener){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setNegativeButton("确定", negativeClickListener);
        builder.create().show();
    }

    public static void generalDialog(Context context, String message, DialogInterface.OnClickListener negativeClickListener){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("确定", negativeClickListener);
        builder.create().show();
    }
}
