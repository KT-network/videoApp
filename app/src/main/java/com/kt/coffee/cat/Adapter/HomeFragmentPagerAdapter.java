package com.kt.coffee.cat.Adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kt.coffee.cat.utils.HomeFragmentPagerAndTabsBase;

import java.util.List;

/*
* HomeFragment页面的ViewPager适配器（以及TabLayout）
* */

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {


    private static final String TAG = "HomeFragmentPagerAdapter";
    List<HomeFragmentPagerAndTabsBase> homeFragmentPagerAndTabsBases;


    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm,List<HomeFragmentPagerAndTabsBase> homeFragmentPagerAndTabsBases){
        super(fm);
        this.homeFragmentPagerAndTabsBases = homeFragmentPagerAndTabsBases;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return homeFragmentPagerAndTabsBases.get(position).getFragment();
    }

    @SuppressLint("LongLogTag")
    @Override
    public int getCount() {

        return homeFragmentPagerAndTabsBases == null ? 0 : homeFragmentPagerAndTabsBases.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return homeFragmentPagerAndTabsBases.get(position).getTitle();

        //return super.getPageTitle(position);
    }
}
