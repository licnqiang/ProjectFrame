package com.example.administrator.laundry.activity;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.LoadDataView;
import com.example.administrator.laundry.view.progress.LSProgressDialog;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_psw)
    EditText etUserPsw;
    private LSProgressDialog progressDialog;

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

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {

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
                login();
                break;
        }
    }


    public void login() {
        progressDialog = new LSProgressDialog(this);
        progressDialog.show();
        EMClient.getInstance().login(etUserName.getText().toString(), etUserPsw.getText().toString(), new EMCallBack() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        // 加载所有群组到内存，如果使用了群组的话
//                         EMClient.getInstance().groupManager().loadAllGroups();
                        // 加载所有会话到内存
                        EMClient.getInstance().chatManager().loadAllConversations();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("----------","-----好友列表-----"+EMClient.getInstance().contactManager().getBlackListUsernames());
                            }
                        });
                        ToastUtil.show(LoginActivity.this, "登录，成功开始聊天吧");
                        toActivity(MainActivity.class);
                        finish();
                    }
                });
            }

            @Override
            public void onError(final int i, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        ToastUtil.show(LoginActivity.this, "登录失败 code: " + i + ",message: " + s);
                        switch (i) {
                            case EMError.NETWORK_ERROR:
                                ToastUtil.show(LoginActivity.this, "网络异常，请检查网络！ code: " + i + "，message: " + s);
                                break;
                            case EMError.INVALID_USER_NAME:
                                ToastUtil.show(LoginActivity.this, "无效用户名！ code: " + i + "，message: " + s);
                                break;
                            case EMError.INVALID_PASSWORD:
                                ToastUtil.show(LoginActivity.this, "用户密码不正确！ code: " + i + "，message: " + s);
                                break;
                            case EMError.USER_AUTHENTICATION_FAILED:
                                ToastUtil.show(LoginActivity.this, "用户名或密码不正确！ code: " + i + "，message: " + s);
                                break;
                            case EMError.USER_NOT_FOUND:
                                ToastUtil.show(LoginActivity.this, "用户不存在！ code: " + i + "，message: " + s);
                                break;
                            case EMError.SERVER_NOT_REACHABLE:
                                ToastUtil.show(LoginActivity.this, "无法连接到服务器！ code: " + i + "，message: " + s);
                                break;
                            case EMError.SERVER_BUSY:
                                ToastUtil.show(LoginActivity.this, "服务器繁忙，请稍后.... code: " + i + "，message: " + s);
                                break;
                            case EMError.SERVER_TIMEOUT:
                                ToastUtil.show(LoginActivity.this, "等待服务器响应超时！ code: " + i + "，message: " + s);
                                break;
                            case EMError.SERVER_UNKNOWN_ERROR:
                                ToastUtil.show(LoginActivity.this, "未知服务器错误！ code: " + i + "，message: " + s);
                                break;
                            case EMError.USER_ALREADY_LOGIN:
                                ToastUtil.show(LoginActivity.this, "用户已登录！ code: " + i + "，message: " + s);
                                break;

                        }
                    }
                });


            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

}

