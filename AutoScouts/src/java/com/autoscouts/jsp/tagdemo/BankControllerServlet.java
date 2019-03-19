
package com.autoscouts.jsp.tagdemo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(name = "BankServlet", urlPatterns = {"/BankServlet"})
public class BankControllerServlet extends HttpServlet {
    private ProductInventoryDbUtil productInventoryDbUtil;
    
    @Resource(name="jdbc/autoscouts")
    private DataSource dataSource;

    // work that would normally go in a constructor when working w/ servlet, that code 
    // gets placed in the init() method
    // inherited from "GenericServlet" & override to add custom functionality
    @Override
    public void init() throws ServletException {
        super.init();
        
        // create our product inventory db util... and pass in the connection pool / datasource
        try{
            productInventoryDbUtil = new ProductInventoryDbUtil(dataSource);
        }
        catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    private void checkCreditCard(HttpServletRequest request, HttpServletResponse response)throws ServletException{
        
    }
}
