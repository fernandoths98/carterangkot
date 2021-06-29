<?php


if($_SERVER['REQUEST_METHOD']=='POST'){

  $name = $_POST['name'];
  $email = $_POST['email'];
  $password = $_POST['password'];

  require_once('config.php');

  $sql = "INSERT INTO user (name,email,password) VALUES ('$name','$email','$password')";

$query = mysqli_query($con, $sql);

if ($query) {
  # code...
  echo json_encode(array("response"=>"Success"));
} 
 else {
  echo json_encode(array("response"=>"Failed"));
}

}