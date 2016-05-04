package com.eyetracker.mobile.model;

import com.orm.SugarRecord;

/**
 * Created by fabia on 4/25/2016.
 */
public class Coordinate extends SugarRecord {

    private float xCoord;
    private float yCoord;

    public Coordinate() {

    }

    public Coordinate(float xCoord, float yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

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

}
