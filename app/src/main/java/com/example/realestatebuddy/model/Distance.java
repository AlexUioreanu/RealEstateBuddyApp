package com.example.realestatebuddy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Distance {
    @PrimaryKey
    int id;

    int distanceKm;

    public Distance(int id, int distanceKm) {
        this.id = id;
        this.distanceKm = distanceKm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistanceKm() {
        return distanceKm;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return id == distance.id && distanceKm == distance.distanceKm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distanceKm);
    }

    @Override
    public String toString() {
        return "Distance{" +
                "id=" + id +
                ", distanceKm=" + distanceKm +
                '}';
    }
}

