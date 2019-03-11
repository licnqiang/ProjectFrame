package com.example.administrator.laundry.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.PostListBean;
import com.example.administrator.laundry.NetService.util.Log;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.activity.CreatMessageActivity;
import com.example.administrator.laundry.activity.ForgetPswActivity;
import com.example.administrator.laundry.activity.LoginActivity;
import com.example.administrator.laundry.activity.MessagDetailActivity;
import com.example.administrator.laundry.activity.SearchActivity;
import com.example.administrator.laundry.adapter.HomeAdapter;
import com.example.administrator.laundry.base.BaseApplication;
import com.example.administrator.laundry.base.BaseFragment;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.SelectPopupWindow;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment implements HomeAdapter.OnRecyclerViewItemClickListener {
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
    private List<PostListBean.NoteBean> listItem;
    private PostListBean postListBean;
    HashMap<String, String> mHashMap = new HashMap<>();

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
        homeAdapter.setOnItemClickListener(this);
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
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        toActivity(MessagDetailActivity.class);
        startActivity(new Intent(getActivity(),MessagDetailActivity.class).putExtra("noteId",listItem.get(position).noteId));
    }

    @Override
    public void onResume() {
        super.onResume();
        mHashMap.clear();
        mHashMap.put("noteType","0");
        mHashMap.put("use","0");
        mHashMap.put("noteId","0");
        NetControl.GetPostList(postListCallback,mHashMap);
    }

    NetControl.GetResultListenerCallback postListCallback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            if(null!=o){
                postListBean=(PostListBean)o;
                if(postListBean.getNote().size()>0){
                    listItem.addAll(postListBean.getNote());
                    homeAdapter.notifyDataSetChanged();
                }
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
                            startActivity(new Intent(getActivity(),
                                    LoginActivity.class).putExtra("flag",
                                    false));
                            getActivity().finish();
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
                Toast.makeText(getActivity(),
                        "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
