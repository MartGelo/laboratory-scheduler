-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2024 at 11:05 PM
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
-- Database: `labsched`
--

-- --------------------------------------------------------

--
-- Table structure for table `lab`
--

CREATE TABLE `lab` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `position` varchar(100) NOT NULL,
  `room` varchar(255) NOT NULL,
  `year_level` varchar(50) NOT NULL,
  `section` varchar(50) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `month` varchar(50) NOT NULL,
  `week` varchar(50) NOT NULL,
  `day` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `time` varchar(50) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lab`
--

INSERT INTO `lab` (`id`, `name`, `position`, `room`, `year_level`, `section`, `subject`, `month`, `week`, `day`, `semester`, `time`, `status`) VALUES
(9, 'dfbf', 'Affiliate Instructor', 'Computer Laboratory 1', 'First Year', '1A', 'Laboratory Maintenance', 'January', 'Week A', 'Monday', 'First Semester', '8:00 AM - 9:00 AM', 'Pending'),
(10, 'mghmf', 'Affiliate Instructor', 'Computer Laboratory 2A', 'First Year', '1A', 'First Year - First Sem', 'February', 'Week A', 'Friday', 'First Semester', '11:00 AM - 12:00 PM', 'Pending'),
(11, 'thfh', 'Affiliate Instructor', 'Computer Laboratory 1', 'First Year', '1A', 'Laboratory Maintenance', 'January', 'Week A', 'Monday', 'First Semester', '10:00 AM - 11:00 AM', 'Pending'),
(12, 'hd', 'Affiliate Instructor', 'Computer Laboratory 1', 'First Year', '1A', 'Laboratory Maintenance', 'January', 'Week A', 'Monday', 'First Semester', '9:00 AM - 10:00 AM', 'Accepted'),
(13, 'theh', 'Affiliate Instructor', 'Computer Laboratory 2B', 'First Year', '1A', 'Introduction to Computing ', 'January', 'Week A', 'Monday', 'First Semester', '8:00 AM - 9:00 AM', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `status`) VALUES
(1, 'gelo@gmail.com', '12345', 'user'),
(2, 'daniel@gmail.com', '12345', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `lab`
--
ALTER TABLE `lab`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `lab`
--
ALTER TABLE `lab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
