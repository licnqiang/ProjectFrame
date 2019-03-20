package com.example.administrator.laundry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.UserInfo;
import com.example.administrator.laundry.NetService.util.LoadingUI;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.SpHelper;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.LoadDataView;
import com.hyphenate.easeui.EaseConstant;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendInfoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_image)
    ImageView userImage;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_age)
    EditText userAge;
    @BindView(R.id.user_address)
    EditText userAddress;
    @BindView(R.id.store_name)
    EditText storeName;
    @BindView(R.id.user_content)
    EditText userContent;
    @BindView(R.id.user_sigin)
    EditText userSigin;
    @BindView(R.id.user_sex)
    TextView userSex;
    HashMap<String, String> mHashMap = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_info;
    }

    @Override
    protected void initView() {
        tvTitle.setText("用户信息");
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
//        //根据条目位置设置图片
//        Glide.with(this)                             //配置上下文
//                .load("")      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.collect_mrtp)           //设置错误图片
//                .placeholder(R.mipmap.collect_mrtp)     //设置占位图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
//                .into(userImage);
//        userName.setText("");
//        userSex.setText("");
//        userAge.setText("");
//        userPhone.setText("");
//        userAddress.setText("");

    }

    @OnClick({R.id.img_back, R.id.btn_send_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_send_message:
                startActivity(new Intent(FriendInfoActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, EaseConstant.EXTRA_HUANXIN + userInfo.userPhone)
                );

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHashMap.clear();
        LoadingUI.showDialogForLoading(this, "正在加载", true);
        mHashMap.put("userId", getIntent().getStringExtra("id"));
        NetControl.getUserInfo(infoCallback, mHashMap);
    }


    private UserInfo userInfo;
    /**
     * 个人信息
     */
    NetControl.GetResultListenerCallback infoCallback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            LoadingUI.hideDialogForLoading();
            if (null != o) {
                userInfo = (UserInfo) o;


                userName.setText(userInfo.userNickname);
                if (userInfo.userSex == 0) {
                    userSex.setText("男");
                } else {
                    userSex.setText("女");
                }
                userAge.setText(userInfo.userWworkingTime);
                userAddress.setText(userInfo.userShopAddress);
                storeName.setText(userInfo.userShop);
                userContent.setText(userInfo.userIntroduce);
                userSigin.setText(userInfo.userSign);
                SpHelper.setStringValue(EaseConstant.EXTRA_USER_name,EaseConstant.EXTRA_HUANXIN+userInfo.userPhone);
            }
        }

        @Override
        public void onErro(Object o) {
            BaseReseponseInfo baseReseponseInfo = (BaseReseponseInfo) o;
            if (null != baseReseponseInfo && null != baseReseponseInfo.getInfo() && baseReseponseInfo.getInfo().isEmpty()) {
                ToastUtil.show(FriendInfoActivity.this, baseReseponseInfo.getInfo());
            } else {
                ToastUtil.show(FriendInfoActivity.this, "获取失败");
            }
        }
    };
}
