package org.CMPE202.starbucks.controller.impl;

import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IItemController;
import org.CMPE202.starbucks.responseVo.ItemResponseVo;
import org.CMPE202.starbucks.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= StarbucksConstants.ITEM)
public class ItemController implements IItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @RequestMapping(value= StarbucksConstants.ALL_ITEMS, method= RequestMethod.GET)
    public @ResponseBody
    ItemResponseVo getAllItems(){
        return itemService.getAllItems();
    }


}
