package org.CMPE202.starbucks.controllerlayer.dao;



import org.CMPE202.starbucks.commondto.model.User;
import org.CMPE202.starbucks.commondto.model.UserSession;

import javax.servlet.http.HttpServletRequest;

public interface IUserDao {

    public UserSession authenticateAndCreateSession(HttpServletRequest httpServletRequest, User user);
}
