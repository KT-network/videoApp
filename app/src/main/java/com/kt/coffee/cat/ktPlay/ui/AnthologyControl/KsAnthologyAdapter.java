package com.kt.coffee.cat.ktPlay.ui.AnthologyControl;

import android.content.Context;
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

import java.util.List;


public class KsAnthologyAdapter extends RecyclerView.Adapter<KsAnthologyAdapter.ItemViewHolder> {


    private static final String TAG = "KsAnthologyAdapter";
    private Context mContext;

    private List<PlayerVideoEntity.VideoUrlArray> videoUrlArrays;

    private ClickListener.OnClickListener mOnClickListener;
    private int defaultSelectedItemIndex = -1;


    private boolean isClick;
    private int currentPosition;
    private boolean firstLoad = true;


    public KsAnthologyAdapter(Context context) {
        this.mContext = context;
    }


    @NonNull
    @Override
    public KsAnthologyAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_landscape_anthology, parent, false);
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
            if (mOnClickListener != null){
                mOnClickListener.onClick(holder.getBindingAdapterPosition());
            }
            notifyDataSetChanged();
        });

    }


    @Override
    public void onBindViewHolder(@NonNull KsAnthologyAdapter.ItemViewHolder holder, int position) {
        holder.mAnthologyText.setText(videoUrlArrays.get(position).getName());
        if (firstLoad) {
            setCurrentPosition(defaultSelectedItemIndex != -1 ? defaultSelectedItemIndex : 0, true);
            firstLoad = false;
        }

        if (getCurrentPosition() == position && isClick) {
            holder.mAnthologyBg.setSelected(true);
        } else {
            holder.mAnthologyBg.setSelected(false);
        }


        /*if (position == bSelectedItemIndex){
//            Toast.makeText(mContext, "Shot!", Toast.LENGTH_SHORT).show();
//            holder.mAnthologyText.append("get you ! !!!");
            holder.mAnthologyBg.setSelected(true);
            lastHolder = holder ;
        }*/

        /*if (mOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedItemIndex == -1) {
                        holder.mAnthologyBg.setSelected(true);
                        mAnthologyBg_ = holder.mAnthologyBg;
                        selectedItemIndex = position;
                    } else if (selectedItemIndex != position) {
                        mAnthologyBg_.setSelected(false);
                        selectedItemIndex = position;
                        holder.mAnthologyBg.setSelected(true);
                        mAnthologyBg_ = holder.mAnthologyBg;
                    } else {
                        holder.mAnthologyBg.setSelected(false);
                        mAnthologyBg_ = null;
                        selectedItemIndex = -1;
                    }

                    bSelectedItemIndex = position;
                    mOnClickListener.onClick(bSelectedItemIndex);
                    lastHolder.mAnthologyBg.setSelected(false);
                    lastHolder = holder;

                }
            });
        }*/

    }


    @Override
    public int getItemCount() {
        return videoUrlArrays == null ? 0 : videoUrlArrays.size();
    }

    /*
    * viewHolder
    * */
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
        setCurrentPosition(position,true);
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

        this.isClick = isClick;
        if (currentPosition >= getItemCount()){
            this.currentPosition = 0;
            return;
        }
        this.currentPosition = currentPosition;

    }


    /*
    * 设置默认选中
    * */
    public void setDefaultSelectedItemIndex(int position) {
        this.defaultSelectedItemIndex = position;
    }

}
