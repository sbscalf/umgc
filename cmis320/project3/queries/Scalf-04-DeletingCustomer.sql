SAVEPOINT delete_customer;
    SELECT * FROM CUSTOMER;
    
    DELETE FROM CUSTOMER WHERE customerID = 3;
    
    SELECT * FROM CUSTOMER;
ROLLBACK TO delete_customer;