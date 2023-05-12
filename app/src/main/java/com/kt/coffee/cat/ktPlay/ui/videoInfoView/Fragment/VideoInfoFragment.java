package com.kt.coffee.cat.ktPlay.ui.videoInfoView.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kt.coffee.cat.Fragment.NewLazyFragment;
import com.kt.coffee.cat.R;
import com.kt.coffee.cat.ktPlay.ui.videoInfoView.Adapter.KsIncompletionVideoInfoAdapter;
import com.kt.coffee.cat.mInterface.ClickListener;
import com.kt.coffee.cat.utils.PlayerVideoEntity;
import com.skydoves.expandablelayout.ExpandableLayout;

import java.util.List;

/*
* 小屏播放状态下（默认播放状态下）显示的视频信息及剧集列表
* */
public class VideoInfoFragment extends NewLazyFragment {

    private static final String TAG = "VideoInfoFragment";
    ExpandableLayout titleExpandable;
    RecyclerView recyclerView;
    KsIncompletionVideoInfoAdapter incompletionVideoInfoAdapter;


    public VideoInfoFragment(){
        incompletionVideoInfoAdapter = new KsIncompletionVideoInfoAdapter(getContext());
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_player_info;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        titleExpandable = view.findViewById(R.id.play_info_expandable_title);
        recyclerView = view.findViewById(R.id.play_info_recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(incompletionVideoInfoAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        TextView title = titleExpandable.parentLayout.findViewById(R.id.expandable_parent_play_title);


    }

    @Override
    protected void initEvent() {
        super.initEvent();


        titleExpandable.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleExpandable.toggleLayout();
            }
        });



    }

    public void addListData(){

    }

    public void setListData(List<PlayerVideoEntity.VideoUrlArray> data) {
        incompletionVideoInfoAdapter.setData(data);
    }

    public void setListItemClick(ClickListener.OnClickListener onClickListener) {
        incompletionVideoInfoAdapter.setOnClick(onClickListener);
    }


}
