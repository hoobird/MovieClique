<!DOCTYPE html>
<html>

<head>
    <title>My Bookings</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <%= session.getAttribute("topBannerHTML") %> 
    </div>



    <div class="body-text">
        <h1>My Bookings</h1>
    </div>

    
    <div class="my-bookings-table">
        <%= session.getAttribute("myBookingsTable") %>
    </div>
</body>
<footer>
    
</footer>

</html>