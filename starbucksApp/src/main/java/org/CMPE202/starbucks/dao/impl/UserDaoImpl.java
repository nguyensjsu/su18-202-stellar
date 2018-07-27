package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.IUserDao;
import org.CMPE202.starbucks.model.User;

public class UserDaoImpl implements IUserDao {
	
	 private DataSource dataSource;
	 private JdbcTemplate jdbcTemplate;
	 
    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public String authenticateUser(User user) {
    	String sql ="SELECT "+
                "userId,"+
                "userName,"+
                "userPIN,"+
                "createdAt"+
                "FROM USERS";
    	
    	//implementation pending
    	

    }
}
