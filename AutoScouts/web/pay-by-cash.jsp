
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Customer Interface - Pay By Cash</title>
        
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/add-product-style.css"/>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2> Pay By Cash </h2>
            </div>
        </div>
        
        <div id="container">
            <h3> Pay By Cash </h3><br/>
            <label><center>Amount Due:</center></label>
            <b><font color="green">$ ${AMOUNT_DUE}</font></b><br/><br/>
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="VERIFY_CASH"/>
                
                <table>
                    <tbody>
                        <tr>
                            <td><label>Enter Cash Amount:</label></td>
                            <td><b>$ </b><input type="text" name="cash"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Submit" class="add"/></td>
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
