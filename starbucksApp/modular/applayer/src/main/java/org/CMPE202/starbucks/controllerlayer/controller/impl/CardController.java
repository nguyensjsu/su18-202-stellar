package org.CMPE202.starbucks.controllerlayer.controller.impl;

import org.CMPE202.starbucks.commondto.constant.StarbucksConstants;
import org.CMPE202.starbucks.commondto.model.Card;
import org.CMPE202.starbucks.commondto.responseVo.GenericResponseVo;
import org.CMPE202.starbucks.controllerlayer.controller.ICardController;
import org.CMPE202.starbucks.controllerlayer.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
