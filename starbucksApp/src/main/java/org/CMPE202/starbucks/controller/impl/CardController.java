package org.CMPE202.starbucks.controller.impl;


import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.ICardController;
import org.CMPE202.starbucks.service.impl.CardServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= StarbucksConstants.CARD)
public class CardController implements ICardController {

    private CardServiceImpl cardService;

}
