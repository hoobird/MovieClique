<!DOCTYPE html>
<html>

<head>
    <title>Manager Create Session</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>Manager Create Session</h1>
    </div>

    <%@ page import = "Entity.Hall" %>
    <%@ page import = "Entity.Movie" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Hall> hallList = Hall.getHallList(); %>
    <% ArrayList<Movie> movieList = Movie.getMovieList(); %>

    <div class="login-register-details">
        <form method="post" action="createSession">
            <div class="row">
                <div class="col-25"><label for="hID">Hall Number:</label></div>
                <div class="col-75"><select type="number" name="hID" required>
                    <% for(Hall hall : hallList) {
                        out.println("<option value=\"" + hall.getHallID() + "\">Hall " + hall.getHallID() + "</option>");
                    } %>  
                </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25"><label for="mov">Movie:</label></div>
                <div class="col-75"><select type="text"  name="mov" required>
                    <% for(Movie movie : movieList) {
                        out.println("<option value=\"" + movie.getName() + "\">" + movie.getName() + "</option>");
                    } %>  
                </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25"><label for="day">Day:</label></div>
                <div class="col-75"><select type="number" name="day" required>
                        <option value="1" selected="selected">Monday</option>
                        <option value="2">Tuesday</option>
                        <option value="3">Wednesday</option>
                        <option value="4">Thursday</option>
                        <option value="5">Friday</option>
                        <option value="6">Saturday</option>
                        <option value="7">Sunday</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="col-25"><label for="slot">Session:</label></div>
                <div class="col-75"><select type="number" name="slot" required>
                        <option value="1" selected="selected">0800 - 1000</option>
                        <option value="2">1100 - 1300</option>
                        <option value="3">1400 - 1600</option>
                        <option value="4">1700 - 1900</option>
                        <option value="5">2000 - 2200</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <button type="submit">Create Session</button>
            </div>

            <div class="login-register">
                <a href="managerSessions.jsp" class="button">Back</a>
            </div>

        </form>


    </div>
</body>
<footer>

</footer>

</html>