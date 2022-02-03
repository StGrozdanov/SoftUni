#P01

CREATE DATABASE `instd`;
USE `instd`;

CREATE TABLE `users` (
	`id` INT PRIMARY KEY,
    `username` VARCHAR(30) NOT NULL UNIQUE,
    `password` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `gender` CHAR(1) NOT NULL,
    `age` INT NOT NULL,
    `job_title` VARCHAR(40) NOT NULL,
    `ip` VARCHAR(30) NOT NULL
);

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `address` VARCHAR(30) NOT NULL,
    `town` VARCHAR(30) NOT NULL,
    `country` VARCHAR(30) NOT NULL,
    `user_id` INT NOT NULL,
    CONSTRAINT fk_addresses_users
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
);

CREATE TABLE `photos` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `description` TEXT NOT NULL,
    `date` DATETIME NOT NULL,
    `views` INT DEFAULT 0
);

CREATE TABLE `users_photos` (
	`user_id` INT NOT NULL,
    `photo_id` INT NOT NULL,
    CONSTRAINT `fk_users_photos_photos`
    FOREIGN KEY (`photo_id`)
    REFERENCES `photos` (`id`),
    CONSTRAINT `fk_users_photos_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
);

CREATE TABLE `comments` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `comment` VARCHAR(255) NOT NULL,
    `date` DATETIME NOT NULL,
    `photo_id` INT NOT NULL,
    CONSTRAINT fk_comments_photos
    FOREIGN KEY (`photo_id`)
    REFERENCES `photos` (`id`)
);

CREATE TABLE `likes` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `photo_id` int,
    `user_id` int,
    CONSTRAINT `fk_likes_photos`
    FOREIGN KEY (`photo_id`)
    REFERENCES `photos` (`id`),
    CONSTRAINT `fk_likes_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
);

#P02
INSERT INTO `addresses`(`address`, `town`, `country`, `user_id`)
SELECT `username`, `password`, `ip`, `age`
FROM `users`
WHERE `gender` = 'M';

#P03
UPDATE `addresses` 
SET `country` = (
	CASE
		WHEN SUBSTRING(`country`, 1, 1) = 'B' THEN 'Blocked'
		WHEN SUBSTRING(`country`, 1, 1) = 'T' THEN 'Test'
		WHEN SUBSTRING(`country`, 1, 1) = 'P' THEN 'In Progress'
    END
)
WHERE SUBSTRING(`country`, 1, 1) IN ('B', 'T', 'P'); 

#P04
DELETE FROM `addresses`
WHERE `id` % 3 = 0;

#P05
SELECT `username`, `gender`, `age`
FROM `users`
ORDER BY `age` DESC, `username`;

#P06
SELECT p.`id`, p.`date` AS `date_and_time`, p.`description`, COUNT(c.`id`) AS `commentsCount`
FROM `photos` AS p
JOIN `comments` AS c
ON p.`id` = c.`photo_id`
GROUP BY p.`id`
ORDER BY `commentsCount` DESC, p.`id`
LIMIT 5;

#P07
SELECT CONCAT(u.`id`, ' ', u.`username`) AS `id_username`, u.`email`
FROM `users` as u
JOIN `users_photos` as up
ON u.`id` = up.`user_id`
WHERE u.`id` = up.`photo_id`
ORDER BY u.`id`;

#P08
SELECT 
p.`id` AS `photo_id`, 
COUNT(DISTINCT l.`id`) AS `likes_count`,
COUNT(DISTINCT c.`id`) AS `comments_count`
FROM `photos` as p
LEFT JOIN `likes` as l
ON p.`id` = l.`photo_id`
LEFT JOIN `comments` as c
ON p.`id` = c.`photo_id`
GROUP BY p.`id`
ORDER BY `likes_count` DESC, `comments_count` DESC, p.`id`;

#P09
SELECT 
IF(CHAR_LENGTH(`description`) < 30, `description`, CONCAT(SUBSTRING(`description`, 1, 30), '', '...')) AS `summary`,
`date`
FROM `photos`
WHERE DAY(`date`) = 10
ORDER BY `date` DESC;

#P10
DELIMITER $
CREATE FUNCTION udf_users_photos_count(username VARCHAR(30))
RETURNS INT 
DETERMINISTIC
BEGIN
	RETURN (SELECT COUNT(*) 
			FROM `users` AS u
			LEFT JOIN `users_photos` AS up
			ON u.`id` = up.`user_id`
			WHERE u.`username` = username);
END $

SELECT udf_users_photos_count('ssantryd') AS photosCount$

#P11
CREATE PROCEDURE udp_modify_user(address VARCHAR(30), town VARCHAR(30)) 
BEGIN
	UPDATE `users` AS u
    JOIN `addresses` AS a
	ON u.`id` = a.`user_id`
    SET u.`age` = u.`age` + 10
	WHERE a.`address` = address AND a.`town` = town;
END $

CALL udp_modify_user ('97 Valley Edge Parkway', 'Divinópolis')$
SELECT u.username, u.email,u.gender,u.age,u.job_title FROM users AS u
WHERE u.username = 'eblagden21'$
