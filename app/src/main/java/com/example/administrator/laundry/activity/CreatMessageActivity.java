package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.fragment.SelectImageFragment;
import com.example.administrator.laundry.view.LoadDataView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatMessageActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private SelectImageFragment selectImageFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_message;
    }

    @Override
    protected void initView() {
        tvTitle.setText("发表动态");
    }

    @Override
    protected void initData() {
        setFragment();
    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {
    }

    /**
     * 设置图片选择fragment
     */
    private void setFragment() {
        selectImageFragment = new SelectImageFragment();
        SelectImageFragment.maxImgCount = 6;
        selectImageFragment.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_image, selectImageFragment).commitAllowingStateLoss();
    }

    @OnClick({R.id.img_back, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_send:
                finish();
                break;
        }
    }
}
