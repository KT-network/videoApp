package com.kt.coffee.cat.ktPlay;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.AnthologyControl.KsAnthologyView;
import com.kt.coffee.cat.ktPlay.ui.Danmuk.KsJsonParser;
import com.kt.coffee.cat.ktPlay.ui.Danmuk.KsJsonLoader;
import com.kt.coffee.cat.ktPlay.ui.KsIncompletionView;
import com.kt.coffee.cat.ktPlay.ui.KsLandscapeView;
import com.kt.coffee.cat.ktPlay.ui.KsStandardVideoController;
import com.kt.coffee.cat.ktPlay.ui.PlayerSpeedControl.KsPlayerSpeedView;
import com.kt.coffee.cat.ktPlay.ui.Danmuk.KsDanmakuView;
import com.kt.coffee.cat.ktPlay.ui.component.KsErrorView;
import com.kt.coffee.cat.ktPlay.ui.component.KsGestureView;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.KsMmkv;
import com.kt.coffee.cat.utils.PlayerVideoEntity;
import com.kt.coffee.cat.utils.Tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.player.VideoViewManager;

public class PlayerActivity extends AppCompatActivity {

    private final static String TAG = "PlayerActivity";
    private int playListIndex = 0;
    VideoView mVideoView;
    KsIncompletionView ksIncompletionView;
    KsLandscapeView ksLandscapeView;
    KsAnthologyView ksAnthologyView;
    KsPlayerSpeedView ksPlayerSpeedView;
    KsStandardVideoController controller;
    KsDanmakuView ksDanmakuView;
    KsGestureView ksGestureView;
    KsErrorView ksErrorView;
    CompleteView completeView;


    //blue
    public int getPlayListIndex(){
        return getPlayListIndex();
    }

    private List<PlayerVideoEntity.VideoUrlArray> videoUrlArrays;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initView();
        initEvent();
        initData();

    }

    private void initEvent() {

        ksIncompletionView.setBack(onClickBack);

        ksLandscapeView.getPlayAnthologyView().setOnClickListener(onClickShowPlayAnthologyView);
        ksLandscapeView.getPlaySpeedView().setOnClickListener(onClickShowPlaySpeedView);
        ksLandscapeView.getmForward().setOnClickListener(onClickPlayForward);

        ksAnthologyView.setListItemClick(onClickPlayAnthology);
        ksPlayerSpeedView.setOnClickListener(onClickPlaySpeed);

    }

    private void initData() {

        try {
            String JSON = Tool.inputStreamToString(getResources().getAssets().open("Video.json"));
            PlayerVideoEntity playerVideoEntity = new Gson().fromJson(JSON, PlayerVideoEntity.class);
            videoUrlArrays = playerVideoEntity.getVideoUrlArrays();

            Log.i(TAG, "initData: " + playerVideoEntity.getVideoUrlArrays().get(playListIndex).getName());

            ksAnthologyView.setListData(playerVideoEntity.getVideoUrlArrays());

            mVideoView.setUrl(playerVideoEntity.getVideoUrlArrays().get(playListIndex).getVideoUrl());
            ksLandscapeView.setTitle(playerVideoEntity.getVideoName() + " " + playerVideoEntity.getVideoUrlArrays().get(playListIndex).getName());

//            ksTitleMoreView.setTitle(playerVideoEntity.getVideoName());
//            Glide.with(this).load(playerVideoEntity.getVideoThumb()).into(thumb);
//            mVideoView.setUrl(playerVideoEntity.getVideoUrl());
            ksDanmakuView.setDanmu(createParser(openDamu()));
            mVideoView.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initView() {



        mVideoView = findViewById(R.id.player);

        mVideoView.setPlayerFactory(IjkPlayerFactory.create());
        // 控制器
        controller = new KsStandardVideoController(this);
        // 全屏状态下的播放器ui
        ksLandscapeView = new KsLandscapeView(this);
        // 普通状态下的播放器ui
        ksIncompletionView = new KsIncompletionView(this);
        // 全屏状态下的选集列表ui
        ksAnthologyView = new KsAnthologyView(this);
        // 全屏状态下的播放速度ui
        ksPlayerSpeedView = new KsPlayerSpeedView(this);
        // 弹幕
        ksDanmakuView = new KsDanmakuView(this);
        // 手势控制ui
        ksGestureView = new KsGestureView(this);

        // 播放完成ui
        completeView = new CompleteView(this);
        // 播放错误ui
        ksErrorView = new KsErrorView(this);


        // 添加控制器
        controller.addControlComponent(ksLandscapeView);
        controller.addControlComponent(ksIncompletionView);
        controller.addControlComponent(ksAnthologyView);
        controller.addControlComponent(ksPlayerSpeedView);
        controller.addControlComponent(ksDanmakuView);
        controller.addControlComponent(completeView);
        controller.addControlComponent(ksErrorView);
        controller.addControlComponent(ksGestureView);

        // 设置是否自动旋转屏幕
        controller.setEnableOrientation(KsMmkv.mv.decodeBool("playerRotate"));
        // 设置控制器
        mVideoView.setVideoController(controller);

    }


    private InputStream openDamu() {
        try {
            return getResources().getAssets().open("danmuks.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private BaseDanmakuParser createParser(InputStream stream) {
        ILoader loader = KsJsonLoader.instance();
        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        KsJsonParser parser = new KsJsonParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }


    /*
     * 播放速度的点击显示
     * */
    private View.OnClickListener onClickShowPlaySpeedView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ksPlayerSpeedView.showPlayerSpeed();
        }
    };


    /*
     * 播放速度列表的点击
     * */
    private ClickListener.OnClickListener onClickPlaySpeed = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {
            if (i == 1) ksLandscapeView.setPlaySpeedText(R.string.play_speed_0_5);
            else if (i == 2) ksLandscapeView.setPlaySpeedText(R.string.play_speed_0_75);
            else if (i == 3) ksLandscapeView.setPlaySpeedText(R.string.play_speed_default);
            else if (i == 4) ksLandscapeView.setPlaySpeedText(R.string.play_speed_1_25);
            else if (i == 5) ksLandscapeView.setPlaySpeedText(R.string.play_speed_1_5);
            else if (i == 6) ksLandscapeView.setPlaySpeedText(R.string.play_speed_2_0);
        }
    };

    /*
     * 选集列表的点击显示
     * */
    private View.OnClickListener onClickShowPlayAnthologyView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ksAnthologyView.showAnthology(playListIndex);
        }
    };

    /*
     * 选集列表的点击
     * */
    private ClickListener.OnClickListener onClickPlayAnthology = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {

            playListIndex = i;
            mVideoView.release();  // 释放播放器
            mVideoView.setUrl(videoUrlArrays.get(playListIndex).getVideoUrl());  // 设置url
            mVideoView.setVideoController(controller);  // 设置控制器
            mVideoView.start();  // 开始

        }
    };

    /*
     *
     * 普通播放器状态下 点击返回
     * */
    private View.OnClickListener onClickBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    /*
     * 点击下一集
     * */
    private View.OnClickListener onClickPlayForward = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

//            ksLandscapeView.getTime();

            playListIndex++;
            if (playListIndex >= videoUrlArrays.size()) {
                playListIndex = 0;
            }
            mVideoView.release();  // 释放播放器
            mVideoView.setUrl(videoUrlArrays.get(playListIndex).getVideoUrl());  // 设置url
            mVideoView.setVideoController(controller);  // 设置控制器
            mVideoView.start();  // 开始*/
            ksAnthologyView.changeNowPlaying(playListIndex);
        }
    };


    /*
     * 以下为生命周期事件 ===================================分割线========================================
     * */
    @Override
    protected void onResume() {
        super.onResume();

        if (mVideoView != null) {
            mVideoView.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

}
