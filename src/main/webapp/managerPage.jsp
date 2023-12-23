<!DOCTYPE html>
<html>

<head>
    <title>Manager Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<% session.removeAttribute("toEdit");  %>

<body>
    <div class="body-text">
        <h1>Manager Dashboard</h1>
    </div>
    <div class="login-register">
        <a href="managerHalls.jsp" class="button">Manage Halls</a>
    </div>

    <div class="login-register">
        <a href="managerMovies.jsp" class="button">Manage Movies</a>
    </div>
    <div class="login-register">
        <a href="managerSessions.jsp" class="button">Manage Sessions</a>
    </div>
    <div class="login-register">
        <a href="fnbInventory.jsp" class="button">Manage Food & Beverages</a>
    </div>
    <div class="login-register">
        <a href="managerReports.jsp" class="button">Manage Report</a>
    </div>
    <div class="login-register">
        <a href="logout" class="button">Logout</a>
    </div>
</body>

</html>