package com.project.Kang_Lee.laundry.ui.activity;


import android.view.View;
import android.widget.EditText;

import com.project.Kang_Lee.laundry.database.dbTab.UserInfo_Tab;
import com.project.Kang_Lee.laundry.netService.control.NetControl;
import com.project.Kang_Lee.laundry.netService.data.LoginInfo;
import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.common.BaseActivity;
import com.project.Kang_Lee.laundry.common.BaseApplication;
import com.project.Kang_Lee.laundry.constant.SysContant;
import com.project.Kang_Lee.laundry.util.SpHelper;
import com.project.Kang_Lee.laundry.util.ToastUtil;

import java.util.HashMap;
import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_psw)
    EditText etUserPsw;

    HashMap<String, String> mHashMap = new HashMap<>();

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
                UserInfo_Tab userInfo_tab=new UserInfo_Tab();
                userInfo_tab.userName="李强";
                userInfo_tab.save();
                toActivity(MainActivity.class);
                break;
        }
    }


    private void login(){
        String userName = etUserName.getText().toString().trim();
        String userPsw = etUserPsw.getText().toString().trim();
        if (userName.isEmpty() || userPsw.isEmpty()) {
            ToastUtil.show(LoginActivity.this, "用户名或密码不能为空");
        } else {
            mHashMap.put("userPhone", userName);
            mHashMap.put("userPassword", userPsw);
            NetControl.Login(callback, mHashMap);
        }
//                toActivity(MainActivity.class);
    }


    /**
     * 登录自己服务器
     */
    NetControl.GetResultListenerCallback callback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            if(null==o){
                return;
            }
            LoginInfo login=(LoginInfo)o;
            //保存token
            SpHelper.setStringValue(SysContant.userInfo.USER_TOKEN, login.token);
            BaseApplication.token= SpHelper.getStringValue(SysContant.userInfo.USER_TOKEN);
        }
    };

}

