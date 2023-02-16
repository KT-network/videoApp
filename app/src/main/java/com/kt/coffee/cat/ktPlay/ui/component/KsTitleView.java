package com.kt.coffee.cat.ktPlay.ui.component;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.kt.coffee.cat.R;
import com.sjx.batteryviewlibrary.BatteryView;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

public class KsTitleView extends FrameLayout implements IControlComponent {

    private ControlWrapper mControlWrapper;
    protected LinearLayout mTitleContainer;
    protected TextView mSysTime,mTitle;
    protected BatteryView mBattery;



    public KsTitleView(@NonNull Context context) {
        super(context);
    }

    public KsTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KsTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KsTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.ktplayer_layout_title_view, this, true);
        mTitleContainer = findViewById(R.id.title_container);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = PlayerUtils.scanForActivity(getContext());
                if (activity != null && mControlWrapper.isFullScreen()) {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mControlWrapper.stopFullScreen();
                }
            }
        });

        ImageView more = findViewById(R.id.more);
        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        mTitle = findViewById(R.id.title);
        mSysTime = findViewById(R.id.sys_time);
        mBattery = findViewById(R.id.battery);
        mBattery.setLifecycleOwner((LifecycleOwner) getContext());


    }
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    /*@Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mIsRegister) {
            getContext().unregisterReceiver(mBatteryReceiver);
            mIsRegister = false;
        }
    }*/

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
        if (!mControlWrapper.isFullScreen()) return;
        if (isVisible) {
            if (getVisibility() == GONE) {
                mSysTime.setText(PlayerUtils.getCurrentSystemTime());
                setVisibility(VISIBLE);
                if (anim != null) {
                    startAnimation(anim);
                }
            }
        } else {
            if (getVisibility() == VISIBLE) {
                setVisibility(GONE);
                if (anim != null) {
                    startAnimation(anim);
                }
            }
        }
    }

    @Override
    public void onPlayStateChanged(int playState) {
        switch (playState) {
            case VideoView.STATE_IDLE:
            case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);
                break;
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (playerState == VideoView.PLAYER_FULL_SCREEN) {
            if (mControlWrapper.isShowing() && !mControlWrapper.isLocked()) {
                setVisibility(VISIBLE);
                mSysTime.setText(PlayerUtils.getCurrentSystemTime());
            }
            mTitle.setSelected(true);
        } else {
            setVisibility(GONE);
            mTitle.setSelected(false);
        }

        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity != null && mControlWrapper.hasCutout()) {
            int orientation = activity.getRequestedOrientation();
            int cutoutHeight = mControlWrapper.getCutoutHeight();
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                mTitleContainer.setPadding(0, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                mTitleContainer.setPadding(cutoutHeight, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                mTitleContainer.setPadding(0, 0, cutoutHeight, 0);
            }
        }

    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {
        if (isLocked) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            mSysTime.setText(PlayerUtils.getCurrentSystemTime());
        }

    }
}
