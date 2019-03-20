package com.example.administrator.laundry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.laundry.NetService.data.Detail;
import com.example.administrator.laundry.R;
import com.example.administrator.laundry.util.DateUtils;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 图片选择适配器
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.SelectedPicViewHolder> {
    private Context mContext;
    private List<Detail.Comment> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private PingItemClickListener pinlistener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


    public interface PingItemClickListener {
        void onPingItemClick(View view, int position);
    }

    public void setOnPingItemClickListener(PingItemClickListener listener) {
        this.pinlistener = listener;
    }


    public CommentAdapter(Context mContext, List<Detail.Comment> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.list_item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int clickPosition;
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.btn_like_num)
        TextView btnLikeNum;
        @BindView(R.id.btn_like)
        ImageView btnLike;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ivImg.setOnClickListener(this);
            btnLike.setOnClickListener(this);
        }

        public void bind(int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            Detail.Comment comment = mData.get(position);
            //根据条目位置设置图片
            Glide.with(mContext)                             //配置上下文
                    .load(comment.userImgNumber)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                    .error(R.mipmap.collect_mrtp)           //设置错误图片
                    .placeholder(R.mipmap.collect_mrtp)     //设置占位图片
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                    .into(ivImg);
            btnLike.setSelected(comment.commentFlag==1);
            tvName.setText(comment.userNickname);
            content.setText(comment.commentContent);
            time.setText(DateUtils.stampToDate(comment.commentTime));
            btnLikeNum.setText(comment.commentPraise);
            clickPosition = position;
        }


        @Override
        public void onClick(View v) {
            if (listener != null) {
                switch (v.getId()) {
                    case R.id.btn_like:
                        pinlistener.onPingItemClick(v, clickPosition);
                        break;
                    case R.id.iv_img:
                        listener.onItemClick(v, clickPosition);
                        break;
                }

            }
        }
    }
}