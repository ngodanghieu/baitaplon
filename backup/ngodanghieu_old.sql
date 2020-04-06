-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2020 at 03:11 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ngodanghieu`
--

-- --------------------------------------------------------

--
-- Table structure for table `acreage`
--

CREATE TABLE `acreage` (
  `acreage_id` bigint(20) NOT NULL,
  `height` float NOT NULL,
  `width` float NOT NULL,
  `total_area` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `acreage`
--

INSERT INTO `acreage` (`acreage_id`, `height`, `width`, `total_area`) VALUES
(1, 10, 10, 100),
(2, 7, 10, 70);

-- --------------------------------------------------------

--
-- Table structure for table `acreage_home`
--

CREATE TABLE `acreage_home` (
  `acreage_home_id` int(11) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `acreage_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `acreage_home`
--

INSERT INTO `acreage_home` (`acreage_home_id`, `home_id`, `acreage_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 2),
(4, 4, 2),
(5, 5, 1),
(6, 6, 2),
(7, 7, 2),
(8, 8, 2);

-- --------------------------------------------------------

--
-- Table structure for table `adress`
--

CREATE TABLE `adress` (
  `adress_id` int(11) NOT NULL,
  `building_id` bigint(20) NOT NULL,
  `district_id` int(11) NOT NULL,
  `latitude` decimal(10,0) NOT NULL,
  `longtitude` decimal(10,0) NOT NULL,
  `content_detail` varchar(255) NOT NULL,
  `home_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adress`
--

INSERT INTO `adress` (`adress_id`, `building_id`, `district_id`, `latitude`, `longtitude`, `content_detail`, `home_id`) VALUES
(1, 1, 1, '1111', '1111', 'Tam Thuan Phuc Tho', 1),
(2, 1, 2, '1111', '1111', 'Thị Trấn Phùng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `adress_home`
--

CREATE TABLE `adress_home` (
  `adress_home_id` bigint(20) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `building_id` int(11) NOT NULL,
  `floor` int(11) NOT NULL,
  `name_home` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adress_home`
--

INSERT INTO `adress_home` (`adress_home_id`, `home_id`, `building_id`, `floor`, `name_home`, `status`, `created_on`, `created_by`, `modified_on`, `modified_by`) VALUES
(1, 1, 1, 11, 'Phòng 111 ', 1, '2019-12-10', '1', '2019-12-10', '1'),
(2, 2, 2, 11, 'Phòng 112', 1, '2019-12-10', '1', '2019-12-10', '1'),
(3, 3, 1, 11, 'Phòng 113', 1, '2019-12-10', '1', '2019-12-10', '1'),
(4, 4, 1, 11, 'Phòng 114', 1, '2019-12-10', '1', '2019-12-10', '1'),
(5, 5, 1, 11, 'Phòng 111 ', 1, '2019-12-10', '1', '2019-12-10', '1'),
(6, 6, 2, 11, 'Phòng 112', 1, '2019-12-10', '1', '2019-12-10', '1'),
(7, 7, 1, 11, 'Phòng 113', 1, '2019-12-10', '1', '2019-12-10', '1'),
(8, 8, 1, 11, 'Phòng 114', 1, '2019-12-10', '1', '2019-12-10', '1');

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE `building` (
  `building_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `number_floor` int(11) NOT NULL,
  `adress_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `building`
--

INSERT INTO `building` (`building_id`, `name`, `number_floor`, `adress_id`, `status`, `created_on`, `created_by`, `modified_on`, `modified_by`) VALUES
(1, 'Tòa nhà VinHome A', 50, 1, 1, '2019-12-10', '1', '2019-12-03', '1'),
(2, 'Tòa nhà VinHome 5', 50, 2, 1, '2019-12-10', '1', '2019-12-03', '1');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`city_id`, `code`, `name`, `status`) VALUES
(1, 'hanoi', 'Ha Noi', 1),
(2, 'cantho', 'Can Tho', 1);

-- --------------------------------------------------------

--
-- Table structure for table `content_email`
--

CREATE TABLE `content_email` (
  `content_email_id` int(11) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `from` varchar(100) NOT NULL,
  `to` varchar(100) NOT NULL,
  `cc` varchar(255) NOT NULL,
  `bcc` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `content_body` text NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `district`
--

CREATE TABLE `district` (
  `district_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `district`
--

INSERT INTO `district` (`district_id`, `city_id`, `code`, `name`, `status`) VALUES
(1, 1, 'phuctho', 'Phuc Tho', 1),
(2, 1, 'danphuong', 'Đan Phượng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `home`
--

CREATE TABLE `home` (
  `home_id` bigint(20) NOT NULL,
  `content` text NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `price` double NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `home`
--

INSERT INTO `home` (`home_id`, `content`, `image_url`, `status`, `price`, `created_on`, `created_by`, `modified_on`, `modified_by`) VALUES
(1, 'Khu căn hộ cao cấp Opal Skyview nằm ngay mặt tiền đại lộ Phạm Văn Đồng, tuyến đường nội đô đẹp nhất TP.HCM và gần sông Sài Gòn thơ mộng. Với những lợi thế về vị trí trung tâm, giao thông thuận lợi, tiện ích đẳng cấp, Opal Skyview hứa hẹn sẽ là nơi lý tưởng để an cư cũng như đầu tư sinh lợi.', 'https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg', 4, 100000, '2019-12-17', '1', '2019-12-10', '1'),
(2, 'ngo dang hieu da den day', 'https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg', 4, 1.111111111111111e17, '2019-12-17', '1', '2019-12-10', '1'),
(3, 'Khu căn hộ cao cấp Opal Skyview nằm ngay mặt tiền đại lộ Phạm Văn Đồng, tuyến đường nội đô đẹp nhất TP.HCM và gần sông Sài Gòn thơ mộng. Với những lợi thế về vị trí trung tâm, giao thông thuận lợi, tiện ích đẳng cấp, Opal Skyview hứa hẹn sẽ là nơi lý tưởng để an cư cũng như đầu tư sinh lợi.', 'https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg', 4, 50000, '2019-12-17', '1', '2019-12-10', '1'),
(4, 'Khu căn hộ cao cấp Opal Skyview nằm ngay mặt tiền đại lộ Phạm Văn Đồng, tuyến đường nội đô đẹp nhất TP.HCM và gần sông Sài Gòn thơ mộng. Với những lợi thế về vị trí trung tâm, giao thông thuận lợi, tiện ích đẳng cấp, Opal Skyview hứa hẹn sẽ là nơi lý tưởng để an cư cũng như đầu tư sinh lợi.', 'https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg', 4, 10000, '2019-12-17', '1', '2019-12-10', '1'),
(5, 'Khu căn hộ cao cấp Opal Skyview nằm ngay mặt tiền đại lộ Phạm Văn Đồng, tuyến đường nội đô đẹp nhất TP.HCM và gần sông Sài Gòn thơ mộng. Với những lợi thế về vị trí trung tâm, giao thông thuận lợi, tiện ích đẳng cấp, Opal Skyview hứa hẹn sẽ là nơi lý tưởng để an cư cũng như đầu tư sinh lợi.', 'https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg', 4, 100000, '2019-12-17', '1', '2019-12-10', '1'),
(6, 'string', 'https://ngodanghieu.herokuapp.com/asd', 4, 0, '2019-12-17', '1', '2019-12-10', '1'),
(7, 'eeerrrrrr', 'https://ngodanghieu.herokuapp.com/asd', 4, 10000, '2019-12-17', '1', '2019-12-10', '1'),
(8, 'Khu căn hộ cao cấp Opal Skyview nằm ngay mặt tiền đại lộ Phạm Văn Đồng, tuyến đường nội đô đẹp nhất TP.HCM và gần sông Sài Gòn thơ mộng. Với những lợi thế về vị trí trung tâm, giao thông thuận lợi, tiện ích đẳng cấp, Opal Skyview hứa hẹn sẽ là nơi lý tưởng để an cư cũng như đầu tư sinh lợi.', 'https://znews-photo.zadn.vn/w1024/Uploaded/kbd_bcvi/2019_11_23/5d828d976f24eb1a752053b5.jpg', 5, 10000, '2019-12-17', '1', '2019-12-10', '1');

-- --------------------------------------------------------

--
-- Table structure for table `home_worktime`
--

CREATE TABLE `home_worktime` (
  `home_worktime_id` int(11) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `close_time` varchar(50) NOT NULL,
  `open_time` varchar(50) NOT NULL,
  `weekday` varchar(50) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `order_date` date NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  `tax_total` decimal(10,0) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `order_request` text DEFAULT NULL,
  `order_code` varchar(100) NOT NULL,
  `payment_with` decimal(10,0) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_id`, `home_id`, `user_id`, `order_date`, `total_price`, `tax_total`, `note`, `order_request`, `order_code`, `payment_with`, `status`) VALUES
(8, 1, 8, '2020-01-03', '5500', '50', '', '{\"idUserl\":8,\"idHome\":1,\"totalPrice\":5500.0,\"taxTotal\":50.0,\"requestJson\":\"dssdsd\",\"paymentWith\":5000,\"open\":\"07:00\",\"close\":\"20:00\",\"weekday\":\"SUN\"}', 'KL7KORAA', '5000', 7);

-- --------------------------------------------------------

--
-- Table structure for table `order_history`
--

CREATE TABLE `order_history` (
  `order_history_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `order_request` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_info`
--

CREATE TABLE `order_info` (
  `order_info_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `open_time` varchar(50) NOT NULL,
  `close_time` varchar(50) NOT NULL,
  `weekday` varchar(50) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_info`
--

INSERT INTO `order_info` (`order_info_id`, `order_id`, `home_id`, `open_time`, `close_time`, `weekday`, `adress`) VALUES
(2, 8, 1, '07:00', '20:00', 'SUN', NULL),
(11, 8, 1, '07:00', '20:00', 'SUN', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `payment_history`
--

CREATE TABLE `payment_history` (
  `payment_history_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `payment_provider_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment_provider`
--

CREATE TABLE `payment_provider` (
  `payment_provider_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment_provider`
--

INSERT INTO `payment_provider` (`payment_provider_id`, `name`, `code`, `created_on`, `created_by`, `modified_on`, `modified_by`, `status`) VALUES
(1, '1', '1', '2019-12-24', '1', '2019-12-10', '1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(200) NOT NULL,
  `role_code` varchar(100) NOT NULL,
  `role_status` int(11) NOT NULL,
  `role_created_on` date NOT NULL,
  `role_created_by` varchar(100) NOT NULL,
  `role_modified_on` date NOT NULL,
  `role_modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `role_code`, `role_status`, `role_created_on`, `role_created_by`, `role_modified_on`, `role_modified_by`) VALUES
(1, 'ownener', 'ownener', 1, '2019-12-24', 'dang hieu', '2019-12-01', 'dang hieu'),
(2, 'user', 'user', 1, '2019-12-24', 'dang hieu', '2019-12-01', 'dang hieu'),
(3, 'admin', 'admin', 1, '2019-12-24', 'dang hieu', '2019-12-01', 'dang hieu');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL,
  `status_code` varchar(20) NOT NULL,
  `status_name` varchar(50) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_code`, `status_name`, `created_on`, `created_by`) VALUES
(1, 'admin', 'admin', '2019-12-24', 'danghiwue'),
(2, 'owner', 'owner', '2020-01-02', 'danghieu'),
(3, 'user', 'User', '2020-01-02', 'danghieu'),
(4, 'active', 'active', '2020-01-02', 'dagnhieu'),
(5, 'inactive', 'inactive', '2020-01-02', 'dagnhieu'),
(6, 'neworder', 'New Order', '2020-01-02', 'dagnhieu'),
(7, 'ordersuccess', 'Order success', '2020-01-02', 'dagnhieu');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` bigint(11) NOT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `user_full_name` varchar(255) NOT NULL,
  `user_phone` varchar(20) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_hash` varchar(255) NOT NULL,
  `user_opt_code` int(11) DEFAULT NULL,
  `user_expired_otp` timestamp NULL DEFAULT NULL,
  `user_status` int(11) NOT NULL,
  `user_provider` int(11) DEFAULT NULL,
  `user_facebook_id` varchar(255) DEFAULT NULL,
  `user_auth_token` varchar(255) DEFAULT NULL,
  `user_created_on` date NOT NULL,
  `user_created_by` varchar(100) DEFAULT NULL,
  `user_modified_on` date DEFAULT NULL,
  `user_modified_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_full_name`, `user_phone`, `user_email`, `user_hash`, `user_opt_code`, `user_expired_otp`, `user_status`, `user_provider`, `user_facebook_id`, `user_auth_token`, `user_created_on`, `user_created_by`, `user_modified_on`, `user_modified_by`) VALUES
(8, NULL, 'Ngo Dang Hieu', '09625416179', NULL, 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwOTYyNTQxNjE3OSIsInJvbGVzIjpbXSwidXNlcmlkIjo4LCJleHAiOjE1Nzk0MDc0NDR9.vEn1cfMYA3WAxKnk1fV0UEy_0N4nX5jbwIOwcJe64jdi8dL8uPgJqtDPHar6bZ7TM1DOtZJsb_CkNPlgXlk_gg', 123456, '2020-01-04 06:37:09', 4, 0, NULL, NULL, '2019-12-29', NULL, NULL, NULL),
(64, NULL, '', '', NULL, '$2a$04$S4MlgtsDgqO6G30tlFF2b.enA4EGmYjKhW4FuFNihN/50p.S8vb0y', 123456, '2020-01-09 12:38:55', 5, 0, NULL, NULL, '2020-01-09', NULL, NULL, NULL),
(65, NULL, 'Bon Bon', '+84962541617', NULL, '$2a$04$R8vc0Ho8d5BU1HOA/8a5YObHsiGis5YUYr.cfBqF79zuKiZBGC1Rq', 123456, '2020-01-09 11:50:11', 4, 0, NULL, 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrODQ5NjI1NDE2MTciLCJyb2xlcyI6WyJ1c2VyIl0sInVzZXJpZCI6NjUsImV4cCI6MTU3OTU3NzM1OH0.zqpjWaaUNByfG06Vo22gSc7s1IDBPK-YsPTKgqj9EputafSnJ_SMcyNZyOBlHBjyHgCGcKwdG9vMbaylO8e3Iw', '2020-01-09', NULL, NULL, NULL),
(66, NULL, 'Bon Bon', '+849625416171', NULL, '$2a$04$zI35othURu0eduDF/RZV1.BQLrC7NddRrWReHmpk61JCGsBzHcub.', 123456, '2020-01-09 12:44:56', 4, 0, NULL, 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrODQ5NjI1NDE2MTcxIiwicm9sZXMiOlsidXNlciJdLCJ1c2VyaWQiOjY2LCJleHAiOjE1Nzk1NzczMDZ9.xrgUrK3JmOSX5PcAU5oVP7hZlv1F_8OsoEOJ51vpIja_UxlX7oXQdL_M03lHvX6jN1w0s8kVHBwNJQ8p6SOH6Q', '2020-01-09', NULL, NULL, NULL),
(67, NULL, 'thuy', '0357257190', NULL, '$2a$04$SBYjKaEw.e7fM.WBaeOqcufXTdOnkGwlCMlarbxSLPSfYY/aayMj.', 123456, '2020-01-10 00:31:48', 4, 0, NULL, 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMzU3MjU3MTkwIiwicm9sZXMiOlsidXNlciJdLCJ1c2VyaWQiOjY3LCJleHAiOjE1Nzk1Nzk2NDZ9.vUVNGjEf2RvkM8kMXX-3xiAvei3aFsLU-Q_0o7lM41YFpHnGYmlGCVm5_oWCTT3dXvE7KVhs01qWQcZZyFvBHg', '2020-01-10', NULL, NULL, NULL),
(68, NULL, 'Bon Bon', '+8496254161713', NULL, '$2a$04$/n4I/obJUvu.YDkP7Fe24ufZOBxZchT9SFPY1Aa3Uo2kWpWGHFUlS', 123456, '2020-01-10 02:29:38', 4, 0, NULL, 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIrODQ5NjI1NDE2MTcxMyIsInJvbGVzIjpbInVzZXIiXSwidXNlcmlkIjo2OCwiZXhwIjoxNTc5NTExNjg0fQ.lRPDy2uTkuiwjpEcYN-kxY8gtJMKhCiOiFcD5FI33E8OXrMyYc7vfqLXgSarnO9xG9AF_Fis1054ttYiRVyrGw', '2020-01-10', NULL, NULL, NULL),
(69, NULL, 'Bon Bon', '+849625416176', NULL, '$2a$04$ByBkS4rCKilDKXX61tyGgOLeZdfPDOB34O33g7BqvwUTNX7/ZKAG6', 123456, '2020-01-10 23:09:06', 5, 0, NULL, NULL, '2020-01-11', NULL, NULL, NULL),
(70, NULL, 'string', '123456789', 'string', '$2a$04$JIBmCMQg1Gh6ukq/y/f4GuTmqYlViNcVy.Y.QQm/f43.Gc03apdKS', 123456, '2020-01-11 22:18:50', 4, 0, NULL, 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjM0NTY3ODkiLCJyb2xlcyI6WyJ1c2VyIl0sInVzZXJpZCI6NzAsImV4cCI6MTU3OTY2OTU2OH0.UX2mqynV4NRbmrzU2wch3rh4eOlam_T7L5i_Ty_4ciYl5yoMFmixOYhi2HriXonvJ3ozGkeAnlj1Qj53KcZVWw', '2020-01-12', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_fcm`
--

CREATE TABLE `user_fcm` (
  `user_fcm_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `device_id` text NOT NULL,
  `token` text NOT NULL,
  `created_date` date NOT NULL,
  `modified_by` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_fcm`
--

INSERT INTO `user_fcm` (`user_fcm_id`, `user_id`, `device_id`, `token`, `created_date`, `modified_by`) VALUES
(1, 8, 'zcasdasd', 'dasdasdas', '2020-01-04', '2020-01-04'),
(4, 65, 'a463bb32fbc97315', 'fKO35Sk9Tmg:APA91bFxsqD81NOhn5mD_sjpa1Wg22Y9XwTeIF1Z9tMZgkCmfb-f2cG5riEXmSFay2e06XHFmSy4RLfzvOmIV61ZdSsA4yBN4sYYhNfBHDgTJHvzZj6Ln-jUfty0hT1NmSz86cLVq4jA', '2020-01-09', '2020-01-11'),
(5, 66, 'a463bb32fbc97315', 'fvYztihP0AA:APA91bHWnAA6sow94NSjqymsS2cA9EHLGFmzk_0XJUQR9wgjKS2KL8GM-_8kvdtev0MC8btD-PiFCGNiqu4pF08Yxmn-y6WOHLfC5GiRdTDm3MDDeebBZh5wyY9_Jk1V7lCweb-qoi58', '2020-01-09', '2020-01-11'),
(6, 67, 'a463bb32fbc97315', 'fyFhwVZfY54:APA91bH-_yzeIbC_JmjK4x9CWeVQj6yBi11Zv6AAErca0siV8i05EdILnhkyYo-tUkwbW3bUKM9UpBCiiOfQtcdVCOC1xICqNHiFL5paG_IjmPdgoeNMMcw6IiT4OYyfP8ro6hBeSvbk', '2020-01-10', '2020-01-10'),
(7, 68, 'a463bb32fbc97315', 'fyFhwVZfY54:APA91bH-_yzeIbC_JmjK4x9CWeVQj6yBi11Zv6AAErca0siV8i05EdILnhkyYo-tUkwbW3bUKM9UpBCiiOfQtcdVCOC1xICqNHiFL5paG_IjmPdgoeNMMcw6IiT4OYyfP8ro6hBeSvbk', '2020-01-10', '2020-01-10'),
(8, 67, 'e1c8776e8bc899bf', 'e4crOh7SR7g:APA91bGceGyqBmgpaG5DZu7UqFMXtjrJ7L8oC2TOLPC2Jv_UDIqrR-1loluS8uIT-5ScZH5Imkk-sHUw-oTPOLUvhad42EOLQ1xzqE0p-VZqDenS4gqMh-C4saviuGfAwX_AYQb2CEAC', '2020-01-11', '2020-01-11'),
(9, 70, 'a463bb32fbc97315', 'fKO35Sk9Tmg:APA91bFxsqD81NOhn5mD_sjpa1Wg22Y9XwTeIF1Z9tMZgkCmfb-f2cG5riEXmSFay2e06XHFmSy4RLfzvOmIV61ZdSsA4yBN4sYYhNfBHDgTJHvzZj6Ln-jUfty0hT1NmSz86cLVq4jA', '2020-01-12', '2020-01-12');

-- --------------------------------------------------------

--
-- Table structure for table `user_home`
--

CREATE TABLE `user_home` (
  `user_home_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `home_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `created_on` date NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `modified_on` date NOT NULL,
  `modified_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_home`
--

INSERT INTO `user_home` (`user_home_id`, `user_id`, `home_id`, `status`, `created_on`, `created_by`, `modified_on`, `modified_by`) VALUES
(1, 65, 1, 4, '2020-01-02', '', '2020-01-02', ''),
(2, 65, 2, 4, '2020-01-02', '', '2020-01-02', ''),
(3, 65, 3, 4, '2020-01-02', '', '2020-01-02', ''),
(4, 65, 4, 4, '2020-01-02', '', '2020-01-02', ''),
(5, 65, 5, 4, '2020-01-02', '', '2020-01-02', ''),
(6, 65, 6, 4, '2020-01-02', '', '2020-01-02', ''),
(7, 65, 7, 4, '2020-01-02', '', '2020-01-02', ''),
(8, 65, 8, 4, '2020-01-02', '', '2020-01-02', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_by` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_role_id`, `user_id`, `role_id`, `created_on`, `created_by`) VALUES
(8, 8, 2, '2020-01-08 09:05:32', 'danghieu'),
(45, 64, 2, '2020-01-09 11:34:53', 'danghieu'),
(46, 65, 2, '2020-01-09 11:35:11', 'danghieu'),
(47, 66, 2, '2020-01-09 12:22:27', 'danghieu'),
(48, 67, 2, '2020-01-10 00:16:48', 'danghieu'),
(49, 68, 2, '2020-01-10 02:14:38', 'danghieu'),
(50, 69, 2, '2020-01-10 22:54:06', 'danghieu'),
(51, 70, 2, '2020-01-11 22:03:50', 'danghieu');

-- --------------------------------------------------------

--
-- Table structure for table `vin_point`
--

CREATE TABLE `vin_point` (
  `vin_point_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `point` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acreage`
--
ALTER TABLE `acreage`
  ADD PRIMARY KEY (`acreage_id`);

--
-- Indexes for table `acreage_home`
--
ALTER TABLE `acreage_home`
  ADD PRIMARY KEY (`acreage_home_id`),
  ADD KEY `acreagehome1` (`acreage_id`),
  ADD KEY `acreagehome2` (`home_id`);

--
-- Indexes for table `adress`
--
ALTER TABLE `adress`
  ADD PRIMARY KEY (`adress_id`),
  ADD KEY `adress1` (`district_id`);

--
-- Indexes for table `adress_home`
--
ALTER TABLE `adress_home`
  ADD PRIMARY KEY (`adress_home_id`),
  ADD KEY `adresshoem1` (`building_id`),
  ADD KEY `adresshoem2` (`home_id`),
  ADD KEY `adresshoem3` (`status`);

--
-- Indexes for table `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`building_id`),
  ADD KEY `building` (`status`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`city_id`),
  ADD KEY `city` (`status`);

--
-- Indexes for table `content_email`
--
ALTER TABLE `content_email`
  ADD PRIMARY KEY (`content_email_id`);

--
-- Indexes for table `district`
--
ALTER TABLE `district`
  ADD PRIMARY KEY (`district_id`),
  ADD KEY `district1` (`status`),
  ADD KEY `district2` (`city_id`);

--
-- Indexes for table `home`
--
ALTER TABLE `home`
  ADD PRIMARY KEY (`home_id`),
  ADD KEY `home` (`status`);

--
-- Indexes for table `home_worktime`
--
ALTER TABLE `home_worktime`
  ADD PRIMARY KEY (`home_worktime_id`),
  ADD KEY `homeworktime` (`home_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `order1` (`user_id`),
  ADD KEY `order2` (`home_id`),
  ADD KEY `order3` (`status`);

--
-- Indexes for table `order_history`
--
ALTER TABLE `order_history`
  ADD PRIMARY KEY (`order_history_id`),
  ADD KEY `orderhistory` (`user_id`);

--
-- Indexes for table `order_info`
--
ALTER TABLE `order_info`
  ADD PRIMARY KEY (`order_info_id`),
  ADD KEY `orderinfo1` (`order_id`),
  ADD KEY `eordrinfo2` (`home_id`);

--
-- Indexes for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD PRIMARY KEY (`payment_history_id`),
  ADD KEY `payment_history1` (`payment_provider_id`);

--
-- Indexes for table `payment_provider`
--
ALTER TABLE `payment_provider`
  ADD PRIMARY KEY (`payment_provider_id`),
  ADD KEY `payment1` (`status`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD KEY `role1` (`role_status`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `user1` (`user_status`);

--
-- Indexes for table `user_fcm`
--
ALTER TABLE `user_fcm`
  ADD PRIMARY KEY (`user_fcm_id`),
  ADD KEY `userfrc` (`user_id`);

--
-- Indexes for table `user_home`
--
ALTER TABLE `user_home`
  ADD PRIMARY KEY (`user_home_id`),
  ADD KEY `userhome` (`status`),
  ADD KEY `userhome1` (`home_id`),
  ADD KEY `userhome2` (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_role_id`),
  ADD KEY `userrole1` (`role_id`),
  ADD KEY `userrole2` (`user_id`);

--
-- Indexes for table `vin_point`
--
ALTER TABLE `vin_point`
  ADD PRIMARY KEY (`vin_point_id`),
  ADD KEY `vinpoint1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acreage`
--
ALTER TABLE `acreage`
  MODIFY `acreage_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `acreage_home`
--
ALTER TABLE `acreage_home`
  MODIFY `acreage_home_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `adress`
--
ALTER TABLE `adress`
  MODIFY `adress_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `adress_home`
--
ALTER TABLE `adress_home`
  MODIFY `adress_home_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `building`
--
ALTER TABLE `building`
  MODIFY `building_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `content_email`
--
ALTER TABLE `content_email`
  MODIFY `content_email_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `district`
--
ALTER TABLE `district`
  MODIFY `district_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `home`
--
ALTER TABLE `home`
  MODIFY `home_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `home_worktime`
--
ALTER TABLE `home_worktime`
  MODIFY `home_worktime_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `order_history`
--
ALTER TABLE `order_history`
  MODIFY `order_history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_info`
--
ALTER TABLE `order_info`
  MODIFY `order_info_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `payment_history`
--
ALTER TABLE `payment_history`
  MODIFY `payment_history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment_provider`
--
ALTER TABLE `payment_provider`
  MODIFY `payment_provider_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `user_fcm`
--
ALTER TABLE `user_fcm`
  MODIFY `user_fcm_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_home`
--
ALTER TABLE `user_home`
  MODIFY `user_home_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `user_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `vin_point`
--
ALTER TABLE `vin_point`
  MODIFY `vin_point_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `acreage_home`
--
ALTER TABLE `acreage_home`
  ADD CONSTRAINT `acreagehome1` FOREIGN KEY (`acreage_id`) REFERENCES `acreage` (`acreage_id`),
  ADD CONSTRAINT `acreagehome2` FOREIGN KEY (`home_id`) REFERENCES `home` (`home_id`);

--
-- Constraints for table `adress`
--
ALTER TABLE `adress`
  ADD CONSTRAINT `adress1` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`);

--
-- Constraints for table `adress_home`
--
ALTER TABLE `adress_home`
  ADD CONSTRAINT `adresshoem1` FOREIGN KEY (`building_id`) REFERENCES `building` (`building_id`),
  ADD CONSTRAINT `adresshoem2` FOREIGN KEY (`home_id`) REFERENCES `home` (`home_id`),
  ADD CONSTRAINT `adresshoem3` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `building`
--
ALTER TABLE `building`
  ADD CONSTRAINT `building` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `city` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `district`
--
ALTER TABLE `district`
  ADD CONSTRAINT `district1` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `district2` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`);

--
-- Constraints for table `home`
--
ALTER TABLE `home`
  ADD CONSTRAINT `home` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `home_worktime`
--
ALTER TABLE `home_worktime`
  ADD CONSTRAINT `homeworktime` FOREIGN KEY (`home_id`) REFERENCES `home` (`home_id`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `order2` FOREIGN KEY (`home_id`) REFERENCES `home` (`home_id`),
  ADD CONSTRAINT `order3` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `order_history`
--
ALTER TABLE `order_history`
  ADD CONSTRAINT `orderhistory` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order_info`
--
ALTER TABLE `order_info`
  ADD CONSTRAINT `eordrinfo2` FOREIGN KEY (`home_id`) REFERENCES `home` (`home_id`),
  ADD CONSTRAINT `orderinfo1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

--
-- Constraints for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD CONSTRAINT `payment_history1` FOREIGN KEY (`payment_provider_id`) REFERENCES `payment_provider` (`payment_provider_id`);

--
-- Constraints for table `payment_provider`
--
ALTER TABLE `payment_provider`
  ADD CONSTRAINT `payment1` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `role1` FOREIGN KEY (`role_status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user1` FOREIGN KEY (`user_status`) REFERENCES `status` (`status_id`);

--
-- Constraints for table `user_fcm`
--
ALTER TABLE `user_fcm`
  ADD CONSTRAINT `userfrc` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `user_home`
--
ALTER TABLE `user_home`
  ADD CONSTRAINT `userhome` FOREIGN KEY (`status`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `userhome1` FOREIGN KEY (`home_id`) REFERENCES `home` (`home_id`),
  ADD CONSTRAINT `userhome2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `userrole1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `userrole2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `vin_point`
--
ALTER TABLE `vin_point`
  ADD CONSTRAINT `vinpoint1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
