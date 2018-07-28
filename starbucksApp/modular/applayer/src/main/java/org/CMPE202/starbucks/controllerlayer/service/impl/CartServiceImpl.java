package org.CMPE202.starbucks.controllerlayer.service.impl;

import org.CMPE202.starbucks.commondto.model.Cart;
import org.CMPE202.starbucks.commondto.model.CartItems;
import org.CMPE202.starbucks.commondto.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.controllerlayer.dao.impl.CartDaoImpl;
import org.CMPE202.starbucks.controllerlayer.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartDaoImpl cartDao;
    private Cart cart;

    public GenericResponseVo addToCart(Cart cart){
        //String uniqueCardID = UUID.randomUUID().toString();
        GenericResponseVo genericResponseVo = new GenericResponseVo();
        cart.setUserId("test@gmail.com");
        genericResponseVo.setMessage(cartDao.addToCart(cart));
        return genericResponseVo;

    }

    /*public List<Cart> viewMyCart(Cart cart){
        //System.out.println("Card " + card.getCardNumber());
        //List<Cart> cartList = cartDao.viewCart(cart);
        cart.setUserId("test@gmail.com");
        return cartDao.viewMyCart(cart);
    }*/


    public List<CartItems> viewMyCart(Cart cart){

        cart.setUserId("test@gmail.com");
        return cartDao.viewMyCart(cart);

    }

}
