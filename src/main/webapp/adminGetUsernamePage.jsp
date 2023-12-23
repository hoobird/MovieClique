<!DOCTYPE html>
<html>

<head>
    <title>Get Username</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="body-text">
        <h1>Get Username</h1>
    </div>


    <div class="login-register-details">
        <!-- action will depend called from which servlet -> (action) -->
        <form action = "adminReadServlet"   method="post">
            <div class="row">
                <div class="col-25"><label for="userRead">Username: </label></div>
                <div class="col-75"><input type="text" name="userRead" required></div>
            </div>
            <div class="row">
                <button type="submit">Submit</button>
            </div>
        </form>
    </div>
</body>
<footer>
    
</footer>

</html>