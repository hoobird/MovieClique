<!DOCTYPE html>
<html>

<head>
    <title>Session Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.Hall" %>
    <%@ page import = "Entity.Movie" %>
    <%@ page import = "Entity.Session" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Hall> hallList = Hall.getHallList(); %>
    <% ArrayList<Movie> movieList = Movie.getMovieList(); %>
    <% ArrayList<Session> sessionList = Session.getSessionList(); %>
    <% String[] days = Session.getDaysInWeek(); %>
    <% String[] slots = Session.getTimeSlots(); %>

    <div class="body-text">
        <h1>Session Dashboard</h1>
    </div>

    <input type="text" id="srchSess" onkeyup="genericSearch()" placeholder="Search for sessions">

    <div class="login-register">
        <a href="managerCreateSession.jsp" class="button">Create</a>
    </div>

    <% Session editSession = (Session)session.getAttribute("toEdit"); %>

    <table id="sessDisplay">
        <tr class="header">
            <th>Session</th>
            <th>Hall</th>
            <th>Movie</th>
            <th>Day</th>
            <th>Slot</th>
            <th>Actions</th>
        </tr>
        <% for (Session sess : sessionList) {
            if (editSession != null && sess == editSession) {
                out.println("<tr>" + 
                    "<form action=\"updateSession\">" +
                        "<td><input type=\"text\" value=" + sess.getSessionID() + " name=\"saveSessID\" readonly></td>" +
                        "<td><div class=\"row\">" +
                            "<div class=\"col-25\"><label for=\"hID\">Hall Number:</label></div>" +
                            "<div class=\"col-75\"><select type=\"number\" name=\"saveSessHallID\">");
                for(Hall hall : hallList) {
                                out.println("<option value=\"" + hall.getHallID() + "\">Hall " + hall.getHallID() + "</option>");
                }
                out.println("</select>" +
                            "</div>" +
                        "</div></td>" +
                        "<td><div class=\"row\">" +
                            "<div class=\"col-25\"><label for=\"saveSessMovieName\">Movie:</label></div>" +
                            "<div class=\"col-75\"><select type=\"text\"  name=\"saveSessMovieName\">");
                for(Movie movie : movieList) {
                    if (movie == sess.getMov()) {
                                out.println("<option value=\"" + movie.getName() + "\" selected>" + movie.getName() + "</option>");
                    } else {
                                out.println("<option value=\"" + movie.getName() + "\">" + movie.getName() + "</option>");
                    }
                }
                out.println("</select>" +
                            "</div>" +
                        "</div></td>" +
                        "<td><div class=\"row\">" +
                            "<div class=\"col-25\"><label for=\"saveSessDay\">Day:</label></div>" +
                            "<div class=\"col-75\"><select type=\"number\" name=\"saveSessDay\">");
                int i = 0;
                for(String day : days) {
                    i++;
                    if (i == sess.getDay()) {
                                out.println("<option value=\"" + i + "\" selected>" + day + "</option>");
                    } else {
                                out.println("<option value=\"" + i + "\">" + day + "</option>");
                    }
                }
                out.println("</select>" +
                            "</div>" +
                        "</div></td>" +
                        "<td><div class=\"row\">" +
                            "<div class=\"col-25\"><label for=\"saveSessSlot\">Session:</label></div>" +
                            "<div class=\"col-75\"><select type=\"number\" name=\"saveSessSlot\">");
                i = 0;
                for(String slot : slots) {
                    i++;
                    if (i == sess.getSlot()) {
                                out.println("<option value=\"" + i + "\" selected>" + slot + "</option>");
                    } else {
                                out.println("<option value=\"" + i + "\">" + slot + "</option>");
                    }
                }
                out.println("</select>" +
                            "</div>" +
                        "</div></td>" +
                        "<td>" +
                            "<div class = \"login-register\">" +
                                "<button type=\"submit\">Save</button>" +
                                "<a href=\"updateSession\" class=\"button\">Cancel</a>" +
                            "</div>" +
                        "</td>" +
                    "</form>" +
                "</tr>");
            }
            else {
                out.println("<tr>" + 
                    "<td>" + sess.getSessionID() + "</td>" +
                    "<td>Hall " + sess.getHall().getHallID() + "</td>" +
                    "<td>" + sess.getMov().getName() + "</td>" +
                    "<td>" + days[sess.getDay() - 1] + "</td>" +
                    "<td>" + slots[sess.getSlot() - 1] + "</td>" +
                    "<td>" +
                        "<div class = \"login-register\">" +
                            "<a href=\"updateSession?editSess=" + sess.getSessionID() + "\" class=\"button\">Edit</a>" +
                            "<a href=\"deleteSession?delSess=" + sess.getSessionID() + "\" class=\"button\">Delete</a>" + 
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
            input = document.getElementById("srchSess");
            filter = input.value.toUpperCase();
            table = document.getElementById("sessDisplay");
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