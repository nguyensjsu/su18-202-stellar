package com.starbucks.cmpe202.starbucksmobileapp.responseVo;

import java.util.List;

public class checkout {

    String orderId;
    String total;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ViewCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ViewCart> cartItems) {
        this.cartItems = cartItems;
    }

    List<ViewCart> cartItems;
}
