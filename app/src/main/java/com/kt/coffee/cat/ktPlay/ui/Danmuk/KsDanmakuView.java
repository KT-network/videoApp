package com.kt.coffee.cat.ktPlay.ui.Danmuk;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.NonNull;

import java.util.HashMap;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

public class KsDanmakuView extends DanmakuView implements IControlComponent {

    protected ControlWrapper mControlWrapper;
    private final DanmakuContext mContext;
    private BaseDanmakuParser mParser;


    public KsDanmakuView(Context context) {
        super(context);
    }

    public KsDanmakuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KsDanmakuView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行


        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScrollSpeedFactor(1.2f)
                .setMaximumLines(maxLinesPair)
                .setDanmakuMargin(40);

        /*mParser = new BaseDanmakuParser() {
            @Override
            protected IDanmakus parse() {
                return new Danmakus();
            }
        };*/


        setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });

    }


    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {

        this.mControlWrapper = controlWrapper;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

    }

    @Override
    public void onPlayStateChanged(int playState) {
        switch (playState) {
            case VideoView.STATE_IDLE:
                release();
                break;
            case VideoView.STATE_PREPARED:
                Log.i(TAG, "onPlayStateChanged: 加载完成了aaaaaaaaaa");
                if (isPrepared()) {
                    restart();
                }
                prepare(mParser, mContext);
                break;
            case VideoView.STATE_PLAYING:
                if (isPrepared() && isPaused()) {
                    resume();
                }
                break;
            case VideoView.STATE_PAUSED:
                if (isPrepared()) {
                    pause();
                }
                break;
            case VideoView.STATE_PLAYBACK_COMPLETED:
                clear();
                clearDanmakusOnScreen();
                break;
            case VideoView.STATE_BUFFERED:
                seekTo(mControlWrapper.getCurrentPosition());
                break;
            case VideoView.STATE_BUFFERING:
                pause();
                break;
        }

    }



    @Override
    public void onPlayerStateChanged(int playerState) {

    }

    // private int recordPlayProgress = 1000;  // 记录上一秒的进度（判断是否快进或快退）
    @Override
    public void setProgress(int duration, int position) {

        /*if (position <= 1000) return;
        Log.i(TAG, "setProgress: "+recordPlayProgress);
        if (position - recordPlayProgress < 1500){
        }else {
            seekTo((long) position);
        }

        recordPlayProgress = position;*/
    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }

    public void setDanmu(BaseDanmakuParser parser){
        this.mParser = parser;
    }


    /**
     * 发送文字弹幕
     *
     * @param text   弹幕文字
     * @param isSelf 是不是自己发的
     */
    public void addDanmaku(String text, boolean isSelf) {
        mContext.setCacheStuffer(new SpannedCacheStuffer(), null);
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null) {
            return;
        }

        danmaku.text = text;
        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = false;
        Log.i(TAG, "addDanmaku: "+getCurrentTime());
        danmaku.setTime(getCurrentTime());
        danmaku.textSize = PlayerUtils.sp2px(getContext(), 12);
        danmaku.textColor = Color.WHITE;
        danmaku.textShadowColor = Color.GRAY;
        // danmaku.underlineColor = Color.GREEN;
        danmaku.borderColor = isSelf ? Color.GREEN : Color.TRANSPARENT;
        addDanmaku(danmaku);
    }

}
