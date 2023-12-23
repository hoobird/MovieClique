<!DOCTYPE html>
<html>
<!-- genre is not displayed yet for cust -->

<head>
    <title>View Report</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>View Report</h1>
    </div>

    <div class="login-register-details">

        <div class="login-register">
            <%= ((String)session.getAttribute("reportDetails")).replace("\n","<br>") %>
        </div>

        <div class="login-register">
            <a href="managerReports.jsp" class="button">Back</a>
        </div>

    </div>
</body>
<footer>

</footer>

</html>