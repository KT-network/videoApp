package com.kt.coffee.cat.ktPlay;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.AnthologyControl.KsAnthologyView;
import com.kt.coffee.cat.ktPlay.ui.KsIncompletionView;
import com.kt.coffee.cat.ktPlay.ui.KsLandscapeView;
import com.kt.coffee.cat.ktPlay.ui.KsStandardVideoController;
import com.kt.coffee.cat.ktPlay.ui.PlayerSpeedControl.KsPlayerSpeedView;
import com.kt.coffee.cat.ktPlay.ui.component.KsErrorView;
import com.kt.coffee.cat.ktPlay.ui.component.KsGestureView;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.PlayerVideoEntity;
import com.kt.coffee.cat.utils.Tool;

import java.io.IOException;

import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videoplayer.player.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private final static String TAG = "PlayerActivity";

    VideoView mVideoView;

    KsIncompletionView ksIncompletionView;

    KsLandscapeView ksLandscapeView;

    KsAnthologyView ksAnthologyView;
    KsPlayerSpeedView ksPlayerSpeedView;
    KsStandardVideoController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        0,
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }*/

        initView();
        initData();

    }

    private void initData() {

        try {
            String JSON = Tool.inputStreamToString(getResources().getAssets().open("Video.json"));
            PlayerVideoEntity playerVideoEntity = new Gson().fromJson(JSON, PlayerVideoEntity.class);

            Log.i(TAG, "initData: " + playerVideoEntity.getVideoUrlArrays().get(0).getName());
            ksAnthologyView.setListData(playerVideoEntity.getVideoUrlArrays());
            mVideoView.setUrl(playerVideoEntity.getVideoUrlArrays().get(0).getVideoUrl());

//            ksTitleMoreView.setTitle(playerVideoEntity.getVideoName());
//            Glide.with(this).load(playerVideoEntity.getVideoThumb()).into(thumb);
//            mVideoView.setUrl(playerVideoEntity.getVideoUrl());

            mVideoView.start();

            ksAnthologyView.setListItemClick(new ClickListener.OnClickListener() {
                @Override
                public void onClick(int i) {
                    mVideoView.release();
                    mVideoView.setUrl(playerVideoEntity.getVideoUrlArrays().get(i).getVideoUrl());
                    mVideoView.setVideoController(controller);
                    mVideoView.start();


                    Tool.KToast(getApplicationContext(), String.valueOf(i));
                }
            });

            ksPlayerSpeedView.setOnClickListener(new ClickListener.OnClickListener() {
                @Override
                public void onClick(int i) {

                    if (i == 1) ksLandscapeView.setPlaySpeedText(R.string.play_speed_0_5);
                    else if (i == 2) ksLandscapeView.setPlaySpeedText(R.string.play_speed_0_75);
                    else if (i == 3) ksLandscapeView.setPlaySpeedText(R.string.play_speed_1_0);
                    else if (i == 4) ksLandscapeView.setPlaySpeedText(R.string.play_speed_1_25);
                    else if (i == 5) ksLandscapeView.setPlaySpeedText(R.string.play_speed_1_5);
                    else if (i == 2) ksLandscapeView.setPlaySpeedText(R.string.play_speed_2_0);


                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initView() {
        mVideoView = findViewById(R.id.player);


        controller = new KsStandardVideoController(this);

        ksLandscapeView = new KsLandscapeView(this);
        ksIncompletionView = new KsIncompletionView(this);

        ksAnthologyView = new KsAnthologyView(this);
        ksPlayerSpeedView = new KsPlayerSpeedView(this);

        controller.setEnableOrientation(false);  // 设置是否自动旋转屏幕


        controller.addControlComponent(ksIncompletionView);
        ksIncompletionView.setBack(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        controller.addControlComponent(new CompleteView(this));
        controller.addControlComponent(new KsErrorView(this));//错误界面

        controller.addControlComponent(new KsGestureView(this));


        controller.addControlComponent(ksLandscapeView);
        controller.addControlComponent(ksAnthologyView);
        controller.addControlComponent(ksPlayerSpeedView);
        ksLandscapeView.getPlayForwardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ksLandscapeView.hide();
                ksAnthologyView.show();
            }
        });


        ksLandscapeView.getPlaySpeedView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ksLandscapeView.hide();
                ksPlayerSpeedView.show();
            }
        });


        mVideoView.setVideoController(controller);

    }


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
