package com.example.administrator.laundry.activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.LoadDataView;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPswActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_psw)
    EditText etUserPsw;
    @BindView(R.id.et_user_next_psw)
    EditText etUserNextPsw;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_psw;
    }

    @Override
    protected void initView() {
        tvTitle.setText(R.string.title_forget_psw);
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

    @OnClick({R.id.img_back, R.id.btn_forgetPsw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_forgetPsw:
                judgeData();
                break;
        }
    }

    private void judgeData() {
        String userName = etUserName.getText().toString().trim();
        String userPsw = etUserPsw.getText().toString().trim();
        String userNextPsw = etUserNextPsw.getText().toString().trim();

        if (userName.isEmpty() || userPsw.isEmpty() || userNextPsw.isEmpty()) {
            ToastUtil.show(ForgetPswActivity.this, "填写所有信息");
        } else if (!userPsw.equals(userNextPsw)) {
            ToastUtil.show(ForgetPswActivity.this, "两次密码不一致");
        } else {
            //发送请求
        }
    }
}
