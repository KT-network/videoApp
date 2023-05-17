package com.kt.coffee.cat.ktPlay.ui.videoInfoView.Fragment;

import android.content.Context;
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
    private Context mContext;
    ExpandableLayout titleExpandable;
    TextView title,videoLabel;
    RecyclerView recyclerView;
    KsIncompletionVideoInfoAdapter incompletionVideoInfoAdapter;


    public VideoInfoFragment(Context context){
        this.mContext = context;
        incompletionVideoInfoAdapter = new KsIncompletionVideoInfoAdapter(context);

    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_player_info;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        titleExpandable = view.findViewById(R.id.play_info_expandable_title);
        title = titleExpandable.parentLayout.findViewById(R.id.expandable_parent_play_title);

        videoLabel = titleExpandable.parentLayout.findViewById(R.id.expandable_parent_play_label);

        recyclerView = view.findViewById(R.id.play_info_recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(incompletionVideoInfoAdapter);

        title.setText(titleStr);
        videoLabel.setText(getContext().getText(R.string.play_info_label));



    }

    @Override
    protected void initData() {
        super.initData();

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

    // 设置剧集数据
    public void setListData(List<PlayerVideoEntity.VideoUrlArray> data) {
        incompletionVideoInfoAdapter.setData(data);
    }

    // 设置剧集上次播放的位置(设置默认选中)
    public void setDefaultSelectedItemIndex(int position) {
        incompletionVideoInfoAdapter.setDefaultSelectedItemIndex(position);
    }

    public void setListItemClick(ClickListener.OnClickListener onClickListener) {
        incompletionVideoInfoAdapter.setOnClick(onClickListener);
    }

    private String titleStr;
    public void setTitle(String s){

        if (title != null){
            title.setText(s);
            return;
        }
        this.titleStr = s;
        Log.i(TAG, "setTitle: ");

    }


}
