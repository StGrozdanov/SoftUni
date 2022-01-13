# P01
SELECT `first_name`, `last_name` 
FROM `employees`
WHERE LOWER(`first_name`) LIKE LOWER('Sa%');

# P02
SELECT `first_name`, `last_name` 
FROM `employees`
WHERE LOWER(`last_name`) LIKE LOWER('%ei%');

SELECT * FROM `employees`;

# P03
SELECT `first_name` 
FROM `employees`
WHERE `department_id` IN (3, 10) 
AND YEAR(`hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id`;

# P04
SELECT `first_name`, `last_name`
FROM `employees`
WHERE `job_title` NOT LIKE "%engineer%"
ORDER BY `employee_id`;

# P05
SELECT `name` 
FROM `towns`
WHERE CHAR_LENGTH(`name`) IN (5, 6)
ORDER BY `name`;

# P06
SELECT * 
FROM `towns`
WHERE LOWER(`name`) REGEXP ('^m|^k|^b|^e')
ORDER BY `name`;

# P07
SELECT * 
FROM `towns`
WHERE UPPER(`name`) NOT LIKE 'B%' AND UPPER(`name`) NOT LIKE 'D%' AND UPPER(`name`) NOT LIKE 'R%'
ORDER BY `name`;

# P08
CREATE VIEW `v_employees_hired_after_2000` AS 
SELECT `first_name`, `last_name` 
FROM `employees`
WHERE YEAR(`hire_date`) > 2000;
SELECT * FROM `v_employees_hired_after_2000`;

# P09
SELECT `first_name`, `last_name` 
FROM `employees`
WHERE LENGTH(`last_name`) = 5;

# P10
SELECT `country_name`, `iso_code`
FROM `countries`
#WHERE CHAR_LENGTH(`country_name`) - CHAR_LENGTH( REPLACE ( LOWER(`country_name`), 'a', '') ) >= 3
WHERE `country_name` LIKE '%A%A%A%'
ORDER BY `iso_code`;

# P11
SELECT `peak_name`, `river_name`,
LOWER(CONCAT(`peak_name`, SUBSTRING(`river_name`, 2))) AS `mix`
FROM `peaks`, `rivers`
WHERE LOWER(RIGHT(`peak_name`, 1)) = LOWER(LEFT(`river_name`, 1))
ORDER BY `mix`;

# P12
SELECT `name`, 
#SUBSTRING(`start`, 1, 10 ) AS 'start'
DATE_FORMAT(`start`, '%Y-%m-%d') AS 'start'
FROM `games`
WHERE YEAR(`start`) BETWEEN 2011 AND 2012
ORDER BY `start`, `name`
LIMIT 50;

# P13
SELECT `user_name`, 
#SUBSTRING(`email`, LOCATE('@', `email`) + 1) AS `Email Provider`
SUBSTRING_INDEX(`email`, '@', -1) AS `Email Provider`
FROM `users`
ORDER BY SUBSTRING_INDEX(`email`, '@', -1), `user_name`;

# P14
SELECT `user_name`, `ip_address` 
FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

# P15
SELECT `name` AS 'game',
(
	CASE 
		WHEN HOUR(`start`) BETWEEN 0 AND 11 THEN 'Morning'
		WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
		ELSE 'Evening'
	END
) AS 'Part of the Day',
(
	CASE
		WHEN `duration` <= 3 THEN 'Extra Short'
		WHEN `duration` BETWEEN 3 AND 6 THEN 'Short'
		WHEN `duration` BETWEEN 6 AND 10 THEN 'Long'
        ELSE 'Extra Long'
	END
) AS 'Duration'
FROM `games`;
/*
SELECT `name` AS 'game',
IF(SUBSTRING( TIME(`start`), 1, 2) BETWEEN 0 AND 11, 'Morning', IF(SUBSTRING( TIME(`start`), 1, 2) BETWEEN 12 AND 17, 'Afternoon', 'Evening')) AS 'Part of the Day',
IF(`duration` <= 3, 'Extra Short', IF(`duration` BETWEEN 3 AND 6, 'Short', IF(`duration` BETWEEN 6 AND 10, 'Long', 'Extra Long'))) AS 'Duration'
FROM `games`;
*/

# P16
SELECT 
	`product_name`, 
	`order_date`, 
	DATE_ADD(`order_date`, INTERVAL 3 DAY) AS `pay_due`,
	DATE_ADD(`order_date`, INTERVAL 1 MONTH) AS `order_due`
FROM `orders`;
