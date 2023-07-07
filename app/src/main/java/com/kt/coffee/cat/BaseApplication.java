package com.kt.coffee.cat;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";
    @Override
    public void onCreate() {
        super.onCreate();

        // 设置播放器内核
        VideoViewManager.setConfig(
                VideoViewConfig
                        .newBuilder()
                        .setPlayerFactory(
                                IjkPlayerFactory.create()
                        )
                        .build()
        );


        // 初始化mmkv
        MMKV.initialize(this);
        MMKV mv = MMKV.defaultMMKV();


        // 判断默认配置文件是否存在（判断是不是第一此启动）
        if (!mv.decodeBool("config")){
            mv.encode("config",true);

            // 弹幕默认开启
            mv.encode("danmuState",true);

            // 播放页默认不自动旋转
            mv.encode("playerRotate",false);

            // 默认横屏状态下显示实时网速
            mv.encode("playNowNetworkSpeed",true);

            // 默认播放内核
            mv.encode("playerCore",0);
            // 默认画面尺寸
            mv.encode("playerFramesSize",0);
            // 默认播放方式
            mv.encode("playerPlayMode",0);
            // 默认播放设置
            mv.encode("playerSetting",0);

        }



        // RXHttp 初始化
        RxHttpPlugins.init(new OkHttpClient())
                .setDebug(BuildConfig.DEBUG,true,2);



    }


}
