package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.Order;

public interface IOrderDao {

    public String placeOrder(Order order);
}
