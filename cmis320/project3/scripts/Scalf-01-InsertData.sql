INSERT INTO 
CUSTOMER (customerID, firstName, lastName, streetAddress, city, zipCode, phoneNumber)
    SELECT 1, 'First', 'Customer', '1 Easy St.', 'City', 11111, 8005550001 FROM dual UNION ALL
    SELECT 2, 'Second', 'Client', '2 Easy St.', 'City', 22222, 8005550002  FROM dual UNION ALL
    SELECT 3, 'Third', 'Renter', '3 Easy St.', 'City', 33333, 8005550003 FROM dual UNION ALL
    SELECT 4, 'Fourth', 'Consumer', '4 Easy St.', 'City', 44444, 8005550004 FROM dual UNION ALL
    SELECT 5, 'Fifth', 'Maiden', '5 Easy St.', 'City', 55555, 8005550005 FROM dual;

INSERT INTO
RENTAL (rentalID, customerID, dateRented, dueDate, dateReturned, taxRate)
    SELECT 11, 1, '27-FEB-21', '01-MAR-21', '01-MAR-21', 0.8 FROM dual UNION ALL
    SELECT 12, 2, '13-MAR-21', '15-MAR-21', '14-MAR-21', 0.8 FROM dual UNION ALL
    SELECT 13, 3, '27-MAR-21', '29-MAR-21', '31-MAR-21', 0.8 FROM dual UNION ALL
    SELECT 14, 4, '10-APR-21', '12-APR-21', '17-APR-21', 0.8 FROM dual UNION ALL
    SELECT 15, 5, '24-APR-21', '26-APR-21', NULL, 0.8 FROM dual;
    

INSERT INTO
FEE (feeType, charge)
    SELECT 'LATE', 1.0 FROM dual UNION ALL
    SELECT 'DAMAGED', 2.5 FROM dual UNION ALL
    SELECT 'REWIND', .25 FROM dual UNION ALL
    SELECT 'DESTROYED', 25 FROM dual UNION ALL
    SELECT 'LOST', 25 FROM dual;
    
INSERT INTO
APPLIED_FEE (appliedFeeID, rentalID, feeType)
    SELECT 110, 11, 'DAMAGED' FROM dual UNION ALL
    SELECT 120, 12, 'REWIND' FROM dual UNION ALL
    SELECT 130, 13, 'LATE' FROM dual UNION ALL
    SELECT 140, 14, 'LATE' FROM dual UNION ALL
    SELECT 141, 14, 'REWIND' FROM dual;
    
INSERT INTO
MOVIE (movieID, title, isDVD, genre, runningLength, rating, year)
    SELECT 200, 'Movie1', 'N', 'genre1', 90, 'PG13', 1990 FROM dual UNION ALL
    SELECT 210, 'Movie2', 'N', 'genre2', 100, 'R', 1999 FROM dual UNION ALL
    SELECT 211, 'Movie3', 'Y', 'genre3', 98, 'G', 1998 FROM dual UNION ALL
    SELECT 221, 'Movie4', 'Y', 'genre4', 67, 'PG13', 2000 FROM dual UNION ALL
    SELECT 231, 'Movie5', 'Y', 'genre5', 128, 'PG', 2000 FROM dual;
    
INSERT INTO
DISTRIBUTOR (distributorID, distributorName, loyaltyDiscount)
    SELECT 300, 'Dist1', 0.1 FROM dual UNION ALL
    SELECT 301, 'Dist2', 0.05 FROM dual UNION ALL
    SELECT 302, 'Dist3', NULL FROM dual UNION ALL
    SELECT 303, 'Dist4', NULL FROM dual UNION ALL
    SELECT 304, 'Dist5', 0.12 FROM dual;
    
INSERT INTO
CATALOG (catalogID, distributorID, volume)
    SELECT 11999, 300, 'JAN1999' FROM dual UNION ALL
    SELECT 11999, 301, '1999Q1' FROM dual UNION ALL
    SELECT 12099, 302, 'DEC1999' FROM dual UNION ALL
    SELECT 12000, 303, 'JAN2000' FROM dual UNION ALL
    SELECT 200101, 304, 'JAN2001' FROM dual;
    
INSERT INTO
CATALOG_ENTRY (catalogEntryID, catalogID, distributorID, movieID, wholesalePrice)
    SELECT 400, 11999, 300, 200, 2.75 FROM dual UNION ALL
    SELECT 401, 11999, 301, 210, 2.70 FROM dual UNION ALL
    SELECT 402, 12099, 302, 211, 3.65 FROM dual UNION ALL
    SELECT 403, 12000, 303, 221, 3.59 FROM dual UNION ALL
    SELECT 404, 200101, 304, 231, 3.25 FROM dual;
    
INSERT INTO
SHIPMENT (shipmentID, distributorID, dateOrdered, dateShipped)
    SELECT 1234, 300, '01-FEB-99', '07-FEB-99' FROM dual UNION ALL
    SELECT 1234, 301, '01-FEB-99', '10-FEB-99' FROM dual UNION ALL
    SELECT 1302, 302, '06-DEC-99', '08-DEC-99' FROM dual UNION ALL
    SELECT 2300, 300, '09-JAN-00', '13-JAN-00' FROM dual UNION ALL
    SELECT 1304, 304, '15-APR-01', '16-APR-01' FROM dual;
    
INSERT INTO
MOVIE_ORDER (movieOrderID, catalogEntryID, shipmentID, distributorID, quantity)
    SELECT 10, 400, 1234, 300, 10 FROM dual UNION ALL
    SELECT 11, 401, 1234, 301, 10 FROM dual UNION ALL
    SELECT 12, 402, 1302, 302, 10 FROM dual UNION ALL
    SELECT 13, 403, 2300, 300, 10 FROM dual UNION ALL
    SELECT 14, 404, 1304, 304, 10 FROM dual;
    
INSERT INTO
INVENTORY_MOVIE (inventoryID, catalogEntryID, quantityInStock)
    SELECT 91, 400, 10 FROM dual UNION ALL
    SELECT 92, 401, 10 FROM dual UNION ALL
    SELECT 93, 402, 10 FROM dual UNION ALL
    SELECT 94, 403, 10 FROM dual UNION ALL
    SELECT 95, 404, 10 FROM dual;
    
INSERT INTO
MOVIE_RENTAL (movieRentalID, rentalID, inventoryID)
    SELECT 501, 11, 91 FROM dual UNION ALL
    SELECT 502, 12, 92 FROM dual UNION ALL
    SELECT 503, 13, 93 FROM dual UNION ALL
    SELECT 504, 14, 94 FROM dual UNION ALL
    SELECT 505, 15, 95 FROM dual;
    
INSERT INTO
AWARD (name, year, movieID)
    SELECT 'Award1', 1991, 200 FROM dual UNION ALL
    SELECT 'Award2', 2000, 210 FROM dual UNION ALL
    SELECT 'Award3', 1999, 211 FROM dual UNION ALL
    SELECT 'Award4', 2001, 221 FROM dual UNION ALL
    SELECT 'Award5', 2001, 231 FROM dual;
    
INSERT INTO
ACTOR (actorID, name)
    SELECT 1101, 'Actor1' FROM dual UNION ALL
    SELECT 1102, 'Actor2' FROM dual UNION ALL
    SELECT 1103, 'Actor3' FROM dual UNION ALL
    SELECT 1104, 'Actor4' FROM dual UNION ALL
    SELECT 1105, 'Actor5' FROM dual;
    
INSERT INTO
CASTING (castingID, movieID, actorID)
    SELECT 1111, 200, 1101 FROM dual UNION ALL
    SELECT 1112, 210, 1102 FROM dual UNION ALL
    SELECT 1113, 211, 1103 FROM dual UNION ALL
    SELECT 1114, 221, 1104 FROM dual UNION ALL
    SELECT 1115, 231, 1105 FROM dual;
    
INSERT INTO
DIRECTOR (directorID, name)
    SELECT 1201, 'Director1' FROM dual UNION ALL
    SELECT 1202, 'Director2' FROM dual UNION ALL
    SELECT 1203, 'Director3' FROM dual UNION ALL
    SELECT 1204, 'Director4' FROM dual UNION ALL
    SELECT 1205, 'Director5' FROM dual;
    
INSERT INTO
DIRECTOR_SEAT (directorSeatID, movieID, directorID)
    SELECT 1211, 200, 1201 FROM dual UNION ALL
    SELECT 1212, 210, 1202 FROM dual UNION ALL
    SELECT 1213, 211, 1203 FROM dual UNION ALL
    SELECT 1214, 221, 1204 FROM dual UNION ALL
    SELECT 1215, 231, 1205 FROM dual;