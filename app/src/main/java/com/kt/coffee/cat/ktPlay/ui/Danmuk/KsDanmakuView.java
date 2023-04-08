package com.kt.coffee.cat.ktPlay.ui.Danmuk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;

import androidx.annotation.NonNull;

import java.util.HashMap;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Danmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;

public class KsDanmakuView extends DanmakuView implements IControlComponent {

    private final DanmakuContext mContext;
    private final BaseDanmakuParser mParser;

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
        HashMap<Integer,Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL,4);

        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN,3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(1.2f)
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(null)
                .setDanmakuMargin(40);
        mParser = new BaseDanmakuParser() {
            @Override
            protected IDanmakus parse() {
                return null;
            }
        };



    }

    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {

    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

    }

    @Override
    public void onPlayStateChanged(int playState) {

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
}
