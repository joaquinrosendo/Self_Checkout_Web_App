<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Payment Successful!</title>
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    <body align="center">
        <div id="wrapper">
        <div id="header">
            <h2> Payment Successful!</h2>
            <br/>
            <h2> Customer Receipt </h2>
        </div>
    </div>        
        <div id="container">
        <div id="content">            
            <form action="ProductInventoryControllerServlet" method="POST">
                <input type="hidden" name="command" value="HOME_SCREEN"/>
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
            <label><center>Total (6.25% Tax):</center></label><br/>
            <b><font color="green">$ ${SUB_TOTAL}</font></b>
            <br/>-------------------------------------------<br/>
            <label><center>Change:</center></label><br/>
            <b><font color="green">$ 0.00</font></b>
            <br/>___________________________________________<br/><br/><br/><br/>
            
            <label><center>Authorization Code:</center></label><br/>
            <b><font color="blue">${AUTH_CODE}</font></b><br/><br/>
            <input type="submit" value="Exit"class="add-product-button"/>
            </form>
            
        </div>
    </div>
    </body>
    
   </center> 
</html>
