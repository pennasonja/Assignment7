package com.example.assignment7;
import com.google.gson.annotations.SerializedName;

public class Picture {


    @SerializedName("file")
    private String url;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("filter")
    private String filter;

    @SerializedName("tags")
    private String tags;

    @SerializedName("tagMode")
    private String tagMode;


    public Picture(String url, int width, int height, String filter, String tags, String tagMode) {
        this.url = url;
        this.width = width;
        this.height = height;
        this.filter = filter;
        this.tags = tags;
        this.tagMode = tagMode;
    }

    public String getTagMode() {
        return tagMode;
    }
    public void setTagMode(String tagMode) {
        this.tagMode = tagMode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    }

