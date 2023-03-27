package com.kt.coffee.cat.ktPlay.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kt.coffee.cat.R;

public class KsTitleMoreView extends KsTitleView{

    private final LinearLayout moreContainer;
    private final TextView[] moreSetting = new TextView[3];
    private final TextView[] moreMethod = new TextView[3];
    private final TextView[] moreSize = new TextView[6];

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
        LayoutInflater.from(getContext()).inflate(R.layout.ktplayer_layout_title_more_view, this, true);
        moreContainer = findViewById(R.id.more_container);

        /*moreSetting[0] = findViewById(R.id.more_mirror_reversal);
        moreSetting[1] = findViewById(R.id.more_fenestrule);
        moreSetting[2] = findViewById(R.id.more_projection_screen);

        moreMethod[0] = findViewById(R.id.more_method_list);
        moreMethod[1] = findViewById(R.id.more_method_single);
        moreMethod[2] = findViewById(R.id.more_method_suspend);

        moreSize[0] = findViewById(R.id.more_size_adapt);
        moreSize[1] = findViewById(R.id.more_size_stretch);
        moreSize[2] = findViewById(R.id.more_size_fill);
        moreSize[3] = findViewById(R.id.more_size_16_9);
        moreSize[4] = findViewById(R.id.more_size_4_3);
        moreSize[5] = findViewById(R.id.more_size_original);*/

        this.mMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("TAG", "onClick: "+mControlWrapper.isFullScreen());

//                mControlWrapper.hide();

                mControlWrapper.hide();
                mControlWrapper.show();
                moreContainer.setVisibility(VISIBLE);

            }
        });

    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {
        super.onVisibilityChanged(isVisible, anim);

        if (moreContainer.getVisibility() == VISIBLE){
            moreContainer.setVisibility(GONE);
        }

    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        super.onPlayerStateChanged(playerState);

        if (moreContainer.getVisibility() == VISIBLE){
            moreContainer.setVisibility(GONE);
        }
    }

    /*@Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        switch (id){
            case R.id.more:
                mControlWrapper.hide();
                moreContainer.setVisibility(VISIBLE);
                break;
        }


    }*/
}
