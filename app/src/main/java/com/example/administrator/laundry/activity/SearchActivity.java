package com.example.administrator.laundry.activity;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.laundry.Constants.SysConstants;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.InputTipsAdapter;
import com.example.administrator.laundry.adapter.SearchRecordAdapter;
import com.example.administrator.laundry.base.BaseActivity;
import com.example.administrator.laundry.util.SpHelper;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    LinearLayout tvTitle;
    @BindView(R.id.searchView)
    EditText searchView;
    @BindView(R.id.inputtip_list)
    ListView mInputListView;

    @BindView(R.id.search_record_list)
    ListView searchRecordList;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    public String newText;
    @BindView(R.id.search_title)
    TextView searchTitle;

    private InputTipsAdapter mIntipAdapter;

    private List<String> boxDtat;
    private SearchRecordAdapter searchRecordAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        initSearchView();
    }

    @Override
    protected void initData() {
        switchList(false);

        setRecordAdapter();

        setRecordData();
    }

    private void setRecordData() {
        final List<String> serchRecord = SpHelper.getListData(SysConstants.User.SEARCH_RECORD, String.class);
        if (null == serchRecord || serchRecord.size() < 1) {
            searchTitle.setText("暂无搜索记录");
            tvClear.setVisibility(View.GONE);
            searchRecordList.setVisibility(View.GONE);
        } else {
            searchTitle.setText("最近搜索");
            tvClear.setVisibility(View.VISIBLE);
            boxDtat.addAll(serchRecord);
            searchRecordAdapter.notifyDataSetChanged();
            searchRecordList.setVisibility(View.VISIBLE);
        }
    }

    private void setRecordAdapter() {
        boxDtat = new ArrayList<>();
        searchRecordAdapter = new SearchRecordAdapter(getApplicationContext(), boxDtat);
        searchRecordList.setAdapter(searchRecordAdapter);
        searchRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchView.setText(boxDtat.get(position));
            }
        });
    }

    private void initSearchView() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newText = s.toString();
                if (newText.length() < 1) {
                    switchList(false);
                } else {
                    switchList(true);
                }
                if (!TextUtils.isEmpty(newText)) {


                } else {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    @OnClick({R.id.img_back, R.id.tv_right, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right:
                searchView.setText("");
                break;
            //清除历史记录
            case R.id.tv_clear:
                break;
        }
    }


    private void switchList(boolean showList) {
        if (showList) {
            mInputListView.setVisibility(View.VISIBLE);
            searchRecordList.setVisibility(View.GONE);
        } else {
            mInputListView.setVisibility(View.GONE);
            searchRecordList.setVisibility(View.VISIBLE);
        }
    }


//    /*dialog*/
//    private void showDelDialog() {
//        commonDialog = new Dialog.Builder(this)
//                .setTitle("清除记录")
//                .setMessage("是否确定清除搜索记录")
//                .setAnimation(R.style.slide_anim)
//                .setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                    }
//                })
//                .setPositiveButton("确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        commonDialog.dismiss();
//                        //清除本地缓存
//                        SpHelper.setStringValue(SysConstants.User.SEARCH_RECORD, null);
//                        setRecordData();
//                    }
//                })
//                .build();
//        commonDialog.show();
//    }
}
