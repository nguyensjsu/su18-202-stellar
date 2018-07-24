package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.Card;

import java.util.ArrayList;

public interface ICardDao {

    public String addCard(Card card);
    public String updateCard(Card card);
    public ArrayList<Card> viewCards();
    public Card viewCardDetails(String cardid);
    public String deleteCard(String cardid);
}
