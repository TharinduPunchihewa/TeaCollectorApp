package com.teaproject.teacollectorapp.rest;

import com.teaproject.teacollectorapp.dto.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/api/users")
    Call<User> login (@Body User user);
}
