package com.example.realestatebuddy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class House implements Serializable {
    @PrimaryKey
    private int id;

    private String image;
    private int price;
    private int bedrooms;
    private int bathrooms;
    private int size;
    private String description;
    private String zip;
    private String city;
    private int latitude;
    private int longitude;
    private String createdDate;
    private boolean isFavorite;

    public House(int id, String image, int price, int bedrooms, int bathrooms, int size, String description, String zip, String city, int latitude, int longitude, String createdDate) {
        this.id = id;
        this.image = image;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.size = size;
        this.description = description;
        this.zip = zip;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdDate = createdDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public String getDescription() {
        return description;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getSize() {
        return size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id == house.id && price == house.price && bedrooms == house.bedrooms && bathrooms == house.bathrooms && size == house.size && latitude == house.latitude && longitude == house.longitude && isFavorite == house.isFavorite && Objects.equals(image, house.image) && Objects.equals(description, house.description) && Objects.equals(zip, house.zip) && Objects.equals(city, house.city) && Objects.equals(createdDate, house.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, price, bedrooms, bathrooms, size, description, zip, city, latitude, longitude, createdDate, isFavorite);
    }

    @Override
    public String toString() {
        return "House={" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", createdDate='" + createdDate + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
