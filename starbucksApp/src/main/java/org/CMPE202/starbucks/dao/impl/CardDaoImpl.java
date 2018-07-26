package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.ICardDao;
import org.CMPE202.starbucks.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

@Component
public class CardDaoImpl implements ICardDao {
    
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
    public String addCard(Card card) {
        
		if (clearDefaultCard(card.getUserId()) == "success") {
			
			
			String sqlquery = "INSERT INTO CARD(" + "cardId," + "cardNumber," + "securityCode," + "balance," + "userId,"
					+ "isDefault) " + "VALUES (?,?,?,?,?,?)";

			Object[] parameters = new Object[] { card.getCardId(), card.getCardNumber(), card.getSecurityCode(),
					card.getBalance(), card.getUserId(), card.getIsDefault() };
			try {
				
				jdbcTemplate.update(sqlquery, parameters);
				return ("Card added successfully");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
        
    	return ("Card not added.Please try again!!");		
    }
	
	public String clearDefaultCard(String userId){
		
		String sqlQuery = "UPDATE CARD set isDefault = 0 where userId = " + userId;
		
		try{
			
			 jdbcTemplate.update(sqlQuery);
			 return "success";
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return "update failed";
	}

    @Override
    public String setDefaultCard(String cardNumber , String userId) {
        
    	if(clearDefaultCard(userId) == "success"){
    	String sqlQuery = "Update CARD set isDefault = 1 where cardNumber = "+ cardNumber + 
    			" and userId = " + userId;
    	try{
			
			 jdbcTemplate.update(sqlQuery);
			 return "Successfully updated " + cardNumber +" as default card";
			
		}catch(Exception e){
			System.out.println(e);
			}
    	}
    	return "Sorry,cannot update!";
    }

    @Override
    public List<Card> viewCards(Card card) {
    	
    	String sqlQuery = "SELECT "
    			+ "cardNumber,"
    			+ "balance "
    			+ "from "
    			+ "CARD "
    			+ "where "
    			+ "userId = ?";
    	
    	Object[] parameters = new Object[]{card.getUserId()};
    	System.out.println("Inside DAO getUserId= "+ card.getUserId());
    	try{
    		
    		List<Card> cards = jdbcTemplate.query(
    			    sqlQuery,
    			    parameters,
    			    new RowMapper<Card>() {
    			        public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
    			        	Card cardResult = new Card();
    			        	cardResult.setCardNumber(rs.getString(1));
    			        	cardResult.setBalance(rs.getDouble(2));
    			            return cardResult;
    			        }
    			       
						
    			    });
    		 return cards;
    	}catch(Exception e){
    		System.out.println(e);
    		
    	}
        return null;
    }

    @Override
    public Card viewCardDetails(String cardNumber) {
    	
    	String sqlQuery = "SELECT * from CARD where cardNumber = "+ cardNumber;
    	
    	try{
    		
    		Card card = jdbcTemplate.queryForObject(sqlQuery, Card.class);
    		return card;
    		
    	}catch(Exception e){
    		
    		System.out.println(e);
    	}
    	
        return null;
    }

    @Override
    public String deleteCard(String cardNumber) {
    	
    	String sqlQuery = "DELETE from CARD where cardNumber = " + cardNumber;
    	
    	try{
    		
    		jdbcTemplate.update(sqlQuery);
    		return "Card successfully deleted";
    	}catch(Exception e){
    		System.out.println(e);
    		
    	}
 
        return "Sorry,selected card cannot be deleted";
    }
    
//   public boolean duplicateCardEntry(String cardNumber){
//	   
//	   String sqlQuery = "Select cardNumber from CARD where cardNumber = " + cardNumber;
//   	
//   	try{
//   		
//   		jdbcTemplate.update(sqlQuery);
//   		return true;
//   	}catch(Exception e){
//   		System.out.println(e);
//   		
//   	}
//
//       return false;
//	   
//   }
}
