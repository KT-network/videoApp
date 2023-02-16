package com.kt.coffee.cat.utils;

import java.util.List;

public class HomeHTestDataEntity {

    private List<Compilations> compilations;

    public List<Compilations> getCompilations() {
        return compilations;
    }

    public void setCompilations(List<Compilations> compilations) {
        this.compilations = compilations;
    }

    public static class Compilations{

        private String title;
        private String id;

        private List<TestData> data;

        public List<TestData> getData() {
            return data;
        }

        public void setData(List<TestData> data) {
            this.data = data;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public static class TestData{
            private String name;
            private String imageUrl;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }


    }


}
