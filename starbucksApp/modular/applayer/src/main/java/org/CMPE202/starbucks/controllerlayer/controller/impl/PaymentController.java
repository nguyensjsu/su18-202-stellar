package org.CMPE202.starbucks.controllerlayer.controller.impl;


import org.CMPE202.starbucks.commondto.constant.StarbucksConstants;
import org.CMPE202.starbucks.commondto.model.Payment;
import org.CMPE202.starbucks.commondto.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.controllerlayer.controller.IPaymentController;
import org.CMPE202.starbucks.controllerlayer.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= StarbucksConstants.PAYMENT)
public class PaymentController implements IPaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;


    @RequestMapping(value= StarbucksConstants.MAKE_PAYMENT, method= RequestMethod.POST)
    public @ResponseBody
    GenericResponseVo makePayment (@RequestBody Payment payment) {

        return paymentService.makePayment(payment);

    }


}
