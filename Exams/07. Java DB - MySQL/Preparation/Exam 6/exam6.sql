CREATE DATABASE space;
USE space;

CREATE TABLE `planets` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE `spaceports` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `planet_id` INT,
    CONSTRAINT fk_spaceports_planets
    FOREIGN KEY (`planet_id`)
    REFERENCES `planets` (`id`)
);

CREATE TABLE `spaceships` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
	`manufacturer` VARCHAR(30) NOT NULL,
    `light_speed_rate` INT DEFAULT 0
);

CREATE TABLE `colonists`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `ucn` CHAR(10) NOT NULL UNIQUE,
    `birth_date` DATE NOT NULL
);

CREATE TABLE `journeys` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `journey_start` DATETIME NOT NULL,
    `journey_end` DATETIME NOT NULL,
    `purpose` ENUM('Medical', 'Technical', 'Educational', 'Military') NOT NULL,
    `destination_spaceport_id` INT,
    `spaceship_id` INT,
    CONSTRAINT fk_journeys_spaceports
    FOREIGN KEY (`destination_spaceport_id`)
    REFERENCES `spaceports` (`id`),
    CONSTRAINT fk_journeys_spaceships
    FOREIGN KEY (`spaceship_id`)
    REFERENCES `spaceships` (`id`)
);

CREATE TABLE `travel_cards` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `card_number` CHAR(10) NOT NULL UNIQUE,
    `job_during_journey` ENUM('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook') NOT NULL,
    `colonist_id` INT,
    `journey_id` INT,
    CONSTRAINT fk_travel_cards_colonists
    FOREIGN KEY(`colonist_id`)
    REFERENCES `colonists` (`id`),
    CONSTRAINT fk_travel_cards_journeys
    FOREIGN KEY(`journey_id`)
    REFERENCES `journeys` (`id`)
);

#P02
SELECT * FROM `travel_cards`;

INSERT INTO `travel_cards`(`card_number`, `job_during_journey`, `journey_id`, `colonist_id`)
SELECT IF(`birth_date` > '1980-01-01',
CONCAT(YEAR(`birth_date`), DAY(`birth_date`), LEFT(`ucn`, 4)),
CONCAT(YEAR(`birth_date`), MONTH(`birth_date`), RIGHT(`ucn`, 4))
) AS `card_number`,
(
	CASE
		WHEN `id` % 2 = 0 THEN 'Pilot'
		WHEN `id` % 3 = 0 THEN 'Cook'
		ELSE 'Engineer'
	END
) AS `job_during_journey`,
LEFT(`ucn`, 1) AS `journey_id`,
`id` AS `colonist_id`
FROM `colonists`
WHERE `id` BETWEEN 96 AND 100;

SELECT * FROM `colonists` WHERE `birth_date` > '1980-01-01';

SELECT * FROM travel_cards
WHERE id > 95;

#P03
UPDATE `journeys`
SET `purpose` = (
	CASE
		WHEN `id` % 2 = 0 THEN 'Medical'
		WHEN `id` % 3 = 0 THEN 'Technical'
		WHEN `id` % 5 = 0 THEN 'Educational'
		WHEN `id` % 7 = 0 THEN 'Military'
        ELSE `purpose`
    END
);

#P04
DELETE c.*
FROM `colonists` AS c 
LEFT JOIN `travel_cards` AS tc 
ON c.`id` = tc.`colonist_id`
WHERE `journey_id` IS NULL;

#P05
SELECT `card_number`, `job_during_journey` 
FROM `travel_cards`
ORDER BY `card_number`;

#P06
SELECT `id`,
CONCAT_WS(' ', `first_name`, `last_name`) AS `full_name`,
`ucn`
FROM `colonists`
ORDER BY `first_name`, `last_name`, `id`;

#P07
SELECT `id`, `journey_start`, `journey_end`
FROM `journeys`
WHERE `purpose` = 'Military'
ORDER BY `journey_start`;

#P08
SELECT c.`id`, CONCAT_WS(' ', c.`first_name`, c.`last_name`) AS `full_name`
FROM `colonists` AS c
JOIN `travel_cards` AS tc
ON c.`id` = tc.`colonist_id`
WHERE `job_during_journey` = 'Pilot'
ORDER BY c.`id`;

#P09
SELECT COUNT(c.`id`) AS `count`
FROM `colonists` AS c
JOIN `travel_cards` AS tc
ON c.`id` = tc.`colonist_id`
JOIN `journeys` AS j
ON tc.`journey_id` = j.`id`
WHERE j.`purpose` = 'Technical';

#P10
SELECT s.`name` AS `spaceship_name`, sp.`name` AS `spaceport_name`
FROM `spaceships` AS s
JOIN `journeys` AS j
ON s.`id` = j.`spaceship_id`
JOIN `spaceports` AS sp
ON sp.`id` = j.`destination_spaceport_id`
ORDER BY s.`light_speed_rate` DESC
LIMIT 1;

#P11
SELECT s.`name`, s.`manufacturer`
FROM `colonists` AS c
LEFT JOIN `travel_cards` AS tc
ON c.`id` = tc.`colonist_id`
LEFT JOIN `journeys` AS j
ON tc.`journey_id` = j.`id`
LEFT JOIN `spaceships` AS s
ON j.`spaceship_id` = s.`id`
WHERE TIMESTAMPDIFF(YEAR, c.`birth_date`, '2019-01-01') < 30 AND tc.`job_during_journey` = 'Pilot'
ORDER BY s.`name`;

#P12
SELECT p.`name`, sp.`name`
FROM `planets` AS p
JOIN `spaceports` as sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
WHERE j.`purpose` = 'Educational'
ORDER BY sp.`name` DESC;

#P13
SELECT p.`name`, COUNT(j.`id`) AS `journeys_count`
FROM `planets` AS p
JOIN `spaceports` as sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
GROUP BY p.`name`
ORDER BY `journeys_count` DESC, p.`name`;

#P14
SELECT j.`id`, p.`name` AS `planet_name`, sp.`name` AS `spaceport_name`, j.`purpose`
FROM `planets` AS p
JOIN `spaceports` as sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
ORDER BY DATEDIFF(j.`journey_end`, j.`journey_start`)
LIMIT 1;

#P15
SELECT tc.`job_during_journey`
FROM `planets` AS p
JOIN `spaceports` as sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
JOIN `travel_cards` AS tc
ON j.`id` = tc.`journey_id`
ORDER BY DATEDIFF(j.`journey_end`, j.`journey_start`) DESC
LIMIT 1;

#P16
DELIMITER $
CREATE FUNCTION udf_count_colonists_by_destination_planet (planet_name VARCHAR (30)) 
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN(
	SELECT COUNT(c.`id`)
	FROM `planets` AS p
	LEFT JOIN `spaceports` as sp
	ON p.`id` = sp.`planet_id`
	LEFT JOIN `journeys` AS j
	ON sp.`id` = j.`destination_spaceport_id`
	LEFT JOIN `travel_cards` AS tc
	ON j.`id` = tc.`journey_id`
	LEFT JOIN `colonists` AS c
	ON c.`id` = tc.`colonist_id`
	WHERE p.`name` = planet_name
	GROUP BY p.`id`);
END$

SELECT p.name, udf_count_colonists_by_destination_planet('Otroyphus') AS count
FROM planets AS p
WHERE p.name = 'Otroyphus'$

#P17
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
	START TRANSACTION;
	IF
    (
		(SELECT COUNT(`id`) 
        FROM `spaceships` 
        WHERE `name` = spaceship_name) = 0
    ) 
    THEN
    SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
	ROLLBACK;
	ELSE
		UPDATE `spaceships`
        SET `light_speed_rate` = `light_speed_rate` + light_speed_rate_increse
		WHERE `name` = spaceship_name;
	END IF; 
END $

DELIMITER ;

CALL udp_modify_spaceship_light_speed_rate ('Na Pesho koraba', 1914);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'Na Pesho koraba';

CALL udp_modify_spaceship_light_speed_rate ('USS Templar', 5);
SELECT name, light_speed_rate FROM spaceships WHERE name = 'USS Templar';
