package com.si.greenshare.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;

/**
 * Created by joao.silva.
 */

public class ServiceGenerator {

    public static final String BASE_URL = "10.0.2.2/";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();
}