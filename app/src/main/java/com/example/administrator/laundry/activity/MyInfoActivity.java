package com.example.administrator.laundry.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.GlideImageLoader;
import com.example.administrator.laundry.view.LoadDataView;
import com.example.administrator.laundry.view.SelectDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyInfoActivity extends BaseActivity {

    public static final int REQUEST_CODE_SELECT = 100;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_image)
    ImageView userImage;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_sex)
    TextView userSex;
    @BindView(R.id.user_age)
    EditText userAge;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_address)
    EditText userAddress;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    private ArrayList<ImageItem> images;

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


    @OnClick({R.id.img_back, R.id.user_image, R.id.user_name, R.id.user_sex, R.id.user_age, R.id.user_phone, R.id.user_address, R.id.btn_send_message})
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
            case R.id.user_phone:
                break;
            case R.id.user_address:
                break;
            case R.id.btn_send_message:
                finish();
                break;
        }
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
}
