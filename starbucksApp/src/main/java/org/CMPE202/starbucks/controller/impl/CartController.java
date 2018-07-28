package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.ICartController;
import org.CMPE202.starbucks.model.Cart;
import org.CMPE202.starbucks.model.CartItems;
import org.CMPE202.starbucks.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= StarbucksConstants.CART)

public class CartController implements ICartController {

    @Autowired
    private CartServiceImpl cartService;

    @RequestMapping(value= StarbucksConstants.ADD_TO_CART, method= RequestMethod.POST)
    public @ResponseBody
    GenericResponseVo addToCart(@RequestBody Cart cart){

        return cartService.addToCart(cart);

    }

    @RequestMapping(value= StarbucksConstants.VIEW_CART, method= RequestMethod.POST)
    public @ResponseBody
    List<CartItems> viewMyCart(Cart cart){
        return cartService.viewMyCart(cart);
    }


}
