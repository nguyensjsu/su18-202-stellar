package org.CMPE202.starbucks.service.impl;

import org.CMPE202.starbucks.dao.impl.PaymentDaoImpl;
import org.CMPE202.starbucks.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private PaymentDaoImpl paymentDao;
}
