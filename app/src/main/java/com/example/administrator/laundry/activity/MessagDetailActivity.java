package com.example.administrator.laundry.activity;

import android.os.Bundle;
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
import com.hb.dialog.myDialog.MyAlertInputDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessagDetailActivity extends BaseActivity implements CommentAdapter.OnRecyclerViewItemClickListener{


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
    @BindView(R.id.btn_like_num)
    TextView btnLikeNum;
    @BindView(R.id.btn_like)
    TextView btnLike;
    @BindView(R.id.btn_collect_num)
    TextView btnCollectNum;
    @BindView(R.id.btn_collect)
    TextView btnCollect;
    @BindView(R.id.btn_share_num)
    TextView btnShareNum;
    @BindView(R.id.btn_share)
    TextView btnShare;
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
        tvTitle.setText("详情");
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
        commentAdapter.setOnItemClickListener(this);
        commetList.setLayoutManager(new LinearLayoutManager(this));
        commetList.setHasFixedSize(true);
        commetList.setAdapter(commentAdapter);
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

    private void showDialog() {
        final MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(this).builder()
                .setTitle("请输入")
                .setEditText("");
        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showMsg(myAlertInputDialog.getResult());
                myAlertInputDialog.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showMsg("取消");
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    @OnClick({R.id.img_back, R.id.btn_like, R.id.btn_collect, R.id.btn_share, R.id.btn_send, R.id.user_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_like:
                btnLike.setSelected(!btnLike.isSelected());
                if(btnLike.isSelected()){
                    btnLikeNum.setText(""+(Integer.valueOf(btnLikeNum.getText().toString())+1));
                } else {
                    btnLikeNum.setText(""+(Integer.valueOf(btnLikeNum.getText().toString())-1));
                }
                break;
            case R.id.btn_collect:
                btnCollect.setSelected(!btnCollect.isSelected());
                if(btnCollect.isSelected()){
                    btnCollectNum.setText(""+(Integer.valueOf(btnCollectNum.getText().toString())+1));
                } else {
                    btnCollectNum.setText(""+(Integer.valueOf(btnCollectNum.getText().toString())-1));
                }
                break;
            case R.id.btn_share:
                btnShare.setSelected(!btnLike.isSelected());
                if(btnShare.isSelected()){
                    btnShareNum.setText(""+(Integer.valueOf(btnShareNum.getText().toString())+1));
                } else {
                    btnShareNum.setText(""+(Integer.valueOf(btnShareNum.getText().toString())-1));
                }
                break;
            case R.id.btn_send:
                showDialog();
                break;
            case R.id.user_image:
                toActivity(FriendInfoActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        toActivity(FriendInfoActivity.class);
    }
}
