<!DOCTYPE html>
<html>

<head>
    <title>Movie Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.Account" %>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <% Account acc = Account.getAccount((String)session.getAttribute("sessAccUser"));
        if (acc.getType() == 0) {
            out.println(session.getAttribute("topBannerHTML"));
        } %>
    </div>

    <div class="movie-details">
        <%= session.getAttribute("movieDetailHtml") %>
    </div>

    <div class="sessions-for-movie">

        <%= session.getAttribute("movieSessionsHtml") %>

    </div>

    <% if (acc.getType() == 1) {
        out.println("<div class=\"login-register\">" +
            "<a href=\"staffBookings.jsp\" class=\"button\">Back</a>" +
        "</div>");
    } %>
</body>

<footer>

</footer>

</html>
