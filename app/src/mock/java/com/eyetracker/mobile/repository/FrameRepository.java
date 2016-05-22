package com.eyetracker.mobile.repository;

import com.eyetracker.mobile.model.Coordinate;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fabia on 5/4/2016.
 */
public class FrameRepository implements IRepository<Frame> {

    List<Frame> frames = new ArrayList();

    public FrameRepository() {
        Frame f1 = new Frame();
        f1.setTitle("Title frame");
        f1.setLeftCoordinates(new Coordinate(0.3f, 25.23f));
        f1.setRightCoordinates(new Coordinate(342.23f, 123.32f));
        f1.setFilterType("Filter");
        f1.setCreatedOn(new Date());
        f1.setImage(new Image());
        f1.getImage().setUrl("http://www.geek.com/wp-content/uploads/2013/11/eye-track-header.jpg");
        f1.setId(0L);

        Frame f2 = new Frame();
        f2.setTitle("Another frame");
        f2.setLeftCoordinates(new Coordinate(0.3f, 25.23f));
        f2.setRightCoordinates(new Coordinate(342.23f, 123.32f));
        f2.setFilterType("OtherFilter");
        f2.setCreatedOn(new Date());
        f2.setImage(new Image());
        f2.getImage().setUrl("http://flaeyecenter.com/wp-content/uploads/2011/07/FeatureEye.jpg");
        f2.setId(1L);

        frames.add(f1);
        frames.add(f2);
    }

    @Override
    public void insert(Frame frame) {
        frames.add(frame);
    }

    @Override
    public void delete(Frame frame) {
        frames.remove(frame);
    }

    @Override
    public void update(Frame frame) {
        Frame fact = null;
        for (Frame f : frames) {
            if (f.getId() == frame.getId())
                fact = f;
        }
        fact.setTitle(frame.getTitle());
        fact.setFilterType(frame.getFilterType());
        fact.setLeftCoordinates(frame.getLeftCoordinates());
        fact.setRightCoordinates(frame.getRightCoordinates());
    }

    @Override
    public Frame getById(Long id) {
        for (Frame f : frames) {
            if (f.getId() == id)
                return f;
        }
        return null;
    }

    @Override
    public List<Frame> listAll() {
        return frames;
    }

}
