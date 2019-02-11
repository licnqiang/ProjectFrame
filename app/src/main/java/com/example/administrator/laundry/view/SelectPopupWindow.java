package com.example.administrator.laundry.view;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.administrator.laundry.R;
import com.example.administrator.laundry.adapter.ProjectPopAdapter;

import java.util.List;


/**
 * @author lq
 * @time 2018/9/29
 */
public class SelectPopupWindow extends PopupWindow {
    private final ListView listProject;
    private List<String> mEntityList;
    private final ProjectPopAdapter projectPopAdapter;
    private final SelectCategory selectCategory;

    public SelectPopupWindow(Activity activity, SelectCategory selectCategory, List<String> List) {
        this.selectCategory=selectCategory;
        View contentView = LayoutInflater.from(activity).inflate(R.layout.project_select_pop_, null);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小
        this.setContentView(contentView);
        this.setWidth(dm.widthPixels);
        this.setHeight(dm.heightPixels*7/10);
        this.mEntityList=List;

        setAnimationStyle(R.style.main_menu_animstyle);
        /* 设置触摸外面时消失 */
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true); /*设置点击menu以外其他地方以及返回键退出 */
        /**
         * 1.解决再次点击MENU键无反应问题
         */
        contentView.setFocusableInTouchMode(true);
        listProject = (ListView) contentView.findViewById(R.id.list_project);
        projectPopAdapter = new ProjectPopAdapter(activity,mEntityList);
        listProject.setAdapter(projectPopAdapter);
        listProject.setOnItemClickListener(parentItemClickListener);
    }


    /**
     * 父类别点击事件
     */
    private AdapterView.OnItemClickListener parentItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            if(selectCategory!=null){
                selectCategory.selectCategory(position);
            }
            dismiss();
        }
    };

    /**
     * 选择成功回调
     * @author apple
     *
     */
    public interface SelectCategory{
        /**
         * 把选中的下标通过方法回调回来
         * @param parentSelectposition  父类别选中下标
         */
        public void selectCategory(int parentSelectposition);
    }


    public void setData(List<String> List){
        mEntityList.clear();
        mEntityList.addAll(List);
        projectPopAdapter.notifyDataSetChanged();
    }

}
