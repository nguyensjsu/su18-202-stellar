package com.starbucks.cmpe202.starbucksmobileapp.responseVo;

import java.io.Serializable;

public class Card implements Serializable {

    private String cardNumber;
    private String securityCode;
    private String cardId;
    private double balance;
    private String userId;
    private boolean isDefault;

    public Card(){

    }

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean getIsDefault() {
        return isDefault;
    }
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }
    public Card(String cardNumber, String securityCode){
        this.cardNumber=cardNumber;
        this.securityCode = securityCode;
    }
}
