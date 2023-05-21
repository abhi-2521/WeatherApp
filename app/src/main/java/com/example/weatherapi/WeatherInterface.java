package com.example.weatherapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    String BASEURL="https://api.openweathermap.org/data/2.5/";
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @GET("weather")
    Call<Main> getreport(@Query("q") String city, @Query("appid")String apikey,@Query("units")String units);
}
