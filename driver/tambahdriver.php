<?php
include 'config.php';
?>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <title>List Driver</title>
</head>

<body>
    <section class="content">
        <div class="container ml-7">
            <strong>
                <h2 class="text-center mt-3">Tambah Data Supir</h2>
            </strong>
            <br>
            <form method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label>ID Driver</label>
                    <input type="number" name="id_driver" class="form-control">
                </div>
                <div class="form-group">
                    <label>No. KTP</label>
                    <input type="text" name="no_ktp" class="form-control">
                </div>
                <div class="form-group">
                    <label>Nama Driver</label>
                    <input type="text" name="nama_driver" class="form-control">
                </div>
                <div class="form-group">
                    <label>No. SIM</label>
                    <input type="text" name="no_sim" class="form-control">
                </div>
                <div class="form-group">
                    <label>Umur</label>
                    <input type="text" name="umur" class="form-control">
                </div>
                <div class="form-group">
                    <label>Alamat</label>
                    <textarea type="text" name="alamat" class="form-control"></textarea>
                </div>
                <div class="form-group">
                    <label>No. Telpon</label>
                    <input type="text" name="no_tlp" class="form-control">
                </div>
                <div class="form-group">
                    <label>Tipe Mobil</label>
                    <input type="text" name="tipe_mobil" class="form-control">
                </div>
                <div class="form-group">
                    <label>Plat Mobil</label>
                    <input type="text" name="plat_mobil" class="form-control">
                </div>
                <div class="form-group">
                    <label>Jurusan Mobil</label>
                    <input type="text" name="jurusan_mobil" class="form-control">
                </div>
                <button class="btn btn-primary pull-right" name="tambah">Tambah Data</button>
                <br><br>
            </form>
            <?php
            if (isset($_POST['tambah'])) {
                mysqli_query($con, "INSERT INTO supir(id_driver,no_ktp,nama_driver,no_sim,umur,
                                                        alamat,no_tlp,tipe_mobil,plat_mobil,jurusan_mobil) 
                                    VALUES('$_POST[id_driver]','$_POST[no_ktp]','$_POST[nama_driver]','$_POST[no_sim]',
                                        '$_POST[umur]','$_POST[alamat]','$_POST[no_tlp]','$_POST[tipe_mobil]',
                                        '$_POST[plat_mobil]','$_POST[jurusan_mobil]')");

                echo "<script>alert('Data Berhasil di Tambah');</script>";
                echo "<script>location='listdriver.php';</script>";
            }
            ?>
        </div>
    </section>
</body>

</html>