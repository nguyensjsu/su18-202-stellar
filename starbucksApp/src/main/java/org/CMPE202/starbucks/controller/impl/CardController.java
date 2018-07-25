package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.ICardController;
import org.CMPE202.starbucks.responseVo.CardResponseVo;
import org.CMPE202.starbucks.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= StarbucksConstants.CARD)
public class CardController implements ICardController {

	@Autowired
	private CardServiceImpl cardService;
    
    @RequestMapping(value= StarbucksConstants.ADD_NEW_CARD, method= RequestMethod.GET)
    public @ResponseBody
    CardResponseVo setNewCardDetails (@RequestParam ("cardNumber") String cardNumber,@RequestParam ("securityCode") String securityCode) {
    	
        return cardService.setNewCardDetails(cardNumber,securityCode);

    }
    
    @RequestMapping(value= StarbucksConstants.VIEW_ALL_CARDS, method= RequestMethod.GET)
    public @ResponseBody
    CardResponseVo viewAllCards (){
        return cardService.viewAllCards();

    }

}
