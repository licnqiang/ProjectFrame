package com.example.administrator.laundry.adapter;

/**
 * @author lq
 * @time 2018/12/7
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.laundry.R;

import java.util.List;

/**
 *  输入提示adapter，展示item名称和地址
 */
public class InputTipsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mListTips;

    public InputTipsAdapter(Context context, List<String> tipList) {
        mContext = context;
        mListTips = tipList;
    }

    @Override
    public int getCount() {
        if (mListTips != null) {
            return mListTips.size();
        }
        return 0;
    }


    @Override
    public Object getItem(int i) {
        if (mListTips != null) {
            return mListTips.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_inputtips, null);
            holder.mName =  view.findViewById(R.id.name);
            holder.mAddress =  view.findViewById(R.id.address);
            view.setTag(holder);
        } else{
            holder = (Holder)view.getTag();
        }
        if(mListTips == null){
            return view;
        }


        return view;
    }

    class Holder {
        TextView mName;
        TextView mAddress;
    }
}
