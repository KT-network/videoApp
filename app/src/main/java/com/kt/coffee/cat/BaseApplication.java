package com.kt.coffee.cat;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";
    @Override
    public void onCreate() {
        super.onCreate();

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



        }




    }
}
