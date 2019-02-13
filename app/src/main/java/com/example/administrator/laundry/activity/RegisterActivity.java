package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.LoadDataView;
import com.example.administrator.laundry.view.SelectDialog;
import com.example.administrator.laundry.view.progress.LSProgressDialog;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.et_user_sex)
    TextView etUserSex;
    @BindView(R.id.et_user_code)
    EditText etUserCode;
    @BindView(R.id.et_user_login_psw)
    EditText etUserLoginPsw;
    @BindView(R.id.et_user_time)
    EditText etUserTime;
    @BindView(R.id.et_user_store_name)
    EditText etUserStoreName;
    @BindView(R.id.et_user_store_location)
    EditText etUserStoreLocation;
    @BindView(R.id.et_user_content)
    EditText etUserContent;
    @BindView(R.id.et_user_centent)
    EditText etUserCentent;
    @BindView(R.id.regitster_layout)
    LinearLayout regitsterLayout;

    private LSProgressDialog progressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tvTitle.setText(R.string.title_register);
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

    @OnClick({R.id.img_back, R.id.et_user_sex, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.et_user_sex:
                showSexDialog();
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }


    private void showSexDialog() {
        List<String> names = new ArrayList<>();
        names.add("男");
        names.add("女");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etUserSex.setText("男");
                        break;
                    case 1:
                        etUserSex.setText("女");
                        break;
                }

            }
        }, names);

    }

    /**
     * 选择性别的dialog
     *
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(RegisterActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        return dialog;
    }


    public void register() {
        progressDialog = new LSProgressDialog(this);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(etUserName.getText().toString().trim(), etUserLoginPsw.getText().toString().trim());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            ToastUtil.show(RegisterActivity.this, "注册成功，用户名是:" + etUserName.getText().toString() + "  快开始聊天吧");
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    /**
                     * 关于错误码可以参考官方api详细说明
                     * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                     */
                    final int errorCode = e.getErrorCode();
                    final String message = e.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch (errorCode) {
                                case EMError.NETWORK_ERROR:
                                    ToastUtil.show(RegisterActivity.this, "网络异常，请检查网络！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_ALREADY_EXIST:
                                    ToastUtil.show(RegisterActivity.this, "用户名已存在,请尝试登录！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_ALREADY_LOGIN:
                                    ToastUtil.show(RegisterActivity.this, "用户已登录！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_AUTHENTICATION_FAILED:
                                    ToastUtil.show(RegisterActivity.this, "用户id或密码错误！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.SERVER_UNKNOWN_ERROR:
                                    ToastUtil.show(RegisterActivity.this, "服务器位置错误！ code: " + errorCode + "，message: " + message);
                                    break;
                                case EMError.USER_REG_FAILED:
                                    ToastUtil.show(RegisterActivity.this, "注册失败！ code: " + errorCode + "，message: " + message);
                                    break;
                                default:
                                    ToastUtil.show(RegisterActivity.this, "ml_sign_up_failed  code: " + errorCode + "，message: " + message);
                                    break;
                            }
                        }
                    });

                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}