package com.example.administrator.laundry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.laundry.NetService.data.PostListBean;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.util.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.SelectedPicViewHolder> {

    private Context mContext;
    private List<PostListBean.NoteBean> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public HomeAdapter(Context mContext, List<PostListBean.NoteBean> data) {
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
        PostListBean.NoteBean noteBean = mData.get(position);
//        holder.userHead.
        holder.itemTitle.setText(noteBean.userNickname);
        holder.content.setText(noteBean.noteContent);
        holder.time.setText(DateUtils.stampToDate(noteBean.noteTime));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int clickPosition;
        @BindView(R.id.user_head)
        ImageView userHead;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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