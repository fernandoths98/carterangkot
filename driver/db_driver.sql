-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 21, 2020 at 02:02 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_driver`
--

-- --------------------------------------------------------

--
-- Table structure for table `supir`
--

CREATE TABLE `supir` (
  `id_driver` int(6) NOT NULL,
  `no_ktp` varchar(16) NOT NULL,
  `nama_driver` varchar(50) NOT NULL,
  `no_sim` varchar(12) NOT NULL,
  `umur` varchar(3) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_tlp` varchar(15) NOT NULL,
  `tipe_mobil` varchar(20) NOT NULL,
  `plat_mobil` varchar(15) NOT NULL,
  `jurusan_mobil` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supir`
--

INSERT INTO `supir` (`id_driver`, `no_ktp`, `nama_driver`, `no_sim`, `umur`, `alamat`, `no_tlp`, `tipe_mobil`, `plat_mobil`, `jurusan_mobil`) VALUES
(101001, '3671022810980006', 'Fernando Teguh', '981013481031', '22', 'Jalan Danau Batur 1 No. 10 Perumnas 2 Tangerang', '08981152982', 'Grandmax', 'B 3016 CCZ', 'R11'),
(101002, '3671022710980006', 'M. Zainul', '981013481001', '21', 'Jalan Kembangan nomor 17', '081316250192', 'Kijang', 'B 1006 CAZ', 'R14');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(5, 'Fernando Teguh', 'fernandoteguh31@gmail.com', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `supir`
--
ALTER TABLE `supir`
  ADD PRIMARY KEY (`id_driver`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `supir`
--
ALTER TABLE `supir`
  MODIFY `id_driver` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101003;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
