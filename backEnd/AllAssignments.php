<?php
// including the database file
include 'DatabaseConfig.php';

// Create connection
$conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);

// checking for error in connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// query to display all assignments
$sql = "SELECT * FROM assignment";

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
