package org.CMPE202.starbucks.controllerlayer.controller.impl;

import org.CMPE202.starbucks.commondto.constant.StarbucksConstants;
import org.CMPE202.starbucks.commondto.model.Order;
import org.CMPE202.starbucks.controllerlayer.controller.IOrderController;
import org.CMPE202.starbucks.controllerlayer.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= StarbucksConstants.ORDER)
public class OrderController implements IOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping(value= StarbucksConstants.VIEW_ORDERS, method= RequestMethod.POST)
    public @ResponseBody
    List<Order> viewAllOrders (@RequestBody Order order){
        return orderService.viewAllOrders(order);

    }
}
