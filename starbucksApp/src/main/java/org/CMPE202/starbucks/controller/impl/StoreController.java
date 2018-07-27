package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IStoreController;
import org.CMPE202.starbucks.model.Store;
import org.CMPE202.starbucks.responseVo.StoreReponseVo;
import org.CMPE202.starbucks.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= StarbucksConstants.STORE)
public class StoreController implements IStoreController{

    @Autowired
    private StoreServiceImpl storeService;

    @RequestMapping(value= StarbucksConstants.ALLSTORES, method= RequestMethod.GET)
    public @ResponseBody
    List<Store> getAllStores () {

        return storeService.getAllStores();

    }
}
