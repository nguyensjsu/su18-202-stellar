package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.IStoreDao;
import org.CMPE202.starbucks.model.Store;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreDaoImpl implements IStoreDao {

    @Override
    public List<Store> viewStores() {

       List<Store> stores = new ArrayList<>();

       Store st = new Store("abc","def");
       Store st1 = new Store("ghi","jkl");


        stores.add(st);
        stores.add(st1);
        System.out.println("View Stores");
        return stores;
}
}
