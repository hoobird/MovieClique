<!DOCTYPE html>
<html>

<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <%= session.getAttribute("topBannerHTML") %>
    </div>



    <div class="body-text">
        <h1>Login</h1>
    </div>


    <div class="login-register-details">
        <form action="login" method="post">
            <div class="row">
                <div class="col-25"><label for="uname">Username:</label></div>
                <div class="col-75"><input type="text" placeholder="Username" name="username" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="pword">Password:</label></div>
                <div class="col-75"><input type="password" placeholder="Password" name="password" required></div>
            </div>
            <div class="row">
                <button type="submit">Login</button>
            </div>
        </form>
    </div>
</body>
<footer>
</footer>

</html>