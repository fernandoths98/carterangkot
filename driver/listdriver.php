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
        <div class="container-fluid">
            <strong>
                <h2 class="text-center mt-3">Tabel Data Supir</h2>
            </strong>
            <br><br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID Driver</th>
                        <th>No KTP</th>
                        <th>Nama Driver</th>
                        <th>No. SIM</th>
                        <th>Umur</th>
                        <th>Alamat</th>
                        <th>No. Telepon</th>
                        <th>Tipe Mobil</th>
                        <th>Plat Nomor Mobil</th>
                        <th>Jurusan Mobil</th>
                        <th colspan="2" class="text-center">Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    <?php $ambil = mysqli_query($con, "SELECT * FROM supir"); ?>
                    <?php while ($pecah = mysqli_fetch_assoc($ambil)) { ?>

                        <tr>
                            <td><?= $pecah['id_driver']; ?></td>
                            <td><?= $pecah['no_ktp']; ?></td>
                            <td><?= $pecah['nama_driver']; ?></td>
                            <td><?= $pecah['no_sim']; ?></td>
                            <td><?= $pecah['umur']; ?></td>
                            <td><?= $pecah['alamat']; ?></td>
                            <td><?= $pecah['no_tlp']; ?></td>
                            <td><?= $pecah['tipe_mobil']; ?></td>
                            <td><?= $pecah['plat_mobil']; ?></td>
                            <td><?= $pecah['jurusan_mobil']; ?></td>
                            <td>
                                <div class="btn-group">
                                    <a href="editdriver.php?id=<?= $pecah['id_driver']; ?>" class="btn btn-warning mr-2">Ubah</a>
                                    <a href="hapusdriver.php?id=<?= $pecah['id_driver']; ?>" class="btn btn-danger">Hapus</a>
                                </div>
                            </td>
                        </tr>
                    <?php } ?>
                </tbody>
            </table>
            <a href="tambahdriver.php" class="btn btn-primary" type="submit">Tambah Data</a>
        </div>
        <br>
        <br>
    </section>

</body>

</html>