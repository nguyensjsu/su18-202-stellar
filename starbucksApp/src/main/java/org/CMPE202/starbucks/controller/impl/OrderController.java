package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IOrderController;
import org.CMPE202.starbucks.model.Card;
import org.CMPE202.starbucks.model.Order;
import org.CMPE202.starbucks.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= StarbucksConstants.ORDER)
public class OrderController implements IOrderController{

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value= StarbucksConstants.VIEW_ORDERS, method= RequestMethod.POST)
    public @ResponseBody
    List<Order> viewAllOrders (@RequestBody Order order){
        return orderService.viewAllOrders(order);

    }
}
