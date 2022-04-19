package com.example.realestatebuddy.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DistanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDistance(Distance distance);

    @Query("SELECT * FROM Distance WHERE id= :distanceId")
    Distance getDistance(int distanceId);

}
