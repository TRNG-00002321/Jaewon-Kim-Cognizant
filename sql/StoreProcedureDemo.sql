-- Active: 1763658951365@@127.0.0.1@3306@Chinook
DELIMITER $$
CREATE PROCEDURE GetCustomers()
BEGIN
    SELECT
        CONCAT(`FirstName`,' ', `LastName`) as customerName,
        City,
        State,
        PostalCode,
        Country
    FROM
        Customer
    ORDER BY customerName;
END$$
DELIMITER ;
CALL GetCustomers();

DELIMITER //
CREATE PROCEDURE GetCustomerByCountry(
    IN countryName VARCHAR(255)
)
BEGIN
    SELECT *
    FROM Customer
    WHERE Country = countryName;
END //
DELIMITER ;

CALL GetCustomerByCountry('USA');

DELIMITER$$
CREATE PROCEDURE GetInvoiceCountByBillingCountry(
    IN country VARCHAR(255),
    OUT mytotal INT
)
BEGIN
    SELECT SUM(Total)
    INTO mytotal
    FROM `Invoice`WHERE `BillingCountry` = `BillingCountry`;
END $$
DELIMITER ;

CALL GetInvoiceCountByBillingCountry('USA');

CALL GetInvoiceCountByBillingCountry('Brazil', @total);
SELECT @total;

DELIMITER $$
CREATE PROCEDURE SetCounter(
    INOUT counter INT,
    IN inc INT
)
BEGIN ADD
    SET counter = counter + inc;
END $$
DELIMITER ;

SET @counter = 1;
CALL SetCounter(@counter, 1);
CALL SetCounter(@counter, 2);
CALL SetCounter(@counter, 3);
SELECT @counter;