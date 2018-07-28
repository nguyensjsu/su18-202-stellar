package org.CMPE202.starbucks.controllerlayer.service.impl;


import org.CMPE202.starbucks.commondto.model.Store;
import org.CMPE202.starbucks.controllerlayer.dao.impl.StoreDaoImpl;
import org.CMPE202.starbucks.controllerlayer.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private StoreDaoImpl storeDao;

    public List<Store> getAllStores() {
        List<Store> storeList =  storeDao.viewStores();

        /*StoreReponseVo storeReponseVo = new StoreReponseVo();
        storeReponseVo.getStores().addAll(storeList);*/

        return storeList;
    }
}
