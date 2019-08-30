package com.teaproject.teacollectorapp.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolderResponse {

    @SerializedName("records")
    @Expose
    private List<HolderList> holderLists;

    public List<HolderList> getHolderLists() {
        return holderLists;
    }

    public void setHolderLists(List<HolderList> holderLists) {
        this.holderLists = holderLists;
    }
}
