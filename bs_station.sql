-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2026 at 01:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bs_station`
--

-- --------------------------------------------------------

--
-- Table structure for table `baterai`
--

CREATE TABLE `baterai` (
  `id_baterai` int(11) NOT NULL,
  `kode_baterai` varchar(20) DEFAULT NULL,
  `kapasitas` varchar(10) DEFAULT NULL,
  `persentase` int(11) DEFAULT NULL,
  `status` enum('Tersedia','Dipakai','Rusak') DEFAULT 'Tersedia',
  `lokasi` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `baterai`
--

INSERT INTO `baterai` (`id_baterai`, `kode_baterai`, `kapasitas`, `persentase`, `status`, `lokasi`) VALUES
(1, '1', '2', 90, 'Dipakai', 'zimbabwe'),
(2, '2', '5', 90, 'Dipakai', 'zimbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `motor`
--

CREATE TABLE `motor` (
  `id_motor` int(11) NOT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `plat_nomor` varchar(15) DEFAULT NULL,
  `merek_tipe` varchar(50) DEFAULT NULL,
  `tahun` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nik` varchar(20) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nik`, `nama`, `no_hp`, `alamat`) VALUES
(1, '231011401926', 'Rizqi Andhika Pratama', '083891418545', 'amerika'),
(2, '231011401927', 'gracia', '083891418549', 'amerika');

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_lengkap` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `username`, `password`, `nama_lengkap`) VALUES
(1, 'admin', 'admin123', 'Admin Utama');

-- --------------------------------------------------------

--
-- Table structure for table `tarif`
--

CREATE TABLE `tarif` (
  `id_tarif` int(11) NOT NULL,
  `jenis_tarif` varchar(50) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `keterangan` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tarif`
--

INSERT INTO `tarif` (`id_tarif`, `jenis_tarif`, `harga`, `keterangan`) VALUES
(1, 'mingguan', 100000, 'ada');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_swap`
--

CREATE TABLE `transaksi_swap` (
  `id_swap` int(11) NOT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `id_motor` int(11) DEFAULT NULL,
  `baterai_lama` int(11) DEFAULT NULL,
  `baterai_baru` int(11) DEFAULT NULL,
  `id_tarif` int(11) DEFAULT NULL,
  `id_petugas` int(11) DEFAULT NULL,
  `tanggal` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi_swap`
--

INSERT INTO `transaksi_swap` (`id_swap`, `id_pelanggan`, `id_motor`, `baterai_lama`, `baterai_baru`, `id_tarif`, `id_petugas`, `tanggal`) VALUES
(1, 1, NULL, 1, 1, NULL, NULL, '2026-06-26 17:36:04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `baterai`
--
ALTER TABLE `baterai`
  ADD PRIMARY KEY (`id_baterai`),
  ADD UNIQUE KEY `kode_baterai` (`kode_baterai`);

--
-- Indexes for table `motor`
--
ALTER TABLE `motor`
  ADD PRIMARY KEY (`id_motor`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`),
  ADD UNIQUE KEY `nik` (`nik`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `tarif`
--
ALTER TABLE `tarif`
  ADD PRIMARY KEY (`id_tarif`);

--
-- Indexes for table `transaksi_swap`
--
ALTER TABLE `transaksi_swap`
  ADD PRIMARY KEY (`id_swap`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_motor` (`id_motor`),
  ADD KEY `baterai_lama` (`baterai_lama`),
  ADD KEY `baterai_baru` (`baterai_baru`),
  ADD KEY `id_tarif` (`id_tarif`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `baterai`
--
ALTER TABLE `baterai`
  MODIFY `id_baterai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `motor`
--
ALTER TABLE `motor`
  MODIFY `id_motor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tarif`
--
ALTER TABLE `tarif`
  MODIFY `id_tarif` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transaksi_swap`
--
ALTER TABLE `transaksi_swap`
  MODIFY `id_swap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `motor`
--
ALTER TABLE `motor`
  ADD CONSTRAINT `motor_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE;

--
-- Constraints for table `transaksi_swap`
--
ALTER TABLE `transaksi_swap`
  ADD CONSTRAINT `transaksi_swap_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`),
  ADD CONSTRAINT `transaksi_swap_ibfk_2` FOREIGN KEY (`id_motor`) REFERENCES `motor` (`id_motor`),
  ADD CONSTRAINT `transaksi_swap_ibfk_3` FOREIGN KEY (`baterai_lama`) REFERENCES `baterai` (`id_baterai`),
  ADD CONSTRAINT `transaksi_swap_ibfk_4` FOREIGN KEY (`baterai_baru`) REFERENCES `baterai` (`id_baterai`),
  ADD CONSTRAINT `transaksi_swap_ibfk_5` FOREIGN KEY (`id_tarif`) REFERENCES `tarif` (`id_tarif`),
  ADD CONSTRAINT `transaksi_swap_ibfk_6` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
