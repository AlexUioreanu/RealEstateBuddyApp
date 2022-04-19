package com.example.realestatebuddy.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addHome(House house);

    @Update
    void updateHome(House house);

    @Delete
    void deleteHome(House house);

    @Query("SELECT * FROM House WHERE isFavorite = 1 ORDER BY price")
    List<House> getAllFavoriteHouses();

    @Query("DELETE FROM House WHERE id = :locationId")
    void deleteHouseById(long locationId);

    @Query("UPDATE House SET isFavorite = 1 WHERE id = :locationId")
    void setFavorite(long locationId);

    @Query("UPDATE House SET isFavorite = 0 WHERE id = :locationId")
    void removeFavorite(long locationId);

    @Query("SELECT * FROM House ORDER BY price ")
    List<House> getAllHouse();

    @Query("SELECT * FROM House WHERE id = :locationId")
    House getHouse(long locationId);

    @Query("SELECT * FROM House WHERE city LIKE '%' || :city || '%'")
    List<House> findByCityOrZip(String city);
}
