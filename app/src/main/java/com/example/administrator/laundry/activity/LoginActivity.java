package com.example.administrator.laundry.activity;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.Login;
import com.example.administrator.laundry.NetService.util.LoadingUI;
import com.example.administrator.laundry.NetService.util.Log;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.base.BaseApplication;
import com.example.administrator.laundry.constant.SysContant;
import com.example.administrator.laundry.util.SpHelper;
import com.example.administrator.laundry.util.ToastUtil;
import com.google.gson.Gson;
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
            LoadingUI.showDialogForLoading(this,"正在登录",true);
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
            Login login=(Login)o;
            LoadingUI.hideDialogForLoading();
            //保存token
            SpHelper.setStringValue(SysContant.userInfo.USER_TOKEN, login.token);
            BaseApplication.token= SpHelper.getStringValue(SysContant.userInfo.USER_TOKEN);
        }

        @Override
        public void onErro(Object o) {
            LoadingUI.hideDialogForLoading();
            if (o != null) {
                BaseReseponseInfo mBaseReseponseInfo = (BaseReseponseInfo) o;
                int code = mBaseReseponseInfo.getFlag();
                String msg = mBaseReseponseInfo.getInfo();
                if (msg != null && msg.length() > 0) {
                    Log.e("TAG-code", code + "");
                    Log.e("TAG-msg", msg);
                    Toast.makeText(
                            BaseApplication.ApplicationContext,
                            msg + " code:" + code,
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this,
                        "用户不存在", Toast.LENGTH_SHORT).show();
            }
        }
    };

}

