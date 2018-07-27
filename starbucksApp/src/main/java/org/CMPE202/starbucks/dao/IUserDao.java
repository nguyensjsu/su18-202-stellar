package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.User;
import org.CMPE202.starbucks.model.UserSession;

import javax.servlet.http.HttpServletRequest;

public interface IUserDao {

    public UserSession authenticateAndCreateSession(HttpServletRequest httpServletRequest, User user);
}
