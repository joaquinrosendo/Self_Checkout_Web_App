/*
ProductInventoryDbUtil.java -
This class is used for interacting directly with the "product_inventory" table
found in the "autoscouts" database/schema. 

It contains methods used for grabbing data from and putting data into the database.
Any controller class (or controller servlet) is what will call these methods to
perform its required logic

Configurations of database connection can be found in "META-INF/context.xml"
*/


package com.autoscouts.jsp.tagdemo;
import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

public class ProductInventoryDbUtil {

    // DataSource is a class that contains the database configurations 
    // found in META-INF/context.xml
    private DataSource dataSource;
    
    // Constructor for database to create connection & interaction 
    // with database
    public ProductInventoryDbUtil(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    // Outputs a list of the rows from the database
    public List<ProductInventory> getProductInventory() throws Exception{
        
        List<ProductInventory> productInventory = new ArrayList();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{ 
        // get a connection
        connection = dataSource.getConnection();
        
        // create sql statement
        String sql = "select * from product_inventory";
        statement = connection.createStatement();
        
        // execute query
        resultSet = statement.executeQuery(sql);
        
        // process result set
        while(resultSet.next()){
            // retrieve data from result set row
            int id = resultSet.getInt("id_product_inventory");
            String item = resultSet.getString("item");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");
            float discount = resultSet.getFloat("discount");
            int quantity = resultSet.getInt("quantity");
            
            // create new ProductInventory object
            ProductInventory tempProductInventory = new ProductInventory(id, item, description, price, discount, quantity);
            
            // add to productInvetory list that was created above
            productInventory.add(tempProductInventory);
            
        }
        // return list of rows extracted from database
        return productInventory;
        
        }finally{
            // close jdbc objects
            close(connection, statement, resultSet);
        }
        
    }
    
    // Outputs a list of the rows from the database INCLUDING quantity
    public List<ProductInventory> getQuantityProductInventory() throws Exception{
        
        List<ProductInventory> quantityProductInventory = new ArrayList();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{ 
        // get a connection
        connection = dataSource.getConnection();
        
        // create sql statement
        String sql = "select * from product_inventory";
        statement = connection.createStatement();
        
        // execute query
        resultSet = statement.executeQuery(sql);
        
        // process result set
        while(resultSet.next()){
            // retrieve data from result set row
            int id = resultSet.getInt("id_product_inventory");
            String item = resultSet.getString("item");
            String description = resultSet.getString("description");
            float price = resultSet.getFloat("price");
            float discount = resultSet.getFloat("discount");
            int quantity = resultSet.getInt("quantity");
            
            // create new ProductInventory object
            ProductInventory tempProductInventory = new ProductInventory(id, item, description, price, discount, quantity);
            
            // add to quantityProductInvetory list that was created above
            quantityProductInventory.add(tempProductInventory);
            
        }
        // return list of rows extracted from database
        return quantityProductInventory;
        
        }finally{
            // close jdbc objects
            close(connection, statement, resultSet);
        }
        
    }
    
    // used to close connection, statement, & resultSet when done interacting
    // with the database
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

    // used to add a new product to the database
    public void addProduct(ProductInventory theProduct) throws Exception{
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            // get db connection
            connection = dataSource.getConnection();
            
            // create sql statement for insert
            String sql = "insert into product_inventory"
                        + "(item, description, price, discount)"
                        + "values (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            
            // set the parameter values for the product
            statement.setString(1, theProduct.getItem());
            statement.setString(2, theProduct.getDescription());
            statement.setFloat(3, theProduct.getPrice());
            statement.setFloat(4, theProduct.getDiscount());
            // excute sql insert
            statement.execute();
        }
        finally{
            // clean up JDBC ojbects 
            close(connection, statement, null);
        }
    }
    
    // used to grab product and its info from the database according to 
    // its id number
    public ProductInventory getProduct(String theProductId) throws Exception {
        ProductInventory theProduct = null;
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int productId;
        
        try{
            // convert product id to int
            productId = Integer.parseInt(theProductId);
            
            // get connection to db
            connection = dataSource.getConnection();
            
            // create sql to get selected product
            String sql = "select * from product_inventory where id_product_inventory=?";
            // create prepared statement 
            statement = connection.prepareStatement(sql);
            
            // set params
            statement.setInt(1, productId);
            
            // execute statement 
            resultSet = statement.executeQuery();
            
            // retrieve data from result set row
            if(resultSet.next()){
                String item = resultSet.getString("item");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price");
                float discount = resultSet.getFloat("discount");
                
                // use the productId during construction
                theProduct = new ProductInventory(productId, item, description, price, discount);
            }
            else{
                throw new Exception("Could not find Product ID: " + productId);
            }
            
            return theProduct;
        }
        finally{
            // clean up jdbc objects
            close(connection, statement, resultSet);
        }
    }

    // updates an existing product to the database
    void updateProduct(ProductInventory theProduct) throws Exception{

        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            // get db connection
            connection = dataSource.getConnection();

            // create sql update statement
            String sql = "update product_inventory "
                        + "set item=?, description=?, price=?, discount=? "
                        + "where id_product_inventory=?";

            // prepare statement
            statement = connection.prepareStatement(sql);

            // set parameters for that statement
            statement.setString(1, theProduct.getItem());
            statement.setString(2, theProduct.getDescription());
            statement.setFloat(3, theProduct.getPrice());
            statement.setFloat(4, theProduct.getDiscount());
            statement.setInt(5, theProduct.getId());

            // execute sql statement, will update the db
            statement.execute();
        }
        finally{
            close(connection, statement, null);
        }
    }
    
    public boolean productExists(ProductInventory theProduct) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            // get connection to db
            connection = dataSource.getConnection();
            
            // create sql to get selected product
            String sql = "select * from product_inventory where item=?";
            // create prepared statement 
            statement = connection.prepareStatement(sql);
            
            // set params
            statement.setString(1, theProduct.getItem());
            
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

    void restockProduct(ProductInventory theProduct) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        //ResultSet resultSet = null;
        
        try{
            // get db connection
            connection = dataSource.getConnection();

            // create sql update statement
            String sql = "update product_inventory "
                        + "set quantity=? "
                        + "where item=?";

            // prepare statement
            statement = connection.prepareStatement(sql);

            // set parameters for that statement
            statement.setInt(1, theProduct.getQuantity());
            statement.setString(2, theProduct.getItem());
            // execute statement 
            statement.execute();
           // resultSet = statement.executeQuery();
        }
        finally{
            close(connection, statement, null);
        }
    }

    // Outputs a list of the rows from the database
    public List<ProductInventory> getCustomerProductInventory() throws Exception{
        
        List<ProductInventory> productInventory = new ArrayList();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{ 
        // get a connection
        connection = dataSource.getConnection();
        
        // create sql statement
        String sql = "select id_product_inventory, item, price from product_inventory";
        statement = connection.createStatement();
        
        // execute query
        resultSet = statement.executeQuery(sql);
        
        // process result set
        while(resultSet.next()){
            // retrieve data from result set row
            int id = resultSet.getInt("id_product_inventory");
            String item = resultSet.getString("item");
            float price = resultSet.getFloat("price");           
            
            // create new ProductInventory object
            ProductInventory tempProductInventory = new ProductInventory(id, item, price);
            
            // add to productInvetory list that was created above
            productInventory.add(tempProductInventory);
            
        }
        // return list of rows extracted from database
        return productInventory;
        
        }finally{
            // close jdbc objects
            close(connection, statement, resultSet);
        }
        
    }

    void addScannedProduct(ProductInventory scannedProduct) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            // get db connection
            connection = dataSource.getConnection();

            // create sql update statement
            String sql = "insert into temp_scanned_items set "
                        + "scanned_items=?, items_cost=?, items_quantity=?";

            // prepare statement
            statement = connection.prepareStatement(sql);

            // multiply the quantity by the price
            float price = scannedProduct.getPrice();
            int quantity = scannedProduct.getQuantity();
            float totalItemPrice = price*quantity;
            
            // set parameters for that statement
            statement.setString(1, scannedProduct.getItem());
            statement.setFloat(2, totalItemPrice);
            statement.setInt(3, quantity);

            // execute sql statement, will update the db
            statement.execute();
        }
        finally{
            close(connection, statement, null);
        }
    }

}
