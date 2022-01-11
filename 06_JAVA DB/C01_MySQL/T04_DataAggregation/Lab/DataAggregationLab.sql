SELECT * FROM `employees`;


#P01
SELECT `department_id`, COUNT(`id`) AS 'Number of employees'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`, `Number of employees`;

#P02
SELECT `department_id`, ROUND( AVG(`salary`), 2 ) AS 'Average Salary'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;

#P03
SELECT `department_id`, ROUND( MIN(`salary`), 2 ) AS 'Minimum Salary'
FROM `employees`
GROUP BY `department_id`
HAVING `Minimum Salary` > 800;

#P04
SELECT COUNT(`category_id`) AS 'Appetizers Count' 
FROM `products`
WHERE `category_id` = 2 AND `price` > 8
GROUP BY `category_id`;

#P05
SELECT `category_id`, 
ROUND(AVG(`price`), 2) AS 'Average Price',
ROUND(MIN(`price`), 2) AS 'Cheapest Product',
ROUND(MAX(`price`), 2) AS 'Most Expensive Product'
FROM `products`
GROUP BY `category_id`
ORDER BY `category_id`;
