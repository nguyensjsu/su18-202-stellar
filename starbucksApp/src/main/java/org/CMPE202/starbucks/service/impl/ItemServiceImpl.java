package org.CMPE202.starbucks.service.impl;

import org.CMPE202.starbucks.dao.IItemDao;
import org.CMPE202.starbucks.dao.impl.ItemDaoImpl;
import org.CMPE202.starbucks.model.Item;
import org.CMPE202.starbucks.responseVo.ItemResponseVo;
import org.CMPE202.starbucks.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService{

    @Autowired
    private ItemDaoImpl itemDao;

    public ItemResponseVo getAllItems(){
        List<Item>  itemList = itemDao.viewItems();

        ItemResponseVo itemResponseVo = new ItemResponseVo();
        itemResponseVo.getItems().addAll(itemList);

        return itemResponseVo;

    }


}
