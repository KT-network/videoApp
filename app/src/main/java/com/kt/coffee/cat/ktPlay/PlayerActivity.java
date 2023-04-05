package com.kt.coffee.cat.ktPlay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.KsIncompletionViewNew;
import com.kt.coffee.cat.ktPlay.ui.KsLandscapeView;
import com.kt.coffee.cat.ktPlay.ui.KsLandscapeViewNew;
import com.kt.coffee.cat.ktPlay.ui.KsStandardVideoController;
import com.kt.coffee.cat.ktPlay.ui.component.KsErrorView;
import com.kt.coffee.cat.ktPlay.ui.component.KsGestureView;
import com.kt.coffee.cat.ktPlay.ui.component.KsIncompletionView;
import com.kt.coffee.cat.ktPlay.ui.component.KsPlayerSpeedView;
import com.kt.coffee.cat.ktPlay.ui.component.KsPrepareView;
import com.kt.coffee.cat.ktPlay.ui.component.KsTitleMoreView;
import com.kt.coffee.cat.ktPlay.ui.component.KsTitleView;
import com.kt.coffee.cat.ktPlay.ui.component.KsVodControlView;
import com.kt.coffee.cat.utils.PlayerVideoEntity;
import com.kt.coffee.cat.utils.Tool;

import java.io.IOException;

import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videoplayer.player.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private final static String TAG = "PlayerActivity";

    VideoView mVideoView;

    KsIncompletionViewNew ksIncompletionViewNew;

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
            PlayerVideoEntity playerVideoEntity = new Gson().fromJson(JSON,PlayerVideoEntity.class);

//            ksTitleMoreView.setTitle(playerVideoEntity.getVideoName());
//            Glide.with(this).load(playerVideoEntity.getVideoThumb()).into(thumb);
            mVideoView.setUrl(playerVideoEntity.getVideoUrl());

            mVideoView.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initView(){
        mVideoView = findViewById(R.id.player);

        KsStandardVideoController controller = new KsStandardVideoController(this);
        controller.setEnableOrientation(false);  // 设置是否自动旋转屏幕


        ksIncompletionViewNew = new KsIncompletionViewNew(this);
        controller.addControlComponent(ksIncompletionViewNew);
        ksIncompletionViewNew.setBack(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        prepareView = new KsPrepareView(this);
//        prepareView.setClickStart();
//        thumb = prepareView.findViewById(R.id.thumb);
////        Glide.with(this).load(THUMB).into(thumb);
//        controller.addControlComponent(prepareView);

        controller.addControlComponent(new CompleteView(this));
        controller.addControlComponent(new KsErrorView(this));//错误界面

        controller.addControlComponent(new KsGestureView(this));



        controller.addControlComponent(new KsLandscapeViewNew(this));

        mVideoView.setVideoController(controller);

    }






    @Override
    protected void onResume() {
        super.onResume();

        if (mVideoView != null){
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
