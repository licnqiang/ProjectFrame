package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.view.SelectDialog;

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
                finish();
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
}
