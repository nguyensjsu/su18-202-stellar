package org.CMPE202.starbucks.commondto.responseVo;


import org.CMPE202.starbucks.commondto.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemResponseVo {

    List<Item> items = new ArrayList<>();

    @Override
    public String toString() {
        return "ItemResponseVo{" +
                "items=" + items +
                '}';
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> item) {
        this.items = item;
    }


}
