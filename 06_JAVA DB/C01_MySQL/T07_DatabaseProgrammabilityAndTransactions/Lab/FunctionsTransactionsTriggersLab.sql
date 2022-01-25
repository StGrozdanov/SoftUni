#P01
DELIMITER $
CREATE FUNCTION `ufn_count_employees_by_town`(town_name VARCHAR(50)) 
	RETURNS int
    DETERMINISTIC
BEGIN
	RETURN (SELECT COUNT(*) FROM 
	`employees` AS `e`
	JOIN `addresses` AS `a`
	USING(`address_id`)
	JOIN `towns` AS `t`
	USING(`town_id`)
	WHERE t.`name` = town_name);
END$

DELIMITER ;
SELECT ufn_count_employees_by_town('Sofia') AS 'count';

#P02
DELIMITER $$
CREATE PROCEDURE usp_raise_salaries_by_department_name(department_name VARCHAR(30)) 
BEGIN
	UPDATE `employees` AS `e`
    JOIN `departments` AS `d`
    USING (`department_id`)
	SET e.`salary` = e.`salary` * 1.05
	WHERE d.`name` = department_name;
END $$

DELIMITER ;
CALL usp_raise_salaries_by_department_name('Finance');

SELECT `first_name`, `salary` 
FROM `employees` 
WHERE `department_id` = 3
ORDER BY `first_name`, `salary` DESC;

#P03
DELIMITER $$
CREATE PROCEDURE `usp_raise_salary_by_id`(id int)
BEGIN
	START TRANSACTION;
	IF
    (
		SELECT `employee_id` 
        FROM employees 
        WHERE employee_id LIKE id = NULL
    ) 
    THEN
	ROLLBACK;
	ELSE
		UPDATE `employees` AS `e` 
        SET e.`salary` = e.`salary` * 1.05 
		WHERE e.employee_id = id;
	END IF; 
END $$

CALL usp_raise_salary_by_id(178)$$
select salary from employees as e where e.employee_id = 178$$

#P04
DELIMITER ;
CREATE TABLE `deleted_employees` (
	`employee_id` INT PRIMARY KEY AUTO_INCREMENT, 
    `first_name` VARCHAR(50),
    `last_name` VARCHAR(50),
    `middle_name` VARCHAR(50),
    `job_title` VARCHAR(100),
    `department_id` INT,
    `salary` DECIMAL(19, 4)
);

DELIMITER $
CREATE TRIGGER `tr_deleted_employees`
AFTER DELETE
ON `employees`
FOR EACH ROW
BEGIN
	INSERT INTO `deleted_employees` (`first_name`, `last_name`, `middle_name`, `job_title`, `department_id`, `salary`)
	VALUES
    (
    OLD.`first_name`,
    OLD.`last_name`,
    OLD.`middle_name`,
    OLD.`job_title`,
    OLD.`department_id`,
    OLD.`salary`
    );
END$

DELIMITER ;

SELECT * FROM `employees` WHERE `employee_id` = 1;

DELETE FROM `employees` WHERE `employee_id` = 1;

SELECT employee_id, first_name, last_name
FROM deleted_employees as de order by employee_id;
