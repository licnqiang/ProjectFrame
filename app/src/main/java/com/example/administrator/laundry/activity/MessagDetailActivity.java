package com.example.administrator.laundry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.Detail;
import com.example.administrator.laundry.NetService.util.Log;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.CommentAdapter;
import com.example.administrator.laundry.adapter.ImageAdapter;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.base.BaseApplication;
import com.example.administrator.laundry.util.DateUtils;
import com.example.administrator.laundry.view.LoadDataView;
import com.hb.dialog.myDialog.MyAlertInputDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessagDetailActivity extends BaseActivity implements CommentAdapter.OnRecyclerViewItemClickListener {

    HashMap<String, String> mHashMap = new HashMap<>();
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
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.local)
    TextView local;
    @BindView(R.id.time)
    TextView time;
    private ImageAdapter imageAdapter;
    private CommentAdapter commentAdapter;
    private List<String> iamges;
    private List<Detail.Comment> comments;
    private String noteId;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_messag_detail;
    }

    @Override
    protected void initView() {
        getData();
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
        commentAdapter = new CommentAdapter(this, comments);
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
                if (btnLike.isSelected()) {
                    btnLikeNum.setText("" + (Integer.valueOf(btnLikeNum.getText().toString()) + 1));
                } else {
                    btnLikeNum.setText("" + (Integer.valueOf(btnLikeNum.getText().toString()) - 1));
                }
                break;
            case R.id.btn_collect:
                btnCollect.setSelected(!btnCollect.isSelected());
                if (btnCollect.isSelected()) {
                    btnCollectNum.setText("" + (Integer.valueOf(btnCollectNum.getText().toString()) + 1));
                } else {
                    btnCollectNum.setText("" + (Integer.valueOf(btnCollectNum.getText().toString()) - 1));
                }
                break;
            case R.id.btn_share:
                btnShare.setSelected(!btnLike.isSelected());
                if (btnShare.isSelected()) {
                    btnShareNum.setText("" + (Integer.valueOf(btnShareNum.getText().toString()) + 1));
                } else {
                    btnShareNum.setText("" + (Integer.valueOf(btnShareNum.getText().toString()) - 1));
                }
                break;
            case R.id.btn_send:
                showDialog();
                break;
            case R.id.user_image:
                startActivity(new Intent(MessagDetailActivity.this, FriendInfoActivity.class).putExtra("id", detail.userId));
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(MessagDetailActivity.this, FriendInfoActivity.class).putExtra("id", detail.comment.get(position).userId));
    }

    public void getData() {

        noteId = getIntent().getStringExtra("noteId");
        Log.e("-------","-----noteId-----"+noteId);
    }


    private Detail detail;
    NetControl.GetResultListenerCallback postListCallback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            if (null != o) {
                detail = (Detail) o;
                id = detail.noteId + "";
                type.setText(detail.noteType + "");
                local.setText(detail.noteAddress);
                time.setText(DateUtils.stampToDate(detail.noteTime));
                Glide.with(MessagDetailActivity.this)                             //配置上下文
                        .load(detail.userImgNumber)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                        .error(R.mipmap.collect_mrtp)           //设置错误图片
                        .placeholder(R.mipmap.collect_mrtp)     //设置占位图片
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                        .into(userImage);

                userName.setText(detail.userNickname);
                btnLikeNum.setText(detail.notePraise);
                comments.addAll(detail.comment);
                commentAdapter.notifyDataSetChanged();
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
                            startActivity(new Intent(MessagDetailActivity.this,
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
                Toast.makeText(MessagDetailActivity.this,
                        "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mHashMap.put("noteId", noteId);
        NetControl.getDetail(postListCallback, mHashMap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
