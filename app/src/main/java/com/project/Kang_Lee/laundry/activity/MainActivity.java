package com.project.Kang_Lee.laundry.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.base.BaseActivity;
import com.project.Kang_Lee.laundry.fragment.GroupFragment;
import com.project.Kang_Lee.laundry.fragment.MapFragment;
import com.project.Kang_Lee.laundry.fragment.MeFragment;
import com.project.Kang_Lee.laundry.view.BottomBar;

import butterknife.BindView;


public class MainActivity extends BaseActivity {
    private MapFragment mapFragment = new MapFragment();
    private MeFragment meFragment = new MeFragment();
    private GroupFragment groupFragment = new GroupFragment();
    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView() {
        bottomBar.setContainer(R.id.fl_container)
                .setTitleSize(12)
                .setTitleBeforeAndAfterColor("#999999", "#1587FD")
                .addItem(mapFragment,
                        "首页",
                        R.mipmap.main_ziyuan,
                        R.mipmap.main_ziyuan_sel)
                .addItem(groupFragment,
                        "群组",
                        R.mipmap.home_tab_msg_p,
                        R.mipmap.home_tab_msg_p)
                .addItem(meFragment,
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
        if (mapFragment == null && fragment instanceof MapFragment) {
            mapFragment = (MapFragment) fragment;
        }
        if (meFragment == null && fragment instanceof MeFragment) {
            meFragment = (MeFragment) fragment;
        }
        super.onAttachFragment(fragment);
    }

}