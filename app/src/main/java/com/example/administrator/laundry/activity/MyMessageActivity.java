package com.example.administrator.laundry.activity;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.PostListBean;
import com.example.administrator.laundry.NetService.util.LoadingUI;
import com.example.administrator.laundry.NetService.util.Log;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.HomeAdapter;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.base.BaseApplication;
import com.example.administrator.laundry.view.LoadDataView;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<String, String> mHashMap = new HashMap<>();
    public PostListBean postListBean;

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
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(MyMessageActivity.this,MessagDetailActivity.class).putExtra("noteId",listItem.get(position).noteId));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHashMap.clear();
        mHashMap.put("noteType","0");
        mHashMap.put("use","1");
        mHashMap.put("noteId","0");
        LoadingUI.showDialogForLoading(this,"正在加载",true);
        NetControl.GetPostList(postListCallback,mHashMap);
    }


    NetControl.GetResultListenerCallback postListCallback = new NetControl.GetResultListenerCallback() {

        @Override
        public void onFinished(Object o) {
            LoadingUI.hideDialogForLoading();
            if(null!=o){
                postListBean=(PostListBean)o;
                if(postListBean.getNote().size()>0){
                    listItem.addAll(postListBean.getNote());
                    homeAdapter.notifyDataSetChanged();
                }
            }

        }

        @Override
        public void onErro(Object o) {
            if (o != null) {
                BaseReseponseInfo mBaseReseponseInfo = (BaseReseponseInfo) o;
                int code = mBaseReseponseInfo.getFlag();
                String msg = mBaseReseponseInfo.getInfo();
                if (msg != null && msg.length() > 0) {
                    Log.e("TAG-code", code + "");
                    Log.e("TAG-msg", msg);
                    switch (code) {
                        case BaseReseponseInfo.CODE_TOKEN_ERRO:
                            Toast.makeText(
                                    BaseApplication.ApplicationContext,
                                    "登录失效，请重新登录！", Toast.LENGTH_SHORT)
                                    .show();
                            startActivity(new Intent(MyMessageActivity.this,
                                    LoginActivity.class).putExtra("flag",
                                    false));
                            finish();
                            break;
                        default:
                            Toast.makeText(
                                    BaseApplication.ApplicationContext,
                                    msg + " code:" + code,
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            } else {
                Toast.makeText(MyMessageActivity.this,
                        "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
