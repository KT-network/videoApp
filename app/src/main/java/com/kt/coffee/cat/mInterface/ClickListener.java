package com.kt.coffee.cat.mInterface;

import android.widget.LinearLayout;
import android.widget.TextView;

public class ClickListener {

        public interface OnClickListener {
        void onClick(int i);
    }

    public interface OnLongClickListener {
        void onLongClick(int position);
    }

    public interface OnAnthologyItemClickListener{
        void onClick(LinearLayout linearLayout, TextView textView,int position);
    }


}
