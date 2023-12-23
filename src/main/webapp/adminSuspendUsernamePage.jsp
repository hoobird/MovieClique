<!DOCTYPE html>
<html>

<head>
    <title>Suspend User</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="body-text">
        <h1>Suspend User</h1>
    </div>


    <div class="login-register-details">
        <!-- action will depend called from which servlet -> (action) -->
        <form action = "adminSuspendAccount"   method="post">
            <div class="row">
                <div class="col-25"><label for="userSuspend">Username: </label></div>
                <div class="col-75"><input type="text" name="userSuspend" required></div>
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