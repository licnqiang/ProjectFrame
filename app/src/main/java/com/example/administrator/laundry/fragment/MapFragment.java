package com.example.administrator.laundry.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.activity.CreatMessageActivity;
import com.example.administrator.laundry.activity.SearchActivity;
import com.example.administrator.laundry.adapter.HomeAdapter;
import com.example.administrator.laundry.base.BaseFragment;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.SelectPopupWindow;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment implements HomeAdapter.OnRecyclerViewItemClickListener{
    private static PopupWindow projectSelectLayer;
    @BindView(R.id.home_preject)
    TextView homePreject;
    @BindView(R.id.home_search)
    ImageView homeSearch;
    @BindView(R.id.list)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private SelectPopupWindow mPopupWindow;
    private List<String> showType = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private List<String> listItem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    protected void initView() {
    }

    private void setData() {
        showType.add("全部");
        showType.add("求助站");
        showType.add("转让");
        showType.add("招聘");
        showType.add("其他");
    }

    @Override
    protected void initData() {
        setData();
        setAdapter();
        homePreject.setText(showType.get(0));
    }

    private void setAdapter() {
        listItem = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), listItem);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setPullRefreshEnable(false);
        pullLoadMoreRecyclerView.setPushRefreshEnable(false);
        pullLoadMoreRecyclerView.setAdapter(homeAdapter);
    }


    @OnClick({R.id.home_menu, R.id.home_preject, R.id.home_search, R.id.home_iv_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*主菜单*/
            case R.id.home_menu:
                ToastUtil.show(getActivity(), getString(R.string.ing_code));
                break;
            /*项目选择*/
            case R.id.home_preject:
                if (mPopupWindow == null) {
                    mPopupWindow = new SelectPopupWindow(getActivity(), selectCategory, showType);
                }
                mPopupWindow.showAsDropDown(homePreject, -5, 10);
                break;
            /*搜索*/
            case R.id.home_search:
                toActivity(SearchActivity.class);
                break;
            /*拍照*/
            case R.id.home_iv_photo:
                toActivity(CreatMessageActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 选择完成回调接口
     */
    private SelectPopupWindow.SelectCategory selectCategory = new SelectPopupWindow.SelectCategory() {
        @Override
        public void selectCategory(int position) {
            if (null != showType && showType.size() > 0) {
                homePreject.setText(showType.get(position));
            }
        }
    };

    /**
     * 点击条目
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {

    }
}
