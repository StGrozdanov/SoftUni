CREATE DATABASE ruk_database;
USE ruk_database;

CREATE TABLE `branches` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(10, 2) NOT NULL,
    `started_on` DATE NOT NULL,
    `branch_id` INT NOT NULL,
    CONSTRAINT fk_employees_branches
    FOREIGN KEY (`branch_id`)
    REFERENCES `branches`(`id`)
);

CREATE TABLE `clients` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `full_name` VARCHAR(50) NOT NULL,
	`age` INT NOT NULL
);

CREATE TABLE `employees_clients` (
    `employee_id` INT,
    `client_id` INT,
    CONSTRAINT fk_employees_clients_employees
    FOREIGN KEY (`employee_id`)
    REFERENCES `employees` (`id`),
    CONSTRAINT fk_employees_clients_clients
    FOREIGN KEY (`client_id`)
    REFERENCES `clients` (`id`)
);

CREATE TABLE `bank_accounts` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `account_number` VARCHAR(10) NOT NULL,
    `balance` DECIMAL(10,2) NOT NULL,
    `client_id` INT NOT NULL UNIQUE,
    CONSTRAINT fk_bank_accounts_clients
    FOREIGN KEY(`client_id`)
    REFERENCES `clients` (`id`)
);

CREATE TABLE `cards` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `card_number` VARCHAR(19) NOT NULL,
    `card_status` VARCHAR(7) NOT NULL,
    `bank_account_id` INT NOT NULL,
    CONSTRAINT fk_cards_bank_accounts
    FOREIGN KEY (`bank_account_id`)
    REFERENCES `bank_accounts`(`id`)
);

#P02
INSERT INTO `cards`(`card_number`, `card_status`, `bank_account_id`)
SELECT REVERSE(`full_name`) AS `card_number`, 'Active' AS `card_status`, `id` AS `bank_account_id`
FROM `clients`
WHERE `id` BETWEEN 191 AND 200;

#P03
UPDATE `employees_clients` AS ec
SET ec.`employee_id` = 
( SELECT * FROM (
	SELECT ec.`employee_id`  
	FROM `clients` AS c
	JOIN `employees_clients` AS ec
	ON c.`id` = ec.`client_id`
	GROUP BY ec.`employee_id`
	ORDER BY COUNT(ec.`client_id`), ec.`employee_id`
	LIMIT 1) AS em
)
WHERE ec.`employee_id` = ec.`client_id`;

#P04
DELETE e.* 
FROM `employees` AS e
LEFT JOIN `employees_clients` AS ec
ON e.`id` = ec.`employee_id`
WHERE ec.`client_id` IS NULL;

#P05
SELECT `id`, `full_name` FROM `clients` ORDER BY `id`;

#P06
SELECT `id`, CONCAT_WS(' ', `first_name`, `last_name`) AS `full_name`, CONCAT('$', `salary`) AS `salary`, `started_on` 
FROM `employees`
WHERE `salary` >= 100000 AND `started_on` >= '2018-01-01'
ORDER BY `salary` DESC, `id`;

#P07
SELECT c.`id`, CONCAT(c.`card_number`, ' : ', `client`.`full_name`) AS `card_token`
FROM `cards` AS c
JOIN `bank_accounts` AS ba
ON c.`bank_account_id` = ba.`id`
JOIN `clients` AS `client`
ON ba.`client_id` = `client`.`id`
ORDER BY c.`id` DESC;

#P08
SELECT CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS `name`, e.`started_on`, COUNT(c.`id`) AS `clients_count`
FROM `employees` AS e
LEFT JOIN `employees_clients` as cs
ON cs.`employee_id` = e.`id`
LEFT JOIN `clients` AS c
ON c.`id` = cs.`client_id`
GROUP BY e.`id`
ORDER BY `clients_count` DESC, e.`id`;

#P09
SELECT b.`name`, COUNT(card.`id`) AS 'count_of_cards'
FROM `branches` AS b
LEFT JOIN `employees` AS e
ON b.`id` = e.`branch_id`
LEFT JOIN `employees_clients` AS ec
ON e.`id` = ec.`employee_id`
LEFT JOIN `clients` AS c
ON c.`id` = ec.`client_id`
LEFT JOIN `bank_accounts` AS ba
ON c.`id` = ba.`client_id`
LEFT JOIN `cards` AS card
ON ba.`id` = card.`bank_account_id`
GROUP BY b.`id`
ORDER BY `count_of_cards` DESC, b.`name`;

#P10
DELIMITER $
CREATE FUNCTION udf_client_cards_count(name VARCHAR(30)) 
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN(
	SELECT COUNT(card.`id`) AS 'count_of_cards'
	FROM `clients` AS c
	LEFT JOIN `bank_accounts` AS ba
	ON c.`id` = ba.`client_id`
	LEFT JOIN `cards` AS card
	ON ba.`id` = card.`bank_account_id`
	WHERE c.`full_name` = name
	GROUP BY c.`id`);
END$

SELECT c.full_name, udf_client_cards_count('Baxy David') as `cards` FROM clients c
WHERE c.full_name = 'Baxy David'$

CREATE PROCEDURE udp_clientinfo (name VARCHAR(30))
BEGIN
SELECT c.`full_name`, c.`age`, ba.`account_number`, CONCAT('$', ba.`balance`) AS `balance`
FROM `clients` AS c
LEFT JOIN `bank_accounts` AS ba
ON c.`id` = ba.`client_id`
LEFT JOIN `cards` AS card
ON ba.`id` = card.`bank_account_id`
WHERE c.`full_name` = name
GROUP BY c.`id`;
END$

CALL udp_clientinfo('Hunter Wesgate')$
