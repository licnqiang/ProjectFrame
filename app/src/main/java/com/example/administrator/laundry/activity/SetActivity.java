package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.base.BaseApplication;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.img_back, R.id.ll_self_setting_update_pwd, R.id.about, R.id.ll_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_self_setting_update_pwd:
                toActivity(ChangPswActivity.class);
                break;
            case R.id.about:
                break;
            case R.id.ll_exit:
                logOut();
                break;
        }
    }

    public void logOut() {
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                BaseApplication.closeAllActivityByMap();
            }

            @Override
            public void onError(int i, String s) {
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
