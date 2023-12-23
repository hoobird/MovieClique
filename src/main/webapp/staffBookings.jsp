<!DOCTYPE html>
<html>

<head>
    <title>Booking Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "java.util.HashMap" %>
    <%@ page import = "java.util.Map" %>
    <%@ page import = "Entity.Account" %>
    <%@ page import = "Entity.FnB" %>
    <%@ page import = "Entity.Seat" %>
    <%@ page import = "Entity.Booking" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Booking> bookingList = Booking.getAllBookings(); %>

    <div class="body-text">
        <h1>Booking Dashboard</h1>
    </div>

    <input type="text" id="srchBooks" onkeyup="genericSearch()" placeholder="Search for bookings">

    <div class="login-register">
        <a href="NowShowing.jsp" class="button">Create</a>
    </div>

    <% Booking editBooking = (Booking)session.getAttribute("toEdit"); %>

    <table id="bookingDisplay">
        <tr class="header">
            <th>Booking</th>
            <th>Account</th>
            <th>Session</th>
            <th>Movie</th>
            <th>Hall</th>
            <th>Seats</th>
            <th>Price</th>
            <th>Tickets</th>
            <th>Total</th>
            <th>Loyalty Points Redeemed</th>
            <th>Actions</th>
        </tr>
        <% for (Booking booking : bookingList) {
            out.println("<tr>" + 
                "<td>" + booking.getBookingID() + "</td>" +
                "<td>" + booking.getAccount().getUsername() + "</td>" +
                "<td>" + booking.getSessionBooked().getSessionID() + "</td>" +
                "<td>" + booking.getSessionBooked().getMov().getName() + "</td>" +
                "<td>" + booking.getSessionBooked().getHall().getHallID() + "</td>" +
                "<td>Seats: ");
            for (Seat seat : booking.getSeatsBooked()) {
                out.println(seat.getSeatID() + " ");
            }
            out.println("</td>" +
                "<td>" + booking.getTotal() + "</td>" +
                "<td>Adult: " + booking.getTicketsOfEachType()[0] + " Child: " + booking.getTicketsOfEachType()[1] + " Student: " + booking.getTicketsOfEachType()[2] + " Senior: " + booking.getTicketsOfEachType()[3] + "</td>" +
                "<td>$" + booking.getTotal() + "</td>" +
                "<td>");
            if (booking.isLoyaltyUsed()) {
                out.println("Yes");
            } else {
                out.println("No");
            }
            out.println("</td>" +
                "<td>" +
                    "<div class = \"login-register\">" +
                        "<!--a href=\"completeBooking?completeBooks=" + booking.getBookingID() + "\" class=\"button\">Complete</a-->" +
                        "<a href=\"deleteBooking?delBook=" + booking.getBookingID() + "\" class=\"button\">Cancel</a>" + 
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
            input = document.getElementById("srchBooks");
            filter = input.value.toUpperCase();
            table = document.getElementById("bookingDisplay");
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