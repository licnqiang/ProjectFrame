package com.example.administrator.laundry.activity;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.util.LoadingUI;
import com.example.administrator.laundry.NetService.util.Log;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.UpLoadFile.upLoadFile;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.base.BaseApplication;
import com.example.administrator.laundry.fragment.SelectImageFragment;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.LoadDataView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatMessageActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.store_local)
    EditText storeLocal;
    private SelectImageFragment selectImageFragment;
    HashMap<String, String> mHashMap = new HashMap<>();
    private String nimageNumber;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_message;
    }

    @Override
    protected void initView() {
        tvTitle.setText("发表动态");
    }

    @Override
    protected void initData() {
        setFragment();
    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {
    }

    /**
     * 设置图片选择fragment
     */
    private void setFragment() {
        selectImageFragment = new SelectImageFragment();
        SelectImageFragment.maxImgCount = 6;
        selectImageFragment.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_image, selectImageFragment).commitAllowingStateLoss();
    }

    @OnClick({R.id.img_back, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_send:
                String tent = content.getText().toString();
                String lacal = storeLocal.getText().toString();

                if (!tent.isEmpty() && !lacal.isEmpty()) {
                    getImage();
                    mHashMap.clear();
                    mHashMap.put("noteImgNumber", nimageNumber);
                    mHashMap.put("noteContent", tent);
                    mHashMap.put("noteTypeId", "1");
                    mHashMap.put("noteAddress", lacal);
                    LoadingUI.showDialogForLoading(this, "正在加载", true);
                    NetControl.CreatMessage(callback, mHashMap);
                }else {
                    ToastUtil.show(this,"请输入所有信息");
                }

                break;
        }
    }


    private void getImage(){
        upLoadFile.uploadFile(selectImageFragment.getSelectImg(), new upLoadFile.ResultCallBack() {
            @Override
            public void succeed(String str) {
                nimageNumber=nimageNumber+str+",";
                nimageNumber.substring(0,nimageNumber.length()-1);
                ToastUtil.show(CreatMessageActivity.this, "文件上传成功");
            }

            @Override
            public void faild() {
                ToastUtil.show(CreatMessageActivity.this, "文件上传失败");
            }
        });
    }




    NetControl.GetResultListenerCallback callback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            ToastUtil.show(CreatMessageActivity.this, "发表成功");
            finish();
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
                    Toast.makeText(
                            BaseApplication.ApplicationContext,
                            msg + " code:" + code,
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CreatMessageActivity.this,
                        "网络连接失败，请稍后重试！", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
