<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include ('DatabaseConfig.php'); // linking the database.

    // Getting values from frontend
    $S_fullname = $_POST['FullName'];
    $S_username = $_POST['Username'];
    $S_email = $_POST['Email'];
    $S_password = $_POST['Password'];

    //SQL query to insert values into the user table.
    $Sql_Query = "INSERT INTO user (FullName,Username,Email, Password) values ('$S_fullname','$S_username', '$S_email','$S_password')";

    //Condition that checks if the query succeed or failed.
    if (mysqli_query($con, $Sql_Query)) {
        echo 'User Registered Successfully';
    } else {
        echo 'Something went wrong';
    }
}
mysqli_close($con);
