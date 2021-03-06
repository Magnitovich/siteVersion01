DROP TABLE role_mapping;
DROP TABLE car;
DROP TABLE car_description;
DROP TABLE users;
DROP TABLE user_role;
DROP TABLE whisky;
DROP TABLE yachts;

CREATE TABLE `whisky` (
  `photo` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `yachts` (
  `photo` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `descriptions` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `IDYACHT` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IDYACHT`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `role_mapping` (
  `USER_ID` varchar(255) NOT NULL,
  `ROLE_UID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_UID`),
  KEY `fk_role_user` (`ROLE_UID`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`ROLE_UID`) REFERENCES `user_role` (`ID`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `car` (
  `PHOTO` varchar(45) DEFAULT NULL,
  `NAME_CAR` varchar(45) DEFAULT NULL,
  `CAR_DESCRIPTION_ID` int(11) DEFAULT NULL,
  `IDCAR` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IDCAR`),
  KEY `FK_CAR_ID_idx` (`CAR_DESCRIPTION_ID`),
  CONSTRAINT `FK_CAR_ID` FOREIGN KEY (`CAR_DESCRIPTION_ID`) REFERENCES `car_description` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `car_description` (
  `DESCRIPTIOON` varchar(255) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRICE` double NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB;

INSERT INTO USERS (name, password) VALUE ('test', 'test');
INSERT INTO USERS (name, password) VALUE ('test2', 'test2');
INSERT INTO USERS (name, password) VALUE ('test3', 'test3');

INSERT INTO user_role (NAME) VALUE ('admin');
INSERT INTO user_role (NAME) VALUE ('moderator');
INSERT INTO user_role (NAME) VALUE ('user');

INSERT INTO role_mapping VALUES('test', 1);
INSERT INTO role_mapping VALUES('test', 2);

INSERT INTO role_mapping VALUES('test2', 1);

INSERT INTO role_mapping VALUES('test2', 3);