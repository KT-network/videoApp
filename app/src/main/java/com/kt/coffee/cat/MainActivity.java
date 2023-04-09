package com.kt.coffee.cat;


import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kt.coffee.cat.Fragment.MainPagerFragment.CollectFragment;
import com.kt.coffee.cat.Fragment.MainPagerFragment.HomeFragment;
import com.kt.coffee.cat.Fragment.MainPagerFragment.InfoFragment;
import com.kt.coffee.cat.Fragment.MainPagerFragment.SelectFragment;
import com.tencent.mmkv.MMKV;

import java.io.IOException;

import nl.joery.animatedbottombar.AnimatedBottomBar;
import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.callback.Function;
import rxhttp.wrapper.param.RxHttp;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final MMKV mv = MMKV.defaultMMKV();

    FrameLayout frameLayout;
    AnimatedBottomBar bottom_bar;



    HomeFragment homeFragment;
    CollectFragment collectFragment;
    SelectFragment selectFragment;
    InfoFragment infoFragment;



    final FragmentManager supportFragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        StatusBarKt.immersive(this);

//        Log.i(TAG, "onCreate: 状态栏高度："+ Tool.getStatusBarByResId(getApplicationContext()));

        initView();

        initEvent();

        VideoViewManager.setConfig(VideoViewConfig.newBuilder().setPlayerFactory(IjkPlayerFactory.create()).build());


        bottom_bar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {

                Log.i(TAG, "onTabSelected 当前点击的tag: i1-> " + i1);
                TabTOFragment(i1);

            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {
                Log.i(TAG, "onTabReselected: " + tab.getTitle());

            }
        });








    }

    private void initView() {

        frameLayout = findViewById(R.id.myFragment);

        bottom_bar = findViewById(R.id.bottom_bar);
        bottom_bar.setSelectedTabType(AnimatedBottomBar.TabType.TEXT);
        bottom_bar.setTabColor(getResources().getColor(R.color.ThemeColor_200));

    }

    private void initEvent() {
        homeFragment = new HomeFragment();
        collectFragment = new CollectFragment();
        selectFragment = new SelectFragment();
        infoFragment = new InfoFragment();

        MyFragmentManager();
    }



    private void MyFragmentManager() {

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);

        fragmentTransaction.add(R.id.myFragment, homeFragment);
        fragmentTransaction.add(R.id.myFragment, collectFragment);
        fragmentTransaction.add(R.id.myFragment, infoFragment);
        fragmentTransaction.add(R.id.myFragment, selectFragment);

        fragmentTransaction.show(homeFragment);
        fragmentTransaction.commit();

    }


    private void TabTOFragment(int index) {

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);

        switch (index) {
            case 0:
                fragmentTransaction.show(homeFragment);

                /*if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.myFragment, homeFragment);
                } else {
                    fragmentTransaction.show(homeFragment);
                }*/
                break;
            case 1:
                fragmentTransaction.show(selectFragment);
                /*if (selectFragment == null) {
                    selectFragment = new SelectFragment();
                    fragmentTransaction.add(R.id.myFragment, selectFragment);
                } else {
                    fragmentTransaction.show(selectFragment);
                }*/
                break;
            case 2:
                fragmentTransaction.show(collectFragment);
                /*if (collectFragment == null) {
                    collectFragment = new CollectFragment();
                    fragmentTransaction.add(R.id.myFragment, collectFragment);
                } else {
                    fragmentTransaction.show(collectFragment);
                }*/

                break;
            case 3:
                fragmentTransaction.show(infoFragment);
                /*if (infoFragment == null) {
                    infoFragment = new InfoFragment();
                    fragmentTransaction.add(R.id.myFragment, infoFragment);
                } else {
                    fragmentTransaction.show(infoFragment);
                }*/

                break;
        }

        fragmentTransaction.commit();

    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (selectFragment != null) {
            transaction.hide(selectFragment);
        }
        if (collectFragment != null) {
            transaction.hide(collectFragment);
        }
        if (infoFragment != null) {
            transaction.hide(infoFragment);
        }
    }


}