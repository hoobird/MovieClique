<!DOCTYPE html>
<html>

<head>
    <title>Movie Dashboard</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <%@ page import = "Entity.Movie" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList<Movie> movieList = Movie.getMovieList(); %>

    <div class="body-text">
        <h1>Movie Dashboard</h1>
    </div>

    <input type="text" id="srchMovie" onkeyup="genericSearch()" placeholder="Search for movies">

    <div class="login-register">
        <a href="managerCreateMovie.jsp" class="button">Create</a>
    </div>

    <% Movie editMovie = (Movie)session.getAttribute("toEdit"); %>

    <table id="movieDisplay">
        <tr class="header">
            <th>Movie</th>
            <th>Name</th>
            <th>Image File</th>
            <th>Description</th>
            <th>Genre</th>
            <th>Actions</th>
        </tr>
        <% for (Movie movie : movieList) {
            if (editMovie != null && movie == editMovie) {
                out.println("<tr>" + 
                    "<form action=\"updateMovie\">" +
                        "<td><input type=\"text\" value=" + movie.getMovieID() + " name=\"saveMovieID\" readonly></td>" +
                        "<td><input type=\"text\" placeholder=" + movie.getName() + " name=\"saveMovieName\"></td>" +
                        "<td><input type=\"text\" placeholder=" + movie.getImgFile() + " name=\"saveMovieImgFile\" disabled></td>" +
                        "<td><input type=\"text\" placeholder=" + movie.getDescription() + " name=\"saveMovieDescription\"></td>" +
                        "<td><div class=\"row\">" +
                            "<div class=\"col-25\"><label for=\"saveMovieGenre\">Genre:</label></div>" +
                            "<div class=\"col-75\"><select type=\"text\" name=\"saveMovieGenre\">" +
                                    "<option value=\"Action\">Action</option>" +
                                    "<option value=\"Adventure\">Adventure</option>" +
                                    "<option value=\"Comedy\">Comedy</option>" +
                                    "<option value=\"Crime\">Crime</option>" +
                                    "<option value=\"Drama\">Drama</option>" +
                                    "<option value=\"Fantasy\">Fantasy</option>" +
                                    "<option value=\"Historical\">Historical</option>" +
                                    "<option value=\"Horror\">Horror</option>" +
                                    "<option value=\"Romance\">Romance</option>" +
                                "</select>" +
                            "</div>" +
                        "</div></td>" +
                        "<td>" +
                            "<div class = \"login-register\">" +
                                "<button type=\"submit\">Save</button>" +
                                "<a href=\"updateMovie\" class=\"button\">Cancel</a>" +
                            "</div>" +
                        "</td>" +
                    "</form>" +
                "</tr>");
            }
            else {
                out.println("<tr>" + 
                    "<td>" + movie.getMovieID() + "</td>" +
                    "<td>" + movie.getName() + "</td>" +
                    "<td><img src=\"Resources\\" + movie.getImgFile() + "\" width=\"50\" height=\"50\"></td>" +
                    "<td>" + movie.getDescription() + "</td>" +
                    "<td>" + movie.getGenre() + "</td>" +
                    "<td>" +
                        "<div class = \"login-register\">" +
                            "<a href=\"updateMovie?editMovie=" + movie.getName() + "\" class=\"button\">Edit</a>" +
                            "<a href=\"deleteMovie?delMovie=" + movie.getMovieID() + "\" class=\"button\">Delete</a>" + 
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
            input = document.getElementById("srchMovie");
            filter = input.value.toUpperCase();
            table = document.getElementById("movieDisplay");
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