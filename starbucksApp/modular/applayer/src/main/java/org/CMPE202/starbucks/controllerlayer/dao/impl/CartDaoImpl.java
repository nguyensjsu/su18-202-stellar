package org.CMPE202.starbucks.controllerlayer.dao.impl;


import org.CMPE202.starbucks.commondto.model.Cart;
import org.CMPE202.starbucks.commondto.model.CartItems;
import org.CMPE202.starbucks.commondto.model.Item;
import org.CMPE202.starbucks.controllerlayer.dao.ICartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CartDaoImpl implements ICartDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private double priceVal;
    private boolean checkAvailable;


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
    public String addToCart(Cart cart) {

        String sql =
                "SELECT "
                        + "itemPrice,"
                        + "isAvailbale "
                        + "from "
                        + "ITEM "
                        + "where "
                        + "itemId = "+ cart.getItemId();

        List<Item> items = new ArrayList<Item>();

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        for (Map row : rows) {
            Item item = new Item();
          this.priceVal =  (double) (row.get("itemPrice"));
          this.checkAvailable = (boolean) (row.get("isAvailbale"));
        }

        if(checkAvailable) {
            String cartQuery = "INSERT INTO CART(" +
                    "cartId," +
                    "userId," +
                    "itemId," +
                    "quantity," +
                    "total)" +
                    "VALUES (?,?,?,?,?)";

            int quant = Integer.parseInt(cart.getQuantity());
            double totalAmt = quant * priceVal;
            Object[] parameters = new Object[]{cart.getCartId(), cart.getUserId(), cart.getItemId(), cart.getQuantity(), cart.getTotal()};

            try {

                jdbcTemplate.update(cartQuery, parameters);
                return ("Added item to Cart successfully");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return ("Item is unavailable");
    }


    @Override
    public List<CartItems> viewMyCart(Cart cart) {

        String sqlQuery = "SELECT "
                + "ITEM.ItemName,"
                + "ITEM.itemPrice,"
                + "CART.quantity,"
                + "CART.total "
                + "from "
                + "CART join ITEM on ITEM.itemId = CART.itemId "
                + "where "
                + "CART.userId = ?";

        Object[] parameters = new Object[]{cart.getUserId()};

        List<CartItems> carts = new ArrayList<CartItems>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery,parameters);
        for (Map row : rows) {
            CartItems cartResult = new CartItems();
            cartResult.setItemName((String)(row.get("ItemName")));
            cartResult.setItemPrice((double)row.get("itemPrice"));
            cartResult.setQuantity((String) row.get("quantity"));
            cartResult.setTotal((double) row.get("total"));
            carts.add(cartResult);
        }
        return carts;
    }



}

