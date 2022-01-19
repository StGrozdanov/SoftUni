# P01
CREATE TABLE `mountains`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30)
);

CREATE TABLE `peaks`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30),
    `mountain_id` INT,
    CONSTRAINT
    FOREIGN KEY (`mountain_id`)
	REFERENCES `mountains` (`id`)
);

# P02
SELECT `driver_id`, `vehicle_type`,
CONCAT(`first_name`, ' ', `last_name`) AS `driver_name`  
FROM `vehicles` AS `v`
JOIN `campers` AS `c`
ON v.`driver_id` = c.`id`;

# P03
SELECT 
`starting_point` AS `route_starting_point`,
`end_point` AS `route_ending_point`,
`leader_id`, 
CONCAT(`first_name`, ' ', `last_name`) AS `leader_name`
FROM `routes` AS `r`
JOIN `campers` AS `c`
ON r.`leader_id` = c.`id`;

# P04
CREATE TABLE `mountains`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30)
);

CREATE TABLE `peaks`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30),
    `mountain_id` INT,
    CONSTRAINT
    FOREIGN KEY (`mountain_id`)
	REFERENCES `mountains` (`id`)
    ON DELETE CASCADE
);
