package org.CMPE202.starbucks.responseVo;

import java.util.ArrayList;
import java.util.List;

import org.CMPE202.starbucks.model.Card;


public class CardResponseVo{

	 String cardNumber;
	 String securityCode;
   
	List<Card> allcards = new ArrayList<>();
	

    public List<Card>  getCardDetails() {
        return allcards;
    }

    public void setCardDetails(String cardNumber,String securityCode) {
        Card card = new Card(cardNumber,securityCode);
        allcards.add(card);
        
    }

    @Override
    public String toString() {
        return "CardResponseVo{" +
                "allcards=" + allcards +
                '}';
    }
}