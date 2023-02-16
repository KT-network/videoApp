package com.kt.coffee.cat.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.HomeHTestDataEntity;

import java.util.List;

public class HomeHRecyclerInAdapter extends RecyclerAdapterBase{

    private Context mContext;
    private List<HomeHTestDataEntity.Compilations.TestData> testDataList;

    private ClickListener.OnClickListener mOnClick;
    private ClickListener.OnLongClickListener mOnLongClick;

    public HomeHRecyclerInAdapter(Context context, List<HomeHTestDataEntity.Compilations.TestData> testDataList) {
        this.mContext = context;
        this.testDataList = testDataList;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.item_general;
    }

    @Override
    protected int getInflateType() {
        return inflate_2;
    }

    @Override
    protected int getItemCountSon() {
        return testDataList.size();
    }


    ImageView imageView;
    TextView name,tv_update_text;
    LinearLayout tv_update_linear;

    @Override
    protected void initView(View itemView,int position) {
        super.initView(itemView,position);
        imageView = itemView.findViewById(R.id.general_image);
        name = itemView.findViewById(R.id.general_name);
        tv_update_text = itemView.findViewById(R.id.general_tv_update_text);
        tv_update_linear = itemView.findViewById(R.id.general_tv_update_linear);

        if (mOnClick != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClick.onClick(position);
                }
            });

        }

    }

    @Override
    protected void setEvent(ItemViewHolder holder, int position) {
        super.setEvent(holder, position);

        name.setText(testDataList.get(position).getName());

        Glide.with(mContext).load(testDataList.get(position).getImageUrl()).into(imageView);

    }

    public void setOnClick(ClickListener.OnClickListener listener) {
        this.mOnClick = listener;
    }
}
/*

public class HomeHRecyclerInAdapter extends RecyclerView.Adapter<HomeHRecyclerInAdapter.ItemViewHolder> {


    private Context mContext;
    private List<HomeHTestDataEntity.Compilations.TestData> testDataList;

    private ClickListener.OnClickListener mOnClick;
    private ClickListener.OnLongClickListener mOnLongClick;

    public HomeHRecyclerInAdapter(Context context, List<HomeHTestDataEntity.Compilations.TestData> testDataList) {
        this.mContext = context;
        this.testDataList = testDataList;
    }


    @NonNull
    @Override
    public HomeHRecyclerInAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_home_in, null, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHRecyclerInAdapter.ItemViewHolder holder, int position) {

        holder.name.setText(testDataList.get(position).getName());

        Glide.with(mContext).load(testDataList.get(position).getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return testDataList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ihh_image);
            name = itemView.findViewById(R.id.ihh_name);

            if (mOnClick != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClick.onClick(getBindingAdapterPosition());
                    }
                });

            }

            if (mOnLongClick != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnLongClick.onLongClick(getBindingAdapterPosition());
                        return true;
                    }
                });
            }

        }
    }

    public void setOnClick(ClickListener.OnClickListener listener) {
        this.mOnClick = listener;
    }

    public void setOnLongClick(ClickListener.OnLongClickListener listener) {
        this.mOnLongClick = listener;
    }


}
*/
