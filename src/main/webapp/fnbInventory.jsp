<!DOCTYPE html>
<html>

<head>
    <title>F & B Inventory</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.FnB" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<FnB> fnbList = FnB.getFnbList(); %>

    <div class="body-text">
        <h1>F & B Inventory</h1>
    </div>

    <input type="text" id="srchFnb" onkeyup="genericSearch()" placeholder="Search for items">

    <div class="login-register">
        <a href="fnbCreate.jsp" class="button">Create</a>
    </div>

    <% FnB editFnb = (FnB)session.getAttribute("toEdit"); %>

    <table id="fnbDisplay">
        <tr class="header">
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Actions</th>
        </tr>
        <% for (FnB fnb : fnbList) {
            if (editFnb != null && fnb == editFnb) {
                out.println("<tr>" + 
                    "<form action=\"updateFnb\">" +
                        "<td><input type=\"number\" value=" + fnb.getFnbID() + " name=\"saveFnbID\" readonly></td>" +
                        "<td><input type=\"text\" placeholder=" + fnb.getName() + " name=\"saveFnbName\"></td>" +
                        "<td><input type=\"number\" placeholder=" + fnb.getPrice() + " step=\"0.01\"  name=\"saveFnbPrice\"></td>" +
                        "<td><input type=\"number\" placeholder=" + fnb.getStock() + " name=\"saveFnbStock\"></td>" +
                        "<td>" +
                            "<div class = \"login-register\">" +
                                "<button type=\"submit\">Save</button>" +
                                "<a href=\"updateFnb\" class=\"button\">Cancel</a>" +
                            "</div>" +
                        "</td>" +
                    "</form>" +
                "</tr>");
            }
            else {
                out.println("<tr>" + 
                    "<td>" + fnb.getFnbID() + "</td>" +
                    "<td>" + fnb.getName() + "</td>" +
                    "<td>$" + fnb.getPrice() + "</td>" +
                    "<td>" + fnb.getStock() + "</td>" +
                    "<td>" +
                        "<div class = \"login-register\">" +
                            "<a href=\"updateFnb?editFnb=" + fnb.getFnbID() + "\" class=\"button\">Edit</a>" +
                            "<a href=\"deleteFnb?delFnb=" + fnb.getFnbID() + "\" class=\"button\">Delete</a>" + 
                        "</div>" +
                    "</td>" +
                "</tr>");
            }
        } %>
    </table>

    <%@ page import = "Entity.Account" %>
    <% int userType = (Account.getAccount((String)session.getAttribute("sessAccUser"))).getType();
    if (userType == 1) {
        out.println("<div class=\"login-register\">" +
            "<a href=\"staffPage.jsp\" class=\"button\">Back</a>" +
        "</div>");
    } else if (userType == 2) {
        out.println("<div class=\"login-register\">" +
            "<a href=\"managerPage.jsp\" class=\"button\">Back</a>" +
        "</div>");
    } %>

    <script>
        function genericSearch() {
            // Declare variables
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("srchFnb");
            filter = input.value.toUpperCase();
            table = document.getElementById("fnbDisplay");
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
