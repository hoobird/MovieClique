<!DOCTYPE html>
<html>

<head>
    <title>Manager Create Hall</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>Manager Create Hall</h1>
    </div>


    <div class="login-register-details">
        <form method="post" action="createHall">
            <div class="row">
                <div class="col-25"><label for="hID">Hall Number:</label></div>
                <div class="col-75"><input type="number" placeholder="Enter Hall Number" name="hID" required></div>
            </div>
            <!--
            <div class="row">
                <div class="col-25"><label for="hNo">Number of Hall Seats:</label></div>
                <div class="col-75"><input type="number" value="50" name="hNo"></div>
            </div>
            -->
            <div class="row">
                <button type="submit">Create Hall</button>
            </div>

            <div class="login-register">
                <a href="managerHalls.jsp" class="button">Back</a>
            </div>

        </form>
    </div>
</body>
<footer>

</footer>

</html>