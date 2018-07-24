package org.CMPE202.starbucks.service.impl;


import org.CMPE202.starbucks.dao.impl.OrderDaoImpl;
import org.CMPE202.starbucks.service.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    private OrderDaoImpl orderDao;
}
