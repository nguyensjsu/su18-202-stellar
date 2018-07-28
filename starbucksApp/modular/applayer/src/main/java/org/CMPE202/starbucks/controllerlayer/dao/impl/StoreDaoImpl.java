package org.CMPE202.starbucks.controllerlayer.dao.impl;

import org.CMPE202.starbucks.commondto.model.Store;
import org.CMPE202.starbucks.controllerlayer.dao.IStoreDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreDaoImpl implements IStoreDao {

    @Override
    public List<Store> viewStores() {

       List<Store> stores = new ArrayList<>();

       Store st = new Store("Starbucks Mathilda Ave","Sunnyvale Square Shopping Center");
       Store st1 = new Store("Starbucks Washington Ave","231 W Washington Ave");
       Store st2 = new Store("Starbucks Blvd","5, 3113 Mission College Blvd");


        stores.add(st);
        stores.add(st1);
        stores.add(st2);
        System.out.println("View Stores");
        return stores;


    }

}
