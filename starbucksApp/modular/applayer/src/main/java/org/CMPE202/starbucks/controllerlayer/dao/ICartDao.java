package org.CMPE202.starbucks.controllerlayer.dao;



import org.CMPE202.starbucks.commondto.model.Cart;
import org.CMPE202.starbucks.commondto.model.CartItems;

import java.util.List;

public interface ICartDao {

    public String addToCart(Cart cart);
    public List<CartItems> viewMyCart(Cart cart);

}
