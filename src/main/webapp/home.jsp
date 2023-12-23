<!DOCTYPE html>
<html>

<head>
    <title>MovieClique</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <%= session.getAttribute("topBannerHTML") %> 
    </div>



    <div class="body-text">
        <h1>Welcome to MovieClique</h1>
    </div>

    <% if (session.getAttribute("sessAccUser") ==null) {
        out.println("<div class=\"login-register\"><a href=\"login.jsp\" class=\"button\">Login</a><a href=\"register.jsp\" class=\"button\">Register</a></div>");
    }  %>
    
</body>
<footer>
    
</footer>

</html>