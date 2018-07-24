package org.CMPE202.starbucks.responseVo;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.ws.developer.Serialization;
import org.CMPE202.starbucks.model.Store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreReponseVo{


    List<Store> stores = new ArrayList<>();

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "StoreReponseVo{" +
                "stores=" + stores +
                '}';
    }
}
