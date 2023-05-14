package com.kt.coffee.cat.ktPlay.ui.AnthologyControl;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.KsControlAbstract;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.PlayerVideoEntity;

import java.util.List;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;

/*
* 选集列表
* */
public class KsAnthologyView extends KsControlAbstract implements IControlComponent {


    private ControlWrapper mControlWrapper;
    private KsAnthologyAdapter ksAnthologyAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public KsAnthologyView(Context context) {
        super(context);
        super.width = 250.0F;
        this.initAnimation();
        initList();
    }

    private void initList() {
        recyclerView = this.view.findViewById(R.id.play_select_RecyclerView);

        linearLayoutManager = new LinearLayoutManager(this.context);
        ksAnthologyAdapter = new KsAnthologyAdapter(this.context);


        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ksAnthologyAdapter);

    }

    public void setListData(List<PlayerVideoEntity.VideoUrlArray> data) {
        ksAnthologyAdapter.setData(data);
    }

    public void setListAddData() {

    }

    public void setListItemClick(ClickListener.OnClickListener onClickListener) {
        ksAnthologyAdapter.setOnClick(onClickListener);
    }


    @Override
    public int getBGID() {
        return R.id.landscape_select;
    }

    @Override
    public int getLayout() {
        return R.layout.ktplayer_layout_anthology_view;
    }

    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {
        this.mControlWrapper = controlWrapper;
    }

    @Nullable
    @Override
    public View getView() {
        return super.view;
    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

    }

    @Override
    public void onPlayStateChanged(int playState) {

    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (playerState == 10) {
            super.view.setVisibility(View.GONE);
        }
    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {

    }

    /*
     * 显示
     * */
    public void showAnthology(int position) {
        mControlWrapper.hide();
        show();
        changeNowPlaying(position);
    }


    public void changeNowPlaying(int position) {
        ksAnthologyAdapter.setNowSelectState(position);
    }

    /*
     * 设置默认选中
     * */
    public void setDefaultSelectedItemIndex(int position) {
        ksAnthologyAdapter.setDefaultSelectedItemIndex(position);
    }


    public void hideAnthology() {
        hide();
    }




}
