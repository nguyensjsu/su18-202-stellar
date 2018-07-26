package org.CMPE202.starbucks.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.CMPE202.starbucks.dao.impl.CardDaoImpl;
import org.CMPE202.starbucks.model.Card;
import org.CMPE202.starbucks.responseVo.CardResponseVo;
import org.CMPE202.starbucks.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	private CardDaoImpl cardDao;

	public String addCard(Card card){
		
		String uniqueCardID = UUID.randomUUID().toString();
        card.setCardId(uniqueCardID);
        card.setIsDefault(1);
        card.setBalance(25.00);

        return cardDao.addCard(card);
	}
	
	 public List<Card> viewAllCards(Card card) {
	      
		 List<Card> cardList = cardDao.viewCards(card);
//		 List<Card> cardResultSet = new ArrayList<>();
//		 CardResponseVo cardResponseVo = new CardResponseVo();
//		 for(Card cardresult : cardList){
//			 
//			 cardResponseVo.setCardNumber(cardresult.getCardNumber());
//			 cardResponseVo.setBalance(cardresult.getBalance());
//			 
//			 cardResponseVo.getCardDetails().addAll(cardResultSet);
//		 }
//		 
		 
	        return cardList;
	    }
	 
	 public String setDefaultCard(Card card){
		 
		 return cardDao.setDefaultCard(card.getCardNumber(),card.getUserId());
	 }
	 
	 public Card viewCardDetails(Card card){
		 System.out.println("Card in service " + card.getCardNumber());
		 return cardDao.viewCardDetails(card.getCardNumber());
	 }
	 
}
