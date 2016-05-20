package com.eyetracker.mobile.repository;

import java.util.List;

/**
 * Created by fabia on 5/4/2016.
 */
public interface IRepository<Item> {

    void insert(Item item);
    void delete(Item item);
    void update(Item item);
    Item getById(Long id);
    List<Item> listAll();

}
