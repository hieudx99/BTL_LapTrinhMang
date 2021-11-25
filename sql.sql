-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 28, 2021 lúc 08:54 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `laptrinhmang`
--
CREATE DATABASE IF NOT EXISTS `laptrinhmang` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `laptrinhmang`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblcard`
--

DROP TABLE IF EXISTS `tblcard`;
CREATE TABLE `tblcard` (
  `id` int(10) NOT NULL,
  `image` varchar(255) NOT NULL,
  `number` int(10) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbldealtcard`
--

DROP TABLE IF EXISTS `tbldealtcard`;
CREATE TABLE `tbldealtcard` (
  `id` int(10) NOT NULL,
  `totalValue` int(10) NOT NULL,
  `Cardid` int(10) NOT NULL,
  `Playerid` int(10) NOT NULL,
  `Matchid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblmatch`
--

DROP TABLE IF EXISTS `tblmatch`;
CREATE TABLE `tblmatch` (
  `id` int(10) NOT NULL,
  `totalSlot` int(10) NOT NULL,
  `firstRank` varchar(255) DEFAULT NULL,
  `secondRank` varchar(255) DEFAULT NULL,
  `thirdRank` varchar(255) DEFAULT NULL,
  `lastRank` varchar(255) DEFAULT NULL,
  `Tableid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblmatch`
--

INSERT INTO `tblmatch` (`id`, `totalSlot`, `firstRank`, `secondRank`, `thirdRank`, `lastRank`, `Tableid`) VALUES
(1, 2, '1', '2', NULL, NULL, 1),
(2, 3, '2', '3', '1', NULL, 1),
(3, 4, '3', '2', '1', '4', 1),
(4, 4, '1', '2', '3', '4', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblplayer`
--

DROP TABLE IF EXISTS `tblplayer`;
CREATE TABLE `tblplayer` (
  `id` int(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `point` int(10) NOT NULL,
  `status` varchar(255) NOT NULL,
  `firstRankTime` int(10) NOT NULL,
  `secondRankTime` int(10) NOT NULL,
  `thirdRankTime` int(10) NOT NULL,
  `lastRankTime` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblplayer`
--

INSERT INTO `tblplayer` (`id`, `username`, `password`, `point`, `status`, `firstRankTime`, `secondRankTime`, `thirdRankTime`, `lastRankTime`) VALUES
(1, 'hieudx', '123456', 3, 'O', 10, 3, 2, 6),
(2, 'duyvd', '123456', 4, 'O', 12, 5, 3, 4),
(3, 'lochd', '123456', 1, 'O', 15, 3, 6, 3),
(4, 'binhnt', '123456', 2, 'O', 3, 7, 4, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbltable`
--

DROP TABLE IF EXISTS `tbltable`;
CREATE TABLE `tbltable` (
  `id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tbltable`
--

INSERT INTO `tbltable` (`id`, `name`, `status`) VALUES
(1, 'random name', 'playing');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tblcard`
--
ALTER TABLE `tblcard`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbldealtcard`
--
ALTER TABLE `tbldealtcard`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKDealtCard596187` (`Cardid`),
  ADD KEY `FKDealtCard83162` (`Playerid`),
  ADD KEY `FKDealtCard287867` (`Matchid`);

--
-- Chỉ mục cho bảng `tblmatch`
--
ALTER TABLE `tblmatch`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKMatch430657` (`Tableid`);

--
-- Chỉ mục cho bảng `tblplayer`
--
ALTER TABLE `tblplayer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `tbltable`
--
ALTER TABLE `tbltable`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tblcard`
--
ALTER TABLE `tblcard`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tbldealtcard`
--
ALTER TABLE `tbldealtcard`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tblmatch`
--
ALTER TABLE `tblmatch`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tblplayer`
--
ALTER TABLE `tblplayer`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tbltable`
--
ALTER TABLE `tbltable`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tbldealtcard`
--
ALTER TABLE `tbldealtcard`
  ADD CONSTRAINT `FKDealtCard287867` FOREIGN KEY (`Matchid`) REFERENCES `tblmatch` (`id`),
  ADD CONSTRAINT `FKDealtCard596187` FOREIGN KEY (`Cardid`) REFERENCES `tblcard` (`id`),
  ADD CONSTRAINT `FKDealtCard83162` FOREIGN KEY (`Playerid`) REFERENCES `tblplayer` (`id`);

--
-- Các ràng buộc cho bảng `tblmatch`
--
ALTER TABLE `tblmatch`
  ADD CONSTRAINT `FKMatch430657` FOREIGN KEY (`Tableid`) REFERENCES `tbltable` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
