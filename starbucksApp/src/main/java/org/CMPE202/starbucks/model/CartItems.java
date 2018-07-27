package org.CMPE202.starbucks.model;

public class CartItems {

        private String itemName;
        private double itemPrice;
        private String quantity;
        private double total;

        public String getItemName() {
            return itemName;
        }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemName(String itemName) {
            this.itemName = itemName;
        }



        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }


        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
}

