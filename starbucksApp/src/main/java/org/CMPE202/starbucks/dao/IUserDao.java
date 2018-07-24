package org.CMPE202.starbucks.dao;

import org.CMPE202.starbucks.model.User;

public interface IUserDao {

    public String authenticateUser(User user);
}
