
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Restocker Interface</title>
        
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/add-product-style.css"/>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2> Restocker Interface </h2>
            </div>
        </div>
        
        <div id="container">
            <h3> Restock Item </h3>
            
            <form action="ProductInventoryControllerServlet" method="POST">
                <input type="hidden" name="command" value="ITEM_RESTOCK"/>
                
                <table>
                    <tbody>
                        <tr>
                            <td><label>Enter Item to be Restocked:</label></td>
                            <td><input type="text" name="item"/></td>
                        </tr>
                        <tr>
                            <td><label>Enter Quantity:</label></td>
                            <td><input type="text" name="quantity"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Restock" class="add"/></td>
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
