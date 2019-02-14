package com.example.netcommon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.netcommon.control.NetControl;
import com.example.netcommon.util.Log;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, String> mHashMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHashMap.put("userName", "");
        mHashMap.put("passWord", "");
        NetControl.Login(callback, mHashMap);
    }

    NetControl.GetResultListenerCallback callback=new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
        }

        @Override
        public void onErro(Object o) {

        }
    };
}
