package com.example.realestatebuddy.retrofit;

import com.example.realestatebuddy.model.House;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HouseApi {

    @GET("house")
    Call<List<House>> getAllLocations(@Header("Access-Key") String key);
}
