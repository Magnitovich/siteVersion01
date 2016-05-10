CREATE TABLE `users` (
  `user_Name` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `whisky` (
  `photo` varchar(45) DEFAULT NULL,
  `name_Whisky` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`name_Whisky`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `yachts` (
  `photo` varchar(45) DEFAULT NULL,
  `name_Yachts` varchar(45) NOT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`name_Yachts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;