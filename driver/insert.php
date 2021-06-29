<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $no_ktp = $_POST['no_ktp'];
   $nama_driver = $_POST['nama_driver'];
   $no_sim = $_POST['no_sim'];
   $umur = $_POST['umur'];
   $alamat = $_POST['alamat'];
   $no_tlp = $_POST['no_tlp'];
   $tipe_mobil = $_POST['tipe_mobil'];
   $plat_mobil = $_POST['plat_mobil'];
   $jurusan_mobil = $_POST['jurusan_mobil'];
   


   require_once('config.php');
   //Cek nim sudah terdaftar apa belum
   $sql = "SELECT * FROM supir WHERE no_ktp ='$no_ktp'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! No KTP sudah terdaftar!";
     echo json_encode($response);
   } else {
     $sql = "INSERT INTO supir (no_ktp,nama_driver,no_sim,umur,alamat,no_tlp,tipe_mobil,plat_mobil,jurusan_mobil) VALUES('$no_ktp','$nama_driver','$no_sim','$umur','$alamat','$no_tlp','$tipe_mobil','$plat_mobil','$jurusan_mobil')";
     if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi!";
       echo json_encode($response);
     }
   }
   // tutup database
   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi!";
  echo json_encode($response);
}