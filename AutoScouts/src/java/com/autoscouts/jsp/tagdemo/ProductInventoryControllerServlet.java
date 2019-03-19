package com.autoscouts.jsp.tagdemo;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

// 
@WebServlet(name = "ProductInventoryControllerServlet", urlPatterns = {"/ProductInventoryControllerServlet"})

public class ProductInventoryControllerServlet extends HttpServlet {
    
    private ProductInventoryDbUtil productInventoryDbUtil;
    private TempScannedItemsDbUtil tempScannedItemsDbUtil;
    private BankDbUtil bankDbUtil;
    
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
            tempScannedItemsDbUtil = new TempScannedItemsDbUtil(dataSource);
            bankDbUtil = new BankDbUtil(dataSource);
        }
        catch(Exception e){
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try{
            
            String theCommand = request.getParameter("command");
            
            if(theCommand.equals("MANAGER_PAGE")){
                theCommand = "LIST_MANAGER";
            }
            else if(theCommand.equals("RESTOCKER_PAGE")){
                theCommand = "LIST_RESTOCKER";
            }
            else if(theCommand.equals("CUSTOMER_PAGE")){
                theCommand = "CUSTOMER_SCAN_ITEMS";
            }
            
            // route to the appropriate method
                switch (theCommand){
                    case "LIST_MANAGER":
                        listProductInventory(request, response);
                        break;
                    case "LOAD_MANAGER":
                        loadProduct(request, response); // populates input fields on "add-product-form.jsp" with
                        break;                          // product's info for editting/updating 
                    case "UPDATE":
                        updateProduct(request, response);
                        break;
                    case "LOAD_RESTOCKER":
                        restockProduct(request, response);
                    case "LIST_RESTOCKER":
                        listQuantityProductInventory(request, response);
                        break;
                    case "CUSTOMER_SCAN_ITEMS":
                        customerScanItemsList(request, response);
                        break;
                    case "BAG_SCANNED_PRODUCT":
                        bagScannedProduct(request, response);
                        break;
                    case "ENTER_SCANNED_ITEM_QUANTITY":
                        enterScannedItemQuantity(request, response);
                        break;
                    case "CASH_SELECTED":
                        payByCash(request, response);
                        break;
                    case "CREDIT_SELECTED":
                        payByCredit(request, response);
                        break;
                    case "DEBIT_SELECTED":
                        payByDebit(request, response);
                        break;
                    case "VERIFY_CASH":
                        verifyCash(request, response);
                        break;
                    case "VERIFY_CREDIT":
                        verifyCredit(request, response);
                        break;
                    case "VERIFY_DEBIT":
                        verifyDebit(request, response);
                        break;
                    //case "ADD":
                    //  *ADD was moved to "doPost" method due to
                    // duplicating issues on web browser (newly added item
                    // kept adding itself again when refreshing browser)

                    default:
                        listProductInventory(request, response);
                }
            
        }
        catch(Exception e){
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
         try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
                    
            // route to the appropriate method
            switch (theCommand) {
                            
            case "ADD":
                addProduct(request, response);
                break;
            case "ITEM_RESTOCK":
                restockProduct(request, response);
                break;
            case "CANCEL_CHECKOUT":
                cancelCheckout(request, response);
                break;
            case "CHOOSE_PAYMENT":
                choosePayment(request, response);
                break;
            case "HOME_SCREEN":
                homeScreen(request, response);
                break;
            default:
                listProductInventory(request, response);
            }
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    private void listProductInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // get items from db util
        List<ProductInventory> productInventory = productInventoryDbUtil.getProductInventory();
        
        // add items to request option as an attribute
        request.setAttribute("PRODUCT_INVENTORY_LIST", productInventory);
        
        // send to JSP page (view) using request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manager-interface.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listQuantityProductInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get items from db util
        List<ProductInventory> productInventory = productInventoryDbUtil.getProductInventory();
        
        // add items to request option as an attribute
        request.setAttribute("QUANTITY_PRODUCT_INVENTORY_LIST", productInventory);
        
        // send to JSP page (view) using request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/restocker-interface.jsp");
        dispatcher.forward(request, response);
    }

    private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get product id from form data
        String theProductId = request.getParameter("productId");
        
        // get product info from the database (db util)
        ProductInventory theProduct = productInventoryDbUtil.getProduct(theProductId);
        
        // place product in the request attribute
        request.setAttribute("THE_PRODUCT", theProduct);
        
        // send to input fields of "add-product-form.jsp"
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read product info from the form data 
        int id = Integer.parseInt(request.getParameter("productId"));
        String item = request.getParameter("item");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        float discount = Float.parseFloat(request.getParameter("discount"));
        
        // create a product object based on form data
        ProductInventory theProduct = new ProductInventory(id, item, description, price, discount);
        
        // perform update on database
        productInventoryDbUtil.updateProduct(theProduct);
        
        // send user back to the manager interface page (manager-interface.jsp)
        listProductInventory(request, response);
    }
    
    private void bagScannedProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read product info from the form data 
        int id = Integer.parseInt(request.getParameter("productId"));
        String item = request.getParameter("productItem");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("productPrice"));
        
        // create a product object based on form data
        ProductInventory theProduct = new ProductInventory(item, quantity, price);
        
        // perform update on database
        productInventoryDbUtil.addScannedProduct(theProduct);
        
        // send user back to the manager interface page (manager-interface.jsp)
        customerScanItemsList(request, response);
    }
    
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read product info from <form> in add-product-form.jsp
        String item = request.getParameter("item");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        float discount = Float.parseFloat(request.getParameter("discount"));
        
        // create new product object to store that info as data
        ProductInventory theProduct = new ProductInventory(item, description, price, discount);
        
        // add the new product to the database
        productInventoryDbUtil.addProduct(theProduct);
        
        // send back to Manager Interface to see new entry in table
        response.sendRedirect(request.getContextPath() + "/ProductInventoryControllerServlet?command=LIST");
    }

    private void restockProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read product from form in restocker-interfacem.jsp
        String item = request.getParameter("item");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // create a new product object to store that info
        ProductInventory theProduct = new ProductInventory(item, quantity);
        
        // restock the entered item and quantity
        if(productInventoryDbUtil.productExists(theProduct)){
            productInventoryDbUtil.restockProduct(theProduct);
            listProductInventory(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/add-product-form.jsp");
        }
        
        // if successful... 
    }

    private void customerScanItemsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get items from db util
        List<ProductInventory> customerProductInventory = productInventoryDbUtil.getCustomerProductInventory();
        
        // add items to request option as an attribute
        request.setAttribute("CUSTOMER_ITEMS_LIST", customerProductInventory);
        
        // send to JSP page (view) using request dispatcher
        customerItemsAndSubtotal(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-interface.jsp");
        dispatcher.forward(request, response);
    }
    
    private void customerItemsAndSubtotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get items from temp scanned items db util
        List<TempScannedItems> tempScannedItemsList = tempScannedItemsDbUtil.getTempScannedItems();
        String subTotal = tempScannedItemsDbUtil.getTempScannedItemsTotal();
        
        // add items to request as an attribute
        request.setAttribute("CUSTOMER_SCANNED_ITEMS_SUBTOTAL", tempScannedItemsList);
        request.setAttribute("SUB_TOTAL", subTotal);
        
        // send to JSP page 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer-interface.jsp");
        dispatcher.forward(request, response);
    }
    
    private void customerReceiptCash(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get items from temp scanned items db util
        List<TempScannedItems> tempScannedItemsList = tempScannedItemsDbUtil.getTempScannedItems();
        String subTotal = tempScannedItemsDbUtil.getTempScannedItemsTotal();
        float cash = Float.parseFloat(request.getParameter("cash"));
        float total = Float.parseFloat(subTotal);
        float change = cash - total;
        String formattedTotal = String.format("%.02f", change);
        // add items to request as an attribute
        request.setAttribute("CUSTOMER_SCANNED_ITEMS_SUBTOTAL", tempScannedItemsList);
        request.setAttribute("SUB_TOTAL", subTotal);
        request.setAttribute("CHANGE", formattedTotal);
    }
    
    private void customerReceiptCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get items from temp scanned items db util
        List<TempScannedItems> tempScannedItemsList = tempScannedItemsDbUtil.getTempScannedItems();
        String subTotal = tempScannedItemsDbUtil.getTempScannedItemsTotal();
        float total = Float.parseFloat(subTotal);
        String formattedTotal = String.format("%.02f", total);
        Random rand = new Random();
        int  authorizationCode = rand.nextInt(500000) + 100000;
        
        // add items to request as an attribute
        request.setAttribute("CUSTOMER_SCANNED_ITEMS_SUBTOTAL", tempScannedItemsList);
        request.setAttribute("SUB_TOTAL", formattedTotal);
        request.setAttribute("AUTH_CODE", authorizationCode);
    }
    
    private void cancelCheckout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // call cancel checkout from databas
        // delete the temp scanned items
        tempScannedItemsDbUtil.deleteTempScannedItemsTable();
        
        // send to JSP page 
        customerScanItemsList(request, response);
    }
    
    private void homeScreen(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // call cancel checkout from databas
        // delete the temp scanned items
        tempScannedItemsDbUtil.deleteTempScannedItemsTable();
        
        // send to JSP page 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void enterScannedItemQuantity(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read product name (item)
        String theProductId = request.getParameter("productId");
        
        // create product object for db util 
        ProductInventory theProduct = productInventoryDbUtil.getProduct(theProductId);
        
        // add item and quantity to temp scanned item database
        request.setAttribute("THE_PRODUCT", theProduct);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/add-scanned-item-quantity.jsp");
        dispatcher.forward(request, response);
    }

    private void choosePayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/choose-payment-method.jsp");
        dispatcher.forward(request, response);
    }

    private void payByCash(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String subTotal = tempScannedItemsDbUtil.getTempScannedItemsTotal();
        request.setAttribute("AMOUNT_DUE", subTotal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pay-by-cash.jsp");
        dispatcher.forward(request, response);
    }

    private void payByCredit(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String subTotal = tempScannedItemsDbUtil.getTempScannedItemsTotal();
        request.setAttribute("AMOUNT_DUE", subTotal);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pay-by-credit.jsp");
        dispatcher.forward(request, response);
    }

    private void payByDebit(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String subTotal = tempScannedItemsDbUtil.getTempScannedItemsTotal();
        request.setAttribute("AMOUNT_DUE", subTotal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pay-by-debit.jsp");
        dispatcher.forward(request, response);
    }

    private void verifyCash(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // read cash input by user
        float cash = Float.parseFloat(request.getParameter("cash"));
        
        // create bank object
        Bank thePayment = new Bank(cash);
        
        // get customer's total from temp scanned db util
        float total = Float.parseFloat(tempScannedItemsDbUtil.getTempScannedItemsTotal());
        
        // call changemaker from bank db util
        boolean cashValid = bankDbUtil.changeMaker(thePayment, total);
        
        if(cashValid){
            customerReceiptCash(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction-valid.jsp");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pay-by-cash.jsp");
            dispatcher.forward(request, response);
        }
        
    }

    private void verifyCredit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read card input by user
        int credit = Integer.parseInt(request.getParameter("credit"));
        
        // create bank object
        Bank thePayment = new Bank(credit);
        
        // get customer's total from temp scanned db util
        float total = Float.parseFloat(tempScannedItemsDbUtil.getTempScannedItemsTotal());
        
        // call changemaker from bank db util
        boolean cardValid = bankDbUtil.checkCreditCard(thePayment);
        
        if(cardValid){
            customerReceiptCard(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction-valid.jsp");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pay-by-credit.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void verifyDebit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read card input by user
        int debit = Integer.parseInt(request.getParameter("debit"));
        int pin = Integer.parseInt(request.getParameter("pin"));
        
        // create bank object
        Bank thePayment = new Bank(debit, pin);
        
        // get customer's total from temp scanned db util
        float total = Float.parseFloat(tempScannedItemsDbUtil.getTempScannedItemsTotal());
        
        // call changemaker from bank db util
        boolean cardValid = bankDbUtil.checkDebitCard(thePayment);
        
        if(cardValid){
            customerReceiptCard(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/transaction-valid.jsp");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pay-by-debit.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    
}
