package com.example.realestatebuddy.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface MyLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCurrentLocation(MyLocation myLocation);

    @Query("SELECT * FROM MyLocation WHERE id = :id")
    MyLocation getLocation(long id);

}
