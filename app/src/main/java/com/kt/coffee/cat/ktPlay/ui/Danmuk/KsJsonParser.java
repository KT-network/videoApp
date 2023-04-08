package com.kt.coffee.cat.ktPlay.ui.Danmuk;

import android.graphics.Color;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.kt.coffee.cat.utils.KsDanmukuBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.util.List;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.android.JSONSource;

public class KsJsonParser extends BaseDanmakuParser {
    @Override
    protected IDanmakus parse() {

        Danmakus danmakus = new Danmakus();


        if (mDataSource != null && mDataSource instanceof KsJsonSource) {
            KsJsonSource jsonSource = (KsJsonSource) mDataSource;
            return doParse(jsonSource.data());
        }

        return new Danmakus();
    }


    private Danmakus doParse(Reader danmakuListData) {
        Danmakus danmakus = new Danmakus();

        Gson gson = new Gson();

        KsDanmukuBase ksDanmukuBase = gson.fromJson(danmakuListData, new TypeToken<KsDanmukuBase>() {
        }.getType());

        List<KsDanmukuBase.DanmuData> danmu = ksDanmukuBase.getDanmu();

        if (ksDanmukuBase != null && danmu.size() > 0) {

            for (int i = 0; i < danmu.size(); i++) {
                KsDanmukuBase.DanmuData danmuData = danmu.get(i);

                int pattern = danmuData.getPattern();
                long playTime = (long) (Float.parseFloat(danmuData.getPlayTime()) * 1000);
                int color = Integer.parseInt(danmuData.getColor());
                float textSize =  Float.parseFloat(danmuData.getTextSize());
                String text = danmuData.getText();


                BaseDanmaku item = mContext.mDanmakuFactory.createDanmaku(pattern, mContext);
                item.setTime(playTime);
                item.textSize = textSize * (mDispDensity - 0.6f);
                item.textColor = color;
                item.textShadowColor = color <= Color.BLACK ? Color.WHITE : Color.BLACK;
                item.index = i;
                item.flags = mContext.mGlobalFlagValues;
                item.setTimer(mTimer);
                item.text = text;

                danmakus.addItem(item);
            }

        }


        return danmakus;




        /*if (danmakuListData == null || danmakuListData.length() == 0){
            return danmakus;
        }
        danmakus = _parse(danmakuListData,danmakus);
        return danmakus;*/

    }


    private Danmakus _parse(JSONArray jsonArray, Danmakus danmakus) {
        if (danmakus == null) {
            danmakus = new Danmakus();
        }
        if (jsonArray == null || jsonArray.length() == 0) {
            return danmakus;
        }

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                long playTime = (long) (Float.parseFloat(jsonObject.getString("playTime")) * 1000);
                int color = Integer.parseInt(jsonObject.getString("color"));
                int pattern = jsonObject.getInt("pattern");
                float textSize = Float.parseFloat(jsonObject.getString("textSize"));

                String text = jsonObject.getString("text");

                BaseDanmaku item = mContext.mDanmakuFactory.createDanmaku(pattern, mContext);
                if (item != null) {

                    item.setTime(playTime);
                    item.textSize = textSize * (mDispDensity - 0.6f);
                    item.textColor = color;
                    item.textShadowColor = color <= Color.BLACK ? Color.WHITE : Color.BLACK;
                    item.index = i;
                    item.flags = mContext.mGlobalFlagValues;
                    item.setTimer(mTimer);
                    item.text = text;
                    danmakus.addItem(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return danmakus;


    }


}
