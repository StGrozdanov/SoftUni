CREATE DATABASE `minions`;

USE `minions`;

CREATE TABLE `minions` (
	`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	`name` VARCHAR(30),
	`age` INT
);

CREATE TABLE `towns` (
	`town_id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(30)
);

ALTER TABLE `towns`
CHANGE COLUMN `town_id` `id` INT;

SELECT * FROM `townstowns`;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT,
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`);

SELECT * FROM `minions`;

INSERT INTO `towns`
VALUES 
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO `minions` 
VALUES 
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL, 2);

SELECT * FROM `towns`;

TRUNCATE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;

CREATE TABLE `people` (
	`id` INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
	`name` VARCHAR(200) NOT NULL,
	`picture` BLOB,
	`height` DOUBLE(3, 2),
	`weight` DOUBLE(3, 2),
	`gender` CHAR(1) NOT NULL,
	`birthdate` DATE NOT NULL,
	`biography` TEXT
);

INSERT INTO `people`
VALUES 
(1, 'Ivan', NULL, NULL, NULL, 'M', '1992-02-15', NULL),
(2, 'Stoyan', NULL, NULL, NULL, 'M', '1992-02-15', NULL),
(3, 'Dragan', NULL, NULL, NULL, 'M', '1992-02-15', NULL),
(4, 'Pesho', NULL, NULL, NULL, 'M', '1992-02-15', NULL),
(5, 'Misho', NULL, NULL, NULL, 'M', '1992-02-15', NULL);

CREATE TABLE `users` (
	`id` INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
	`username` VARCHAR(30) NOT NULL UNIQUE,
	`password` VARCHAR(26) NOT NULL,
	`profile_picture` BLOB,
	`last_login_time` DATETIME,
	`is_deleted` BOOLEAN DEFAULT FALSE
);

INSERT INTO `users`
VALUES 
(1, 'Ivan', 123, NULL, NULL, NULL), 
(2, 'Stoyan', 123, NULL, NULL, NULL),
(3, 'Dragan', 123, NULL, NULL, NULL),
(4, 'Pesho', 123, NULL, NULL, NULL),
(5, 'Misho', 123, NULL, NULL, NULL);

SELECT * FROM `users`;

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY(`id`, `username`);


ALTER TABLE `users`
CHANGE COLUMN `last_login_time` `last_login_time` DATETIME DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY(`id`),
CHANGE COLUMN `username` `username` VARCHAR(30) UNIQUE NOT NULL;

CREATE DATABASE `Movies`;
USE `Movies`;

CREATE TABLE `directors`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

INSERT INTO `directors`(`id`,`director_name`, `notes`)
VALUES
('1','Pesho',NULL), 
('2','Ivan',NULL), 
('3','Gosho',NULL), 
('4','Tapata',NULL), 
('5','Ali',NULL) 
;
 
CREATE TABLE `genres`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);
 
INSERT INTO `genres` (`id`, `genre_name`, `notes`)
VALUES
('1','Parody',NULL),
('2','Comedy',NULL),
('3','Drama',NULL),
('4','Action',NULL),
('5','Animation',NULL)
;
 
CREATE TABLE `categories`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`category_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);
 
INSERT INTO `categories` (`id`, `category_name`, `notes`)
VALUES
('1', '+0', NULL),
('2', '+6', NULL),
('3', '+12', NULL),
('4', '+16', NULL),
('5', '+18', NULL)
;
 
CREATE TABLE `movies`(
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`director_id` INT,
`copyright_year` YEAR,
`LENGTH` TIME,
`genre_id` INT,
`category_id` INT,
`rating` DECIMAL(2,1),
`notes` TEXT
);
 
INSERT INTO `movies`(`id`, `title`, `director_id`, `copyright_year`,`LENGTH`, `genre_id`,`category_id`, `rating`, `notes`)
VALUES
('1', 'No comment', '1', '1991', '00:05:00', '1','1', NULL, NULL),
('2', 'No comment', '2', '1992', '00:04:00', '2','5', NULL, NULL),
('3', 'No comment', '3', '1993', '00:03:00', '5','4', NULL, NULL),
('4', 'No comment', '4', '1994', '00:02:00', '4', '3', NULL, NULL),
('5', 'No comment', '4', '1995', '00:01:00', '3','2', NULL, NULL);

CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE `categories`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`category` VARCHAR(50) NOT NULL,
	`daily_rate` INT,
	`weekly_rate` INT,
	`monthly_rate` INT,
	`weekend_rate` INT
);

CREATE TABLE `cars`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`plate_number` VARCHAR(30) NOT NULL,
	`make` VARCHAR(30),
	`model` VARCHAR(30),
	`car_year` INT,
	`category_id` INT,
	`doors` INT,
	`picture` BLOB,
	`car_condition` VARCHAR(10),
	`available` BOOL
);

CREATE TABLE `employees`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`first_name` VARCHAR(40) NOT NULL,
	`last_name` VARCHAR(40) NOT NULL,
	`title` VARCHAR(30),
	`notes` TEXT
);

CREATE TABLE `customers`(
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`driver_licence_number` INT NOT NULL,
	`full_name` VARCHAR(50),
	`address` VARCHAR(50),
	`city` VARCHAR(50),
	`zip_code` INT,
	`notes` TEXT
);
 
CREATE TABLE `rental_orders` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
	`employee_id` INT NOT NULL,
	`customer_id` INT,
	`car_id` INT NOT NULL,
	`car_condition` VARCHAR(50),
	`tank_level` INT,
	`kilometrage_start` INT,
	`kilometrage_end` INT,
	`total_kilometrage` INT,
	`start_date` DATE,
	`end_date` DATE,
	`total_days` INT,
	`rate_applied` INT,
	`tax_rate` INT,
	`order_status` VARCHAR(50),
	`notes` TEXT
);

INSERT INTO `cars`(`plate_number`)
VALUES ('123'),('1234'),('12345');

INSERT INTO `categories`(`category`)
VALUES ('Classic'),('SUV'),('Sport');

INSERT INTO `customers`(`driver_licence_number`)
VALUES ('2232'),('232323'),('111');

INSERT INTO `employees`(`first_name`,`last_name`)
VALUES ('Ivan', 'Ivanov'),('Ivan1', 'Ivanov1'), ('Ivan2', 'Ivanov2');

INSERT INTO `rental_orders`(`employee_id`,`car_id`)
VALUES (1, 1),(1, 2), (2, 3);
