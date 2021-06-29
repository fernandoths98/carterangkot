<?php

if($_SERVER['REQUEST_METHOD']=='POST'){

	$email = $_POST['email'];
	$password = $_POST['password'];

	require_once('config.php');

	$sql = "SELECT password, email FROM user WHERE email='$email' and '$password'";

	$query = mysqli_query($con,$sql);
	if ($query) {
  # code...
  echo json_encode(array("response"=>"Success"));
} 
 else {
  echo json_encode(array("response"=>"Failed"));
	}
}
