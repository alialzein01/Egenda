<?php

//Host
$server = "localhost";

//Username
$username = "root";

//Password
$password = "";

//DB Name
$database_name = "egenda";

//Initializing and connecting the DB
$mysqli = new mysqli($server, $username, $password, $database_name);

//Checking if the connection call was failed
if (mysqli_connect_errno()) {
    die("Conenction Failed!");
}
