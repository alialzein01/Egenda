<?php
//including the DB configuration
include 'DatabaseConfig.php';

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
