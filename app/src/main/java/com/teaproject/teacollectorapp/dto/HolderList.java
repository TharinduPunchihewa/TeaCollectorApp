package com.teaproject.teacollectorapp.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolderList {


    @SerializedName("holder_id")
    @Expose
    private String holderId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
