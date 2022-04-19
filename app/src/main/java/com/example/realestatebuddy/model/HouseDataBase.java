package com.example.realestatebuddy.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {House.class, MyLocation.class, Distance.class}, version = 6, exportSchema = false)
public abstract class HouseDataBase extends RoomDatabase {
    public final static String DATABASE_NAME = "database";
    private static HouseDataBase dataBase;

    public synchronized static HouseDataBase getInstance(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context.getApplicationContext(), HouseDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dataBase;
    }

    public abstract HouseDao getHouseDao();

    public abstract MyLocationDao getMyLocationDao();

    public abstract DistanceDao getMyDistance();
}
