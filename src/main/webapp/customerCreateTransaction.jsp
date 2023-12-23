<!DOCTYPE html>
<html>

<head>
    <title>Customer Create Transaction</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>Customer Create Transaction</h1>
    </div>

    <%@ page import = "Entity.Account" %>
    <%@ page import = "Entity.FnB" %>
    <%@ page import = "Entity.Transaction" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Account> accountList = Account.getAccountList(); %>
    <% ArrayList<FnB> fnbList = FnB.getFnbList(); %>
    <% ArrayList<Transaction> transactionList = Transaction.getAllTransactions(); %>

    <div class="login-register-details">
        <form method="post" action="createTransaction">
            <div class="row">
                <div class="col-25"><label for="account">Account Username:</label></div>
                <% out.println("<div class=\"col-75\"><input type=\"text\" name=\"account\" value=\"" + (Account.getAccount((String)session.getAttribute("sessAccUser"))).getUsername() + "\" required readonly>"); %>
                </select>
                </div>
            </div>

            <% for (FnB item : fnbList) {
                out.println("<div class=\"row\">" +
                    "<div class=\"col-25\"><label for=" + item.getFnbID() + ">" + item.getName() + "</label></div>" +
                    "<div class=\"col-75\"><input type=\"number\"  name=" + item.getFnbID() + " min=\"0\" max=\"" + item.getStock() + "\"></div>" +
                "</div>");
            } %>

            <div class="row">
                <button type="submit">Create</button>
            </div>

            <div class="login-register">
                <a href="customerTransactions.jsp" class="button">Back</a>
            </div>

        </form>


    </div>
</body>
<footer>

</footer>

</html>