package com.eyetracker.mobile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fabia on 5/9/2016.
 */
public class Image {

    @SerializedName("width")
    private Integer width = null;

    @SerializedName("height")
    private Integer height = null;

    @SerializedName("url")
    private String url = null;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
