# P01
SELECT e.`employee_id`, e.`job_title`, e.`address_id`, a.`address_text` 
FROM `employees` AS `e`
JOIN `addresses` AS `a`
ON e.`address_id` = a.`address_id`
ORDER BY `address_id`
LIMIT 5;

# P02
SELECT e.`first_name`, e.`last_name`, t.`name` AS `town`, a.`address_text` 
FROM `employees` AS `e`
JOIN `addresses` AS `a`
ON e.`address_id` = a.`address_id`
JOIN `towns` AS `t`
ON a.`town_id` = t.`town_id`
ORDER BY `first_name`, `last_name`
LIMIT 5;

#P03
SELECT e.`employee_id`, e.`first_name`, e.`last_name`, d.`name`
FROM `employees` AS `e`
JOIN `departments` AS `d`
USING (`department_id`)
WHERE d.`name` = 'Sales'
ORDER BY `employee_id` DESC;

#P04
SELECT e.`employee_id`, e.`first_name`, e.`salary`, d.`name` AS `department_name`
FROM `employees` AS `e`
JOIN `departments` AS `d`
USING (`department_id`)
WHERE e.`salary` > 15000
ORDER BY d.`department_id` DESC
LIMIT 5;

SELECT * FROM `employees_projects`
WHERE `employee_id` = 2;

#P05
SELECT `employee_id`, `first_name` 
FROM `employees` AS `e`
WHERE e.`employee_id` NOT IN 
(
	SELECT ep.`employee_id` 
    FROM `employees_projects` AS `ep`
)
ORDER BY `employee_id` DESC
LIMIT 3;

#P06
SELECT e.`first_name`, e.`last_name`, e.`hire_date`, d.`name` AS `depart_name`
FROM `employees` AS `e`
JOIN `departments` AS `d`
USING (`department_id`)
WHERE DATE(e.`hire_date`) > '1999-01-01' AND d.`name` IN ('Sales', 'Finance')
ORDER BY e.`hire_date`; 

#P07
SELECT e.`employee_id`, e.`first_name`, p.`name` AS `project_name` 
FROM `employees` AS `e`
JOIN `employees_projects` AS `ep`
ON e.`employee_id` = ep.`employee_id`
JOIN `projects` AS `p`
ON ep.`project_id` = p.`project_id`
WHERE DATE(p.`start_date`) > '2002-08-13' AND p.`end_date` IS NULL
ORDER BY `first_name`, `project_name`
LIMIT 5;



#P08
SELECT e.`employee_id`, e.`first_name`, IF(YEAR(p.`start_date`) >= 2005, NULL, p.`name`) AS `project_name`
FROM `employees` AS `e`
JOIN `employees_projects`
USING (`employee_id`)
JOIN `projects` AS `p`
USING (`project_id`)
WHERE `employee_id` = 24
ORDER BY `project_name`;

#P09
SELECT e.`employee_id`, e.`first_name`, e.`manager_id`, oe.`first_name` AS `manager_name`
FROM `employees` AS `e`
JOIN `employees` AS `oe`
ON e.`manager_id` = oe.`employee_id`
WHERE e.`manager_id` IN (3, 7) 
ORDER BY e.`first_name`;

#P10
SELECT 
e.`employee_id`, 
CONCAT(e.`first_name`, ' ', e.`last_name`) AS `employee_name`, 
CONCAT(oe.`first_name`, ' ', oe.`last_name`) AS `manager_name`,
d.`name` AS `department_name`
FROM `employees` AS `e`
JOIN `employees` AS `oe`
ON e.`manager_id` = oe.`employee_id`
JOIN `departments` AS `d`
ON e.`department_id` = d.`department_id`
WHERE e.`manager_id` IS NOT NULL 
ORDER BY e.`employee_id`
LIMIT 5;

#P11
SELECT AVG(`salary`) AS `lowest_average_salary`
FROM `employees`
GROUP BY `department_id`
ORDER BY `lowest_average_salary`
LIMIT 1;

#P12
SELECT c.`country_code`, m.`mountain_range`, p.`peak_name`, p.`elevation`
FROM `countries` AS `c`
JOIN `mountains_countries` AS `mc`
USING (`country_code`)
JOIN `mountains` AS `m`
ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS `p`
ON m.`id` = p.`mountain_id`
WHERE c.`country_code` = 'BG' AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

#P13
SELECT c.`country_code`, COUNT(m.`mountain_range`) AS `mountain_range`
FROM `countries` AS `c`
JOIN `mountains_countries` AS `mc`
USING (`country_code`)
JOIN `mountains` AS `m`
ON mc.`mountain_id` = m.`id`
WHERE c.`country_code` IN ('BG', 'RU', 'US')
GROUP BY c.`country_code`
ORDER BY `mountain_range` DESC;

#P14
SELECT c.`country_name`, r.`river_name`
FROM `countries` AS `c`
LEFT JOIN `countries_rivers` AS `cr`
USING (`country_code`)
LEFT JOIN `rivers` AS `r`
ON cr.`river_id` = r.`id`
WHERE c.`continent_code` = 'AF'
ORDER BY c.`country_name`
LIMIT 5;

#P16
SELECT COUNT(c.`country_code`) AS `country_count`
FROM `countries` AS `c`
LEFT JOIN `mountains_countries` AS `mc`
USING (`country_code`)
WHERE mc.`country_code` IS NULL;

#P17
SELECT 
c.`country_name`,
MAX(p.`elevation`) AS `highest_peak_elevation`,
MAX(r.`length`) AS `longest_river_length`
FROM `countries` AS `c`
JOIN `mountains_countries` AS `mc`
USING (`country_code`)
JOIN `mountains` AS `m`
ON mc.`mountain_id` = m.`id`
JOIN `peaks` AS `p`
ON m.`id` = p.`mountain_id`
JOIN `countries_rivers` AS `cr`
USING(`country_code`)
JOIN `rivers` AS `r`
ON cr.`river_id` = r.`id`
GROUP BY c.`country_name`
ORDER BY `highest_peak_elevation` DESC, `longest_river_length` DESC, `country_name`
LIMIT 5;

#P15
SELECT c.`continent_code`, c.`currency_code`, COUNT(c.`country_name`) AS `currency_usage`
FROM `countries` AS `c`
GROUP BY `continent_code`, `currency_code`
HAVING `currency_usage` = (
	SELECT COUNT(country_code) AS `coun`
    FROM countries AS c1
    WHERE c1.continent_code = c.continent_code
    GROUP BY currency_code
    ORDER BY coun DESC
    LIMIT 1
) 
AND currency_usage > 1
ORDER BY `continent_code`, `currency_code`;
