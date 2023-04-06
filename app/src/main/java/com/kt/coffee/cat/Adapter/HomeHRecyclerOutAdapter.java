package com.kt.coffee.cat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.PlayerActivity;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.HomeHTestDataEntity;

import java.util.List;

public class HomeHRecyclerOutAdapter extends RecyclerAdapterBase{
    private Context mContext;

    private List<HomeHTestDataEntity.Compilations> compilationsList;
    private static final String TAG = "HomeHRecyclerOutAdapter";

    public HomeHRecyclerOutAdapter(Context context){
        this.mContext = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.item_home_home_out;
    }

    @Override
    protected int getInflateType() {
        return inflate_1;
    }

    @Override
    protected int getItemCountSon() {
        return compilationsList.size();
    }

    public void addData(List<HomeHTestDataEntity.Compilations> compilationsList){
        this.compilationsList = compilationsList;
        notifyDataSetChanged();
    }

    TextView typeTitle;
    ImageView titleIcon;
    RecyclerView recyclerView;
    @Override
    protected void initView(View itemView,int position) {
        super.initView(itemView,position);

        typeTitle = itemView.findViewById(R.id.ihh_title);
        titleIcon = itemView.findViewById(R.id.ihh_title_icon);
        recyclerView = itemView.findViewById(R.id.ihh_recycler_in);

    }

    @Override
    protected void setEvent(ItemViewHolder holder, int position) {
        super.setEvent(holder, position);

        typeTitle.setText(compilationsList.get(position).getTitle());

        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        HomeHRecyclerInAdapter homeHRecyclerInAdapter = new HomeHRecyclerInAdapter(mContext, compilationsList.get(position).getData());
        homeHRecyclerInAdapter.setOnClick(onClickListener);
        recyclerView.setAdapter(homeHRecyclerInAdapter);

    }

    private final ClickListener.OnClickListener onClickListener = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {

            Intent intent = new Intent(mContext, PlayerActivity.class);
            mContext.startActivity(intent);

            Log.i(TAG, "onClick: "+ i);
        }
    };

}


/*

public class HomeHRecyclerOutAdapter extends RecyclerView.Adapter<HomeHRecyclerOutAdapter.ItemViewHolder> {

    private static final String TAG = "HomeHRecyclerOutAdapter";
    private Context mContext;
    private List<HomeHTestDataEntity.Compilations> compilationsList;


    public HomeHRecyclerOutAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public HomeHRecyclerOutAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_home_out, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHRecyclerOutAdapter.ItemViewHolder holder, int position) {
        holder.typeTitle.setText(compilationsList.get(position).getTitle());

        holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        HomeHRecyclerInAdapter homeHRecyclerInAdapter = new HomeHRecyclerInAdapter(mContext, compilationsList.get(position).getData());
        homeHRecyclerInAdapter.setOnClick(onClickListener);
        holder.recyclerView.setAdapter(homeHRecyclerInAdapter);

    }

    @Override
    public int getItemCount() {
        return compilationsList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView typeTitle;
        ImageView titleIcon;
        RecyclerView recyclerView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTitle = itemView.findViewById(R.id.ihh_title);
            titleIcon = itemView.findViewById(R.id.ihh_title_icon);
            recyclerView = itemView.findViewById(R.id.ihh_recycler_in);


        }
    }

    public void addData(List<HomeHTestDataEntity.Compilations> compilationsList){
        this.compilationsList = compilationsList;
        notifyDataSetChanged();
    }

    private final ClickListener.OnClickListener onClickListener = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int position) {
            Log.i(TAG, "onClick: "+position);
        }
    };



}
*/
