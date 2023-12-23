<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Now Showing</title>
    <link rel="stylesheet" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.Account" %>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <% Account acc = Account.getAccount((String)session.getAttribute("sessAccUser"));
        if (acc ==null || acc.getType() == 0) {
            out.println(session.getAttribute("topBannerHTML"));
        } %>
    </div>


    <div class="body-text">
        <h1>Now Showing</h1>
    </div>
    

    <div class="movie-posters">
        <jsp:include page="/getmv" />
    </div>

    <%if (acc !=null){ 
    if (acc.getType() == 1) {
        out.println("<div class=\"login-register\">" +
            "<a href=\"staffBookings.jsp\" class=\"button\">Back</a>" +
        "</div>");
    } }%>

    <footer>
        <!-- footer content here -->
    </footer>
</body>

</html>
