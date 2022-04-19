package com.example.realestatebuddy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;


@Entity
public class MyLocation {

    @PrimaryKey
    private int id;

    private double lat;

    private double lon;

    public MyLocation(int id, double lat, double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLocation that = (MyLocation) o;
        return id == that.id && Double.compare(that.lat, lat) == 0 && Double.compare(that.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }

    @Override
    public String toString() {
        return "MyLocation{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
