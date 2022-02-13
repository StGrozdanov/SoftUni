#P01
CREATE DATABASE online_store;
USE online_store;

CREATE TABLE `brands` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE `reviews` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `content` TEXT,
    `rating` DECIMAL(10,2) NOT NULL,
    `picture_url` VARCHAR(80) NOT NULL,
    `published_at` DATETIME NOT NULL
);

CREATE TABLE `products`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `price` DECIMAL(19,2) NOT NULL,
    `quantity_in_stock` INT,
    `description` TEXT,
    `brand_id` INT NOT NULL,
    `category_id` INT NOT NULL,
    `review_id` INT,
    CONSTRAINT fk_products_brands
    FOREIGN KEY (`brand_id`)
    REFERENCES `brands` (`id`),
    CONSTRAINT fk_products_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`),
    CONSTRAINT fk_products_reviews
    FOREIGN KEY (`review_id`)
    REFERENCES `reviews` (`id`)
);

CREATE TABLE `customers`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `phone` VARCHAR(30) NOT NULL UNIQUE,
    `address` VARCHAR(60) NOT NULL,
    `discount_card` BIT NOT NULL DEFAULT FALSE
);

CREATE TABLE `orders` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `order_datetime` DATETIME NOT NULL,
    `customer_id` INT NOT NULL,
    CONSTRAINT fk_orders_customers
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers`(`id`)
);

CREATE TABLE `orders_products`(
	`order_id` INT,
	`product_id` INT,
    CONSTRAINT fk_orders_products_orders
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`(`id`),
    CONSTRAINT fk_orders_products_products
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`id`)
);

#P02
INSERT INTO `reviews`(`content`, `picture_url`, `published_at`, `rating`)
SELECT 
LEFT(`description`, 15) AS `content`, 
REVERSE(`name`) AS `picture_url`, 
'2010-10-10' AS `published_at`, 
`price` / 8 AS `rating`
FROM `products`
WHERE `id` >= 5;

#P03
UPDATE `products`
SET `quantity_in_stock` = `quantity_in_stock` - 5
WHERE `quantity_in_stock` BETWEEN 60 AND 70;

#P04
DELETE c.* 
FROM `customers` AS c
LEFT JOIN `orders` AS o
ON c.`id` = o.`customer_id`
WHERE o.`id` IS NULL;

#P05
SELECT `id`, `name`
FROM `categories`
ORDER BY `name` DESC;

#P06
SELECT `id`, `brand_id`, `name`, `quantity_in_stock`
FROM `products`
WHERE `price` > 1000 AND `quantity_in_stock` < 30
ORDER BY `quantity_in_stock`, `id`;

#P07
SELECT * FROM `reviews`
WHERE LEFT(`content`, 2) = 'My' AND CHAR_LENGTH(`content`) > 61
ORDER BY `rating` DESC;

#P08
SELECT 
CONCAT(c.`first_name`, ' ', c.`last_name`) AS `full_name`,
c.`address`,
o.`order_datetime`
FROM `customers` AS c
JOIN `orders` AS o
ON c.`id` = o.`customer_id`
WHERE YEAR(`order_datetime`) <= 2018
ORDER BY `full_name` DESC;

#P09
SELECT COUNT(p.`id`) AS `products_count`, c.`name`, SUM(p.`quantity_in_stock`) AS `total_quantity`
FROM `categories` AS c
JOIN `products`  AS p
ON c.`id` = p.`category_id`
GROUP BY c.`id`
ORDER BY `products_count` DESC, `total_quantity`
LIMIT 5;

#P10
DELIMITER $
CREATE FUNCTION udf_customer_products_count(name VARCHAR(30))
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN(
	SELECT COUNT(p.`id`) AS `total_products` 
	FROM `customers` AS c
	LEFT JOIN `orders` AS o
	ON c.`id` = o.`customer_id`
	LEFT JOIN `orders_products` AS op
	ON o.`id` = op.`order_id`
	LEFT JOIN `products` AS p
	ON p.`id` = op.`product_id`
	WHERE c.`first_name` = name
	GROUP BY c.`id`);
END$

SELECT c.first_name,c.last_name, udf_customer_products_count('Shirley') as `total_products` FROM customers c
WHERE c.first_name = 'Shirley'$

#P11
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
	UPDATE `categories` AS c
	JOIN `products`  AS p
	ON c.`id` = p.`category_id`
	JOIN `reviews` AS r
	ON r.`id` = p.`review_id`
	SET p.`price` = p.`price` * 0.7
	WHERE c.`name` = category_name AND r.`rating` < 4;
END$

CALL udp_reduce_price ('Phones and tablets')$

SELECT * FROM `categories` AS c
	JOIN `products`  AS p
	ON c.`id` = p.`category_id`
	JOIN `reviews` AS r
	ON r.`id` = p.`review_id`
	WHERE c.`name` = 'Phones and tablets' AND r.`rating` < 4$
