package com.si.greenshare.communication;

import com.si.greenshare.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by joao.silva.
 */

public interface WSUser {

    public static final String BASE_URL = "http://10.0.2.2:8080/";

    @GET("auth/login")
    Call<User> authLogin(@Header("Authorization") String authHeader);
}
