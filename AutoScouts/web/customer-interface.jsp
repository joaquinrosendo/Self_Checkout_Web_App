<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Customer Scan Items</title>
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    <body align="center">
        <div id="wrapper">
        <div id="header">
            <h2> Customer Scan Items </h2>
        </div>
    </div>        
        <div id="container">
        <div id="content">
            <form action="ProductInventoryControllerServlet" method="POST">
                <input type="hidden" name="command" value="CHOOSE_PAYMENT"/>
                <table>

                   <tr>
                       <th>Item</th>
                       <th>Scan</th>

                       <c:forEach var="customerProductInventory" items="${CUSTOMER_ITEMS_LIST}">

                           <c:url var="tempLink" value="ProductInventoryControllerServlet" >
                               <c:param name="command" value="ENTER_SCANNED_ITEM_QUANTITY"/>
                               <c:param name="productId" value="${customerProductInventory.id}"/>
                           </c:url>
                           <tr>
                               <!-- Below code calls the "get" methods-->
                               <td> ${customerProductInventory.item} </td>
                               <td> <a href="${tempLink}"> Scan </a></td>
                           </tr>
                       </c:forEach>
                   </tr>
               </table>
               <br/><br/>
                <!-- ****************** adding the "Pay Now" button **************** -->
                <input type="submit" value="Pay Now"class="add-product-button"/>
            </form>  
            
            
            <form action="ProductInventoryControllerServlet" method="POST">
                <input type="hidden" name="command" value="CANCEL_CHECKOUT"/>
            
            <br/>
            <table>
                
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Cost</th>
                    
                    <c:forEach var="customerScannedItems" items="${CUSTOMER_SCANNED_ITEMS_SUBTOTAL}">
                        <tr>
                            <!-- Below code calls the "get" methods-->
                            <td> ${customerScannedItems.scanned_items} </td>
                            <td> ${customerScannedItems.items_quantity} </td>
                            <td>$ ${customerScannedItems.items_cost} </td>
                        </tr>
                    </c:forEach>
                </tr>
                
            </table>
            <br/><br/>
            <!-- display the subtotal -->
            <br/>
            <label><center>SubTotal (6.25% Tax):</center></label><br/>
            <b><font color="green">$ ${SUB_TOTAL}</font></b>
            <br/><br/>
            
            <!-- ****************** adding the "Cancel Checkout" button **************** -->
            <input type="submit" value="Cancel Checkout" class="add-product-button"/>
            </form>
            <br/><br/>
            <p>
                <a href="index.jsp">Back to AutoSCoutS Menu</a>
            </p>
        </div>
    </div>
    </body>
    
   </center> 
</html>
