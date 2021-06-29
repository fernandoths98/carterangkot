<?php
include 'config.php';
$id_driver = $_GET['id'];

mysqli_query($con, "DELETE FROM supir WHERE id_driver='$id_driver'");

echo "<script>alert('Data Supir dihapus');</script>";
echo "<script>location='listdriver.php';</script>";
