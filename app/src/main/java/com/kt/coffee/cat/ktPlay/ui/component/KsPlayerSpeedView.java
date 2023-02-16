package com.kt.coffee.cat.ktPlay.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kt.coffee.cat.R;

public class KsPlayerSpeedView extends KsVodControlView{

    private final LinearLayout mSpeedContainer;

    private final TextView[] mSpeed = new TextView[6];

    public KsPlayerSpeedView(@NonNull Context context) {
        super(context);
    }

    public KsPlayerSpeedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KsPlayerSpeedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    {

        LayoutInflater.from(getContext()).inflate(R.layout.ktplay_layout_voc_play_speed_view,this,true);
        mSpeedContainer = findViewById(R.id.speed_container);

        mSpeed[0] = findViewById(R.id.play_speed_0_5);
        mSpeed[1] = findViewById(R.id.play_speed_0_75);
        mSpeed[2] = findViewById(R.id.play_speed_1_0);
        mSpeed[3] = findViewById(R.id.play_speed_1_25);
        mSpeed[4] = findViewById(R.id.play_speed_1_5);
        mSpeed[5] = findViewById(R.id.play_speed_2_0);

        mSpeed[0].setOnClickListener(this);
        mSpeed[1].setOnClickListener(this);
        mSpeed[2].setOnClickListener(this);
        mSpeed[3].setOnClickListener(this);
        mSpeed[4].setOnClickListener(this);
        mSpeed[5].setOnClickListener(this);


        this.mPlaySpeed.setOnClickListener(this);


    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {
        super.onVisibilityChanged(isVisible, anim);
        if (mSpeedContainer.getVisibility() == VISIBLE){
            mSpeedContainer.setVisibility(GONE);
        }
        /*if (isVisible){
            mSpeedContainer.setVisibility(GONE);
        }*/
    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        super.onPlayerStateChanged(playerState);
        if (mSpeedContainer.getVisibility() == VISIBLE){
            mSpeedContainer.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        int speed = 0;
        switch (id){
            case R.id.play_speed:
                mControlWrapper.hide();
                mSpeedContainer.setVisibility(VISIBLE);
                break;
            case R.id.play_speed_0_5:
                speed = 1;
                break;
            case R.id.play_speed_0_75:
                speed = 2;
                break;
            case R.id.play_speed_1_0:
                speed = 3;
                break;
            case R.id.play_speed_1_25:
                speed = 4;
                break;
            case R.id.play_speed_1_5:
                speed = 5;
                break;
            case R.id.play_speed_2_0:
                speed = 6;
                break;
        }

        setVideoPlaySpeed(speed);
        setSpeedTextColor(speed);

    }

    private void setVideoPlaySpeed(int speed){
        if (speed == 0) return;
        if (speed == 1)mControlWrapper.setSpeed(0.5f);
        if (speed == 2)mControlWrapper.setSpeed(0.75f);
        if (speed == 3)mControlWrapper.setSpeed(1.0f);
        if (speed == 4)mControlWrapper.setSpeed(1.25f);
        if (speed == 5)mControlWrapper.setSpeed(1.5f);
        if (speed == 6)mControlWrapper.setSpeed(2.0f);
    }

    private void setSpeedTextColor(int speed){
        if (speed == 0) return;

        if (speed == 1){
            mSpeed[0].setTextColor(getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(getResources().getColor(R.color.white));
            setSpeedText(R.string.play_speed_0_5);
        }
        if (speed == 2){
            mSpeed[1].setTextColor(getResources().getColor(R.color.ThemeColor));
            mSpeed[0].setTextColor(getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(getResources().getColor(R.color.white));
            setSpeedText(R.string.play_speed_0_75);
        }
        if (speed == 3){
            mSpeed[2].setTextColor(getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(getResources().getColor(R.color.white));
            setSpeedText(R.string.play_speed_default);
        }
        if (speed == 4){
            mSpeed[3].setTextColor(getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(getResources().getColor(R.color.white));
            setSpeedText(R.string.play_speed_1_25);

        }
        if (speed == 5){
            mSpeed[4].setTextColor(getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(getResources().getColor(R.color.white));
            setSpeedText(R.string.play_speed_1_5);
        }
        if (speed == 6){
            mSpeed[5].setTextColor(getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(getResources().getColor(R.color.white));
            setSpeedText(R.string.play_speed_2_0);
        }

    }


}
