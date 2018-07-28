package org.CMPE202.starbucks.controllerlayer.controller.impl;

import org.CMPE202.starbucks.commondto.constant.StarbucksConstants;
import org.CMPE202.starbucks.commondto.responseVo.ItemResponseVo;
import org.CMPE202.starbucks.controllerlayer.controller.IItemController;
import org.CMPE202.starbucks.controllerlayer.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
