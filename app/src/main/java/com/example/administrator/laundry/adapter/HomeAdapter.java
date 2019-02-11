package com.example.administrator.laundry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.laundry.R;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.SelectedPicViewHolder> {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public HomeAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = data;
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(final int position) {
            clickPosition = position;
            //设置条目的点击事件
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, clickPosition);
            }
        }
    }
}