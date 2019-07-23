package com.project.Kang_Lee.laundry.ui.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.Kang_Lee.laundry.netService.control.NetControl;
import com.project.Kang_Lee.laundry.netService.data.BaseReseponseInfo;
import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.common.BaseActivity;
import com.project.Kang_Lee.laundry.common.BaseApplication;
import com.project.Kang_Lee.laundry.util.RxDeviceTool;
import com.project.Kang_Lee.laundry.util.ToastUtil;
import com.project.Kang_Lee.laundry.ui.view.CountDownTextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPswActivity extends BaseActivity {

    HashMap<String, String> mHashMap = new HashMap<>();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_yzm)
    EditText etUserYzm;
    @BindView(R.id.et_user_next_psw)
    EditText etUserNextPsw;
    @BindView(R.id.send_yzm)
    CountDownTextView sendYzm;

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

    @OnClick({R.id.img_back, R.id.btn_forgetPsw, R.id.send_yzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_forgetPsw:
//                judgeData();
                finish();
                break;
            case R.id.send_yzm:
                String etUserPhone = etUserName.getText().toString();
                if (TextUtils.isEmpty(etUserPhone)) {
                    ToastUtil.show(this, "请输入手机号");
                    sendYzm.reset();
                } else {
                    if (!RxDeviceTool.isMobileNO(etUserPhone)) {
                        ToastUtil.show(this, "输入手机号有误");
                        sendYzm.reset();
                        return;
                    }
                    sendYzm.start();
                    mHashMap.put("userPhone", etUserPhone);
                    NetControl.GetCode(callback, mHashMap);
                }
                break;
        }
    }

    private void judgeData() {
        String userName = etUserName.getText().toString().trim();
        String userYzm = etUserYzm.getText().toString().trim();
        String userNextPsw = etUserNextPsw.getText().toString().trim();

        if (userName.isEmpty() || userYzm.isEmpty() || userNextPsw.isEmpty()) {
            ToastUtil.show(ForgetPswActivity.this, "填写所有信息");
        } else {
            mHashMap.clear();
            mHashMap.put("userPhone", userName);
            mHashMap.put("proof", userYzm);
            mHashMap.put("userPassword", userNextPsw);
            NetControl.ForgetPsw(registerCallback, mHashMap);
        }
    }

    NetControl.GetResultListenerCallback callback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            ToastUtil.show(ForgetPswActivity.this,"验证码获取成功");
        }
    };


    NetControl.GetResultListenerCallback registerCallback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            BaseReseponseInfo info = (BaseReseponseInfo) o;
            if (null != info && null != info.getInfo()) {
                Toast.makeText(
                        BaseApplication.ApplicationContext,
                        info.getInfo(),
                        Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    };

}
