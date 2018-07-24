package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IOrderController;
import org.CMPE202.starbucks.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= StarbucksConstants.ORDER)
public class OrderController implements IOrderController{

    private OrderServiceImpl orderService;
}
