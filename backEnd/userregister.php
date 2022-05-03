<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include 'DatabaseConfig.php'; // linking the database.

    // Sending values to DB.
    $S_fullname = $_POST['FullName'];
    $S_username = $_POST['Username'];
    $S_email = $_POST['Email'];
    $S_password = $_POST['Password'];
    $S_image = $_POST['Image'];
    $S_Cid = $_POST['Cid'];

    //SQL query to insert values into the user table.
    $Sql_Query = "INSERT INTO user (FullName,Username,Email, Password,Image,Cid) values ('$S_fullname','$S_username', '$S_email','$S_password','$S_image','$S_Cid')";

    //Condition that checks if the query succeed or failed.
    if (mysqli_query($con, $Sql_Query)) {
        echo 'User Registered Successfully';
    } else {
        echo 'Something went wrong';
    }
}
mysqli_close($con);
