package org.CMPE202.starbucks.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Store {

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

    String storeName;
    String storeAddress;

    public Store(String name, String address){
        this.storeName=name;
        this.storeAddress = address;
    }
}
