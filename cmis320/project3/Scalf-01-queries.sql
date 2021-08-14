SET FEEDBACK OFF;

SPOOL .\scalf-queries-output.txt;

PROMPT 1. All customer's names account numbers, and addresses sorted by account number;
@queries\Scalf-00-Customers.sql

PROMPT ;
PROMPT ;
PROMPT 2. Videos rented in last 30 days, sorted chronologically;
@queries\Scalf-01-Past30DaysRentals.sql


PROMPT ;
PROMPT ;
PROMPT 3. List of all distributor's information, sorted by company name;
@queries\Scalf-02-Distributors.sql


PROMPT ;
PROMPT ;
PROMPT 4. Updating a customer's last name;
@queries\Scalf-03-UpdateSurname.sql


PROMPT ;
PROMPT ;
PROMPT 5. Deleting a customer from the database
@queries\Scalf-04-DeletingCustomer.sql

SPOOL OFF;

SET FEEDBACK ON;