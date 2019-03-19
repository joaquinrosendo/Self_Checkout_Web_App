<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<center>
<head>
    <title>Manager Interface</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

    <div id="wrapper">
        <div id="header">
            <h2> Manager Interface </h2>
        </div>
    </div>
    
    <div id="container">
        <div id="content">
            
            <!-- adding the "Add" button -->
            <input type="button" value="Add Product" 
                   onclick="window.location.href='add-product-form.jsp'; return false;"
                   class="add-product-button"
            />
            
            <table>
                
                <tr>
                    <th>Item</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Discount</th>
                    <th>Quantity</th>
                    <th>Edit</th>
                    
                    <c:forEach var="productInventory" items="${PRODUCT_INVENTORY_LIST}">
                        
                        <c:url var="tempLink" value="ProductInventoryControllerServlet" >
                            <c:param name="command" value="LOAD_MANAGER"/>
                            <c:param name="productId" value="${productInventory.id}"/>
                        </c:url>
                        <tr>
                            <!-- Below code calls the "get" methods-->
                            <td> ${productInventory.item} </td>
                            <td> ${productInventory.description} </td>
                            <td> ${productInventory.price} </td>
                            <td> ${productInventory.discount} % </td>
                            <td> ${productInventory.quantity} </td>
                            <td> <a href="${tempLink}"> Edit </a></td>
                        </tr>
                    </c:forEach>
                </tr>
                
            </table>
        </div>
    </div>
    
    <div style="clear: both;"></div>
            <p>
                <a href="index.jsp">Back to AutoSCoutS Menu</a>
            </p>
    
</body>
</center>
</html>
