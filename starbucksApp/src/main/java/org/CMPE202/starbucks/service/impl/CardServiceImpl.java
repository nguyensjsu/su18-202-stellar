package org.CMPE202.starbucks.service.impl;


import org.CMPE202.starbucks.responseVo.CardResponseVo;
import org.CMPE202.starbucks.service.ICardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements ICardService {

//	@Autowired
//	private CardDaoImpl cardDao;
	
	public CardResponseVo setNewCardDetails(String cardNumber, String securityCode) {
        
		//List<Card> allCards = cardDao.viewCards();

		CardResponseVo cardReponseVo = new CardResponseVo();
		cardReponseVo.setCardDetails(cardNumber,securityCode);

        return cardReponseVo;
    }
	
	 public CardResponseVo viewAllCards() {
	      
		 
		 CardResponseVo cardResponseVo = new CardResponseVo();
		 cardResponseVo.getCardDetails();

	        return cardResponseVo;
	    }
}
