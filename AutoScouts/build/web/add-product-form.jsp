
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Manager Interface - Add Product</title>
        
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
            <h3> Add Product </h3>
            
            <form action="ProductInventoryControllerServlet" method="POST">
                <input type="hidden" name="command" value="ADD"/>
                
                <table>
                    <tbody>
                        <tr>
                            <td><label>Product Name:</label></td>
                            <td><input type="text" name="item"/></td>
                        </tr>
                        <tr>
                            <td><label>Product Description:</label></td>
                            <td><input type="text" name="description"/></td>
                        </tr>
                        <tr>
                            <td><label>Product Price:</label></td>
                            <td><input type="text" name="price"/></td>
                        </tr>
                        <tr>
                            <td><label>Product Discount %:</label></td>
                            <td><input type="text" name="discount"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Add" class="add"/></td>
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
