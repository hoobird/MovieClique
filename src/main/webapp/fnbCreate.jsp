<!DOCTYPE html>
<html>

<head>
    <title>Create F & B Item</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>Create F & B Item</h1>
    </div>


    <div class="login-register-details">
        <form method="post" action="createFnb">
            <div class="row">
                <div class="col-25"><label for="fnbName">Item Name:</label></div>
                <div class="col-75"><input type="text" placeholder="Enter Item Name" name="fnbName" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="fnbPrice">Price:</label></div>
                <div class="col-75"><input type="number" step="0.01" placeholder="Enter Item Price" name="fnbPrice" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="fnbStock">Item Stock:</label></div>
                <div class="col-75"><input type="number" placeholder="Enter Item Stock" name="fnbStock" required></div>
            </div>
            <div class="row">
                <button type="submit">Create Item</button>
            </div>

            <div class="login-register">
                <a href="fnbInventory.jsp" class="button">Back</a>
            </div>

        </form>
    </div>
</body>
<footer>

</footer>

</html>