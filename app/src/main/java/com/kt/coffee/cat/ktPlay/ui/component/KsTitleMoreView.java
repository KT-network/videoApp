package com.kt.coffee.cat.ktPlay.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kt.coffee.cat.R;

public class KsTitleMoreView extends KsTitleView{

    private final LinearLayout moreContainer;
    private final TextView[] moreSetting = new TextView[3];
    private final TextView[] moreMethod = new TextView[3];
    private final TextView[] moreSize = new TextView[5];

    public KsTitleMoreView(@NonNull Context context) {
        super(context);
    }

    public KsTitleMoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KsTitleMoreView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.ktplayer_layout_title_more_view, this, true);
        moreContainer = findViewById(R.id.more_container);



    }

}
