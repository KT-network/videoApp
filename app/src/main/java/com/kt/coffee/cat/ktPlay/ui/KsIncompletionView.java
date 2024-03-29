package com.kt.coffee.cat.ktPlay.ui;

import static xyz.doikki.videoplayer.util.PlayerUtils.stringForTime;

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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kt.coffee.cat.R;

import java.util.List;

import rxhttp.RxHttpPlugins;
import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

public class KsIncompletionView extends FrameLayout implements IControlComponent, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    protected ControlWrapper mControlWrapper;

    private final RelativeLayout mIncompletionControl,mIncompletionControlBottom;
    private final TextView mAllTime;
    private final ImageView mPlayButton, mFullScreen,mBack;
    private final SeekBar mVideoProgress;
    private final ProgressBar mBottomProgress;

    private boolean mIsShowBottomProgress = true;
    private boolean mIsDragging;


    private static final String TAG = "KsIncompletionViewNew";


    public KsIncompletionView(@NonNull Context context) {
        super(context);
    }

    public KsIncompletionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KsIncompletionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.ktplayer_layout_incompletion_view, this, true);
        mIncompletionControl = findViewById(R.id.incompletion_control);
        mIncompletionControlBottom = findViewById(R.id.incompletion_control_bottom);
        mBack = findViewById(R.id.back);
        mPlayButton = findViewById(R.id.play);
        mFullScreen = findViewById(R.id.fullscreen);
        mAllTime = findViewById(R.id.allTime);
        mVideoProgress = findViewById(R.id.seekBar);
        mBottomProgress = findViewById(R.id.bottom_progress);

        mBack.setOnClickListener(this);
        mPlayButton.setOnClickListener(this);
        mFullScreen.setOnClickListener(this);
        mVideoProgress.setOnSeekBarChangeListener(this);

    }

    /**
     * 是否显示底部进度条，默认显示
     */
    public void showBottomProgress(boolean isShow) {
        mIsShowBottomProgress = isShow;
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

        /*
         * 需求：
         * 竖屏状态下显示，isVisible为true 显示拖动条 否则 显示小进度条
         * 横屏状态下什么都不显示
         * */
        if (mControlWrapper.isFullScreen()){
            setVisibility(GONE);
            return;
        }else {
            // 点击 true
            if (isVisible){
                mIncompletionControl.setVisibility(VISIBLE);
                if (anim != null){
                    mIncompletionControl.startAnimation(anim);
                }
                if (mIsShowBottomProgress){
                    mBottomProgress.setVisibility(GONE);
                }

            }else {
                mIncompletionControl.setVisibility(GONE);
                if (anim != null){
                    mIncompletionControl.startAnimation(anim);
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
        Log.i(TAG, "onPlayStateChanged: "+playState);

        if (mControlWrapper.isFullScreen()) return;

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
                // 准备中隐藏底部控制器（视频未加载成功）
                Log.i(TAG, "onPlayStateChanged: 加载中");
                mIncompletionControlBottom.setVisibility(GONE);
                break;

            case VideoView.STATE_PREPARED:
                Log.i(TAG, "onPlayStateChanged: 加载完成");
                mIncompletionControlBottom.setVisibility(VISIBLE);
                break;
            case VideoView.STATE_ERROR:
                Log.i(TAG, "onPlayStateChanged: 加载失败");
                mIncompletionControlBottom.setVisibility(GONE);
                // setVisibility(GONE);
                break;
            case VideoView.STATE_PLAYING:
                mPlayButton.setSelected(true);
                if (mIsShowBottomProgress) {
                    if (mControlWrapper.isFullScreen()) return;
                    if (mControlWrapper.isShowing()) {
                        mBottomProgress.setVisibility(GONE);
                        mIncompletionControl.setVisibility(VISIBLE);
                    } else {
                        mIncompletionControl.setVisibility(GONE);
                        mBottomProgress.setVisibility(VISIBLE);
                    }
                } else {
                    mIncompletionControl.setVisibility(GONE);
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

        if (playerState == VideoView.PLAYER_NORMAL){
            mPlayButton.setSelected(mControlWrapper.isPlaying());
            setVisibility(VISIBLE);
        }else {
            setVisibility(GONE);
        }
        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity != null && mControlWrapper.hasCutout()) {
            int orientation = activity.getRequestedOrientation();
            int cutoutHeight = mControlWrapper.getCutoutHeight();
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                mIncompletionControl.setPadding(0, 0, 0, 0);
                mBottomProgress.setPadding(0, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                mIncompletionControl.setPadding(cutoutHeight, 0, 0, 0);
                mBottomProgress.setPadding(cutoutHeight, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                mIncompletionControl.setPadding(0, 0, cutoutHeight, 0);
                mBottomProgress.setPadding(0, 0, cutoutHeight, 0);
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

        if (mAllTime != null){
            mAllTime.setText(stringForTime(position)+"/"+stringForTime(duration));
        }

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }



    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.play){
            mControlWrapper.togglePlay();
        } else if (id == R.id.fullscreen){
            toggleFullScreen();
        }
    }

    // 设置 back 事件
    public void setBack(OnClickListener onClickListener){
        if (onClickListener != null){
            mBack.setOnClickListener(onClickListener);
        }
    }

    /**
     * 横竖屏切换
     */
    private void toggleFullScreen() {
        Activity activity = PlayerUtils.scanForActivity(getContext());
        mControlWrapper.toggleFullScreen(activity);
        // 下面方法会根据适配宽高决定是否旋转屏幕
//        mControlWrapper.toggleFullScreenByVideoSize(activity);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        if (!b) {
            return;
        }
        long duration = mControlWrapper.getDuration();
        long newPosition = (duration * i) / mVideoProgress.getMax();



        if (mAllTime != null)
            mAllTime.setText(stringForTime((int) newPosition)+"/"+stringForTime((int) duration));
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
