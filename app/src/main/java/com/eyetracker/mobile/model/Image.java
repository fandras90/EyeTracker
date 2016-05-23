package com.eyetracker.mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fabia on 5/9/2016.
 */
public class Image implements Parcelable {

    @SerializedName("width")
    private Integer width = null;

    @SerializedName("height")
    private Integer height = null;

    @SerializedName("url")
    private String url = null;

    @SerializedName("data")
    private byte[] data = null;

    public Image() {
    }

    protected Image(Parcel in) {
        width = in.readInt();
        height = in.readInt();
        url = in.readString();
        data = in.createByteArray();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(url);
        dest.writeByteArray(data);
    }
}
