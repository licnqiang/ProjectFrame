package com.example.administrator.laundry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.view.LoadDataView;
import com.hyphenate.easeui.EaseConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_info;
    }

    @Override
    protected void initView() {
        tvTitle.setText("用户信息");
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


    @OnClick({R.id.img_back, R.id.btn_send_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_send_message:
                startActivity(new Intent(FriendInfoActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,"jiting"));
                break;
        }
    }
}