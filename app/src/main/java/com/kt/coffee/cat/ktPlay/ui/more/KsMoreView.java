package com.kt.coffee.cat.ktPlay.ui.more;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.KsControlAbstract;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.KsMmkv;

import me.everything.android.ui.overscroll.OverScrollBounceEffectDecoratorBase;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;

public class KsMoreView extends KsControlAbstract implements IControlComponent {

    private Context mContext;
    private GridView mPlayFramesSizeGrid, mPlayCoreGrid, mPlayMethodGrid, mPlaySettingGrid;
    private KsMoreAdapter mPlayFramesSizeGridAdapter, mPlayerCoreGridAdapter, mPlayMethodGridAdapter, mPlaySettingGridAdapter;
    private SwitchCompat mPlayNowNetSpeedSwitch,mPlayAutoRotateSwitch;
    private ControlWrapper mControlWrapper;
    private ScrollView scrollView;

    public KsMoreView(Context context) {
        super(context);
        mContext = context;
        super.width = 250.0F;
        this.initAnimation();
        initView();
    }

    private void initView() {

        scrollView = view.findViewById(R.id.more_container_scroll);

        mPlayFramesSizeGrid = view.findViewById(R.id.play_more_play_frames_size_grid);
        //mPlayCoreGrid = view.findViewById(R.id.play_more_play_player_core_grid);
        mPlayMethodGrid = view.findViewById(R.id.play_more_play_method_grid);
        mPlaySettingGrid = view.findViewById(R.id.play_more_play_setting_grid);

        mPlayFramesSizeGridAdapter = new KsMoreAdapter(mContext, mContext.getResources().getStringArray(R.array.play_more_play_frames_size_array));
        //mPlayerCoreGridAdapter = new KsMoreAdapter(mContext, mContext.getResources().getStringArray(R.array.play_more_play_player_core_array));
        mPlayMethodGridAdapter = new KsMoreAdapter(mContext, mContext.getResources().getStringArray(R.array.play_more_play_method_array));
        mPlaySettingGridAdapter = new KsMoreAdapter(mContext, mContext.getResources().getStringArray(R.array.play_more_play_setting_array));

        mPlayFramesSizeGridAdapter.setDefaultSelectedItemIndex(KsMmkv.mv.decodeInt("playerFramesSize"));
        //mPlayerCoreGridAdapter.setDefaultSelectedItemIndex(KsMmkv.mv.decodeInt("playerCore"));
        mPlayMethodGridAdapter.setDefaultSelectedItemIndex(KsMmkv.mv.decodeInt("playerPlayMode"));
        mPlaySettingGridAdapter.setDefaultSelectedItemIndex(KsMmkv.mv.decodeInt("playerSetting"));

        mPlayFramesSizeGridAdapter.setmOnClickListener(mPlayFramesSizeOnClickListener);
        //mPlayerCoreGridAdapter.setmOnClickListener(mPlayCoreOnClickListener);
        mPlayMethodGridAdapter.setmOnClickListener(mPlayMethodOnClickListener);
        mPlaySettingGridAdapter.setmOnClickListener(mPlaySettingOnClickListener);

        mPlayFramesSizeGrid.setAdapter(mPlayFramesSizeGridAdapter);
        //mPlayCoreGrid.setAdapter(mPlayerCoreGridAdapter);
        mPlayMethodGrid.setAdapter(mPlayMethodGridAdapter);
        mPlaySettingGrid.setAdapter(mPlaySettingGridAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);



        mPlayNowNetSpeedSwitch = view.findViewById(R.id.play_more_now_net_speed);
        mPlayAutoRotateSwitch = view.findViewById(R.id.play_more_auto_rotate);
        mPlayNowNetSpeedSwitch.setChecked(KsMmkv.mv.decodeBool("playNowNetworkSpeed"));
        mPlayAutoRotateSwitch.setChecked(KsMmkv.mv.decodeBool("playerRotate"));

    }


    private ClickListener.OnClickListener mPlayFramesSizeOnClickListener = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {

            switch (i) {
                case 0:
                    mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);
                    break;
                case 1:
                    mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_16_9);
                    break;
                case 2:
                    mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_4_3);
                    break;
                case 3:
                    mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL);
                    break;
                case 4:
                    mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);
                    break;
                case 5:
                    mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
                    break;
            }

            KsMmkv.mv.encode("playerFramesSize", i);
        }
    };


    private ClickListener.OnClickListener mPlayCoreOnClickListener = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {
            KsMmkv.mv.encode("playerCore", i);
        }
    };

    private ClickListener.OnClickListener mPlayMethodOnClickListener = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {
            KsMmkv.mv.encode("playerPlayMode", i);
        }
    };

    private ClickListener.OnClickListener mPlaySettingOnClickListener = new ClickListener.OnClickListener() {
        @Override
        public void onClick(int i) {
            KsMmkv.mv.encode("playerSetting", i);
        }
    };


    @Override
    public int getBGID() {
        return R.id.landscape_more;
    }

    @Override
    public int getLayout() {
        return R.layout.ktplayer_layout_title_more_view;
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
    public void showMore() {
        mControlWrapper.hide();
        show();
    }

    public void hideMore() {
        hide();
    }

    public SwitchCompat getmPlayNowNetSpeedSwitch(){
        return mPlayNowNetSpeedSwitch;
    }

    public SwitchCompat getmPlayAutoRotateSwitch(){
        return mPlayAutoRotateSwitch;
    }



}
