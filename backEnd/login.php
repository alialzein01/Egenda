<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    include ('DatabaseConfig.php');
    $con = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName); 
    $S_username = $_POST['Username'];
    $S_password = $_POST['Password'];

    $Sql_Query = "Select * FROM User Where Username = '$S_username' && Password = '$S_password'";

    if (mysqli_query($con, $Sql_Query)) {
        echo 'login successfully';
    } else {
        echo 'Something went wrong';
    }
}
mysqli_close($con);
