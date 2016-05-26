DROP TABLE car_description;
DROP TABLE role_mapping;
DROP TABLE car;
DROP TABLE users;
DROP TABLE user_role;
DROP TABLE whisky;
DROP TABLE yachts;
DROP TABLE role_mapping;

CREATE TABLE car_description(
  DESCRIPTIOON varchar(45),
  QUANTITY INT,
  PRICE NUMERIC,
  ID NUMERIC NOT NULL,
  PRIMARY KEY (ID)
) AUTO_INCREMENT=1;

CREATE TABLE users (
  user_Name varchar(45) NOT NULL,
  password varchar(45) DEFAULT NULL,
  PRIMARY KEY (user_Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE whisky (
  photo varchar(45) DEFAULT NULL,
  name_Whisky varchar(45) NOT NULL,
  description varchar(255) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  price double DEFAULT NULL,
  PRIMARY KEY (name_Whisky)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE yachts (
  photo varchar(45) DEFAULT NULL,
  name_Yachts varchar(45) NOT NULL,
  descriptions varchar(255) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  price varchar(45) DEFAULT NULL,
  PRIMARY KEY (name_Yachts)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_role (
  ID int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(45) NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE role_mapping (
  USER_ID varchar(255) NOT NULL,
  ROLE_UID int(11) NOT NULL,
  PRIMARY KEY (USER_ID,ROLE_UID),
  KEY fk_role_user (ROLE_UID),
  CONSTRAINT fk_role_user FOREIGN KEY (ROLE_UID) REFERENCES user_role (ID),
  CONSTRAINT fk_user_role FOREIGN KEY (USER_ID) REFERENCES users (user_Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE car (
  PHOTO varchar(45) DEFAULT NULL,
  NAME_CAR varchar(45) NOT NULL,
  CAR_DESCRIPTION_ID int(11) DEFAULT NULL,
  PRIMARY KEY (NAME_CAR),
  KEY FK_CAR_ID_idx (CAR_DESCRIPTION_ID),
  CONSTRAINT FK_CAR_ID FOREIGN KEY (CAR_DESCRIPTION_ID) REFERENCES car_description (ID) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE car_description (
  DESCRIPTIOON varchar(255) DEFAULT NULL,
  QUANTITY int(11) DEFAULT NULL,
  PRICE double NOT NULL,
  ID int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO USERS (user_Name, password) VALUE ('test', 'test');
INSERT INTO USERS (user_Name, password) VALUE ('test2', 'test2');
INSERT INTO USERS (user_Name, password) VALUE ('test3', 'test3');

INSERT INTO user_role (NAME) VALUE ('admin');
INSERT INTO user_role (NAME) VALUE ('moderator');
INSERT INTO user_role (NAME) VALUE ('user');

INSERT INTO role_mapping VALUES('test', 1);
INSERT INTO role_mapping VALUES('test', 2);

INSERT INTO role_mapping VALUES('test2', 1);

INSERT INTO role_mapping VALUES('test2', 3);