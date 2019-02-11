package com.example.administrator.laundry.activity;


import android.view.View;
import android.widget.EditText;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_psw)
    EditText etUserPsw;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.regitster, R.id.forget_psw, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regitster:
                toActivity(RegisterActivity.class);
                break;
            case R.id.forget_psw:
                toActivity(ForgetPswActivity.class);
                break;
            case R.id.btn_login:
                toActivity(MainActivity.class);
                break;
        }
    }
}

