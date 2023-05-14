package com.kt.coffee.cat.utils;

import java.util.List;

public class PlayerVideoEntity {

    private String videoName;
    private String videoThumb;
    private String videoID;

    private List<VideoUrlArray> videoUrlArray;

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public List<VideoUrlArray> getVideoUrlArrays() {
        return videoUrlArray;
    }

    public void setVideoUrlArrays(List<VideoUrlArray> videoUrlArrays) {
        this.videoUrlArray = videoUrlArrays;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }


    public static class VideoUrlArray{

        private String name;
        private String videoUrl;


        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
