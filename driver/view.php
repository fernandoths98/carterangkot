<?php
require_once('config.php');
if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM supir ORDER BY nama_driver ASC";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('id_driver'=>$row[0], 'no_ktp'=>$row[1], 'nama_driver'=>$row[2], 'no_sim'=>
    	$row[3], 'umur'=>$row[4], 'alamat'=>$row[5], 'no_tlp'=>$row[6], 'tipe_mobil'=>$row[7], 'plat_mobil'
    	=>$row[8], 'jurusan_mobil'=>$row[9]));
  }
  echo json_encode(array("value"=>1,"result"=>$result));	
  mysqli_close($con);
}