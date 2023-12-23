<!DOCTYPE html>
<html>

<head>
    <title>Staff Create Transaction</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>Staff Create Transaction</h1>
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
                <div class="col-75"><select type="text" name="account" required>
                    <% for(Account account : accountList) {
                        if (account.getType() == 0) {
                            out.println("<option value=\"" + account.getUsername() + "\">" + account.getUsername() + "</option>");
                        }
                    } %>  
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
                <a href="staffTransactions.jsp" class="button">Back</a>
            </div>

        </form>


    </div>
</body>
<footer>

</footer>

</html>