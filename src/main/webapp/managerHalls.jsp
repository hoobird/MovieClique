<!DOCTYPE html>
<html>

<head>
    <title>Hall Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.Hall" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Hall> hallList = Hall.getHallList(); %>

    <div class="body-text">
        <h1>Hall Dashboard</h1>
    </div>

    <input type="number" id="srchHall" onkeyup="genericSearch()" placeholder="Search for halls">

    <div class="login-register">
        <a href="managerCreateHall.jsp" class="button">Create</a>
    </div>

    <% Hall editHall = (Hall)session.getAttribute("toEdit"); %>

    <table id="hallDisplay">
        <tr class="header">
            <th>Hall</th>
            <th>Actions</th>
        </tr>
        <% for (Hall hall : hallList) {
            if (editHall != null && hall == editHall) {
                out.println("<tr>" + 
                    "<form action=\"updateHall\">" +
                        "<td><input type=\"number\" placeholder=" + hall.getHallID() + " name=\"saveHall\"></td>" +
                        "<td>" +
                            "<div class = \"login-register\">" +
                                "<button type=\"submit\">Save</button>" +
                                "<a href=\"updateHall\" class=\"button\">Cancel</a>" +
                            "</div>" +
                        "</td>" +
                    "</form>" +
                "</tr>");
            }
            else {
                out.println("<tr>" + 
                    "<td>Hall " + hall.getHallID() + "</td>" +
                    "<td>" +
                        "<div class = \"login-register\">" +
                            "<a href=\"updateHall?editHall=" + hall.getHallID() + "\" class=\"button\">Edit</a>" +
                            "<a href=\"deleteHall?delHall=" + hall.getHallID() + "\" class=\"button\">Delete</a>" + 
                        "</div>" +
                    "</td>" +
                "</tr>");
            }
        } %>
    </table>

    <div class="login-register">
        <a href="managerPage.jsp" class="button">Back</a>
    </div>

    <script>
        function genericSearch() {
            // Declare variables
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("srchHall");
            filter = input.value.toUpperCase();
            table = document.getElementById("hallDisplay");
            tr = table.getElementsByTagName("tr");
            // Loop through every row
            for (i = 0; i < tr.length; i++) {
                found = 0;
                td = tr[i].getElementsByTagName("td");
                // Loop through every column
                for (j = 0; j < td.length; j++) {
                    if (td[j]) {
                        txtValue = td[j].textContent;
                        // If any column in the row matches the search
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                            found++;
                            break;
                        }
                    }
                }
                // If no columns in the row matches the search
                if (found == 0) {
                    tr[i].style.display = "none";
                }
            }
        }
        </script>

</body>

</html>