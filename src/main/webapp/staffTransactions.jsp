<!DOCTYPE html>
<html>

<head>
    <title>Session Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "java.util.HashMap" %>
    <%@ page import = "java.util.Map" %>
    <%@ page import = "Entity.Account" %>
    <%@ page import = "Entity.FnB" %>
    <%@ page import = "Entity.Transaction" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Transaction> transactionList = Transaction.getAllTransactions(); %>

    <div class="body-text">
        <h1>Transaction Dashboard</h1>
    </div>

    <input type="text" id="srchTrans" onkeyup="genericSearch()" placeholder="Search for transactions">

    <div class="login-register">
        <a href="staffCreateTransaction.jsp" class="button">Create</a>
    </div>

    <% Transaction editTransaction = (Transaction)session.getAttribute("toEdit"); %>

    <table id="transactionDisplay">
        <tr class="header">
            <th>Transaction</th>
            <th>Account</th>
            <th>Order Quantity</th>
            <th>Ammount due</th>
            <th>Actions</th>
        </tr>
        <% for (Transaction transaction : transactionList) {
            out.println("<tr>" + 
                "<td>" + transaction.getTransactionID() + "</td>" +
                "<td>" + transaction.getAccount().getUsername() + "</td>" +
                "<td>");
            for (Map.Entry<FnB, Integer> me : (transaction.getOrderQty()).entrySet()) {
                out.println(me.getKey().getName() + ": " + me.getValue());
            }
            out.println("</td>" +
                "<td>$" + transaction.getTotal() + "</td>" +
                "<td>" +
                    "<div class = \"login-register\">" +
                        "<!--a href=\"completeTransaction?completeTrans=" + transaction.getTransactionID() + "\" class=\"button\">Complete</a-->" +
                        "<a href=\"deleteTransaction?delTrans=" + transaction.getTransactionID() + "\" class=\"button\">Cancel</a>" + 
                    "</div>" +
                "</td>" +
            "</tr>");
        } %>
    </table>

    <div class="login-register">
        <a href="staffPage.jsp" class="button">Back</a>
    </div>

    <script>
        function genericSearch() {
            // Declare variables
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("srchTrans");
            filter = input.value.toUpperCase();
            table = document.getElementById("transactionDisplay");
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