package com.kt.coffee.cat.ktPlay.ui.component;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kt.coffee.cat.R;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IGestureComponent;
import xyz.doikki.videoplayer.player.VideoView;

import static xyz.doikki.videoplayer.util.PlayerUtils.stringForTime;

public class KsGestureView extends FrameLayout implements IGestureComponent {

    private ControlWrapper mControlWrapper;

    private final ImageView mIcon;
    private final ProgressBar mProgressPercent;
    private final TextView mTextPercent;

    private final RelativeLayout mCenterContainer;

    public KsGestureView(@NonNull Context context) {
        super(context);
    }

    public KsGestureView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KsGestureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KsGestureView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.ktplayer_layout_gesture_control_view, this, true);
        mCenterContainer = findViewById(R.id.center_container);
        mIcon = findViewById(R.id.iv_icon);
        mProgressPercent = findViewById(R.id.pro_percent);
        mTextPercent = findViewById(R.id.tv_percent);

    }



    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {
        mControlWrapper = controlWrapper;
    }

    @Nullable
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

    }

    @Override
    public void onPlayStateChanged(int playState) {
        if (playState == VideoView.STATE_IDLE
                || playState == VideoView.STATE_START_ABORT
                || playState == VideoView.STATE_PREPARING
                || playState == VideoView.STATE_PREPARED
                || playState == VideoView.STATE_ERROR
                || playState == VideoView.STATE_PLAYBACK_COMPLETED) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {

    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }




    @Override
    public void onStartSlide() {
        mControlWrapper.hide();
        mCenterContainer.setVisibility(VISIBLE);
        mCenterContainer.setAlpha(1f);
    }

    @Override
    public void onStopSlide() {
        mCenterContainer.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mCenterContainer.setVisibility(GONE);
                    }
                })
                .start();
    }

    @Override
    public void onPositionChange(int slidePosition, int currentPosition, int duration) {

        mProgressPercent.setVisibility(GONE);
        mTextPercent.setVisibility(VISIBLE);
        if (slidePosition > currentPosition) {
            mIcon.setImageResource(xyz.doikki.videocontroller.R.drawable.dkplayer_ic_action_fast_forward);
        } else {
            mIcon.setImageResource(xyz.doikki.videocontroller.R.drawable.dkplayer_ic_action_fast_rewind);
        }
        mTextPercent.setText(String.format("%s/%s", stringForTime(slidePosition), stringForTime(duration)));
    }

    @Override
    public void onBrightnessChange(int percent) {
        mProgressPercent.setVisibility(VISIBLE);
        mIcon.setImageResource(xyz.doikki.videocontroller.R.drawable.dkplayer_ic_action_brightness);
//        mTextPercent.setText(percent + "%");
        mProgressPercent.setProgress(percent);
    }

    @Override
    public void onVolumeChange(int percent) {
        mProgressPercent.setVisibility(VISIBLE);
        mTextPercent.setVisibility(GONE);
        if (percent <= 0) {
            mIcon.setImageResource(xyz.doikki.videocontroller.R.drawable.dkplayer_ic_action_volume_off);
        } else {
            mIcon.setImageResource(xyz.doikki.videocontroller.R.drawable.dkplayer_ic_action_volume_up);
        }
//        mTextPercent.setText(percent + "%");
        mProgressPercent.setProgress(percent);
    }
}
