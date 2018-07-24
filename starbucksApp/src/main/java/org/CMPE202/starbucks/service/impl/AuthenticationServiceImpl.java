package org.CMPE202.starbucks.service.impl;

import org.CMPE202.starbucks.dao.impl.UserDaoImpl;
import org.CMPE202.starbucks.service.IAuthenticationService;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private UserDaoImpl userDao;
}
