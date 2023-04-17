package com.kt.coffee.cat.Fragment.MainPagerFragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.kt.coffee.cat.Adapter.HomeFragmentPagerAdapter;
import com.kt.coffee.cat.Fragment.HomePagerFragment.HomeHFragment;
import com.kt.coffee.cat.utils.HomeFragmentPagerAndTabsBase;
import com.kt.coffee.cat.Fragment.NewLazyFragment;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.utils.Tool;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends NewLazyFragment {

    private static final String TAG = "HomeFragment";
    ViewPager home_vp;
    TabLayout home_tabs;
    AppBarLayout appBarLayout;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        appBarLayout = view.findViewById(R.id.appBarLayout);
        home_vp = view.findViewById(R.id.home_vp);
        home_tabs = view.findViewById(R.id.home_tabs);

    }
    List<HomeFragmentPagerAndTabsBase> homeFragmentPagerAndTabsBases = new ArrayList<>();
    @Override
    protected void initEvent() {
        super.initEvent();


        /*appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {


                if (verticalOffset != 0){
                    home_tabs.setBackgroundColor(getResources().getColor(R.color.white));
                }

                Log.i(TAG, "onOffsetChanged: "+verticalOffset);
            }
        });*/


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.i(TAG, "onOffsetChanged: "+verticalOffset);
            }
        });





        homeFragmentPagerAndTabsBases.add(new HomeFragmentPagerAndTabsBase(new HomeHFragment(),"首页"));
        homeFragmentPagerAndTabsBases.add(new HomeFragmentPagerAndTabsBase(new HomeHFragment(),"热播"));
        homeFragmentPagerAndTabsBases.add(new HomeFragmentPagerAndTabsBase(new HomeHFragment(),"电影"));
        homeFragmentPagerAndTabsBases.add(new HomeFragmentPagerAndTabsBase(new HomeHFragment(),"电视剧"));
        homeFragmentPagerAndTabsBases.add(new HomeFragmentPagerAndTabsBase(new HomeHFragment(),"动漫"));
        homeFragmentPagerAndTabsBases.add(new HomeFragmentPagerAndTabsBase(new HomeHFragment(),"番剧"));


        HomeFragmentPagerAdapter homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getActivity().getSupportFragmentManager(),homeFragmentPagerAndTabsBases);

        home_vp.setAdapter(homeFragmentPagerAdapter);
        home_vp.setOffscreenPageLimit(homeFragmentPagerAndTabsBases.size());
        home_tabs.setupWithViewPager(home_vp);

        home_tabs.setTabTextColors(getResources().getColor(R.color.lucencyGray_200),getResources().getColor(R.color.white));


    }




}