package com.kt.coffee.cat.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

/*
* Fragment 懒加载基类
* */
public abstract class NewLazyFragment extends Fragment {
    private Context mContext;
    private boolean isFirstLoad = true; // 是否第一次加载

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(mContext).inflate(getContentViewId(), null);
        initView(view);
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initEvent();
            initData();
            isFirstLoad = false;
        }
    }

    /**
     * 设置布局资源id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 初始化视图
     *
     * @param view
     * findViewById
     */
    protected void initView(View view) {

    }

    /**
     * 初始化事件
     */
    protected void initEvent() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

}
