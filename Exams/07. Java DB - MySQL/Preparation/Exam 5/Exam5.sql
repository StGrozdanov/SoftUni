CREATE DATABASE sgd;
USE sgd;

CREATE TABLE `addresses` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL
);

CREATE TABLE `offices` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `workspace_capacity` INT NOT NULL,
	`website` VARCHAR(50),
    `address_id` INT NOT NULL,
    CONSTRAINT fk_offices_addresses
    FOREIGN KEY (`address_id`)
    REFERENCES `addresses` (`id`)
);

CREATE TABLE `employees`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(30) NOT NULL,
    `last_name` VARCHAR(30) NOT NULL,
    `age` INT NOT NULL,
    `salary` DECIMAL(10,2) NOT NULL,
    `job_title` VARCHAR(20) NOT NULL, 
    `happiness_level` CHAR NOT NULL
);

CREATE TABLE `teams` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) NOT NULL,
    `office_id` INT NOT NULL,
    `leader_id` INT NOT NULL UNIQUE,
    CONSTRAINT fk_teams_offices
    FOREIGN KEY (`office_id`)
    REFERENCES `offices` (`id`),
    CONSTRAINT fk_teams_employees
    FOREIGN KEY (`leader_id`)
    REFERENCES `employees` (`id`)
);

CREATE TABLE `games` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    `description` TEXT,
    `rating` FLOAT NOT NULL DEFAULT 5.5,
    `budget` DECIMAL(10,2) NOT NULL,
    `release_date` DATE,
    `team_id` INT NOT NULL,
    CONSTRAINT fk_games_teams
    FOREIGN KEY(`team_id`)
    REFERENCES `teams` (`id`)
);

CREATE TABLE `games_categories`(
	`game_id` INT NOT NULL,
	`category_id` INT NOT NULL,
    CONSTRAINT fk_games_categories_games
    FOREIGN KEY (`game_id`)
    REFERENCES `games` (`id`),
    CONSTRAINT fk_games_categories_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`),
    CONSTRAINT pk_games_categories
    PRIMARY KEY(`game_id`, `category_id`)
);

#P02
INSERT INTO `games`(`name`, `rating`, `budget`, `team_id`)
SELECT 
LOWER(REVERSE(SUBSTRING(`name`, 2))) AS `name`, 
`id` AS `rating`,
`leader_id` * 1000 AS `budget`,
`id` AS `team_id`
FROM `teams` 
WHERE `id` BETWEEN 1 AND 9;

#P03
UPDATE `employees` AS e
JOIN `teams` as t 
ON e.`id` = t.`leader_id`
SET `salary` = `salary` + 1000
WHERE `age` < 40 AND t.`leader_id` = e.`id` AND `salary` < 5000;

#P04
DELETE g.*
FROM `games` AS g
LEFT JOIN `games_categories` as gc
ON gc.`game_id` = g.`id` 
WHERE g.`release_date` IS NULL AND gc.`category_id` IS NULL;

#P05
SELECT `first_name`, `last_name`, `age`, `salary`, `happiness_level`
FROM `employees`
ORDER BY `salary`, `id`;

#P06
SELECT t.`name` AS `team_name`, a.`name` AS `address_name`, CHAR_LENGTH(a.`name`) AS `count_of_characters`
FROM `teams` AS t
LEFT JOIN `offices` AS o
ON t.`office_id` = o.`id`
LEFT JOIN `addresses` AS a
ON a.`id` = o.`address_id`
WHERE o.`website` IS NOT NULL
ORDER BY t.`name`, a.`name`;

#P07
SELECT 
c.`name`, 
COUNT(g.`id`) AS `games_count`, 
ROUND(AVG(g.`budget`), 2) AS `average_budget`, 
MAX(g.`rating`) AS `max_rating` 
FROM `categories` as c
LEFT JOIN `games_categories` as gc
ON c.`id` = gc.`category_id`
LEFT JOIN `games` as g
ON gc.`game_id` = g.`id`
GROUP BY c.`name`
HAVING `max_rating` >= 9.5
ORDER BY `games_count` DESC, `name`;

#P08
SELECT 
g.`name`, 
g.`release_date`, 
CONCAT(LEFT(g.`description`, 10), '...') AS `summary`,
(
CASE
	WHEN MONTH(g.`release_date`) = '2' THEN 'Q1' 
	WHEN MONTH(g.`release_date`) IN ('4', '6') THEN 'Q2' 
	WHEN MONTH(g.`release_date`) = '8' THEN 'Q3' 
	WHEN MONTH(g.`release_date`) IN ('10', '12') THEN 'Q4' 
END
) AS `quarter`,
t.`name`
FROM `games` AS g 
JOIN `teams` AS t
ON g.`team_id` = t.`id`
WHERE YEAR(g.`release_date`) = 2022 AND MONTH(g.`release_date`) % 2 = 0 AND RIGHT(g.`name`, 1) = '2'
ORDER BY `quarter`;

#P09
SELECT 
g.`name`, 
IF(g.`budget` < 50000, 'Normal budget', 'Insufficient budget') AS `budget_level`,
t.`name` AS `team_name`,
a.`name` AS `address_name`
FROM `games` AS g
LEFT JOIN `games_categories` as gc
ON gc.`game_id` = g.`id` 
LEFT JOIN `teams` AS t
ON t.`id` = g.`team_id`
LEFT JOIN `offices` AS o
ON t.`office_id` = o.`id`
LEFT JOIN `addresses` AS a
ON o.`address_id` = a.`id`
WHERE g.`release_date` IS NULL AND gc.`category_id` IS NULL
ORDER BY g.`name`;

#P10
DELIMITER $
CREATE FUNCTION udf_game_info_by_name(game_name VARCHAR (20)) 
RETURNS VARCHAR(255) 
DETERMINISTIC
BEGIN
RETURN (
	SELECT 
	CONCAT_WS(' ', 'The', g.`name`, 'is developed by a', t.`name`, 'in an office with an address', a.`name`) AS `descr`
	FROM `games` AS g
	JOIN `teams` AS t
	ON t.`id` = g.`team_id`
	JOIN `offices` AS o
	ON t.`office_id` = o.`id`
	JOIN `addresses` AS a
	ON o.`address_id` = a.`id`
	WHERE g.`name` = game_name);
END$

SELECT udf_game_info_by_name('Bitwolf') AS info$
SELECT udf_game_info_by_name('Fix San') AS info$
SELECT udf_game_info_by_name('Job') AS info$

#P11
CREATE PROCEDURE udp_update_budget(min_game_rating FLOAT) 
BEGIN
	UPDATE `games` AS g
	LEFT JOIN `games_categories` as gc
	ON gc.`game_id` = g.`id`
	SET 
	g.`budget` = g.`budget` + 100000,
	g.`release_date` = DATE_ADD(g.`release_date`, INTERVAL 1 YEAR) 
	WHERE g.`release_date` IS NOT NULL AND gc.`category_id` IS NULL AND g.`rating` > min_game_rating;
END$

CALL udp_update_budget (8)$

SELECT * FROM `games` WHERE `name` IN ('Quo Lux', 'Daltfresh', 'Span')$
