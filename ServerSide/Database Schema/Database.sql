CREATE DATABASE IF NOT EXISTS `tictactoedb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tictactoedb`;
CREATE TABLE `games` (
  `gid` bigint(20)  UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `turn` enum('X','O','') NOT NULL DEFAULT '',
  `cell0` enum('X','O','') NOT NULL DEFAULT '',
  `cell1` enum('X','O','') NOT NULL DEFAULT '',
  `cell2` enum('X','O','') NOT NULL DEFAULT '',
  `cell3` enum('X','O','') NOT NULL DEFAULT '',
  `cell4` enum('X','O','') NOT NULL DEFAULT '',
  `cell5` enum('X','O','') NOT NULL DEFAULT '',
  `cell6` enum('X','O','') NOT NULL DEFAULT '',
  `cell7` enum('X','O','') NOT NULL DEFAULT '',
  `cell8` enum('X','O','') NOT NULL DEFAULT '',
  `player1` bigint(20) UNSIGNED NOT NULL,
  `player2` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `pid` bigint(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `username` longtext NOT NULL,
  `passwd` longtext NOT NULL,
  `email` longtext DEFAULT NULL,
  `status` enum('offline','online','busy','none') NOT NULL DEFAULT 'none',
  `score` bigint(20) UNSIGNED NOT NULL DEFAULT 0,
  `avatar` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`pid`, `username`, `passwd`, `email`, `status`, `score`, `avatar`) VALUES
(1, 'user1', '123456789', NULL, 'offline', 30, NULL),
(2, 'user2', '123456789', NULL, 'offline', 300, NULL),
(3, 'user3', '123456789', NULL, 'offline', 0, NULL),
(5, 'userpass', '$2a$10$j2d3Vzab4uK.3uzqfABrgOeh9og.qwiKaUSUa.IBEhb/SGtoEB4v6', NULL, 'offline', 0, NULL),
(6, 'ahmed', '123456789', 'null', 'offline', 0, NULL),
(7, 'ali', '123456789', 'null', 'offline', 0, NULL);

ALTER TABLE `games`
  ADD CONSTRAINT `games_ibfk_1` FOREIGN KEY (`player1`) REFERENCES `players` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `games_ibfk_2` FOREIGN KEY (`player2`) REFERENCES `players` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;