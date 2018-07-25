package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.IPaymentDao;
import org.CMPE202.starbucks.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Date;

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
            return("File Uploaded Successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return("FAILURE");

    }





}
