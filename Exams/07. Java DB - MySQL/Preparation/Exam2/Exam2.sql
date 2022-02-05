CREATE DATABASE `stc`;
USE `stc`;

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL
);

CREATE TABLE `clients` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `full_name` VARCHAR(50) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE `drivers` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(30) NOT NULL,
    `last_name` VARCHAR(30) NOT NULL,
    `age` INT NOT NULL,
    `rating` FLOAT DEFAULT 5.5
);

CREATE TABLE `cars` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `make` VARCHAR(20) NOT NULL,
    `model` VARCHAR(20),
    `year` INT NOT NULL DEFAULT 0,
    `mileage` INT DEFAULT 0,
    `condition` CHAR(1) NOT NULL,
    `category_id` INT NOT NULL,
    CONSTRAINT fk_cars_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories`(`id`)
);

CREATE TABLE `courses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `from_address_id` INT NOT NULL,
    `start` DATETIME NOT NULL,
    `bill` DECIMAL(10, 2) DEFAULT 10.00,
    `car_id` INT NOT NULL,
    `client_id` INT NOT NULL,
    CONSTRAINT fk_courses_addresses
    FOREIGN KEY (`from_address_id`)
    REFERENCES `addresses`(`id`),
    CONSTRAINT fk_courses_cars
    FOREIGN KEY (`car_id`)
    REFERENCES `cars`(`id`),
    CONSTRAINT fk_courses_clients
    FOREIGN KEY (`client_id`)
    REFERENCES `clients`(`id`)
);

CREATE TABLE `cars_drivers` (
	`car_id` INT NOT NULL,
	`driver_id` INT NOT NULL,
    CONSTRAINT fk_cars_drivers_cars
    FOREIGN KEY (`car_id`)
    REFERENCES `cars`(`id`),
    CONSTRAINT fk_cars_drivers_drivers
    FOREIGN KEY (`driver_id`)
    REFERENCES `drivers`(`id`),
    CONSTRAINT pk_cars_drivers
    PRIMARY KEY(`car_id`, `driver_Id`)
);

#P02
INSERT INTO `clients`(`full_name`, `phone_number`)
SELECT 
CONCAT(`first_name`, ' ', `last_name`) AS 'full_name', 
CONCAT('(088) 9999', '', `id` * 2) AS 'phone_number'
FROM `drivers`
WHERE `id` BETWEEN 10 AND 20;

#P03
UPDATE `cars`
SET `condition` = 'C'
WHERE `mileage` IS NULL OR `mileage` >= 800000 AND `year` < 2011 AND `make` != 'Mercedes-Benz';

#P04
DELETE c.*
FROM `clients` AS c
LEFT JOIN `courses` AS crs
ON c.`id` = crs.`client_id`
WHERE crs.`id` IS NULL AND CHAR_LENGTH(c.`full_name`) > 3;

#P05
SELECT `make`, `model`, `condition` FROM `cars` ORDER BY `id`; 

#P06
SELECT d.`first_name`, d.`last_name`, c.`make`, c.`model`, c.`mileage`
FROM `drivers` AS d
JOIN `cars_drivers` AS cd
ON d.`id` = cd.`driver_id`
JOIN `cars` AS c
ON c.`id` = cd.`car_id`
WHERE c.`mileage` IS NOT NULL
ORDER BY c.`mileage` DESC, d.`first_name`;

#P07
SELECT c.`id`, c.`make`, c.`mileage`, COUNT(crs.`id`) AS `count_of_courses`, ROUND(AVG(crs.`bill`), 2) AS `avg_bill`
FROM `cars` AS c
LEFT JOIN `courses` AS crs
ON c.`id` = crs.`car_id`
GROUP BY c.`id`
HAVING `count_of_courses` != 2
ORDER BY `count_of_courses` DESC, c.`id`;

#P08
SELECT c.`full_name`, COUNT(crs.`id`) AS `count_of_cars`, SUM(crs.`bill`) AS `total_sum`
FROM `clients` AS c
JOIN `courses` AS crs
ON c.`id` = crs.`client_id`
WHERE SUBSTRING(c.`full_name`, 2, 1) = 'a'
GROUP BY c.`id`
HAVING `count_of_cars` > 1
ORDER BY c.`full_name`;

#P09
SELECT 
a.`name`,
IF(HOUR(crs.`start`) BETWEEN 6 AND 20, 'Day', 'Night') AS `day_time`,
crs.`bill`,
c.`full_name`,
car.`make`,
car.`model`,
cat.`name`
FROM `courses` AS crs
JOIN `addresses` AS a
ON crs.`from_address_id` = a.`id`
JOIN `clients` AS c
ON crs.`client_id` = c.`id`
JOIN `cars` AS car
ON car.`id` = crs.`car_id`
JOIN `categories` AS cat
ON cat.`id` = car.`category_id`
ORDER BY crs.`id`;

#P10
DELIMITER $
CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20)) 
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN(SELECT COUNT(crs.`id`) AS `count`
	FROM `clients` AS c
	LEFT JOIN `courses` AS crs
	ON c.`id` = crs.`client_id`
	WHERE c.`phone_number` = phone_num
	GROUP BY c.`id`);
END$

SELECT udf_courses_by_client ('(831) 1391236') as `count`$

#P11
CREATE PROCEDURE udp_courses_by_address (address_name VARCHAR(100))
BEGIN
	SELECT 
	a.`name`,
	c.`full_name`,
	(
		CASE
			WHEN crs.`bill` <= 20 THEN 'Low'
			WHEN crs.`bill` <= 30 THEN 'Medium'
			ELSE 'High' 
		END
	) AS `level_of_bill`,
	car.`make`,
	car.`condition`,
	cat.`name`
	FROM `addresses` AS a
	JOIN `courses` AS crs
	ON a.`id` = crs.`from_address_id`
	JOIN `clients` AS c
	ON c.`id` = crs.`client_id`
	JOIN `cars` AS car
	ON car.`id` = crs.`car_id`
	JOIN `categories` AS cat
	ON cat.`id` = car.`category_id`
	WHERE a.`name` = address_name 
	ORDER BY car.`make`, c.`full_name`;
END$

CALL udp_courses_by_address('66 Thompson Drive');
