package com.teaproject.teacollectorapp.dto;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
