package com.eyetracker.mobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabia on 5/9/2016.
 */
public class Frames {

    @SerializedName("offset")
    private Integer offset = null;

    @SerializedName("limit")
    private Integer limit = null;

    @SerializedName("count")
    private Integer count = null;

    @SerializedName("frames")
    private List<Frame> frames = new ArrayList<Frame>();

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void setFrames(List<Frame> frames) {
        this.frames = frames;
    }
}
