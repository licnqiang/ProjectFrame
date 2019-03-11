package com.example.administrator.laundry.activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.laundry.NetService.data.PostListBean;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.HomeAdapter;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.view.LoadDataView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyMessageActivity extends BaseActivity implements HomeAdapter.OnRecyclerViewItemClickListener{
    @BindView(R.id.list)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private HomeAdapter homeAdapter;
    private List<PostListBean.NoteBean> listItem;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initView() {
        tvTitle.setText("我的帖子");
    }

    @Override
    protected void initData() {
        setAdapter();
    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {

    }

    private void setAdapter() {
        listItem = new ArrayList<>();
        homeAdapter = new HomeAdapter(this, listItem);
        homeAdapter.setOnItemClickListener(this);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setAdapter(homeAdapter);
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 点击条目
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        toActivity(MessagDetailActivity.class);
    }
}
