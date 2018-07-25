package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.IPaymentDao;
import org.CMPE202.starbucks.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Component
public class PaymentDaoImpl implements IPaymentDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String makePayment(Payment payment) {

        String sql ="INSERT INTO PAYMENT("+
                "paymentId,"+
                "orderId,"+
                "cardId,"+
                "isPaymentSuccessful,"+
                "paymentDate)"+
                "VALUES (?,?,?,?,?)";

        Object[] params = new Object[] {payment.getPaymentId(),payment.getOrderId(),payment.getCardId(),payment.isPaymentSuccessful(), new Date()};
        try {
            jdbcTemplate.update(sql, params);
            try{
                String str =deductAmountFromCard(payment);
                if(str.equals("Balance updated Successfully")){
                    return("Payment done Successfully");
                }
                else if(str.equals("Insufficient Balance")){
                    return str;
                }
            }
            catch(Exception e){
                System.out.println(e);
                return("Payment Failed");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return("Payment Failed");

    }

    private String deductAmountFromCard(Payment payment){

        //get current balance
        double balance =getCurrentBalance(payment.getCardId());

        //get amount to be paid
        double amount =getAmountToPay(payment.getOrderId());

        double newBalance = balance-amount;

        //deduct amount
        if(newBalance >=0){
            String sql = "UPDATE CARD SET "+
                    "balance = ? "+
                    "WHERE cardId = ?";
            Object[] params = new Object[] {newBalance,payment.getCardId()};

            try {
                jdbcTemplate.update(sql, params);

                return("Balance updated Successfully");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else{
            return "Insufficient Balance";
        }

        return("Update Failed");
    }

    private double getCurrentBalance(String cardId){

        String sql = "SELECT balance FROM CARD WHERE cardId = ? ";
        Object[] params = new Object[] {cardId};

        double balance=(double)jdbcTemplate.queryForObject(sql,params,Double.class);
            return balance;
    }

    private double getAmountToPay(String orderId){

        String sql = "SELECT amount FROM ORDERS WHERE orderId = ? ";
        Object[] params = new Object[] {orderId};
        double amount=(double)jdbcTemplate.queryForObject(sql,params,Double.class);
        return amount;
    }






}
