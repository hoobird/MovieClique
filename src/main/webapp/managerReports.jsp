<!DOCTYPE html>
<html>

<head>
    <title>Report Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.Report" %>
    <%@ page import = "java.util.ArrayList" %>
    <%  ArrayList<Report> reportList = Report.getReportList(); %>

    <div class="body-text">
        <h1>Report Dashboard</h1>
    </div>

    <div class="login-register">
        <a href="generateReportServlet" class="button">Generate Report</a>
    </div>

    <table id="reportDisplay">
            <tr>
                <th>Report ID</th>
                <th>Report Creation Date</th>
                <th>Actions</th>
            </tr>
              <% for (Report report : reportList) { %>
            <tr>
                <td><%= report.getReportID() %></td>
                <td><%= report.getDateOfCreation() %></td>
                <td>
                    <a href="viewReportServlet?reportID=<%= report.getReportID() %>">View</a>
                    <a href="deleteReportServlet?reportID=<%= report.getReportID() %>">Delete</a>
                </td>
            </tr>
            <% } %>
    </table>

    <div class="login-register">
        <a href="managerPage.jsp" class="button">Back</a>
    </div>

</body>

</html>