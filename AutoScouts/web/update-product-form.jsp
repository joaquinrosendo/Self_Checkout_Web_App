
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Manager Interface - Update Product</title>
        
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/add-product-style.css"/>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2> Manager Interface </h2>
            </div>
        </div>
        
        <div id="container">
            <h3> Update Product </h3>
            
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="UPDATE"/>
                <input type="hidden" name="productId" value="${THE_PRODUCT.id}"/>
                
                <table>
                    <tbody>
                        <tr>
                            <td><label>Product Name:</label></td>
                            <td><input type="text" name="item" value="${THE_PRODUCT.item}"/></td>
                        </tr>
                        <tr>
                            <td><label>Product Description:</label></td>
                            <td><input type="text" name="description" value="${THE_PRODUCT.description}"/></td>
                        </tr>
                        <tr>
                            <td><label>Product Price:</label></td>
                            <td><input type="text" name="price" value="${THE_PRODUCT.price}"/></td>
                        </tr>
                        <tr>
                            <td><label>Product Discount %:</label></td>
                            <td><input type="text" name="discount" value="${THE_PRODUCT.discount}"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Update" class="update"/></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <div style="clear: both;"></div>
            <p>
                <a href="javascript:history.back()">Go Back</a>
            </p>
        </div>
        
    </body>
    </center>
</html>
