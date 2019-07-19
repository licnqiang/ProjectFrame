package com.project.Kang_Lee.laundry.ui.fragment;


import android.support.v4.app.Fragment;

import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.base.BaseFragment;
import com.project.Kang_Lee.laundry.ui.view.banner.AutoSwitchAdapter;
import com.project.Kang_Lee.laundry.ui.view.banner.AutoSwitchView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.loopswitch)
    AutoSwitchView loopswitch;
    private AutoSwitchAdapter mAdapter;
    private ArrayList<String> im;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        setSwitch();
    }

    private void setSwitch() {
        im = new ArrayList<String>();
        im.add("");
        im.add("");
        im.add("");
        im.add("");
        mAdapter = new AutoSwitchAdapter(getActivity(), im);
        loopswitch.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
    }

}
