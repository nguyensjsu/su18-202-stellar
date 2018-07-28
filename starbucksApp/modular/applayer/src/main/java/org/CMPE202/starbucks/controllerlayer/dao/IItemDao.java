package org.CMPE202.starbucks.controllerlayer.dao;


import org.CMPE202.starbucks.commondto.model.Item;

import java.util.List;

public interface IItemDao {

    public List<Item> viewItems();
}
