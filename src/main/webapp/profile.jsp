<!DOCTYPE html>
<html>

<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <%= session.getAttribute("topBannerHTML") %> 
    </div>



    <div class="body-text">
        <h1>My Profile</h1>
    </div>

    <%@ page import = "Entity.Account" %>
    <%@ page import = "Entity.Profile" %>
    <%@ page import = "Entity.CustProfile" %>
    <% Account userAccount= Account.getAccount((String)session.getAttribute("sessAccUser")); %>
    <% Profile userProfile= userAccount.getUserProfile(); %>

    <div class="login-register-details">
        <form action = "updateAccount" method="post">
        <!-- Account -->
        <div class="row">
            <div class="col-25"><label for="uname">Username:</label></div>
            <div class="col-75"><input type="text" placeholder="Username" name="uname" value = <%= userAccount.getUsername() %> disabled></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="pword">Password:</label></div>
            <div class="col-75"><input type="text" placeholder="Password" name="pword" value = <%= userAccount.getPassword() %> required></div>
        </div>
        <!--Profile  -->
        <div class="row">
            <div class="col-25"><label for="fname">First Name:</label></div>
            <div class="col-75"><input type="text" placeholder="First Name" name="fname" value = <%= userProfile.getFirstName() %> required></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="lname">Last Name:</label></div>
            <div class="col-75"><input type="text" placeholder="Last Name" name="lname" value = <%= userProfile.getLastName() %> required></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="gender">Gender:</label></div>
            <div class="col-75"><select name="gender" required>
                    <option value="Male" <% if (userProfile.getGender().equals("Male")) out.print("selected=\"selected\"") ;%>>Male</option>
                    <option value="Female"<% if (userProfile.getGender().equals("Female")) out.print("selected=\"selected\"") ;%>>Female</option>
                </select></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="age">Age:</label></div>
            <div class="col-75"><input type="number" name="age" value = <%= userProfile.getAge() %> required></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="phone">Phone Number:</label></div>
            <div class="col-75"><input type="text" placeholder="+65 9876 5432" name="phone" value = <%= userProfile.getPhoneNo() %> required></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="email">Email:</label></div>
            <div class="col-75"><input type="text" placeholder="anEmail@somemail.com" name="email" value = <%= userProfile.getEmail() %> required></div>
        </div>
        <!--Maybe reuse this page for staff by hiding the genre drop-down for staff-->
        <!--Maybe somehow pull options from genreList in CustProfile class-->
        <div class="row">
            <div class="col-25"><label for="genre">Favourite Genre:</label></div>
            <div class="col-75"><select name="genre">
                    <option value="Action" <% if (((CustProfile)(userProfile)).getGenre().equals("Action")) out.print("selected=\"selected\"") ;%>>Action</option>
                    <option value="Adventure"<% if (((CustProfile)(userProfile)).getGenre().equals("Adventure")) out.print("selected=\"selected\"") ;%>>Adventure</option>
                    <option value="Comedy"<% if (((CustProfile)(userProfile)).getGenre().equals("Comedy")) out.print("selected=\"selected\"") ;%>>Comedy</option>
                    <option value="Crime"<% if (((CustProfile)(userProfile)).getGenre().equals("Crime")) out.print("selected=\"selected\"") ;%>>Crime</option>
                    <option value="Drama"<% if (((CustProfile)(userProfile)).getGenre().equals("Drama")) out.print("selected=\"selected\"") ;%>>Drama</option>
                    <option value="Fantasy"<% if (((CustProfile)(userProfile)).getGenre().equals("Fantasy")) out.print("selected=\"selected\"") ;%>>Fantasy</option>
                    <option value="Historical"<% if (((CustProfile)(userProfile)).getGenre().equals("Historical")) out.print("selected=\"selected\"") ;%>>Historical</option>
                    <option value="Horror"<% if (((CustProfile)(userProfile)).getGenre().equals("Horror")) out.print("selected=\"selected\"") ;%>>Horror</option>
                    <option value="Romance"<% if (((CustProfile)(userProfile)).getGenre().equals("Romance")) out.print("selected=\"selected\"") ;%>>Romance</option>
                </select></div>
        </div>
        <div class="row">
            <div class="col-25"><label for="loyaltyPoints">Loyalty Points:</label></div>
            <div class="col-75"><input type="number" name="loyaltyPoints" value = <%= ((CustProfile)userAccount.getUserProfile()).getLoyaltyPoints() %> disabled></div>
        </div>
        <div class="row">
            <button type="submit">Save Changes</button>
        </div>
    </form>
    </div>
</body>
<footer>
    
</footer>

</html>