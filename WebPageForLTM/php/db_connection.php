<?php
$servername = "localhost";
$database = "LapTrinhMang";
$username = "root";
$password = "";

$con = mysqli_connect($servername, $username, $password, $database);

if (!$con) {
    die("Connection failed: " . mysqli_connect_error());
}
// echo "Connected successfully";