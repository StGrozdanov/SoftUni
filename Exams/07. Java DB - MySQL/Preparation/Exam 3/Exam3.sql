CREATE DATABASE softuni_stores_system;
USE softuni_stores_system;

CREATE TABLE `pictures` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `url` VARCHAR(100) NOT NULL,
    `added_on` DATETIME NOT NULL
);

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE `products` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE,
    `best_before` DATE,
    `price` DECIMAL(10, 2) NOT NULL,
    `description` TEXT,
    `category_id` INT NOT NULL,
    `picture_id` INT NOT NULL,
    CONSTRAINT fk_products_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`),
    CONSTRAINT fk_products_pictures
    FOREIGN KEY (`picture_id`)
    REFERENCES `pictures` (`id`)
);

CREATE TABLE `towns`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE `addresses` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    `town_id` INT NOT NULL,
    CONSTRAINT fk_addresses_towns
    FOREIGN KEY (`town_id`)
    REFERENCES `towns` (`id`)
);

CREATE TABLE `stores` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE,
    `rating` FLOAT NOT NULL,
    `has_parking` BOOLEAN DEFAULT FALSE,
    `address_id` INT NOT NULL,
    CONSTRAINT fk_stores_addresses
    FOREIGN KEY (`address_id`)
    REFERENCES `addresses` (`id`)
);

CREATE TABLE `products_stores`(
	`product_id` INT NOT NULL,
	`store_id` INT NOT NULL,
    CONSTRAINT fk_products_stores_product
    FOREIGN KEY (`product_id`)
    REFERENCES `products` (`id`),
    CONSTRAINT fk_products_stores_store
    FOREIGN KEY (`store_id`)
    REFERENCES `stores` (`id`),
    CONSTRAINT pk_products_stores
    PRIMARY KEY(`product_id`, `store_id`)
);

CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(15) NOT NULL,
    `middle_name` CHAR(1),
    `last_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(19,2) DEFAULT 0 NOT NULL,
    `hire_date` DATE NOT NULL,
    `manager_id` INT, 
    `store_id` INT NOT NULL,
    CONSTRAINT fk_employees_stores	
    FOREIGN KEY (`store_id`)
    REFERENCES `stores` (`id`),
    CONSTRAINT fk_employee_manager
    FOREIGN KEY (`manager_id`)
    REFERENCES `employees`(`id`)
);

#P02
INSERT INTO products_stores(`product_id`, `store_id`)
SELECT p.`id` AS `product_id`, '1' AS `store_id`
FROM `products` AS p
LEFT JOIN `products_stores` AS ps
ON p.`id` = ps.`product_id`
LEFT JOIN `stores` AS s
ON s.`id` = ps.`store_id`
WHERE s.`id` IS NULL;

#P03
UPDATE `employees`
SET `salary` = `salary` - 500, `manager_id` = 3
WHERE YEAR(`hire_date`) > 2003 AND `store_id` NOT IN (5,14);

#P04
DELETE 
FROM `employees`
WHERE `manager_id` IS NOT NULL AND `salary` > 6000;

#P05
SELECT `first_name`, `middle_name`, `last_name`, `salary`, `hire_date`
FROM `employees`
ORDER BY `hire_date` DESC;

#P06
SELECT 
p.`name`, 
p.`price`, 
p.`best_before`, 
CONCAT(LEFT(p.`description`, 10), '...') AS `short_description`,
pic.`url`
FROM `products` as p
JOIN `pictures` AS pic
ON p.`picture_id` = pic.`id`
WHERE CHARACTER_LENGTH(p.`description`) > 100 AND YEAR(pic.`added_on`) < 2019 AND p.`price` > 20
ORDER BY p.`price` DESC;

#P07
SELECT s.`name`, COUNT(p.`id`) AS `product_count`, ROUND(AVG(p.`price`), 2) AS `avg`
FROM `stores` AS s
LEFT JOIN `products_stores` AS ps
ON s.`id` = ps.`store_id`
LEFT JOIN `products` AS p
ON p.`id` = ps.`product_id`
GROUP BY s.`id`
ORDER BY `product_count` DESC, `avg` DESC, s.`id`;

#P08
SELECT 
CONCAT(e.`first_name`, ' ', e.`last_name`) AS `full_name`,
s.`name` AS `Store_name`,
a.`name` AS `address`,
e.`salary`
FROM `employees` AS e
JOIN `stores` AS s
ON e.`store_id` = s.`id`
JOIN `addresses` AS a
ON s.`address_id` = a.`id`
WHERE e.`salary` < 4000 AND 
a.`name` LIKE '%5%' AND 
CHAR_LENGTH(s.`name`) > 8 AND 
RIGHT(e.`last_name`, 1) = 'n';

#P09
SELECT
REVERSE(s.`name`) AS `reversed_name`,
CONCAT(UPPER(t.`name`), '-', a.`name`) AS `full_address`,
COUNT(DISTINCT e.`id`) AS `employees_count`
FROM `employees` AS e
JOIN `stores` AS s
ON e.`store_id` = s.`id`
JOIN `addresses` AS a
ON s.`address_id` = a.`id`
JOIN `towns` AS t
ON t.`id` = a.`town_id`
GROUP BY s.`id`
HAVING `employees_count` > 0 
ORDER BY `full_address`;

#P10
DELIMITER $
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(255) 
DETERMINISTIC
BEGIN
	RETURN(
    SELECT 
	CONCAT(e.`first_name`, ' ', e.`middle_name`, '. ', e.`last_name`, ' works in store for ', 
	TIMESTAMPDIFF(YEAR, e.`hire_date`, '2020-10-18'), ' years') AS `full_info`
	FROM `employees` AS e
	JOIN `stores` AS s
	ON e.`store_id` = s.`id`
	WHERE s.`name` = store_name
	ORDER BY e.`salary` DESC
	LIMIT 1);
END $

DELIMITER ;
SELECT udf_top_paid_employee_by_store('Keylex') as 'full_info';

#P11
DELIMITER $
CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50)) 
BEGIN
	UPDATE `products` as p
    JOIN `products_stores` as ps
	ON p.`id` = ps.`product_id`
	JOIN `stores` as s
	ON s.`id` = ps.`store_id`
	JOIN `addresses` as a
	ON s.`address_id` = a.`id`
    SET p.`price` = p.`price` + (IF (LEFT(address_name, 1) = '0', 100, 200))
	WHERE a.`name` = address_name;
END$

DELIMITER ;

CALL udp_update_product_price('07 Armistice Parkway');
SELECT name, price FROM products WHERE id = 15;

CALL udp_update_product_price('1 Cody Pass');
SELECT name, price FROM products WHERE id = 17;
