package org.CMPE202.starbucks.controllerlayer.dao;


import org.CMPE202.starbucks.commondto.model.Card;

import java.util.List;

public interface ICardDao {

    public String addCard(Card card);
    //public String updateCard(Card card);
    //public String setDefaultCard(String cardNumber);
    public List<Card> viewCards(Card card);
    public Card viewCardDetails(String cardid);
    public String deleteCard(String cardid);
	public String setDefaultCard(String cardNumber, String userId);
}
