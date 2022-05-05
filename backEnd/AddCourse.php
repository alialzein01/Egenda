<?php
// check if there is posting
if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    include 'DatabaseConfig.php';

    $S_coursename = $_GET['CourseName'];
    $S_credits = $_GET['Credits'];
    $S_time = $_GET['Time'];
    $S_instructor = $_GET['Instructor'];
    $S_category = $_GET['Category'];

    $Sql_Query = "INSERT INTO course(CourseName,Credits,Time,Instructor,Category) values ('$S_coursename','$S_credits','$S_time','$S_instructor','$S_category')";
    if (mysqli_query($con, $Sql_Query)) {
        echo 'Course Added Successfully';
    } else {
        echo 'Something went wrong';
    }
}
mysqli_close($con);
