package com.eyetracker.mobile.repository;

import com.eyetracker.mobile.model.Frame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabia on 5/4/2016.
 */
public class FrameRepository implements IRepository<Frame> {
    
    @Override
    public void insert(Frame frame) {
        frame.save();
    }

    @Override
    public void delete(Frame frame) {
        Frame f = Frame.findById(Frame.class, frame.getId());
        f.delete();
    }

    @Override
    public void update(Frame frame) {
        Frame f = Frame.findById(Frame.class, frame.getId());
        f.setTitle(frame.getTitle());
        f.setFilterType(frame.getFilterType());
        f.setLeftCoordinates(frame.getLeftCoordinates());
        f.setRightCoordinates(frame.getRightCoordinates());
    }

    @Override
    public Frame getById(Long id) {
        return Frame.findById(Frame.class, id);
    }

    @Override
    public List<Frame> listAll() {
        return Frame.listAll(Frame.class);
    }

}
