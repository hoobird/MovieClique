<!DOCTYPE html>
<html>

<head>
    <title>Admin Create Account</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<%@ page import = "Entity.Profile" %>
<%@ page import = "Entity.CustProfile" %>
<body>
    <div class="body-text">
        <h1>Admin Create Account</h1>
    </div>


    <div class="login-register-details">
        <form method="post" action="adminAddAccount">
            <div class="row">
                <div class="col-25"><label for="uname">Username:</label></div>
                <div class="col-75"><input type="text" placeholder="Username" name="uname" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="pword">Password:</label></div>
                <div class="col-75"><input type="text" placeholder="Password" name="pword" required></div>
            </div>
            <div class="row" >
                <div class="col-25"><label for="type">Account Type:</label></div>
                <div class="col-75"><select name="type" required onchange="toggleGenre(this)">
                        <option value="0">Customer</option>
                        <option value="1">Staff</option>
                        <option value="2">Manager</option>
                        <option value="3">Admin</option>
                    </select>
                </div>
            </div>
            <!-- For Profile -->
            <div class="row">
                <div class="col-25"><label for="fname">First Name:</label></div>
                <div class="col-75"><input type="text" placeholder="First Name" name="fname" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="lname">Last Name:</label></div>
                <div class="col-75"><input type="text" placeholder="Last Name" name="lname" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="gender">Gender:</label></div>
                <div class="col-75"><select name="gender" required>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="age">Age:</label></div>
                <div class="col-75"><input type="number" name="age" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="phone">Phone Number:</label></div>
                <div class="col-75"><input type="text" placeholder="+65 9876 5432" name="phone" required></div>
            </div>
            <div class="row">
                <div class="col-25"><label for="email">Email:</label></div>
                <div class="col-75"><input type="text" placeholder="anEmail@somemail.com" name="email" required></div>
            </div>
            <div class="row" id="genreRow" class="row">
                <div class="col-25"><label for="genre">Favourite Genre:</label></div>
                <div class="col-75"><select name="genre">
                        <option value="Action" >Action</option>
                        <option value="Adventure">Adventure</option>
                        <option value="Comedy">Comedy</option>
                        <option value="Crime">Crime</option>
                        <option value="Drama">Drama</option>
                        <option value="Fantasy">Fantasy</option>
                        <option value="Historical">Historical</option>
                        <option value="Horror">Horror</option>
                        <option value="Romance">Romance</option>
                    </select></div>
            </div>

            <div class="row">
                <button type="submit">Create Account</button>
            </div>

            <div class="login-register">
                <a href="adminPage.jsp" class="button">Back</a>
            </div>

        </form>
    </div>
</body>
<script>
    function toggleGenre(select) {
        var genreRow = document.getElementById("genreRow");
        genreRow.style.display = (select.value === "0") ? "block" : "none";
    }
</script>

</html>