
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <center>
    <head>
        <title>AutoScouts</title>
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
    </head>
    
    
    <body align="center">
        <div id="wrapper">
        <div id="header">
            <h2> AutoSCoutS </h2>
        </div>
    </div>
        
        <div id="container">
        <div id="content">
            <form action="ProductInventoryControllerServlet" method="GET">
                <!-- Links to the Manager Interface -->
                <button type="submit" name="command" value="MANAGER_PAGE" class="button"> Manager </button>

                <!-- Links to the Restocker Interface -->
                <button type="submit" name="command" value="RESTOCKER_PAGE" class="button"> Restocker </button>
                
                <br/>
                <!-- Links to the Restocker Interface -->
                <button type="submit" name="command" value="CUSTOMER_PAGE" class="button"> Customer Checkout </button>
            </form>
        </div>
    </div>
    </body>
    </center>
</html>
