package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.Card;

import java.util.ArrayList;
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
