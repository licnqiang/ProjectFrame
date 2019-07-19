package com.project.Kang_Lee.laundry.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.Kang_Lee.laundry.R;
import com.project.Kang_Lee.laundry.base.BaseFragment;
import com.project.Kang_Lee.laundry.view.AutoSwitchAdapter;
import com.project.Kang_Lee.laundry.view.AutoSwitchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment {

    @BindView(R.id.loopswitch)
    AutoSwitchView loopswitch;
    private AutoSwitchAdapter mAdapter;
    private ArrayList<String> im;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
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
