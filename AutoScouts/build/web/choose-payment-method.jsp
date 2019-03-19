
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>Customer Interface - Choose Payment Method</title>
        
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/add-product-style.css"/>
        
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>  Choose Payment Method </h2>
            </div>
        </div>
        
        <div id="container">
            <h3>  Choose Payment Method </h3>
            <br/>
            
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="CASH_SELECTED"/>
                <input type="submit" value="Cash" class="update"/>
            </form>
            <br/><br/>
            
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="CREDIT_SELECTED"/>
                <input type="submit" value="Credit" class="update"/>
            </form>
            <br/><br/>
            
            <form action="ProductInventoryControllerServlet" method="GET">
                <input type="hidden" name="command" value="DEBIT_SELECTED"/>
                <input type="submit" value="Debit" class="update"/>
            </form>
            <br/>
            <h3><center>Or</center></h3>
            <br/>

            
            <div style="clear: both;"></div>
            <p>
                <a href="javascript:history.back()">Go Back</a>
            </p>
        </div>
        
    </body>
    </center>
</html>
