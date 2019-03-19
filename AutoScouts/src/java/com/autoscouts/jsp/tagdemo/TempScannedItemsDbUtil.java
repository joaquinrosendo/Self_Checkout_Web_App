
package com.autoscouts.jsp.tagdemo;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class TempScannedItemsDbUtil {
    // DataSource is a class that contains the database configurations 
    // found in META-INF/context.xml
    private DataSource dataSource;
    
    // Constructor for database to create connection & interaction 
    // with database
    public TempScannedItemsDbUtil(DataSource dataSource){
        this.dataSource = dataSource;
    }

    List<TempScannedItems> getTempScannedItems() throws Exception{
        List<TempScannedItems> tempScannedItems = new ArrayList();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{ 
        // get a connection
        connection = dataSource.getConnection();
        
        // create sql statement
        String sql = "select * from temp_scanned_items";
        statement = connection.createStatement();
        
        // execute query
        resultSet = statement.executeQuery(sql);
        
        // process result set
        while(resultSet.next()){
            // retrieve data from result set row
            String scannedItems = resultSet.getString("scanned_items");
            float itemsCost = Float.parseFloat(resultSet.getString("items_cost"));
            int itemsQuantity = Integer.parseInt(resultSet.getString("items_quantity"));           
            
            // create new ProductInventory object
            TempScannedItems tempList = new TempScannedItems(scannedItems, itemsCost, itemsQuantity);
            
            // add to productInvetory list that was created above
            tempScannedItems.add(tempList);
            
        }
        // return list of rows extracted from database
        return tempScannedItems;
        
        }finally{
            // close jdbc objects
            close(connection, statement, resultSet);
        }
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

    void deleteTempScannedItemsTable() throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            // get db connection
            connection = dataSource.getConnection();

            // create sql update statement
            String sql = "truncate temp_scanned_items";

            // prepare statement
            statement = connection.prepareStatement(sql);
            
            // execute sql statement, will update the db
            statement.execute();
        }
        finally{
            close(connection, statement, null);
        }
    }

    String getTempScannedItemsTotal() throws Exception{
        float total=0;
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{ 
        // get a connection
        connection = dataSource.getConnection();
        
        // create sql statement
        String sql = "SELECT items_cost FROM temp_scanned_items;";
        statement = connection.createStatement();
        
        // execute query
        resultSet = statement.executeQuery(sql);
        
        // get result
        while(resultSet.next()){
            total = total + Float.parseFloat(resultSet.getString("items_cost"));
        }
        
        total = (float) (total + (total*0.0625)); // sales tax calculation
        
        String formattedTotal = String.format("%.02f", total);
        
        // return list of rows extracted from database
        return formattedTotal;
        
        }finally{
            // close jdbc objects
            close(connection, statement, resultSet);
        }
    }
}
