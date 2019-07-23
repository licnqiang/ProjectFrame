package com.project.Kang_Lee.laundry.common;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.dialog.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    private View mContextView = null;
    private Unbinder bind;
    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContextView = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, mContextView);
        initLoadingDialog();
        initView();
        initData();
        return mContextView;
    }

    public void toActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void initLoadingDialog() {
        if (null == loadingDialog) {
            loadingDialog = new LoadingDialog(getActivity());
        }
    }

    public void showLoadingDialog(String message, boolean cancelable) {
        loadingDialog.setMessage(message);
        loadingDialog.setCancelable(cancelable);
        loadingDialog.show();
    }

    public void dismiss() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        dismiss();
    }


}
