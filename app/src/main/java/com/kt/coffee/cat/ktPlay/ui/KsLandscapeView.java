package com.kt.coffee.cat.ktPlay.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
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

import static xyz.doikki.videoplayer.util.PlayerUtils.stringForTime;

// 横屏状态下的播放器ui
public class KsLandscapeView extends FrameLayout implements IControlComponent,View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    protected ControlWrapper mControlWrapper;
    private final LinearLayout mTitleContainer, mBottomContainer;
    private final TextView mSysTime, mTitle, mCurrTime, mTotalTime, mTcpSpeed;
    private final BatteryView mBattery;
    protected final ImageView mMore, mPlayButton, mForward, mBack;

    protected final TextView mPlaySpeed, mPlayForward;

    private final SeekBar mVideoProgress;

    private final ProgressBar mBottomProgress;

    private boolean mIsDragging;

    private boolean mIsShowBottomProgress = true;
    private final static String TAG = "KsLandscapeView";


    public KsLandscapeView(@NonNull Context context) {
        super(context);
    }

    public KsLandscapeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KsLandscapeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.ktplayer_layout_landscape_all_view, this, true);

        mTitleContainer = findViewById(R.id.title_container);
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);

        mMore = findViewById(R.id.more);

        mTitle = findViewById(R.id.title);
        mSysTime = findViewById(R.id.sys_time);
        mBattery = findViewById(R.id.battery);
        mBattery.setLifecycleOwner((LifecycleOwner) getContext());


        /*========================================================================*/
        mBottomContainer = findViewById(R.id.bottom_container);
        mCurrTime = findViewById(R.id.curr_time);
        mTotalTime = findViewById(R.id.total_time);
        mForward = findViewById(R.id.forward);
//        mForward.setOnClickListener(this);

        mPlayButton = findViewById(R.id.play);
        mPlayButton.setOnClickListener(this);

        mPlaySpeed = findViewById(R.id.play_speed);


        mPlayForward = findViewById(R.id.play_forward_select);

        mTcpSpeed = findViewById(R.id.play_tcp_speed);
        mVideoProgress = findViewById(R.id.seekBar);
        mVideoProgress.setOnSeekBarChangeListener(this);

        mBottomProgress = findViewById(R.id.bottom_progress);

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
        if (!mControlWrapper.isFullScreen()) {
            setVisibility(GONE);
        } else {
            if (isVisible) {
//                mBottomContainer.setVisibility(VISIBLE);
                mSysTime.setText(PlayerUtils.getCurrentSystemTime());
                setVisibility(VISIBLE);
                if (anim != null) {
                    mBottomContainer.startAnimation(anim);
                    mTitleContainer.startAnimation(anim);
                }
                if (mIsShowBottomProgress) {
                    mBottomProgress.setVisibility(GONE);
                }

                /*if (getVisibility() == GONE) {
                    mSysTime.setText(PlayerUtils.getCurrentSystemTime());
                    setVisibility(VISIBLE);
                    if (anim != null) {
                        startAnimation(anim);
                    }
                }*/
            } else {
                setVisibility(GONE);
                if (anim != null) {
                    mBottomContainer.startAnimation(anim);
                    mTitleContainer.startAnimation(anim);
                }
                if (mIsShowBottomProgress) {
                    mBottomProgress.setVisibility(VISIBLE);
                    AlphaAnimation animation = new AlphaAnimation(0f, 1f);
                    animation.setDuration(300);
                    mBottomProgress.startAnimation(animation);
                }

            }

        }

    }

    @Override
    public void onPlayStateChanged(int playState) {

        switch (playState) {
            case VideoView.STATE_IDLE:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);
                mBottomProgress.setProgress(0);
                mBottomProgress.setSecondaryProgress(0);
                mVideoProgress.setProgress(0);
                mVideoProgress.setSecondaryProgress(0);
                break;
            case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
                setVisibility(GONE);
                break;
            case VideoView.STATE_PLAYING:
                Log.i(TAG, "onPlayStateChanged: .........");
                mPlayButton.setSelected(true);
                if (mIsShowBottomProgress) {
                    Log.i(TAG, "onPlayStateChanged: 。。，。。。。");
                    if (!mControlWrapper.isFullScreen()) return;
                    Log.i(TAG, "onPlayStateChanged: ,,,,,,,");
                    if (mControlWrapper.isShowing()) {
                        mBottomProgress.setVisibility(GONE);
                        mBottomContainer.setVisibility(VISIBLE);
                    } else {
                        mBottomContainer.setVisibility(GONE);
                        mBottomProgress.setVisibility(VISIBLE);
                    }
                } else {
                    mBottomContainer.setVisibility(GONE);
                }
                setVisibility(VISIBLE);
                //开始刷新进度
                mControlWrapper.startProgress();
                break;
            case VideoView.STATE_PAUSED:
                mPlayButton.setSelected(false);
                break;
            case VideoView.STATE_BUFFERING:
                mPlayButton.setSelected(mControlWrapper.isPlaying());
                // 停止刷新进度
                mControlWrapper.stopProgress();
                break;
            case VideoView.STATE_BUFFERED:
                mPlayButton.setSelected(mControlWrapper.isPlaying());
                //开始刷新进度
                mControlWrapper.startProgress();
                break;
        }

    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (playerState == VideoView.PLAYER_FULL_SCREEN) {
            Log.i(TAG, "onPlayerStateChanged: h");
            setVisibility(VISIBLE);
        } else {
            Log.i(TAG, "onPlayerStateChanged: x");
            setVisibility(GONE);
        }

        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity != null && mControlWrapper.hasCutout()) {
            int orientation = activity.getRequestedOrientation();
            int cutoutHeight = mControlWrapper.getCutoutHeight();
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                mBottomContainer.setPadding(0, 0, 0, 0);
                mBottomProgress.setPadding(0, 0, 0, 0);
                mTitleContainer.setPadding(0, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                mBottomContainer.setPadding(cutoutHeight, 0, 0, 0);
                mBottomProgress.setPadding(cutoutHeight, 0, 0, 0);
                mTitleContainer.setPadding(cutoutHeight, 0, 0, 0);

            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                mBottomContainer.setPadding(0, 0, cutoutHeight, 0);
                mBottomProgress.setPadding(0, 0, cutoutHeight, 0);
                mTitleContainer.setPadding(0, 0, cutoutHeight, 0);

            }
        }

    }

    @Override
    public void setProgress(int duration, int position) {


        if (mIsDragging) {
            return;
        }

        if (mVideoProgress != null) {
            if (duration > 0) {
                mVideoProgress.setEnabled(true);
                int pos = (int) (position * 1.0 / duration * mVideoProgress.getMax());
                mVideoProgress.setProgress(pos);
                mBottomProgress.setProgress(pos);
            } else {
                mVideoProgress.setEnabled(false);
            }
            int percent = mControlWrapper.getBufferedPercentage();
            if (percent >= 95) { //解决缓冲进度不能100%问题
                mVideoProgress.setSecondaryProgress(mVideoProgress.getMax());
                mBottomProgress.setSecondaryProgress(mBottomProgress.getMax());
            } else {
                mVideoProgress.setSecondaryProgress(percent * 10);
                mBottomProgress.setSecondaryProgress(percent * 10);
            }
        }

        if (mTotalTime != null)
            mTotalTime.setText(stringForTime(duration));
        if (mCurrTime != null)
            mCurrTime.setText(stringForTime(position));

        if (mTcpSpeed != null) {
            long tcpSpeed = mControlWrapper.getTcpSpeed();

            if (tcpSpeed != 0) {
                mTcpSpeed.setVisibility(VISIBLE);
                String tcp = String.format("%.2f", (float) tcpSpeed / 1024 / 1024);
                mTcpSpeed.setText(tcp.concat(" Mb/s"));
            } else {
                mTcpSpeed.setVisibility(GONE);
            }
        }



    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

        onVisibilityChanged(!isLocked, null);

    }



    /**
     *
     * 点击事件
     * */
    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.back){
            Activity activity = PlayerUtils.scanForActivity(getContext());
            if (activity != null && mControlWrapper.isFullScreen()) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mControlWrapper.stopFullScreen();
            }
        } else if (id == R.id.play) {
            mControlWrapper.togglePlay();
        }


    }

    /*
    *
    * seekBar
    * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (!b) {
            return;
        }
        long duration = mControlWrapper.getDuration();
        long newPosition = (duration * i) / mVideoProgress.getMax();
        if (mCurrTime != null)
            mCurrTime.setText(stringForTime((int) newPosition));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        mIsDragging = true;
        mControlWrapper.stopProgress();
        mControlWrapper.stopFadeOut();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long duration = mControlWrapper.getDuration();
        long newPosition = (duration * seekBar.getProgress()) / mVideoProgress.getMax();
        mControlWrapper.seekTo((int) newPosition);
        mIsDragging = false;
        mControlWrapper.startProgress();
        mControlWrapper.startFadeOut();
    }
}
