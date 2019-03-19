
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Customer Interface - Pay By Debit Card</title>
        
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/add-product-style.css"/>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2> Pay By Debit Card </h2>
            </div>
        </div>
        
        <div id="container">
            <h3> Pay By Debit Card </h3><br/>
            <label><center>Amount Due:</center></label>
            <b><font color="green">$ ${AMOUNT_DUE}</font></b><br/><br/>
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="VERIFY_DEBIT"/>
                
                <table>
                    <tbody>
                        <tr>
                            <td><label>Enter Debit Card Number:</label></td>
                            <td><b></b><input type="text" name="debit"/></td>
                        </tr>
                        <tr>
                            <td><label>Enter Pin Number:</label></td>
                            <td><b></b><input type="text" name="pin"/></td>
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
