package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IPaymentController;
import org.CMPE202.starbucks.model.Payment;
import org.CMPE202.starbucks.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.responseVo.StoreReponseVo;
import org.CMPE202.starbucks.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= StarbucksConstants.PAYMENT)
public class PaymentController implements IPaymentController{

    @Autowired
    private PaymentServiceImpl paymentService;


    @RequestMapping(value= StarbucksConstants.MAKE_PAYMENT, method= RequestMethod.POST)
    public @ResponseBody
    GenericResponseVo makePayment (@RequestBody Payment payment) {

        return paymentService.makePayment(payment);

    }


}
