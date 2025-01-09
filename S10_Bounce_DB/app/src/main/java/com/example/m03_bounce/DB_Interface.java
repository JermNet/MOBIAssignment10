package com.example.m03_bounce;

import java.util.List;

public interface DB_Interface {

    int count();

    void save(DataModel dataModel);

    List<DataModel> findAll();

    void wipeDatabase();

    DataModel findByName(String name);

    DataModel findByX(float x);

    DataModel findByY(float y);

    DataModel findByDX(float dx);

    DataModel findByDY(float dy);

    DataModel findByColor(int color);

    String getNameById(Long id);

}
