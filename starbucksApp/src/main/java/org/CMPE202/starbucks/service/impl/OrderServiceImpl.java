package org.CMPE202.starbucks.service.impl;


import org.CMPE202.starbucks.dao.impl.CartDaoImpl;
import org.CMPE202.starbucks.dao.impl.OrderDaoImpl;
import org.CMPE202.starbucks.model.Card;
import org.CMPE202.starbucks.model.Order;
import org.CMPE202.starbucks.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDaoImpl orderDao;

    public List<Order> viewAllOrders(Order order) {

        List<Order> orderList = orderDao.viewOrders(order);
        return orderList;
    }
}
