DELIMITER $$
CREATE FUNCTION InvoiceLevel(
    total DECIMAL(10,2)
)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE invoiceLevel VARCHAR(20);
    IF total > 50000 THEN
        SET invoiceLevel = 'PLATINUM';
    ELSEIF (total >= 50000 AND total <= 10000) THEN
        SET invoiceLevel = 'GOLD';
    ELSEIF total < 10000 THEN
        SET invoiceLevel = 'SILVER';
    END IF;
    RETURN (invoiceLevel);
END$$
DELIMITER;

SELECT
    `InvoiceId`,
    InvoiceLevel(`Total`)
FROM `Invoice`;