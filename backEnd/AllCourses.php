<?php
//including the DB configuration
include 'DatabaseConfig.php';

// Create connection
$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);

// Check if there is an error in connection with the
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// query to select and retreive all values from <Course> table
$sql = "SELECT * FROM Course";

// executing the query
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    while ($row[] = $result->fetch_assoc()) {
        $tem = $row;
        $json = json_encode($tem);
    }
} else {
    echo "No Results Found.";
}
echo $json;
$conn->close();
