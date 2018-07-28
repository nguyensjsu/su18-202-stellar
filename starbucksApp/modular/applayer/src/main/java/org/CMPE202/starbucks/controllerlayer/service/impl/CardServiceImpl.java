package org.CMPE202.starbucks.controllerlayer.service.impl;

import org.CMPE202.starbucks.commondto.model.Card;
import org.CMPE202.starbucks.commondto.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.controllerlayer.dao.impl.CardDaoImpl;
import org.CMPE202.starbucks.controllerlayer.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	private CardDaoImpl cardDao;

	public GenericResponseVo addCard(Card card){

		GenericResponseVo genericResponseVo = new GenericResponseVo();
		String uniqueCardID = UUID.randomUUID().toString();
        card.setCardId(uniqueCardID);
//        card.setIsDefault(1);
        card.setBalance(25.00);
		genericResponseVo.setMessage(cardDao.addCard(card));
        return genericResponseVo;
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
