package com.kt.coffee.cat.ktPlay.ui.videoInfoView.Adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.kt.coffee.cat.ktPlay.ui.videoInfoView.Fragment.PlayerFragmentPagerAndTabsBase;

import java.util.List;

/*
* HomeFragment页面的ViewPager适配器（以及TabLayout）
* */

public class PlayerFragmentPagerAdapter extends FragmentStatePagerAdapter {


    private static final String TAG = "HomeFragmentPagerAdapter";
    List<PlayerFragmentPagerAndTabsBase> playerFragmentPagerAndTabsBases;


    public PlayerFragmentPagerAdapter(@NonNull FragmentManager fm, List<PlayerFragmentPagerAndTabsBase> playerFragmentPagerAndTabsBases){
        super(fm);
        this.playerFragmentPagerAndTabsBases = playerFragmentPagerAndTabsBases;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return playerFragmentPagerAndTabsBases.get(position).getFragment();
    }

    @SuppressLint("LongLogTag")
    @Override
    public int getCount() {

        return playerFragmentPagerAndTabsBases == null ? 0 : playerFragmentPagerAndTabsBases.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return playerFragmentPagerAndTabsBases.get(position).getTitle();

        //return super.getPageTitle(position);
    }
}
