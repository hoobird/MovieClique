<!DOCTYPE html>
<html>

<head>
    <title>Manager Create Movie</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
    <div class="body-text">
        <h1>Manager Create Movie</h1>
    </div>


    <div class="login-register-details">
        <form method="post" action="createMovie" enctype='multipart/form-data'>
            <div class="row">
                <div class="col-25"><label for="movName">Movie Title:</label></div>
                <div class="col-75"><input type="text" placeholder="Title" name="movName" required></div>
            </div>

            <label for="img">Select image:</label>
            <input type="file" id="img" name="img" accept="image/*" >

            <div class="row">
                <div class="col-25"><label for="desc">Movie Synopsis:</label></div>
                <div class="col-75"><input placeholder="Description" name="desc" required></div>
            </div>

            <!--Might do scriptlet to call options from genreList in custProfile class-->
            <div class="row">
                <div class="col-25"><label for="genre">Genre:</label></div>
                <div class="col-75"><select type="text" name="genre" required>
                        <option value="Action">Action</option>
                        <option value="Adventure">Adventure</option>
                        <option value="Comedy">Comedy</option>
                        <option value="Crime">Crime</option>
                        <option value="Drama">Drama</option>
                        <option value="Fantasy">Fantasy</option>
                        <option value="Historical">Historical</option>
                        <option value="Horror">Horror</option>
                        <option value="Romance">Romance</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <button type="submit">Create Movie</button>
            </div>

            <div class="login-register">
                <a href="managerMovies.jsp" class="button">Back</a>
            </div>

        </form>
    </div>
</body>
<footer>

</footer>

</html>