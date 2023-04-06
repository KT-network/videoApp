package com.kt.coffee.cat.ktPlay.ui;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public abstract class KsControlAbstract {

    private static final String TAG = "KsControlAbstract";
    private final AnimationSet animationSetHide = new AnimationSet(true);
    private final AnimationSet animationSetShow = new AnimationSet(true);

    public Context context;
    public View view;
    public float width = 350.0F;

    public KsControlAbstract(Context context) {
        this.context = context;

        this.view = LayoutInflater.from(context).inflate(this.getLayout(), null, true);
        this.view.setVisibility(View.GONE);

        this.view.findViewById(getBGID()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });

    }

    public abstract int getBGID();

    public abstract int getLayout();

    private float dip2px(float var1) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, var1, this.context.getResources().getDisplayMetrics());
    }


    public void initAnimation() {

        TranslateAnimation translateAnimation = new TranslateAnimation(this.dip2px(this.width), 0.0F, 0.0F, 0.0F);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0F, 1.0F);
        this.animationSetShow.addAnimation(translateAnimation);
        this.animationSetShow.addAnimation(alphaAnimation);
        this.animationSetShow.setDuration(300L);
        this.animationSetShow.setFillAfter(true);
        this.animationSetShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        TranslateAnimation translateAnimation_ = new TranslateAnimation(0.0F, this.dip2px(this.width), 0.0f, 0.0f);
        AlphaAnimation alphaAnimation_ = new AlphaAnimation(1.0f, 0.0f);
        this.animationSetHide.addAnimation(translateAnimation_);
        this.animationSetHide.addAnimation(alphaAnimation_);

        this.animationSetHide.setDuration(300L);
        this.animationSetShow.setFillAfter(true);
        this.animationSetHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    public void show() {
        this.view.clearAnimation();
        this.view.setAnimation(this.animationSetShow);
        this.animationSetShow.start();
    }


    public void hide() {
        this.view.clearAnimation();
        this.view.setAnimation(this.animationSetHide);
        this.animationSetHide.start();
    }

    public void setWidth(float var1) {
        this.width = var1;
    }
}
