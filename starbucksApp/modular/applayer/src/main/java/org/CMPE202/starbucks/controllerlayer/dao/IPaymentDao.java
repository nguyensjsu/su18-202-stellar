package org.CMPE202.starbucks.controllerlayer.dao;


import org.CMPE202.starbucks.commondto.model.Payment;

public interface IPaymentDao {

    public String makePayment(Payment payment);
}
