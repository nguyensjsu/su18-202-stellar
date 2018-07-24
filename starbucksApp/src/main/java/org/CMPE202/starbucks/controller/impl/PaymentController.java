package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IPaymentController;
import org.CMPE202.starbucks.service.impl.PaymentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= StarbucksConstants.PAYMENT)
public class PaymentController implements IPaymentController{

    private PaymentServiceImpl paymentService;
}
