package com.eyetracker.mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by fabia on 4/20/2016.
 */
public class Frame extends SugarRecord implements Parcelable {

    @SerializedName("createdOn")
    private Date createdOn;

    @SerializedName("title")
    private String title;

    @SerializedName("filterType")
    private String filterType;

    @SerializedName("image")
    private Image image;

    @SerializedName("leftCoordinates")
    private Coordinate leftCoordinates;

    @SerializedName("rightCoordinates")
    private Coordinate rightCoordinates;

    public Frame() {
    }

    public Frame(Date createdOn, String title, String filterType, Coordinate leftCoordinates, Coordinate rightCoordinates) {
        this.createdOn = createdOn;
        this.title = title;
        this.filterType = filterType;
        this.leftCoordinates = leftCoordinates;
        this.rightCoordinates = rightCoordinates;
    }

    protected Frame(Parcel in) {
        createdOn = new Date(in.readLong());
        title = in.readString();
        filterType = in.readString();
        image = in.readParcelable(Image.class.getClassLoader());
        leftCoordinates = in.readParcelable(Coordinate.class.getClassLoader());
        rightCoordinates = in.readParcelable(Coordinate.class.getClassLoader());
    }

    public static final Creator<Frame> CREATOR = new Creator<Frame>() {
        @Override
        public Frame createFromParcel(Parcel in) {
            return new Frame(in);
        }

        @Override
        public Frame[] newArray(int size) {
            return new Frame[size];
        }
    };

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Coordinate getLeftCoordinates() {
        return leftCoordinates;
    }

    public void setLeftCoordinates(Coordinate leftCoordinates) {
        this.leftCoordinates = leftCoordinates;
    }

    public Coordinate getRightCoordinates() {
        return rightCoordinates;
    }

    public void setRightCoordinates(Coordinate rightCoordinates) {
        this.rightCoordinates = rightCoordinates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(createdOn.getTime());
        dest.writeString(title);
        dest.writeString(filterType);
        dest.writeParcelable(image, flags);
        dest.writeParcelable(leftCoordinates, flags);
        dest.writeParcelable(rightCoordinates, flags);
    }
}
