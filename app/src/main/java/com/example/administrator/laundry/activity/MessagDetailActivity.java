package com.example.administrator.laundry.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.CommentAdapter;
import com.example.administrator.laundry.adapter.ImageAdapter;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.view.LoadDataView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessagDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_image)
    ImageView userImage;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.image_list)
    RecyclerView imageList;
    @BindView(R.id.commet_list)
    RecyclerView commetList;
    private ImageAdapter imageAdapter;
    private CommentAdapter commentAdapter;
    private List<String> iamges;
    private List<String> comments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_messag_detail;
    }

    @Override
    protected void initView() {
        setImageList();
        setCommentList();
    }

    private void setImageList() {
        iamges = new ArrayList<>();
        imageAdapter = new ImageAdapter(this);
        imageList.setLayoutManager(new GridLayoutManager(this, 3));
        imageList.setHasFixedSize(true);
        imageList.setAdapter(imageAdapter);
    }

    private void setCommentList() {
        comments = new ArrayList<>();
        commentAdapter = new CommentAdapter(this);
        commetList.setLayoutManager(new LinearLayoutManager(this));
        commetList.setHasFixedSize(true);
        commetList.setAdapter(imageAdapter);
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

    @OnClick({R.id.img_back, R.id.btn_like_num, R.id.btn_like, R.id.btn_collect_num, R.id.user_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_image:
                toActivity(FriendInfoActivity.class);
                break;
            case R.id.img_back:
                break;
            case R.id.btn_like_num:
                break;
            case R.id.btn_like:
                break;
            case R.id.btn_collect_num:
                break;
        }
    }
}
