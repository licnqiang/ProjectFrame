package com.example.administrator.laundry.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.UserInfo;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.activity.DatalibraryActivity;
import com.example.administrator.laundry.activity.MyCollectActivity;
import com.example.administrator.laundry.activity.MyInfoActivity;
import com.example.administrator.laundry.activity.MyMessageActivity;
import com.example.administrator.laundry.activity.SetActivity;
import com.example.administrator.laundry.base.BaseFragment;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.CircleImageview;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    HashMap<String, String> mHashMap = new HashMap<>();
    @BindView(R.id.img_head_portrait)
    CircleImageview imgHeadPortrait;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.img_head_portrait, R.id.ll_self_favorite, R.id.ll_lx_map, R.id.ll_no_upload, R.id.file_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head_portrait:
                toActivity(MyInfoActivity.class);
                break;
            case R.id.ll_self_favorite:
                toActivity(MyCollectActivity.class);
                break;
            case R.id.ll_lx_map:
                toActivity(MyMessageActivity.class);
                break;
            case R.id.ll_no_upload:
                toActivity(SetActivity.class);
                break;
            case R.id.file_map:
                toActivity(DatalibraryActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mHashMap.clear();
        mHashMap.put("userId", "0");
        NetControl.getUserInfo(infoCallback, mHashMap);
    }

    /**
     * 个人信息
     */
    NetControl.GetResultListenerCallback infoCallback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            if (null != o) {
                UserInfo userInfo = (UserInfo) o;

                tvUserName.setText(userInfo.userNickname);
                tvAddress.setText(userInfo.userShopAddress);
            }
        }

        @Override
        public void onErro(Object o) {
            BaseReseponseInfo baseReseponseInfo = (BaseReseponseInfo) o;
            if (null != baseReseponseInfo.getInfo() && baseReseponseInfo.getInfo().isEmpty()) {
                ToastUtil.show(getActivity(), baseReseponseInfo.getInfo());
            } else {
                ToastUtil.show(getActivity(), "个人信息获取失败");
            }
        }
    };
}
