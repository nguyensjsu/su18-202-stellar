package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.Cart;
import org.CMPE202.starbucks.model.CartItems;

import java.util.List;

public interface ICartDao {

    public String addToCart(Cart cart);
    public List<CartItems> viewMyCart(Cart cart);

}
