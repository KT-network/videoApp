package com.kt.coffee.cat.utils;


public class Explain {


    // import xyz.doikki.videoplayer.player.VideoView;
    // 播放错误
    private static final int STATE_ERROR = -1;
    // 播放器空闲
    private static final int STATE_IDLE = 0;
    // 播放器准备中
    private static final int STATE_PREPARING = 1;
    // 播放器准备完成
    private static final int STATE_PREPARED = 2;
    // 正在播放
    private static final int STATE_PLAYING = 3;
    // 暂停
    private static final int STATE_PAUSED = 4;
    // 播放完成
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    // 播放缓冲开始
    private static final int STATE_BUFFERING = 6;
    // 缓冲结束
    private static final int STATE_BUFFERED = 7;

    private static final int STATE_START_ABORT = 8;//开始播放中止
    protected int mCurrentPlayState = STATE_IDLE;//当前播放器的状态

    private static final int PLAYER_NORMAL = 10;        // 普通播放器
    private static final int PLAYER_FULL_SCREEN = 11;   // 全屏播放器
    private static final int PLAYER_TINY_SCREEN = 12;   // 小屏播放器
}
