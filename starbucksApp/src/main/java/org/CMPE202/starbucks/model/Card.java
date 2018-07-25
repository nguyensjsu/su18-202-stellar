package org.CMPE202.starbucks.model;

public class Card {
	
	private String cardNumber;
	private String securityCode;
	
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
	
	public Card(String cardNumber, String securityCode){
        this.cardNumber=cardNumber;
        this.securityCode = securityCode;
    }
	
}
