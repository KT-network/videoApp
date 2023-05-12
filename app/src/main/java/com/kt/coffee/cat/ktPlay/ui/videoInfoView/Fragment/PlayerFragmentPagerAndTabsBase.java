package com.kt.coffee.cat.ktPlay.ui.videoInfoView.Fragment;

import androidx.fragment.app.Fragment;

/*
 * ViewPager的Fragment与TabLayout标题绑定 实体类
 * */
public class PlayerFragmentPagerAndTabsBase {

    private Fragment fragment;
    private String title;

    public PlayerFragmentPagerAndTabsBase(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getTitle() {
        return title;
    }
}
