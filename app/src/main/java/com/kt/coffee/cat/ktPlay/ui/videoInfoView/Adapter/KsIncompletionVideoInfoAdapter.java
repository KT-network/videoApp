package com.kt.coffee.cat.ktPlay.ui.videoInfoView.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.PlayerVideoEntity;
import com.kt.coffee.cat.utils.Tool;

import java.util.List;

/*
 * 小屏播放状态下（默认播放状态下）显示的视频剧集列表适配器
 * */
public class KsIncompletionVideoInfoAdapter extends RecyclerView.Adapter<KsIncompletionVideoInfoAdapter.ItemViewHolder> {


    private static final String TAG = "KsIncompletionVideoInfoAdapter";
    private Context mContext;

    private List<PlayerVideoEntity.VideoUrlArray> videoUrlArrays;

    private ClickListener.OnClickListener mOnClickListener;


    private int defaultSelectedItemIndex = -1;

    private boolean isClick;
    private int currentPosition;
    private boolean firstLoad = true;

    public KsIncompletionVideoInfoAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public KsIncompletionVideoInfoAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_incompletion_anthology, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        ItemClickEvent(itemViewHolder);
        return itemViewHolder;

    }

    private void ItemClickEvent(ItemViewHolder holder) {
        holder.itemView.setOnClickListener(view -> {

            if (!isClick) {
                setCurrentPosition(holder.getBindingAdapterPosition(), true);
            } else {
                setCurrentPosition(holder.getBindingAdapterPosition(), getCurrentPosition() != holder.getBindingAdapterPosition());
            }
            if (mOnClickListener != null) {
                mOnClickListener.onClick(holder.getBindingAdapterPosition());
            }
            notifyDataSetChanged();

        });

    }


    @Override
    public void onBindViewHolder(@NonNull KsIncompletionVideoInfoAdapter.ItemViewHolder holder, int position) {

        holder.mAnthologyText.setText(videoUrlArrays.get(position).getName());
        if (firstLoad) {
            Log.i(TAG, "onBindViewHolder: "+firstLoad);
            setCurrentPosition(defaultSelectedItemIndex != -1 ? defaultSelectedItemIndex : 0, true);
            firstLoad = false;
        }

        if (getCurrentPosition() == position && isClick) {
            Log.i(TAG, "onBindViewHolder: "+1111);
            holder.mAnthologyBg.setSelected(true);
        } else {
            holder.mAnthologyBg.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return videoUrlArrays == null ? 0 : videoUrlArrays.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout mAnthologyBg;
        private TextView mAnthologyText;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mAnthologyBg = itemView.findViewById(R.id.item_anthology_bg);
            mAnthologyText = itemView.findViewById(R.id.item_anthology_text);
        }
    }

    /*
     * 点击接口
     * */
    public void setOnClick(ClickListener.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    /*
     * 设置数据
     * */
    public void setData(List<PlayerVideoEntity.VideoUrlArray> data) {
        this.videoUrlArrays = data;
        notifyDataSetChanged();
    }

    /*
     * 添加数据
     * */
    public void addData(List<PlayerVideoEntity.VideoUrlArray> data) {
        if (videoUrlArrays != null) {
            this.videoUrlArrays.addAll(data);
            notifyItemRangeInserted(getItemCount(), data.size());
        }
    }


    /*
     * 设置现在选中的index
     * */
    public void setNowSelectState(int position) {
        setCurrentPosition(position, true);
        notifyDataSetChanged();
    }


    public int getCurrentPosition() {
        return this.currentPosition;
    }

    /*
     * 点击状态
     * */
    public boolean isClick() {
        return isClick;
    }

    /*
     * 设置当前状态
     * */
    public void setCurrentPosition(int currentPosition, boolean isClick) {

        Log.i(TAG, "setCurrentPosition: "+currentPosition);
        this.isClick = isClick;
        if (currentPosition >= getItemCount()) {
            this.currentPosition = 0;
            return;
        }
        this.currentPosition = currentPosition;

    }



}
