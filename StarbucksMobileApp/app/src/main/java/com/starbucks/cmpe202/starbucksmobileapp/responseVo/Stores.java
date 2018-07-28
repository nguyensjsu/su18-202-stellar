package com.starbucks.cmpe202.starbucksmobileapp.responseVo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Stores implements Serializable{
    public List<Store> getStore() {
        return store;
    }

    public void setStore(List<Store> store) {
        this.store = store;
    }

    @SerializedName("stores")
    List<Store> store;
}
