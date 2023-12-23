<!DOCTYPE html>
<html>

<head>
    <title>Staff Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<% session.removeAttribute("toEdit");  %>

<body>
    <div class="body-text">
        <h1>Staff Dashboard</h1>
    </div>
    <div class="login-register">
        <a href="fnbInventory.jsp" class="button">Manage Food & Beverages</a>
    </div>
    <div class="login-register">
        <a href="staffBookings.jsp" class="button">Manage Bookings</a>
    </div>
    <div class="login-register">
        <a href="staffTransactions.jsp" class="button">Manage Transactions</a>
    </div>
    <div class="login-register">
        <a href="logout"><button>Logout</button></a>
    </div>
</body>

</html>