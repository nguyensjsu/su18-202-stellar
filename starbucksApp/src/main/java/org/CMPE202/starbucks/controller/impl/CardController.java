package org.CMPE202.starbucks.controller.impl;



import java.util.List;

import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.ICardController;
import org.CMPE202.starbucks.model.Card;
import org.CMPE202.starbucks.responseVo.CardResponseVo;
import org.CMPE202.starbucks.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= StarbucksConstants.CARD)
public class CardController implements ICardController {

	
	@Autowired
	private CardServiceImpl cardService;
    
    @RequestMapping(value= StarbucksConstants.ADD_NEW_CARD, method= RequestMethod.POST)
    public @ResponseBody
    GenericResponseVo addCard(@RequestBody Card card){
    	
    			return cardService.addCard(card);
    			
    }
    
    @RequestMapping(value= StarbucksConstants.VIEW_ALL_CARDS, method= RequestMethod.POST)
    public @ResponseBody
    List<Card> viewAllCards (@RequestBody Card card){
        return cardService.viewAllCards(card);

    }
    
    @RequestMapping(value= StarbucksConstants.SET_DEFAULT_CARD, method= RequestMethod.POST)
    public @ResponseBody
    String setDefaultCard (@RequestBody Card card){
        return cardService.setDefaultCard(card);

    }
    
    @RequestMapping(value= StarbucksConstants.VIEW_CARD_DETAILS, method= RequestMethod.POST)
    public @ResponseBody
    Card viewCardDetails (@RequestBody Card card){
    	System.out.println("Card in controller " + card.getCardNumber());
        return cardService.viewCardDetails(card);

    }
    
   
}
