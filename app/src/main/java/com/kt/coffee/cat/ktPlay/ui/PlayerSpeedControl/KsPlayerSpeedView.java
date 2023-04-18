package com.kt.coffee.cat.ktPlay.ui.PlayerSpeedControl;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.KsControlAbstract;
import com.kt.coffee.cat.mInterface.ClickListener;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;

public class KsPlayerSpeedView extends KsControlAbstract implements IControlComponent,View.OnClickListener {

    private ControlWrapper mControlWrapper;
    private ClickListener.OnClickListener mOnClickListener;
    private TextView[] mSpeed = new TextView[6];

    public KsPlayerSpeedView(Context context) {
        super(context);
        super.width = 150.0F;
        this.initAnimation();
        initView();
    }

    private void initView(){

        mSpeed[0] = super.view.findViewById(R.id.play_speed_0_5);
        mSpeed[1] = super.view.findViewById(R.id.play_speed_0_75);
        mSpeed[2] = super.view.findViewById(R.id.play_speed_1_0);
        mSpeed[3] = super.view.findViewById(R.id.play_speed_1_25);
        mSpeed[4] = super.view.findViewById(R.id.play_speed_1_5);
        mSpeed[5] = super.view.findViewById(R.id.play_speed_2_0);

        mSpeed[0].setOnClickListener(this);
        mSpeed[1].setOnClickListener(this);
        mSpeed[2].setOnClickListener(this);
        mSpeed[3].setOnClickListener(this);
        mSpeed[4].setOnClickListener(this);
        mSpeed[5].setOnClickListener(this);

    }

    @Override
    public int getBGID() {
        return R.id.landscape_speed;
    }

    @Override
    public int getLayout() {
        return R.layout.ktplay_layout_speed_view;
    }

    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {
        this.mControlWrapper = controlWrapper;
    }

    @Nullable
    @Override
    public View getView() {
        return super.view;
    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

    }

    @Override
    public void onPlayStateChanged(int playState) {

    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (playerState == 10){
            super.view.setVisibility(View.GONE);
        }
    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }

    @Override
    public void onClick(View view) {

        if (mOnClickListener == null) return;
        int id = view.getId();
        int speed = 0;
        switch (id){
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
        mOnClickListener.onClick(speed);

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
            mSpeed[0].setTextColor(super.context.getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(super.context.getResources().getColor(R.color.white));
        }
        if (speed == 2){
            mSpeed[1].setTextColor(super.context.getResources().getColor(R.color.ThemeColor));
            mSpeed[0].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(super.context.getResources().getColor(R.color.white));

        }
        if (speed == 3){
            mSpeed[2].setTextColor(super.context.getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(super.context.getResources().getColor(R.color.white));

        }
        if (speed == 4){
            mSpeed[3].setTextColor(super.context.getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(super.context.getResources().getColor(R.color.white));

        }
        if (speed == 5){
            mSpeed[4].setTextColor(super.context.getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[5].setTextColor(super.context.getResources().getColor(R.color.white));

        }
        if (speed == 6){
            mSpeed[5].setTextColor(super.context.getResources().getColor(R.color.ThemeColor));
            mSpeed[1].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[2].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[3].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[4].setTextColor(super.context.getResources().getColor(R.color.white));
            mSpeed[0].setTextColor(super.context.getResources().getColor(R.color.white));

        }

    }

    public void setOnClickListener(ClickListener.OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }


    public void showPlayerSpeed(){
        mControlWrapper.hide();
        show();
    }

    public void hidePlayerSpeed(){
        hide();
    }



}
