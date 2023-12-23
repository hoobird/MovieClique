<!DOCTYPE html>
<html>

<head>
    <title>Admin Search Query</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="body-text">
        <h1>Admin Search Query</h1>
    </div>


    <div class="login-register-details">
        <form method="post" action="adminSearchAccount">

            <input type="checkbox" id="cusQ" name="adminQuery" value="0" style="width:auto">
            <label for="cusQ"> Customer</label>
            <input type="checkbox" id="staffQ" name="adminQuery" value="1" style="width:auto">
            <label for="staffQ"> Staff</label>
            <input type="checkbox" id="managerQ" name="adminQuery" value="2" style="width:auto">
            <label for="managerQ"> Manager</label>
            <input type="checkbox" id="adminQ" name="adminQuery" value="3" style="width:auto">
            <label for="adminQ"> Admin</label>

            
            <div id="genreCB" style="display:none;">
                <input type="checkbox" id="Action" name="adminCQuery" value="Action" style="width:auto">
                <label for="Action">Action</label>
                <input type="checkbox" id="Adventure" name="adminCQuery" value="Adventure" style="width:auto">
                <label for="Adventure">Adventure</label>
                <input type="checkbox" id="Comedy" name="adminCQuery" value="Comedy" style="width:auto">
                <label for="Comedy">Comedy</label>
                <input type="checkbox" id="Crime" name="adminCQuery" value="Crime" style="width:auto">
                <label for="Crime">Crime</label>
                <input type="checkbox" id="Drama" name="adminCQuery" value="Drama" style="width:auto">
                <label for="Drama">Drama</label>
                <input type="checkbox" id="Fantasy" name="adminCQuery" value="Fantasy" style="width:auto">
                <label for="Fantasy">Fantasy</label>
                <input type="checkbox" id="Historical" name="adminCQuery" value="Historical" style="width:auto">
                <label for="Historical">Historical</label> <br>
                <input type="checkbox" id="Horror" name="adminCQuery" value="Horror" style="width:auto">
                <label for="Horror">Horror</label>
                <input type="checkbox" id="Romance" name="adminCQuery" value="Romance" style="width:auto">
                <label for="Romance">Romance</label>
            </div>

            <div class="row">
                <button type="Submit">Search</button>
            </div>

        </form>
        <br>
        <br>
        <div class="birdTable">
            <%= session.getAttribute("searchQueryResult") %>
        </div>

        <div class="login-register">
            <a href="adminPage.jsp" class="button">Back</a>
        </div>
    </div>
</body>
<footer>

</footer>
<script>
    const checkbox = document.getElementById("cusQ");
    const div = document.getElementById("genreCB");
    const sQ = document.getElementById("staffQ")
    const mQ = document.getElementById("managerQ")
    const aQ = document.getElementById("adminQ")

    checkbox.addEventListener('change', function () {
        if (this.checked) {
            div.style.display = "block";
            sQ.disabled = true
            sQ.checked = false
            mQ.disabled = true
            mQ.checked = false
            aQ.disabled = true
            aQ.checked = false
        } else {
            div.style.display = "none";
            sQ.disabled = false
            mQ.disabled = false
            aQ.disabled = false
        }
    });
</script>

</html>