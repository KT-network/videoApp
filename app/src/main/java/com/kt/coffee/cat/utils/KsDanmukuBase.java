package com.kt.coffee.cat.utils;

import java.util.List;

public class KsDanmukuBase {

    private int code;
    private List<DanmuData> danmu;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DanmuData> getDanmu() {
        return danmu;
    }

    public void setDanmu(List<DanmuData> danmu) {
        this.danmu = danmu;
    }


    public static class DanmuData{
        private String playTime;
        private String color;
        private int pattern;
        private String textSize;
        private String text;


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTextSize() {
            return textSize;
        }

        public void setTextSize(String textSize) {
            this.textSize = textSize;
        }

        public int getPattern() {
            return pattern;
        }

        public void setPattern(int pattern) {
            this.pattern = pattern;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPlayTime() {
            return playTime;
        }

        public void setPlayTime(String playTime) {
            this.playTime = playTime;
        }
    }

}
