package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.IUserDao;
import org.CMPE202.starbucks.model.User;
import org.CMPE202.starbucks.model.UserSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Date;

@Component
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
    public UserSession authenticateAndCreateSession(HttpServletRequest httpServletRequest, User user) {

        UserSession userSession = new UserSession();
        userSession.setSessionId((String)httpServletRequest.getSession().getId());
        userSession.setUserId(user.getUserID());
        userSession.setActive("true");

        if(authenticateUser(user)){
            if(createSession(httpServletRequest,user))
                userSession.setMessage("Authenticated Successfully");
//                return "Authenticated Successfully";
            else
            userSession.setMessage("Invalid User");
//                return "Invalid User";
        }
        else
            userSession.setMessage("Invalid User");
//            return "Invalid User";
        return userSession;

    }

    public String validateSesion(String userId,String sessionId){
        String sql = "SELECT count(*) FROM STARBUCKS_SESSION WHERE userId = ? AND sessionID =? AND activeStatus = TRUE ";
        Object[] params = new Object[] {userId,sessionId};
        Integer activeSession=0;
        try{
            activeSession=(Integer)jdbcTemplate.queryForObject(sql,params,Integer.class);
        }catch(Exception ex){
            System.out.println(ex);
            return "Exception occured";
        }

        if(activeSession==1){
            return null;
        }else if(activeSession > 1){
            return "More than one active session";
        }else{
            return "Inactive session, Login again";
        }
    }

     private boolean createSession(HttpServletRequest httpServletRequest,User user){

         String sql ="INSERT INTO STARBUCKS_SESSION("+
                 "userID,"+
                 "sessionID,"+
                 "activeStatus,"+
                 "createdDate)"+
                 "VALUES (?,?,?,?)";

         Object[] params = new Object[] {user.getUserID(),(String)httpServletRequest.getSession().getId(),true, new Date()};
         try {
             jdbcTemplate.update(sql, params);
             return true;
             }
             catch(Exception e){
                 System.out.println(e);

             }
         return false;
     }

     private boolean authenticateUser(User user){
         String sql = "SELECT userPIN FROM USERS WHERE userId = ? ";
         Object[] params = new Object[] {user.getUserID()};
         String pin="";
         try{
             pin=(String)jdbcTemplate.queryForObject(sql,params,String.class);
         }catch(Exception e){
             System.out.println(e);
             return false;
         }

         if(user.getUserPin().equals(pin)){
             return true;
         }
         return false;
     }
}
