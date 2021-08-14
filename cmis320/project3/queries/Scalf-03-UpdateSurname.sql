SAVEPOINT update_customer;
    SELECT *
    FROM CUSTOMER
    WHERE customerID = 5;

    UPDATE CUSTOMER
    SET lastName = 'Married'
    WHERE customerID = 5;

    SELECT *
    FROM CUSTOMER
    WHERE customerID = 5;
ROLLBACK TO update_customer;