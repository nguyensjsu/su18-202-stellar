package org.CMPE202.starbucks.controllerlayer.service.impl;


import org.CMPE202.starbucks.commondto.model.User;
import org.CMPE202.starbucks.commondto.model.UserSession;
import org.CMPE202.starbucks.controllerlayer.dao.impl.UserDaoImpl;
import org.CMPE202.starbucks.controllerlayer.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserDaoImpl userDao;

    public UserSession authenticateAndCreateSession(HttpServletRequest httpServletRequest, User user) {
        //validate the session using the information present in httpServletRequest with DB

        return userDao.authenticateAndCreateSession(httpServletRequest,user);

    }

    public String validateSesion(String userId,String sessionId) {
        return userDao.validateSesion(userId,sessionId);
        //Create the session information in DB
    }

    public void logOutAndEndSession(HttpServletRequest httpServletRequest) {
        //Make the session inactive for the user and session id present in httpServletRequest
    }
}
