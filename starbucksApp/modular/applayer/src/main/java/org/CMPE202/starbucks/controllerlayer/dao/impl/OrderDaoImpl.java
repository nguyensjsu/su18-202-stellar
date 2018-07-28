package org.CMPE202.starbucks.controllerlayer.dao.impl;

import org.CMPE202.starbucks.commondto.model.Order;
import org.CMPE202.starbucks.controllerlayer.dao.IOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class OrderDaoImpl implements IOrderDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CardDaoImpl cardDao;

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
    public String placeOrder(Order order) {
        String str ="success";

        String sqlquery = "INSERT INTO ORDERS(" + "orderId," + "userId," + "amount," + "orderDate" + ") " + "VALUES (?,?,?,?)";

            Object[] parameters = new Object[] { order.getOrderId(), order.getUserId(), order.getAmount(),
                    new Date() };
            try {

                jdbcTemplate.update(sqlquery, parameters);
                return ("Order placed successfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        return ("Card not added.Please try again!!");
    }

    @Override
    public List<Order> viewOrders(Order order) {

        String sqlQuery = "SELECT "
                + "orderId,"
                + "amount, "
                + "orderDate "
                + "from "
                + "ORDERS "
                + "where "
                + "userId = ?";

        Object[] parameters = new Object[]{order.getUserId()};
        System.out.println("Inside DAO getUserId= "+ order.getUserId());
        try{

            List<Order> orders = jdbcTemplate.query(
                    sqlQuery,
                    parameters,
                    new RowMapper<Order>() {
                        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Order orderResult = new Order();
                            orderResult.setOrderId(rs.getString(1));
                            orderResult.setAmount(rs.getDouble(2));
                            orderResult.setOrderdate(rs.getString(3));
                            return orderResult;
                        }


                    });
            return orders;
        }catch(Exception e){
            System.out.println(e);

        }
        return null;
    }


}
