package com.starbucks.cmpe202.starbucksmobileapp.responseVo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Store implements Serializable{

    @SerializedName("storeName")
    String storeName;
    @SerializedName("storeAddress")
    String storeAddress;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }


}
