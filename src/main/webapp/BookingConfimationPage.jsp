<!DOCTYPE html>
<html>

<head>
    <title>Booking Confirmation</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <%= session.getAttribute("topBannerHTML") %> 
    </div>



    <div class="body-text" >
        <h1>Booking Confirmation</h1>
    </div>
    <%@ page import = "Entity.Session" %>
    <%@ page import = "Entity.Account" %>
    <%@ page import = "Entity.Profile" %>
    <%@ page import = "Entity.CustProfile" %>
    <%@ page import = "java.util.Arrays" %>
    <%@ page import = "java.util.ArrayList" %>
    <% int sessionID = Integer.parseInt(String.valueOf(session.getAttribute("currentBookingSessionID"))); %>
    <% Session currentSession = Session.getSession(sessionID); %>
    
    <div class="login-register-details">
        <% String[] currSessionInfo = (String[]) session.getAttribute("currSessionInfo"); %>
        <% String[] seatsChosenStr = request.getParameterValues("seats"); %>
        <% session.setAttribute("seatsChosen", seatsChosenStr); %>
        <% int[] seatsChosen = new int[seatsChosenStr.length];
        for (int i = 0; i < seatsChosenStr.length; i++) {
            seatsChosen[i] = Integer.parseInt(seatsChosenStr[i]);            
        } %>
        <% int adultQty = Integer.parseInt(request.getParameter("adultQty")); %>
        <% int childQty = Integer.parseInt(request.getParameter("childQty")); %>
        <% int studentQty = Integer.parseInt(request.getParameter("studentQty")); %>
        <% int seniorQty = Integer.parseInt(request.getParameter("seniorQty")); %>
        <% Account acc = Account.getAccount((String)session.getAttribute("sessAccUser")); %>
        <% if (acc.getType() == 1) {
            acc = Account.getAccount(request.getParameter("account"));
        } %>
        <% int loyaltyPoints = ((CustProfile)acc.getUserProfile()).getLoyaltyPoints(); %>
        <% String seatsToDisplay = Arrays.toString(seatsChosen); %>
    
        <form action="ConfirmedBookingServlet" method="POST">
            
           <div class="row">
             
            <label for="account">Account:</label>
            <input type="text" id="account" name="account" value="<%= acc.getUsername() %>" readonly><br>
           </div>
            <div class="row">
            <label for="movie">Movie:</label>
            <input type="text" id="movie" name="movie" value="<%= currSessionInfo[0] %>" readonly><br>
            </div>
            <div class="row">
            <label for="day">Day:</label>
            <input type="text" id="day" name="day" value="<%= currSessionInfo[1] %>" readonly> <br>
            </div>
            <div class="row">
            <label for="timeslot">Time Slot:</label>
            <input type="text" id="timeslot" name="timeslot" value="<%= currSessionInfo[2] %>" readonly> <br>
            </div>
            <div class="row">
            <label for="seats">Seats: </label>
            <input type="text" id="seats" name="seats" value="<%= seatsToDisplay %>" readonly> <br>
            </div>
            <div class="row">    
            <label for="adultQty">Adult Tickets:</label>
            <input type="number" id="adultQty" name="adultQty" value=<%= adultQty %> readonly> <br>
            </div>
            <div class="row">
            <label for="childQty">Child Tickets:</label>
            <input type="number" id="childQty" name="childQty" value=<%= childQty %> readonly> <br>
            </div>
            <div class="row">
            <label for="studentQty">Student Tickets:</label>
            <input type="number" id="studentQty" name="studentQty" value=<%= studentQty %> readonly> <br>
            </div>
            <div class="row">
            <label for="seniorQty">Senior Tickets:</label>
            <input type="number" id="seniorQty" name="seniorQty" value=<%= seniorQty %> readonly> <br>
            </div>
             <div class="row">   
            <label for="total">Total Amount Payable:</label>
            <input type="number" id="total" name="total" readonly> <br>
            </div>
            <div class="row">    
            <label for="useLP">Use Loyalty Points ( <%= loyaltyPoints %> )</label>
            <input type="checkbox" id="useLP" name="useLP" <% if (loyaltyPoints < 10) { %>disabled<% } %>><br>
            </div>
            <div class="row">
            <input type="submit" value="Submit">
            </div>
        </form>
    </div>


</body>

<!-- JavaScript code -->
<script>
    function calculateTotal() {
        var adultQty = parseInt(document.getElementById('adultQty').value);
        var childQty = parseInt(document.getElementById('childQty').value);
        var studentQty = parseInt(document.getElementById('studentQty').value);
        var seniorQty = parseInt(document.getElementById('seniorQty').value);
        var useLP = document.getElementById('useLP').checked;
        var total;

        if (useLP) {
            total = (adultQty * 15 + childQty * 5 + studentQty * 10 + seniorQty * 5) * 0.9;
        } else {
            total = adultQty * 15 + childQty * 5 + studentQty * 10 + seniorQty * 5;
        }

        document.getElementById('total').value = total;
    }

    // Attach an event listener to the checkbox
    document.getElementById('useLP').addEventListener('change', calculateTotal);
    
    // calculate total on load
    calculateTotal();
</script>

<footer>
    
</footer>

</html>