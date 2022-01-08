USE `soft_uni`;

# P01
SELECT * FROM `departments`;

# P02
SELECT `name` FROM `departments`;

# P03
SELECT `first_name`, `last_name`, `salary` FROM `employees`;

# P04
SELECT `first_name`, `middle_name`, `last_name` FROM `employees`;

# P05
SELECT CONCAT(`first_name`, '.', `last_name`, '@softuni.bg')
AS 'full_email_address'
FROM `employees`; 

# P06
SELECT DISTINCT `salary` FROM `employees`;

# P07
SELECT * FROM `employees`
WHERE `job_title` = 'Sales Representative';

# P08
SELECT `first_name`, `last_name`, `job_title`
FROM `employees`
WHERE `salary` BETWEEN 20000 AND 30000;

# P09
SELECT CONCAT_WS(' ', `first_name`, `middle_name`, `last_name`)
AS 'Full Name'
FROM `employees`
WHERE `salary` IN (25000, 14000, 12500, 23600);
 
 # P10
 SELECT `first_name`, `last_name`
 FROM `employees`
 WHERE `manager_id` IS NULL;
 
 # P11
 SELECT `first_name`, `last_name`, `salary`
 FROM `employees`
 WHERE `salary` > 50000
 ORDER BY `salary` DESC;
 
 # P12
 SELECT `first_name`, `last_name`
 FROM `employees`
 ORDER BY `salary` DESC
 LIMIT 5;
 
 # P13
 SELECT `first_name`, `last_name`
 FROM `employees`
 WHERE `department_id` != 4;
 
 # P14
 SELECT * FROM `employees`
 ORDER BY `salary` DESC, `first_name`, `last_name` DESC, `middle_name`;
 
 # P15
 CREATE VIEW `v_employees_salaries` AS
 SELECT `first_name`, `last_name`, `salary`
 FROM `employees`;
 SELECT * FROM `v_employees_salaries`;
 
# P16
CREATE VIEW `v_employees_job_titles` AS
SELECT CONCAT_WS(' ', `first_name`, `middle_name`, `last_name`) AS `full_name`,
`job_title`
FROM `employees`;
SELECT * FROM `v_employees_job_titles`;

# P17
SELECT DISTINCT `job_title` FROM `employees`
ORDER BY `job_title`; 

# P18
SELECT * FROM `projects`
ORDER BY `start_date`, `name`
LIMIT 10;

# P19
SELECT `first_name`, `last_name`, `hire_date` FROM `employees`
ORDER BY `hire_date` DESC
LIMIT 7;

# P20
UPDATE `employees`
SET `salary` = `salary` * 1.12
WHERE `department_id` IN (1, 2, 4, 11);
SELECT `salary` FROM `employees`;

# P21
SELECT `peak_name` FROM `peaks`
ORDER BY `peak_name`;

# P22
SELECT `country_name`, `population` FROM `countries`
WHERE `continent_code` = 'EU'
ORDER BY `population` DESC, `country_name`
LIMIT 30;

# P23
SELECT `country_name`, `country_code`,
IF(`currency_code` = 'EUR', 'Euro', 'Not Euro') AS `currency`
FROM `countries`
ORDER BY `country_name`;

# P24
SELECT `name` FROM `characters` ORDER BY `name`;
