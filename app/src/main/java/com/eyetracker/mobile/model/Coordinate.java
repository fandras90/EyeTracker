package com.eyetracker.mobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by fabia on 4/25/2016.
 */
public class Coordinate extends SugarRecord implements Parcelable {

    private float xCoord;
    private float yCoord;

    public Coordinate() {

    }

    public Coordinate(float xCoord, float yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    protected Coordinate(Parcel in) {
        xCoord = in.readFloat();
        yCoord = in.readFloat();
    }

    public static final Creator<Coordinate> CREATOR = new Creator<Coordinate>() {
        @Override
        public Coordinate createFromParcel(Parcel in) {
            return new Coordinate(in);
        }

        @Override
        public Coordinate[] newArray(int size) {
            return new Coordinate[size];
        }
    };

    public float getxCoord() {
        return xCoord;
    }

    public void setxCoord(float xCoord) {
        this.xCoord = xCoord;
    }

    public float getyCoord() {
        return yCoord;
    }

    public void setyCoord(float yCoord) {
        this.yCoord = yCoord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(xCoord);
        dest.writeFloat(yCoord);
    }
}
