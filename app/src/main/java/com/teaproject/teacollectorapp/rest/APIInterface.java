package com.teaproject.teacollectorapp.rest;

import com.teaproject.teacollectorapp.dto.User;
import com.teaproject.teacollectorapp.dto.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("curd/read_login_details.php")
    Call<UserResponse> login (@Query("username") String username, @Query("password") String password);
}
