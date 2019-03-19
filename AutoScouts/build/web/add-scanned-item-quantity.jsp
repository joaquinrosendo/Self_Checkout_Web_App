
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Customer Interface - Add Quantity & Item</title>
        
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/add-product-style.css"/>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>  Add Quantity & Item </h2>
            </div>
        </div>
        
        <div id="container">
            <h3>  Add Quantity & Item </h3>
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="BAG_SCANNED_PRODUCT"/>
                <input type="hidden" name="productId" value="${THE_PRODUCT.id}"/>
                <input type="hidden" name="productItem" value="${THE_PRODUCT.item}"/>
                <input type="hidden" name="productPrice" value="${THE_PRODUCT.price}"/>
                        <br/>
                        <label><center>Product Name:</center></label>
                        <b> ${THE_PRODUCT.item}</b>
                        <br/>
                        <br/>
                        <label><center>Product Description: </center></label>
                        <b> ${THE_PRODUCT.description}</b>
                        <br/>
                        <br/>
                        <label><center>Product Price:</center></label>
                        <b>$ ${THE_PRODUCT.price}</b>
                        <br/>
                        <br/>
                        <label>Select Quantity: 
                            <select name="quantity">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                        </label>
                        <br/><br/>
                <input type="submit" value="Submit" class="update"/>
            </form>
            <div style="clear: both;"></div>
            <p>
                <a href="javascript:history.back()">Go Back</a>
            </p>
        </div>
        
    </body>
    </center>
</html>
