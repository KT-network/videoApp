package com.kt.coffee.cat.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class RecyclerAdapterBase extends RecyclerView.Adapter<RecyclerAdapterBase.ItemViewHolder> {

    /**
     * inflate(R.layout.item_home_home_out, parent, false)
     */
    protected final static int inflate_1 = 0;
    // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_home_out, parent, false);

    /**
     * inflate(R.layout.item_home_home_in, null, false)
     */
    protected final static int inflate_2 = 1;
    // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_home_in, null, false);


    @NonNull
    @Override
    public RecyclerAdapterBase.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (getInflateType() == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(getContentViewId(), parent, false);
        }

        if (getInflateType() == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(getContentViewId(), null, false);
        }
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterBase.ItemViewHolder holder, int position) {
        setEvent(holder, position);

    }

    @Override
    public int getItemCount() {
        return getItemCountSon();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView,getBindingAdapterPosition());



        }
    }

    /**
     * 设置布局资源id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 布局填充类型
     *
     * @return 0, 1
     */
    protected abstract int getInflateType();

    /**
     * 设置ItemCount数量
     * @return int
     */
    protected abstract int getItemCountSon();

    /**
     * 初始化视图
     *
     * @param itemView findViewById
     */
    protected void initView(View itemView,int position) {

    }

    /**
     * 设置事件
     */
    protected void setEvent(RecyclerAdapterBase.ItemViewHolder holder, int position) {
    }




}
