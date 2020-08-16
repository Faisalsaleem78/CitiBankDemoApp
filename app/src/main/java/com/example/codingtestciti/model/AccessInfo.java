package com.example.codingtestciti.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccessInfo {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public class Item {
        @SerializedName("volumeInfo")
        @Expose
        private VolumeInfo volumeInfo;

        public VolumeInfo getVolumeInfo() {
            return volumeInfo;
        }
    }

    public class ImageLinks {
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;

        public String getThumbnail() {
            return thumbnail;
        }
    }

    public class VolumeInfo {
        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("publishedDate")
        @Expose
        private String publishedDate;

        @SerializedName("imageLinks")
        @Expose
        private ImageLinks imageLinks;

        @SerializedName("averageRating")
        @Expose
        private Double averageRating;

        public String getTitle() {
            return title;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public ImageLinks getImageLinks() {
            return imageLinks;
        }

        public Double getAverageRating() {
            return averageRating;
        }

    }
}