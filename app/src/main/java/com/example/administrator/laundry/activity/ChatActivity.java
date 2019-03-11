package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.view.ViewGroup;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.view.LoadDataView;
import com.hyphenate.easeui.ui.EaseChatFragment;

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EaseChatFragment easeChatFragment = new EaseChatFragment();  //环信聊天界面
        easeChatFragment.setArguments(getIntent().getExtras()); //需要的参数
        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat,easeChatFragment).commit();  //Fragment切换
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {

    }
}
