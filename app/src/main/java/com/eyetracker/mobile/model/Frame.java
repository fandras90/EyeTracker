package com.eyetracker.mobile.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by fabia on 4/20/2016.
 */
public class Frame extends SugarRecord {

    private Date createdOn;
    private String title;
    private String filterType;
    private Image image;
    private Coordinate leftCoordinates;
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

}
