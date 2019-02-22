package com.example.administrator.laundry.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.laundry.R;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;


/**
 * 图片选择适配器
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.SelectedPicViewHolder> {
    public static final int IMAGE_ITEM_ADD = -1;
    public int maxImgCount;
    private Context mContext;
    private List<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    public boolean isAdded;   //是否额外添加了最后一个图片

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


    public CommentAdapter(Context mContext) {
        this.mContext = mContext;
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
        return 6;
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_img;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }

        public void bind(int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            //根据条目位置设置图片
//            String item = "";
//            Glide.with(mContext)                             //配置上下文
//                    .load("")      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                    .error(R.mipmap.collect_mrtp)           //设置错误图片
//                    .placeholder(R.mipmap.collect_mrtp)     //设置占位图片
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
//                    .into(iv_img);
            clickPosition = position;
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, clickPosition);
            }
        }
    }
}