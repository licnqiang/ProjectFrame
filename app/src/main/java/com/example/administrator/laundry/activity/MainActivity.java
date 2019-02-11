package com.example.administrator.laundry.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.fragment.GroupFragment;
import com.example.administrator.laundry.fragment.MapFragment;
import com.example.administrator.laundry.fragment.MeFragment;
import com.example.administrator.laundry.view.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private MapFragment mapFragment = new MapFragment();
    private GroupFragment groupFragment = new GroupFragment();
    private MeFragment meFragment = new MeFragment();

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bottomBar.setContainer(R.id.fl_container)
                .setTitleSize(14)
                .setTitleBeforeAndAfterColor("#999999", "#1587FD")
                .addItem(MapFragment.class,
                        "社区",
                        R.mipmap.main_ziyuan,
                        R.mipmap.main_ziyuan_sel)
                .addItem(GroupFragment.class,
                        "消息",
                        R.mipmap.main_qunzu,
                        R.mipmap.main_qunzu_sel)
                .addItem(MeFragment.class,
                        "我的",
                        R.mipmap.main_wode,
                        R.mipmap.main_wode_sel)
                .build();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        //重新让新的Fragment指向了原本未被销毁的fragment，它就是onAttach方法对应的Fragment对象
        if (mapFragment == null && fragment instanceof MapFragment)
            mapFragment = (MapFragment) fragment;
        if (groupFragment == null && fragment instanceof GroupFragment)
            groupFragment = (GroupFragment) fragment;
        if (meFragment == null && fragment instanceof MeFragment)
            meFragment = (MeFragment) fragment;
        super.onAttachFragment(fragment);
    }
}
