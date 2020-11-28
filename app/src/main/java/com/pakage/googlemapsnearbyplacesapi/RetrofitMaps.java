package com.pakage.googlemapsnearbyplacesapi;

import com.pakage.googlemapsnearbyplacesapi.POJO.Example;
import com.pakage.googlemapsnearbyplacesapi.POJO.POJO.ExampleInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by navneet on 17/7/16.
 */
public interface RetrofitMaps {


    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */
    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY


    @GET("api/place/textsearch/json?sensor=true&key=AIzaSyADP1Z54-Y3M8S3nGlMvqVWmKNroHzhhAk")
    Call<Example> getNearbyPlaces(@Query("query") String type, @Query("location") String location, @Query("radius") int radius);

    @GET("api/place/details/json?fields=geometry,photo,name,rating,formatted_phone_number,formatted_address,opening_hours,website,rating,review&key=AIzaSyADP1Z54-Y3M8S3nGlMvqVWmKNroHzhhAk")
    Call<ExampleInfo> getDetails(@Query("place_id") String placesId);






}
