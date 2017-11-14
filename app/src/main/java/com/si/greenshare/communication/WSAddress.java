package com.si.greenshare.communication;

import com.si.greenshare.pojo.City;
import com.si.greenshare.pojo.Country;
import com.si.greenshare.pojo.State;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import java.util.List;

/**
 * Created by joao.silva.
 */

public interface WSAddress {
    public static final String BASE_URL = "http://10.0.2.2:8080/";

    @GET("country/")
    Call<List<Country>> findAllCountry(@Header("Authorization") String authHeader);

    @GET("state/country/{id}")
    Call<List<State>> findAllStateByCountry(@Header("Authorization") String authHeader, @Path("Id") Long id);

    @GET("city/state/{id}")
    Call<List<City>> findAllCityByState(@Header("Authorization") String authHeader, @Path("Id") Long id);
}

