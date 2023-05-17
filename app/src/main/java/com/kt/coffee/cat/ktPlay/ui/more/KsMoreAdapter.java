package com.kt.coffee.cat.ktPlay.ui.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.AnthologyControl.KsAnthologyAdapter;
import com.kt.coffee.cat.mInterface.ClickListener;

public class KsMoreAdapter extends BaseAdapter {

    private ClickListener.OnClickListener mOnClickListener;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mData;

    private int defaultSelectedItemIndex = -1;
    private boolean isClick;
    private int currentPosition;
    private boolean firstLoad = true;

    public KsMoreAdapter(Context context,String[] data){
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int i) {
        return mData[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View mView = mLayoutInflater.inflate(R.layout.item_player_title_more,null);

        TextView textView = mView.findViewById(R.id.item_title_more);
        textView.setText(mData[i]);
        ItemClickEvent(mView,i);
        if (firstLoad) {
            setCurrentPosition(defaultSelectedItemIndex != -1 ? defaultSelectedItemIndex : 0, true);
            firstLoad = false;
        }

        if (getCurrentPosition() == i && isClick) {
            textView.setTextColor(mContext.getResources().getColor(R.color.ThemeColor));
        }



        return mView;
    }


    public void setmOnClickListener(ClickListener.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    private void ItemClickEvent(View view,int i) {

        view.setOnClickListener(view1 -> {
            if (!isClick) {
                setCurrentPosition(i, true);
            } else {
                setCurrentPosition(i, getCurrentPosition() != i);
            }
            if (mOnClickListener != null){
                mOnClickListener.onClick(i);
            }
            notifyDataSetChanged();
        });

    }

    /*
     * 设置当前状态
     * */
    public void setCurrentPosition(int currentPosition, boolean isClick) {
        this.isClick = isClick;
        if (currentPosition >= getCount()){
            this.currentPosition = 0;
            return;
        }
        this.currentPosition = currentPosition;

    }


    public int getCurrentPosition() {
        return this.currentPosition;
    }

    /*
     * 设置默认选中
     * */
    public void setDefaultSelectedItemIndex(int position) {
        this.defaultSelectedItemIndex = position;
    }


}
