package org.CMPE202.starbucks.controllerlayer.dao;


import org.CMPE202.starbucks.commondto.model.Order;

import java.util.List;

public interface IOrderDao {

    public String placeOrder(Order order);

    public List<Order> viewOrders(Order order);
}
