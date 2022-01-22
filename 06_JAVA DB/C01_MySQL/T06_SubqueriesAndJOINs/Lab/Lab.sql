# P01
SELECT e.`employee_id`, 
CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'full_name',
d.`department_id`,
d.`name` 
FROM employees AS `e`
JOIN departments AS `d`
ON e.`employee_id` = d.`manager_id`
ORDER BY e.`employee_id`
LIMIT 5;

SELECT * FROM `towns`;

# P02
SELECT t.`town_id`, t.`name` AS `town_name`, a.`address_text`
FROM `addresses` AS `a`
JOIN `towns` AS `t`
ON a.`town_id` = t.`town_id`
WHERE t.`name` IN ('San Francisco', 'Sofia', 'Carnation')
ORDER BY t.`town_id`, a.`address_id`;

#P03
SELECT `employee_id`, `first_name`, `last_name`, `department_id`, `salary`
FROM `employees`
WHERE `manager_id` IS NULL;

#P04
SELECT COUNT(`employee_id`) AS `count`
FROM `employees`
WHERE `salary` > (
	SELECT AVG(`salary`) FROM `employees`
);
