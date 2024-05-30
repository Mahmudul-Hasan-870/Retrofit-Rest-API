package com.example.retrofit_rest_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("users")
    Call<List<User>> getUsers();
}
