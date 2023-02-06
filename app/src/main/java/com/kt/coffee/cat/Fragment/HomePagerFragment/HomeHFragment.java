package com.kt.coffee.cat.Fragment.HomePagerFragment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kt.coffee.cat.Fragment.NewLazyFragment;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.utils.HomeBannerEntity;
import com.kt.coffee.cat.utils.Tool;
import com.stx.xhb.androidx.XBanner;

import java.io.IOException;


public class HomeHFragment extends NewLazyFragment {



    private final static String TAG = "HomeHFragment";

    XBanner xBanner;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        xBanner = view.findViewById(R.id.xBanner);

    }


    @Override
    protected void initEvent() {
        super.initEvent();

        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                HomeBannerEntity.FeedBase entryBean = (HomeBannerEntity.FeedBase) model;
                String url = (String) entryBean.getXBannerUrl();

                Glide.with(getActivity()).load(url).into((ImageView) view);

            }
        });

        xBanner.setBannerPlaceholderImg(R.mipmap.ic_launcher_round, ImageView.ScaleType.CENTER_CROP);


    }

    @Override
    protected void initData() {
        super.initData();


        try {

            String JSON = Tool.inputStreamToString(getResources().getAssets().open("Banner.json"));


            HomeBannerEntity homeBannerEntity = new Gson().fromJson(JSON, HomeBannerEntity.class);


            xBanner.setAutoPlayAble(true);
            xBanner.setBannerData(homeBannerEntity.getList());


        }catch (IOException e) {
            e.printStackTrace();
        }







    }
}
