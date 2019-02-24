package com.example.administrator.laundry.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.activity.CreatMessageActivity;
import com.example.administrator.laundry.activity.MyCollectActivity;
import com.example.administrator.laundry.activity.MyMessageActivity;
import com.example.administrator.laundry.activity.SetActivity;
import com.example.administrator.laundry.activity.myInfoActivity;
import com.example.administrator.laundry.base.BaseFragment;
import com.example.administrator.laundry.view.CircleImageview;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {


    @BindView(R.id.img_head_portrait)
    CircleImageview imgHeadPortrait;
    @BindView(R.id.img_head_sex)
    ImageView imgHeadSex;
    Unbinder unbinder;

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


    @OnClick({R.id.img_head_portrait, R.id.ll_self_favorite, R.id.ll_lx_map, R.id.ll_no_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head_portrait:
                toActivity(myInfoActivity.class);
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
        }
    }
}
