package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.Order;

import java.util.List;

public interface IOrderDao {

    public String placeOrder(Order order);

    public List<Order> viewOrders(Order order);
}
