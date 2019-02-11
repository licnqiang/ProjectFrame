package com.example.administrator.laundry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.laundry.R;

import java.util.List;

/**
 * @author lq
 * @time 2018/10/19
 */
public class ProjectPopAdapter extends BaseAdapter {

    private List<String> mList;
    private Context context;
    LayoutInflater inflater = null;

    public ProjectPopAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_project_select, null);
            holder.group_name = convertView.findViewById(R.id.item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String Info = mList.get(position);
        holder.group_name.setText(Info);

        return convertView;
    }

    class ViewHolder {
        TextView group_name;
        ImageView more_group;
        RelativeLayout title_bar_container;

    }


}
