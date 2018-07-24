package org.CMPE202.starbucks.service.impl;

import org.CMPE202.starbucks.dao.impl.CardDaoImpl;
import org.CMPE202.starbucks.service.ICardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements ICardService {

    private CardDaoImpl cardDao;
}
