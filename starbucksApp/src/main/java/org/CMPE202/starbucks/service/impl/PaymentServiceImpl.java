package org.CMPE202.starbucks.service.impl;

import org.CMPE202.starbucks.dao.impl.PaymentDaoImpl;
import org.CMPE202.starbucks.model.Payment;
import org.CMPE202.starbucks.model.Store;
import org.CMPE202.starbucks.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.responseVo.StoreReponseVo;
import org.CMPE202.starbucks.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
