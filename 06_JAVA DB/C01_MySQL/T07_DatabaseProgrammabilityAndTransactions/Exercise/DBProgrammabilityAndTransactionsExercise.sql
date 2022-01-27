#P01
DELIMITER $
CREATE PROCEDURE usp_get_employees_salary_above_35000 () 
BEGIN
	SELECT `first_name`, `last_name` 
	FROM `employees`
	WHERE `salary` > 35000
	ORDER BY `first_name`, `last_name`, `employee_id`;
END $

DELIMITER ;

CALL usp_get_employees_salary_above_35000(); 

#P02
DELIMITER $
CREATE PROCEDURE usp_get_employees_salary_above(target DECIMAL(16,4))
BEGIN
	SELECT `first_name`, `last_name` 
	FROM `employees`
	WHERE `salary` >= target
	ORDER BY `first_name`, `last_name`, `employee_id`;
END $

DELIMITER ;
CALL usp_get_employees_salary_above(45000);

#P03
DELIMITER $
CREATE PROCEDURE usp_get_towns_starting_with(target_word VARCHAR(50))
BEGIN
	SELECT `name` 
	FROM `towns`
	WHERE `name` LIKE CONCAT(target_word, '%')
	ORDER BY `name`;
END $

DELIMITER ;
CALL usp_get_towns_starting_with('b');

#P04
DELIMITER $
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(50))
BEGIN
	SELECT e.`first_name`, e.`last_name`
	FROM `employees` AS `e`
	JOIN `addresses` AS `a`
	USING(`address_id`)
	JOIN `towns` AS `t`
	USING(`town_id`)
	WHERE t.`name` = town_name
	ORDER BY `first_name`, `last_name`, `employee_id`;
END$

CALL usp_get_employees_from_town('Sofia')$

# P05
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(16,4))
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
	RETURN (
		CASE 
			WHEN salary < 30000 THEN 'Low'
            WHEN salary BETWEEN 30000 AND 50000 THEN 'Average' 
            ELSE 'High'
		END
        );
END$

SELECT ufn_get_salary_level(125500)$

#P06
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(30))
BEGIN
	SELECT `first_name`, `last_name`
	FROM `employees`
	WHERE ufn_get_salary_level(`salary`) = salary_level 
	ORDER BY `first_name` DESC, `last_name` DESC;
END $ 

CALL usp_get_employees_by_salary_level('High')$

#P07
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS INTEGER
DETERMINISTIC
BEGIN
	RETURN (SELECT REGEXP_LIKE(word, CONCAT('^[',set_of_letters,']+$')));
END$

SELECT ufn_is_word_comprised('oistmiahf', 'Sofia') AS 'result'$

#P08
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	SELECT CONCAT(`first_name`, ' ', `last_name`) AS `full_name` 
    FROM `account_holders`
    ORDER BY `full_name`, `id`;
END $

#P10
CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(16, 4), yearly_interest_rate DOUBLE(4, 4), number_of_years INT)
RETURNS DECIMAL(16, 4)
DETERMINISTIC
BEGIN
	RETURN(
		sum * (POW(1 + yearly_interest_rate, number_of_years))
    );
END$

SELECT ufn_calculate_future_value(1000, 0.5, 5) AS 'result'$

#P11
CREATE PROCEDURE usp_calculate_future_value_for_account(target_account INT, interest_rate DOUBLE(4, 4))
BEGIN
	SELECT ah.`id` AS `account_id`, 
	ah.`first_name`, 
	ah.`last_name`, 
	a.`balance` AS `current_balance`,
	ufn_calculate_future_value(a.`balance`, interest_rate, 5) AS `balance_in_5_years`
	FROM `account_holders` AS `ah`
	JOIN `accounts` AS `a`
	ON ah.`id` = a.`account_holder_id`
	WHERE a.`id` = target_account;
END $

CALL usp_calculate_future_value_for_account(1, 0.1)$

#P12
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(16,4))
BEGIN
	START TRANSACTION;
	IF
    (
		money_amount <= 0
    ) 
    OR
		(SELECT COUNT(`account_holder_id`) 
        FROM `accounts` 
        WHERE `id` = account_id ) = 0
    THEN
	ROLLBACK;
	ELSE
		UPDATE `accounts` AS `a` 
        SET a.`balance` = a.`balance` + money_amount
		WHERE a.`id` = account_id;
	END IF; 
END $

CALL usp_deposit_money (1, 10)$

select * from accounts
WHERE id = 1$

#P13
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(16,4))
BEGIN
	START TRANSACTION;
	IF
    (
		money_amount <= 0
    ) 
    OR
		(SELECT COUNT(`account_holder_id`) 
        FROM `accounts` 
        WHERE `id` = account_id ) = 0
	OR
		(
			(SELECT `balance` FROM `accounts` WHERE `id` = account_id) - money_amount < 0 
        )
    THEN
	ROLLBACK;
	ELSE
		UPDATE `accounts` AS `a` 
        SET a.`balance` = a.`balance` - money_amount
		WHERE a.`id` = account_id;
	END IF; 
END$

CALL usp_withdraw_money(1, 10)$

select * from accounts
WHERE id = 1$

#P14
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, money_amount DECIMAL(16,4))
BEGIN
	START TRANSACTION;
	IF
    (
		money_amount <= 0
    ) 
    OR
		(SELECT COUNT(`account_holder_id`) 
        FROM `accounts` 
        WHERE `id` = from_account_id ) = 0
	OR
		(SELECT COUNT(`account_holder_id`) 
        FROM `accounts` 
        WHERE `id` = to_account_id ) = 0
	OR 
		(from_account_id = to_account_id)
	OR
		(
			(SELECT `balance` FROM `accounts` WHERE `id` = from_account_id) - money_amount < 0 
        )
    THEN
	ROLLBACK;
	ELSE
		CALL usp_withdraw_money(from_account_id, money_amount);
        CALL usp_deposit_money(to_account_id, money_amount);
	END IF; 
END$

call usp_transfer_money(1, 2, 10)$

select * from accounts 
where id in (1,2)
order by id$
