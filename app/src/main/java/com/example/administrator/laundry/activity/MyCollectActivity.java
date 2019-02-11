package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.HomeAdapter;
import com.example.administrator.laundry.base.BaseActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.list)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private HomeAdapter homeAdapter;
    private List<String> listItem;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initView() {
        tvTitle.setText("我的收藏");
    }

    @Override
    protected void initData() {
        setAdapter();
    }

    private void setAdapter() {
        listItem = new ArrayList<>();
        homeAdapter = new HomeAdapter(this, listItem);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setAdapter(homeAdapter);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
