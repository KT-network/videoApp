package com.kt.coffee.cat.ktPlay.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.agrawalsuneet.loaderspack.loaders.PulseLoader;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.component.KsCompleteView;
import com.kt.coffee.cat.ktPlay.ui.component.KsErrorView;
import com.kt.coffee.cat.ktPlay.ui.component.KsPrepareView;
import com.kt.coffee.cat.ktPlay.ui.component.KsTitleView;

import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.LiveControlView;
import xyz.doikki.videocontroller.component.VodControlView;
import xyz.doikki.videoplayer.controller.GestureVideoController;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

public class KsStandardVideoController extends GestureVideoController implements View.OnClickListener{

    protected ImageView mLockButton_1,mLockButton_2;
    protected PulseLoader loading;
    private boolean isBuffering;



    public KsStandardVideoController(@NonNull Context context) {
        this(context,null);
    }

    public KsStandardVideoController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KsStandardVideoController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.ktplayer_layout_standard_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        mLockButton_1 = findViewById(R.id.lock_1);
        mLockButton_2 = findViewById(R.id.lock_2);

        mLockButton_1.setOnClickListener(this);
        mLockButton_2.setOnClickListener(this);
        loading = findViewById(R.id.loading);

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.lock_1 || id == R.id.lock_2){
            mControlWrapper.toggleLockState();
        }
    }

    @Override
    protected void onLockStateChanged(boolean isLocked) {
        super.onLockStateChanged(isLocked);
        if (isLocked) {
            mLockButton_1.setSelected(true);
            mLockButton_2.setSelected(true);
            Toast.makeText(getContext(), R.string.player_locked, Toast.LENGTH_SHORT).show();
        } else {
            mLockButton_1.setSelected(false);
            mLockButton_2.setSelected(false);
            Toast.makeText(getContext(), R.string.player_unlocked, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onVisibilityChanged(boolean isVisible, Animation anim) {
        if (mControlWrapper.isFullScreen()) {
            if (isVisible) {
                if (mLockButton_1.getVisibility() == GONE || mLockButton_2.getVisibility() == GONE) {
                    mLockButton_1.setVisibility(VISIBLE);
                    mLockButton_2.setVisibility(VISIBLE);
                    if (anim != null) {
                        mLockButton_2.startAnimation(anim);
                        mLockButton_1.startAnimation(anim);
                    }
                }
            } else {

                mLockButton_1.setVisibility(GONE);
                mLockButton_2.setVisibility(GONE);

                if (anim != null) {
                    mLockButton_2.startAnimation(anim);
                    mLockButton_1.startAnimation(anim);
                }
            }
        }
    }


    @Override
    protected void onPlayerStateChanged(int playerState) {
        super.onPlayerStateChanged(playerState);
        switch (playerState) {
            case VideoView.PLAYER_NORMAL:
                setLayoutParams(new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                mLockButton_1.setVisibility(GONE);
                mLockButton_2.setVisibility(GONE);
                break;
            case VideoView.PLAYER_FULL_SCREEN:
                if (isShowing()) {
                    mLockButton_1.setVisibility(VISIBLE);
                    mLockButton_2.setVisibility(VISIBLE);
                } else {
                    mLockButton_1.setVisibility(GONE);
                    mLockButton_2.setVisibility(GONE);
                }
                break;
        }

        if (mActivity != null && hasCutout()) {
            int orientation = mActivity.getRequestedOrientation();
            int dp24 = PlayerUtils.dp2px(getContext(), 24);
            int cutoutHeight = getCutoutHeight();
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {

                FrameLayout.LayoutParams lblp = (LayoutParams) mLockButton_1.getLayoutParams();
                lblp.setMargins(dp24, 0, dp24, 0);

                FrameLayout.LayoutParams lblp1 = (LayoutParams) mLockButton_2.getLayoutParams();
                lblp1.setMargins(dp24, 0, dp24, 0);

            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {

                FrameLayout.LayoutParams layoutParams = (LayoutParams) mLockButton_1.getLayoutParams();
                layoutParams.setMargins(dp24 + cutoutHeight, 0, dp24 + cutoutHeight, 0);

                FrameLayout.LayoutParams layoutParams1 = (LayoutParams) mLockButton_2.getLayoutParams();
                layoutParams1.setMargins(dp24 + cutoutHeight, 0, dp24 + cutoutHeight, 0);

            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) mLockButton_1.getLayoutParams();
                layoutParams.setMargins(dp24, 0, dp24, 0);

                FrameLayout.LayoutParams layoutParams1 = (LayoutParams) mLockButton_2.getLayoutParams();
                layoutParams1.setMargins(dp24, 0, dp24, 0);
            }
        }

    }

    @Override
    protected void onPlayStateChanged(int playState) {
        super.onPlayStateChanged(playState);
        switch (playState) {
            //调用release方法会回到此状态
            case VideoView.STATE_IDLE:
                mLockButton_1.setSelected(false);
                mLockButton_2.setSelected(false);
                loading.setVisibility(GONE); //
                break;
            case VideoView.STATE_PLAYING:
            case VideoView.STATE_PAUSED:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
            case VideoView.STATE_BUFFERED:
                if (playState == VideoView.STATE_BUFFERED) {
                    isBuffering = false;
                }
                if (!isBuffering) {
                    loading.setVisibility(GONE);//
                }
                break;
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_BUFFERING:
                loading.setVisibility(VISIBLE);
                if (playState == VideoView.STATE_BUFFERING) {
                    isBuffering = true;
                }
                break;
            case VideoView.STATE_PLAYBACK_COMPLETED:
                loading.setVisibility(GONE); //
                mLockButton_2.setVisibility(GONE);
                mLockButton_2.setSelected(false);
                mLockButton_1.setVisibility(GONE);
                mLockButton_1.setSelected(false);
                break;
        }
    }

    @Override
    public boolean onBackPressed() {
        if (isLocked()) {
            show();
            Toast.makeText(getContext(), R.string.player_lock_tip, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (mControlWrapper.isFullScreen()) {
            return stopFullScreen();
        }
        return super.onBackPressed();
    }



}