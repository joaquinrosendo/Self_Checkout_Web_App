
package com.autoscouts.jsp.tagdemo;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class BankDbUtil {
    
    // DataSource is a class that contains the database configurations 
    // found in META-INF/context.xml
    private DataSource dataSource;

    public BankDbUtil(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private void close(Connection connection, Statement statement, ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
            
            if(statement != null){
                statement.close();
            }
            
            if(connection != null){
                connection.close(); // doesn't really close, just put back in connection "pool" for later use
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean checkDebitCard(Bank theCard) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            // get connection to db
            connection = dataSource.getConnection();
            
            // create sql to get selected product
            String sql = "select * from bank where card_number=? and pin_number=?";
            
            // create prepared statement 
            statement = connection.prepareStatement(sql);
            
            // set params
            statement.setInt(1, theCard.getDebit());
            statement.setInt(2, theCard.getPin());
           
            // execute statement 
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                return true; 
            }
            else{
                return false;
            }
            
            
        }
        finally{
            close(connection, statement, resultSet);
        }
    }
    
    public boolean checkCreditCard(Bank theCard) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            // get connection to db
            connection = dataSource.getConnection();
            
            // create sql to get selected product
            String sql = "select * from bank where card_number=?";
            
            // create prepared statement 
            statement = connection.prepareStatement(sql);
            
            // set params
            statement.setInt(1, theCard.getCredit());
           
            // execute statement 
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                return true; 
            }
            else{
                return false;
            }
            
            
        }
        finally{
            close(connection, statement, resultSet);
        }
    }
    
    public boolean changeMaker(Bank cash, float total){
        
        float money = cash.getCash();
        
        if(money >= total){
            return true;
        }
        else{
            return false;
        }
    }

}
