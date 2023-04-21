package com.kt.coffee.cat.ktPlay.ui.AnthologyControl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.PlayerVideoEntity;

import java.util.List;

/*public class KsAnthologyAdapter extends RecyclerAdapterBase {

    private static final String TAG = KsAnthologyAdapter.class.getName();
    private Context mContext;

    private List<PlayerVideoEntity.VideoUrlArray> videoUrlArrays;

    private ClickListener.OnAnthologyItemClickListener mOnClickListener;

    private int selectedItemIndex = -1;

    public KsAnthologyAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.item_anthology;
    }

    @Override
    protected int getInflateType() {
        return 0;
    }

    @Override
    protected int getItemCountSon() {
        return videoUrlArrays == null ? 0 : videoUrlArrays.size();
    }


    @Override
    protected void setEvent(ItemViewHolder holder, int position) {
        super.setEvent(holder, position);

        mAnthologyText.setText(videoUrlArrays.get(position).getName());

        *//*if (mOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG, "onClick: "+position);
                    mOnClickListener.onClick(position);
                    if (selectedItemIndex == -1) {
                        mAnthologyBg.setSelected(true);
                        mAnthologyBg_ = mAnthologyBg;
                        selectedItemIndex = position;
                    } else if (selectedItemIndex != position) {
                        mAnthologyBg_.setSelected(false);
                        selectedItemIndex = position;
                        mAnthologyBg.setSelected(true);
                    } else {
                        mAnthologyBg.setSelected(false);
                        mAnthologyBg_ = null;
                        selectedItemIndex = -1;
                    }

                }
            });

        }*//*


    }

    private LinearLayout mAnthologyBg, mAnthologyBg_;
    private TextView mAnthologyText;

    @Override
    protected void initView(View itemView, int position) {
        super.initView(itemView, position);

        mAnthologyBg = itemView.findViewById(R.id.item_anthology_bg);
        mAnthologyText = itemView.findViewById(R.id.item_anthology_text);

        if (mOnClickListener != null){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.onClick(mAnthologyBg,null,position);
                }
            });

        }


    }

    public void setItemState(int position,LinearLayout linearLayout){
        if (selectedItemIndex == -1) {
            linearLayout.setSelected(true);
            mAnthologyBg_ = linearLayout;
            selectedItemIndex = position;
        } else if (selectedItemIndex != position) {
            mAnthologyBg_.setSelected(false);
            selectedItemIndex = position;
            linearLayout.setSelected(true);
        } else {
            linearLayout.setSelected(false);
            mAnthologyBg_ = null;
            selectedItemIndex = -1;
        }
    }


    public void setOnClick(ClickListener.OnAnthologyItemClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void setData(List<PlayerVideoEntity.VideoUrlArray> data) {

        this.videoUrlArrays = data;

        notifyDataSetChanged();

    }

    public void addData(List<PlayerVideoEntity.VideoUrlArray> data) {

        if (videoUrlArrays != null) {
            this.videoUrlArrays.addAll(data);
            notifyItemRangeInserted(getItemCount(),data.size());
        }

    }


}*/

public class KsAnthologyAdapter extends RecyclerView.Adapter<KsAnthologyAdapter.ItemViewHolder> {


    private static final String TAG = "KsAnthologyAdapter";
    private Context mContext;

    private List<PlayerVideoEntity.VideoUrlArray> videoUrlArrays;

    private ClickListener.OnClickListener mOnClickListener;

    private int selectedItemIndex = -1;

    private LinearLayout mAnthologyBg_ = null;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;

    public KsAnthologyAdapter(Context context) {
        this.mContext = context;
    }


    @NonNull
    @Override
    public KsAnthologyAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anthology, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.mAnthologyBg.setSelected(false);

    }


    KsAnthologyAdapter.ItemViewHolder lastHolder = null;

    @Override
    public void onBindViewHolder(@NonNull KsAnthologyAdapter.ItemViewHolder holder, int position) {
        holder.mAnthologyText.setText(videoUrlArrays.get(position).getName());




        if (position == bSelectedItemIndex){
//            Toast.makeText(mContext, "Shot!", Toast.LENGTH_SHORT).show();
//            holder.mAnthologyText.append("get you ! !!!");
            holder.mAnthologyBg.setSelected(true);
            lastHolder = holder ;
        }

        if (mOnClickListener != null) {
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
        }

    }


    @Override
    public int getItemCount() {
        return videoUrlArrays == null ? 0 : videoUrlArrays.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return position;
    }*/

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
     * 获取item的viewHolder
     * */
    private ItemViewHolder getViewHolder(int position) {

        int first = mLinearLayoutManager.findFirstVisibleItemPosition();
        int acutalPos = (position - first) >= 0 ? position - first : 0;

        Log.i(TAG, "getViewHolder: " + acutalPos);

        View childAt = mLinearLayoutManager.getChildAt(acutalPos);
        if (childAt == null) return null;
        ItemViewHolder childViewHolder = (ItemViewHolder) mRecyclerView.getChildViewHolder(childAt);
        return childViewHolder;
    }

    //    public void setNowSelectState(int position) {
//        if (position == selectedItemIndex) return;
//        if (selectedItemIndex != -1) {
//            ItemViewHolder oldViewHolder = getViewHolder(selectedItemIndex);
//            if (oldViewHolder == null) return;
//            oldViewHolder.mAnthologyBg.setSelected(false);
//        }
//        ItemViewHolder nowViewHolder = getViewHolder(position);
//        if (nowViewHolder == null) return;
//        nowViewHolder.mAnthologyBg.setSelected(true);
//
//        selectedItemIndex = position;
//
//        mAnthologyBg_ = nowViewHolder.mAnthologyBg;
//    }




    // lyw changed
    private String currentTitle = "";
    private int bSelectedItemIndex = -1 ;
    public void setNowSelectState(int position) {
//        selectedItemIndex = position;
//        notifyItemChanged(position);
        bSelectedItemIndex = position;


//        if (position == selectedItemIndex) return;
//        if (selectedItemIndex != -1) {
//            ItemViewHolder oldViewHolder = getViewHolder(selectedItemIndex);
//            if (oldViewHolder == null) return;
//            oldViewHolder.mAnthologyBg.setSelected(false);
//        }
//        ItemViewHolder nowViewHolder = getViewHolder(position);
//        if (nowViewHolder == null) return;
//        String tempTitle = nowViewHolder.mAnthologyText.getText().toString();
//            nowViewHolder.mAnthologyBg.setSelected(true);
//            currentTitle = tempTitle;
//
//        selectedItemIndex = position;
//
//        mAnthologyBg_ = nowViewHolder.mAnthologyBg;
    }


    /*
     * 设置mLinearLayoutManager
     * */
    public void setmLinearLayoutManager(LinearLayoutManager manager) {
        this.mLinearLayoutManager = manager;
    }

    /*
     * 设置
     * */
    public void setmRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

}
