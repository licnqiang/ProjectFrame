package com.example.administrator.laundry.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.view.LoadDataView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder mBind;
    private LoadDataView mLoadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.putActivityInfoToMap(this);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        sethidTitle();
        initView();
        initViewGroup();
        initData();

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract ViewGroup loadDataViewLayout();

    protected abstract void getLoadView(LoadDataView loadView);
    /**
     * 嵌入loaddataview
     */
    private void initViewGroup() {
        ViewGroup view = loadDataViewLayout();
        if (null != view) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
                this.mLoadView = new LoadDataView(this, view);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
             /*   params.addRule(RelativeLayout.BELOW, R.id.include_topbar);
                params.setMargins(0, 0, 0, 0);*/
                viewGroup.addView(this.mLoadView, params);
                getLoadView(this.mLoadView);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    public void toActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 设置手机title
     */
    protected void sethidTitle() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //noinspection AliDeprecation
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        }
    }

}
