package org.CMPE202.starbucks.controllerlayer.service.impl;


import org.CMPE202.starbucks.commondto.model.Payment;
import org.CMPE202.starbucks.commondto.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.controllerlayer.dao.impl.PaymentDaoImpl;
import org.CMPE202.starbucks.controllerlayer.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentDaoImpl paymentDao;

    public GenericResponseVo makePayment(Payment payment) {

        String uniqueID = UUID.randomUUID().toString();
        payment.setPaymentId(uniqueID);
        payment.setPaymentSuccessful(true);

        GenericResponseVo genericResponseVo = new GenericResponseVo();
        genericResponseVo.setMessage(paymentDao.makePayment(payment));
        return genericResponseVo;

    }



}
