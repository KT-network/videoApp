package com.kt.coffee.cat.ktPlay.ui.AnthologyControl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.coffee.cat.Adapter.RecyclerAdapterBase;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.PlayerVideoEntity;

import org.w3c.dom.Text;

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
    public void onBindViewHolder(@NonNull KsAnthologyAdapter.ItemViewHolder holder, int position) {
        holder.mAnthologyText.setText(videoUrlArrays.get(position).getName());

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
                    mOnClickListener.onClick(position);
                }
            });
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



    public void setItemState(int position, LinearLayout linearLayout) {
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


    public void setOnClick(ClickListener.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void setData(List<PlayerVideoEntity.VideoUrlArray> data) {

        this.videoUrlArrays = data;

        notifyDataSetChanged();

    }

    public void addData(List<PlayerVideoEntity.VideoUrlArray> data) {

        if (videoUrlArrays != null) {
            this.videoUrlArrays.addAll(data);
            notifyItemRangeInserted(getItemCount(), data.size());
        }

    }
}