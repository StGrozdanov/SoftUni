CREATE DATABASE fsd;
USE fsd;

CREATE TABLE `countries` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

CREATE TABLE `towns` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `country_id` INT NOT NULL,
    CONSTRAINT fk_towns_countries
    FOREIGN KEY (`country_id`)
    REFERENCES `countries`(`id`)
);

CREATE TABLE `stadiums` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `capacity` INT NOT NULL,
    `town_id` INT NOT NULL,
    CONSTRAINT fk_stadiums_towns
    FOREIGN KEY (`town_id`)
    REFERENCES `towns`(`id`)
);

CREATE TABLE `teams` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `established` DATE NOT NULL,
    `fan_base` BIGINT NOT NULL DEFAULT 0,
    `stadium_id` INT NOT NULL,
    CONSTRAINT fk_teams_stadiums
    FOREIGN KEY(`stadium_id`)
    REFERENCES `stadiums` (`id`)
);

CREATE TABLE `skills_data` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `dribbling` INT DEFAULT 0,
    `pace` INT DEFAULT 0,
    `passing` INT DEFAULT 0,
    `shooting` INT DEFAULT 0,
    `speed` INT DEFAULT 0,
    `strength` INT DEFAULT 0
);

CREATE TABLE `coaches` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(10) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
	`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
    `coach_level` INT NOT NULL DEFAULT 0
);

CREATE TABLE `players`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(10) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `age` INT NOT NULL DEFAULT 0,
    `position` CHAR NOT NULL,
    `salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
    `hire_date` DATETIME,
    `skills_data_id` INT NOT NULL,
    `team_id` INT,
    CONSTRAINT fk_players_skills_data
    FOREIGN KEY (`skills_data_id`)
    REFERENCES `skills_data`(`id`),
    CONSTRAINT fk_players_teams
    FOREIGN KEY (`team_id`)
    REFERENCES `teams`(`id`)
);

CREATE TABLE `players_coaches` (
	`player_id` INT,
    `coach_id` INT,
    CONSTRAINT fk_players_coaches_players
    FOREIGN KEY (`player_id`)
    REFERENCES `players`(`id`),
    CONSTRAINT fk_players_coaches_coaches
    FOREIGN KEY (`coach_id`)
    REFERENCES `coaches`(`id`),
    CONSTRAINT pk_players_coaches
    PRIMARY KEY(`player_id`, `coach_id`)
);

#P02
INSERT INTO `coaches` (`first_name`, `last_name`, `salary`, `coach_level`)
SELECT `first_name`, `last_name`, `salary` * 2 AS `salary`, CHARACTER_LENGTH(`first_name`) AS `coach_level` 
FROM `players` 
WHERE `age` >= 45;

#P03
UPDATE `coaches` AS c
JOIN `players_coaches` AS pc
ON c.`id` = pc.`coach_id`
SET c.`coach_level` = c.`coach_level` + 1
WHERE LEFT(c.`first_name`, 1) = 'A';

#P04
DELETE FROM `players`
WHERE `age` >= 45;

#P05
SELECT `first_name`, `age`, `salary`
FROM `players`
ORDER BY `salary` DESC;

#P06
SELECT p.`id`, CONCAT_WS(' ', p.`first_name`, P.`last_name`) AS `full_name`, p.`age`, p.`position`, p.`hire_date`
FROM `players` AS p
JOIN `skills_data` AS sa
ON p.`skills_data_id` = sa.`id`
WHERE p.`age` < 23 AND p.`position` = 'A' AND p.`hire_date` IS NULL AND sa.`strength` > 50
ORDER BY p.`salary`, p.`age`;

#P07
SELECT t.`name` AS `team_name`, t.`established`, t.`fan_base`, COUNT(p.`id`) AS `players_count`
FROM `teams` AS t
LEFT JOIN `players` AS p
ON t.`id` = p.`team_id`
GROUP BY t.`id`
ORDER BY `players_count` DESC, t.`fan_base` DESC;

#P08
SELECT MAX(sa.`speed`) AS `max_speed`, town.`name` AS `town_name`
FROM `players` AS p
RIGHT JOIN `skills_data` AS sa
ON p.`skills_data_id` = sa.`id`
RIGHT JOIN `teams` AS t
ON p.`team_id` = t.`id`
RIGHT JOIN `stadiums` AS s
ON t.`stadium_id` = s.`id`
RIGHT JOIN `towns` AS town
ON town.`id` = s.`town_id`
WHERE t.`name` != 'Devify'
GROUP BY town.`id`
ORDER BY `max_speed` DESC, town.`name`;

#P09
SELECT c.`name`, COUNT(p.`id`) AS `total_count_of_players`, SUM(p.`salary`) AS `total_sum_of_salaries`
FROM `players` AS p
RIGHT JOIN `teams` AS t
ON p.`team_id` = t.`id`
RIGHT JOIN `stadiums` AS s
ON t.`stadium_id` = s.`id`
RIGHT JOIN `towns` AS town
ON town.`id` = s.`town_id`
RIGHT JOIN `countries` AS c
ON town.`country_id` = c.`id`
GROUP BY c.`id`
ORDER BY `total_count_of_players` DESC, c.`name`;

#P10
DELIMITER $
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30))
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN(
	SELECT COUNT(p.`id`) AS `players_count`
	FROM `players` AS p
	RIGHT JOIN `teams` AS t
	ON p.`team_id` = t.`id`
	RIGHT JOIN `stadiums` AS s
	ON t.`stadium_id` = s.`id`
	WHERE s.`name` = stadium_name
	GROUP BY s.`id`);
END$

SELECT udf_stadium_players_count ('Jaxworks') as `count`$
SELECT udf_stadium_players_count ('Linklinks') as `count`$

#P11
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT, team_name VARCHAR(45)) 
BEGIN
	SELECT 
	CONCAT_WS(' ', p.`first_name`, p.`last_name`) AS `full_name`,
	p.`age`,
	p.`salary`,
	sa.`dribbling`,
	sa.`speed`,
	t.`name`
	FROM `players` AS p
	RIGHT JOIN `skills_data` AS sa
	ON p.`skills_data_id` = sa.`id`
	RIGHT JOIN `teams` AS t
	ON p.`team_id` = t.`id`
	WHERE sa.`dribbling` > min_dribble_points AND t.`name` = team_name AND sa.`speed` > (
		SELECT AVG(`speed`)
		FROM `skills_data`
	)
	ORDER BY sa.`speed` DESC
	LIMIT 1;
END$

CALL udp_find_playmaker (20, 'Skyble')$
