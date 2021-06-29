<?php
include 'config.php';
$id_driver = $_GET['id'];

//ambil data dari database
$ambil = mysqli_query($con, "SELECT * FROM supir WHERE id_driver='$id_driver'");
$pecah = mysqli_fetch_assoc($ambil);

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
                <h2 class="text-center mt-3">Ubah Data Supir</h2>
            </strong>
            <br>
            <form method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label>ID Driver</label>
                    <input type="number" name="id_driver" class="form-control" value="<?= $pecah['id_driver']; ?>">
                </div>
                <div class="form-group">
                    <label>No. KTP</label>
                    <input type="text" name="no_ktp" class="form-control" value="<?= $pecah['no_ktp']; ?>">
                </div>
                <div class="form-group">
                    <label>Nama Driver</label>
                    <input type="text" name="nama_driver" class="form-control" value="<?= $pecah['nama_driver']; ?>">
                </div>
                <div class="form-group">
                    <label>No. SIM</label>
                    <input type="text" name="no_sim" class="form-control" value="<?= $pecah['no_sim']; ?>">
                </div>
                <div class="form-group">
                    <label>Umur</label>
                    <input type="text" name="umur" class="form-control" value="<?= $pecah['umur']; ?>">
                </div>
                <div class="form-group">
                    <label>Alamat</label>
                    <textarea type="text" name="alamat" class="form-control"><?= $pecah['alamat']; ?></textarea>
                </div>
                <div class="form-group">
                    <label>No. Telpon</label>
                    <input type="text" name="no_tlp" class="form-control" value="<?= $pecah['no_tlp']; ?>">
                </div>
                <div class="form-group">
                    <label>Tipe Mobil</label>
                    <input type="text" name="tipe_mobil" class="form-control" value="<?= $pecah['tipe_mobil']; ?>">
                </div>
                <div class="form-group">
                    <label>Plat Mobil</label>
                    <input type="text" name="plat_mobil" class="form-control" value="<?= $pecah['plat_mobil']; ?>">
                </div>
                <div class="form-group">
                    <label>Jurusan Mobil</label>
                    <input type="text" name="jurusan_mobil" class="form-control" value="<?= $pecah['jurusan_mobil']; ?>">
                </div>
                <button class="btn btn-primary pull-right" name="ubah">Ubah Data</button>
                <br><br>
            </form>
            <?php
            if (isset($_POST['ubah'])) {
                mysqli_query($con, "UPDATE supir SET 
                                                    id_driver='$_POST[id_driver]',
                                                    no_ktp='$_POST[no_ktp]',
                                                    nama_driver='$_POST[nama_driver]',
                                                    no_sim='$_POST[no_sim]',
                                                    umur='$_POST[umur]',
                                                    alamat='$_POST[alamat]',
                                                    no_tlp='$_POST[no_tlp]',
                                                    tipe_mobil='$_POST[tipe_mobil]',
                                                    plat_mobil='$_POST[plat_mobil]',
                                                    jurusan_mobil='$_POST[jurusan_mobil]'
                                                WHERE
                                                    id_driver='$id_driver'

                ");
                echo "<script>alert('Data Berhasil Di Ubah');</script>";
                echo "<script>location='listdriver.php';</script>";
            }
            ?>

        </div>
    </section>
</body>

</html>