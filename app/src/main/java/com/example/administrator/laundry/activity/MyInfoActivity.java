package com.example.administrator.laundry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.laundry.NetService.control.NetControl;
import com.example.administrator.laundry.NetService.data.BaseReseponseInfo;
import com.example.administrator.laundry.NetService.data.UserInfo;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.GlideImageLoader;
import com.example.administrator.laundry.util.ToastUtil;
import com.example.administrator.laundry.view.LoadDataView;
import com.example.administrator.laundry.view.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyInfoActivity extends BaseActivity {

    public static final int REQUEST_CODE_SELECT = 100;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_image)
    ImageView userImage;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_age)
    EditText userAge;
    @BindView(R.id.user_address)
    EditText userAddress;
    @BindView(R.id.store_name)
    EditText storeName;
    @BindView(R.id.user_content)
    EditText userContent;
    @BindView(R.id.user_sigin)
    EditText userSigin;
    @BindView(R.id.user_sex)
    TextView userSex;
    private ArrayList<ImageItem> images;
    HashMap<String, String> mHashMap = new HashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView() {
        tvTitle.setText("个人信息");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected ViewGroup loadDataViewLayout() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView loadView) {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


    @OnClick({R.id.img_back, R.id.user_image, R.id.user_name, R.id.user_sex, R.id.user_age, R.id.user_address, R.id.btn_send_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.user_image:
                showPicDialog();
                break;
            case R.id.user_name:
                break;
            case R.id.user_sex:
                showSexDialog();
                break;
            case R.id.user_age:
                break;
            case R.id.user_address:
                break;
            case R.id.btn_send_message:
                sendInfo();
                break;
        }
    }

    private void sendInfo() {
        String name = userName.getText().toString();
        String sex = userSex.getText().toString();
        String age = userAge.getText().toString();
        String address = userAddress.getText().toString();
        String storename = storeName.getText().toString();
        String content = userContent.getText().toString();
        String sigin = userSigin.getText().toString();
        mHashMap.clear();
        mHashMap.put("userImgNumber", "");
        mHashMap.put("userIntroduce", content);
        mHashMap.put("userNickname", name);
        mHashMap.put("userSex", (sex.equals("男") ? 1 : 2) + "");
        mHashMap.put("userShopAddress", address);
        mHashMap.put("userSign", sigin);
        mHashMap.put("userWworkingTime", age);
        mHashMap.put("userShop", storename);
        NetControl.saveUserInfo(callback, mHashMap);
    }


    private void showSexDialog() {
        List<String> names = new ArrayList<>();
        names.add("男");
        names.add("女");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        userSex.setText("男");
                        break;
                    case 1:
                        userSex.setText("女");
                        break;
                }

            }
        }, names);
    }

    private void showPicDialog() {
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("相册");
        showDialog(new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 直接调起相机
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setImageLoader(new GlideImageLoader());
                        ImagePicker.getInstance().setMultiMode(false);
                        ImagePicker.getInstance().setCrop(false);
                        ImagePicker.getInstance().setShowCamera(false);
                        Intent intent = new Intent(MyInfoActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setImageLoader(new GlideImageLoader());
                        ImagePicker.getInstance().setMultiMode(false);
                        ImagePicker.getInstance().setCrop(false);
                        ImagePicker.getInstance().setShowCamera(false);
                        Intent intent1 = new Intent(MyInfoActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        break;
                    default:
                        break;
                }

            }
        }, names);
    }

    /**
     * 选择性别的dialog
     *
     * @param listener
     * @param names
     * @return
     */
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(MyInfoActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        dialog.show();
        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    ImagePicker.getInstance().getImageLoader().displayImage(this, images.get(0).path, userImage, 0, 0);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHashMap.clear();
        NetControl.getUserInfo(infoCallback,mHashMap);
    }

    /**
     * 修改个人信息
     */
    NetControl.GetResultListenerCallback callback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            ToastUtil.show(MyInfoActivity.this, "个人信息修改成功");
            finish();
        }

        @Override
        public void onErro(Object o) {
            BaseReseponseInfo baseReseponseInfo = (BaseReseponseInfo) o;
            if (null != baseReseponseInfo.getInfo() && baseReseponseInfo.getInfo().isEmpty()) {
                ToastUtil.show(MyInfoActivity.this, baseReseponseInfo.getInfo());
            } else {
                ToastUtil.show(MyInfoActivity.this, "个人信息修改失败");
            }
        }
    };


    /**
     * 个人信息
     */
    NetControl.GetResultListenerCallback infoCallback = new NetControl.GetResultListenerCallback() {
        @Override
        public void onFinished(Object o) {
            if (null != o) {
                UserInfo userInfo = (UserInfo) o;


                userName.setText(userInfo.userNickname);
                if (userInfo.userSex == 1) {
                    userSex.setText("男");
                } else {
                    userSex.setText("女");
                }
                userAge.setText(userInfo.userWworkingTime);
                userAddress.setText(userInfo.userShopAddress);
                storeName.setText(userInfo.userShop);
                userContent.setText(userInfo.userIntroduce);
                userSigin.setText(userInfo.userSign);

            }
        }

        @Override
        public void onErro(Object o) {
            BaseReseponseInfo baseReseponseInfo = (BaseReseponseInfo) o;
            if (null != baseReseponseInfo.getInfo() && baseReseponseInfo.getInfo().isEmpty()) {
                ToastUtil.show(MyInfoActivity.this, baseReseponseInfo.getInfo());
            } else {
                ToastUtil.show(MyInfoActivity.this, "个人信息修改失败");
            }
        }
    };

}
