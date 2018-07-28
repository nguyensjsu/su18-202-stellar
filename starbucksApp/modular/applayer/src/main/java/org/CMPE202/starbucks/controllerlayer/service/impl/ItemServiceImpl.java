package org.CMPE202.starbucks.controllerlayer.service.impl;

import org.CMPE202.starbucks.commondto.model.Item;
import org.CMPE202.starbucks.commondto.responseVo.ItemResponseVo;
import org.CMPE202.starbucks.controllerlayer.dao.impl.ItemDaoImpl;
import org.CMPE202.starbucks.controllerlayer.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemDaoImpl itemDao;

    public ItemResponseVo getAllItems(){
        List<Item>  itemList = itemDao.viewItems();

        ItemResponseVo itemResponseVo = new ItemResponseVo();
        itemResponseVo.getItems().addAll(itemList);

        return itemResponseVo;

    }


}
