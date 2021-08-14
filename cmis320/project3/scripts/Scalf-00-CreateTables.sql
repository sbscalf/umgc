CREATE TABLE CUSTOMER (
    customerID INT NOT NULL,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    streetAddress VARCHAR(30) NOT NULL,
    city VARCHAR(20) NOT NULL,
    zipCode INT NOT NULL,
    phoneNumber INT,
    
    PRIMARY KEY (customerID)
);

CREATE TABLE RENTAL (
    rentalID INT NOT NULL,
    customerID INT NOT NULL,
    dateRented DATE NOT NULL,
    dueDate DATE NOT NULL,
    dateReturned DATE,
    taxRate NUMBER NOT NULL,
    
    FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID) ON DELETE CASCADE,
    PRIMARY KEY (rentalID)
);

CREATE TABLE FEE (
    feeType VARCHAR(16) NOT NULL,
    charge NUMBER NOT NULL,
    
    PRIMARY KEY (feeType)
);

CREATE TABLE APPLIED_FEE (
    appliedFeeID INT NOT NULL,
    rentalID INT NOT NULL,
    feeType VARCHAR(16) NOT NULL,
    
    FOREIGN KEY (rentalID) REFERENCES RENTAL(rentalID) ON DELETE CASCADE,
    FOREIGN KEY (feeType) REFERENCES FEE(feeType) ON DELETE CASCADE,
    PRIMARY KEY (appliedFeeID)
);

CREATE TABLE MOVIE (
    movieID INT NOT NULL,
    title VARCHAR(30) NOT NULL,
    isDVD CHAR(1) NOT NULL,
    genre VARCHAR(20),
    runningLength INT NOT NULL,
    rating VARCHAR(4),
    year INT NOT NULL,
    
    PRIMARY KEY (movieID)
);

CREATE TABLE DISTRIBUTOR (
    distributorID INT NOT NULL,
    distributorName VARCHAR(10),
    loyaltyDiscount NUMBER,
    
    PRIMARY KEY (distributorID)
);

CREATE TABLE CATALOG (
    catalogID INT NOT NULL,
    distributorID INT NOT NULL,
    volume VARCHAR(10),
    
    FOREIGN KEY (distributorID) REFERENCES DISTRIBUTOR(distributorID) ON DELETE CASCADE,
    PRIMARY KEY (catalogID, distributorID)
);

CREATE TABLE CATALOG_ENTRY (
    catalogEntryID INT NOT NULL,
    catalogID INT NOT NULL,
    distributorID INT NOT NULL,
    movieID INT NOT NULL,
    wholesalePrice NUMBER NOT NULL,
    
    FOREIGN KEY (catalogID, distributorID) REFERENCES CATALOG(catalogID, distributorID) ON DELETE CASCADE,
    FOREIGN KEY (movieID) REFERENCES MOVIE(movieID) ON DELETE CASCADE,
    PRIMARY KEY (catalogEntryID)
);

CREATE TABLE SHIPMENT (
    shipmentID INT NOT NULL,
    distributorID INT NOT NULL,
    dateOrdered DATE NOT NULL,
    dateShipped DATE NOT NULL,
    
    FOREIGN KEY (distributorID) REFERENCES DISTRIBUTOR(distributorID) ON DELETE CASCADE,
    PRIMARY KEY (shipmentID, distributorID)
);

CREATE TABLE MOVIE_ORDER (
    movieOrderID INT NOT NULL,
    catalogEntryID INT NOT NULL,
    shipmentID INT NOT NULL,
    distributorID INT NOT NULL,
    quantity INT NOT NULL,
    
    FOREIGN KEY (catalogEntryID) REFERENCES CATALOG_ENTRY(catalogEntryID) ON DELETE CASCADE,
    FOREIGN KEY (shipmentID, distributorID) REFERENCES SHIPMENT(shipmentID, distributorID) ON DELETE CASCADE,
    PRIMARY KEY (movieOrderID)
);

CREATE TABLE INVENTORY_MOVIE (
    inventoryID INT NOT NULL,
    catalogEntryID INT NOT NULL,
    quantityInStock INT NOT NULL,
    
    FOREIGN KEY (catalogEntryID) REFERENCES CATALOG_ENTRY(catalogEntryID) ON DELETE CASCADE,
    PRIMARY KEY (inventoryID)
);

CREATE TABLE MOVIE_RENTAL (
    movieRentalID INT NOT NULL,
    rentalID INT NOT NULL,
    inventoryID INT NOT NULL,
    
    FOREIGN KEY (rentalID) REFERENCES RENTAL(rentalID) ON DELETE CASCADE,
    FOREIGN KEY (inventoryID) REFERENCES INVENTORY_MOVIE(inventoryID) ON DELETE CASCADE,
    PRIMARY KEY (movieRentalID)
);

CREATE TABLE AWARD (
    name VARCHAR(10) NOT NULL,
    year INT NOT NULL,
    movieID INT NOT NULL,
    
    FOREIGN KEY (movieID) REFERENCES MOVIE(movieID) ON DELETE CASCADE,
    PRIMARY KEY (name, year)
);

CREATE TABLE ACTOR (
    actorID INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (actorID)
);

CREATE TABLE CASTING (
    castingID INT NOT NULL,
    movieID INT NOT NULL,
    actorID INT NOT NULL,
    
    FOREIGN KEY (movieID) REFERENCES MOVIE(movieID) ON DELETE CASCADE,
    FOREIGN KEY (actorID) REFERENCES ACTOR(actorID) ON DELETE CASCADE,
    PRIMARY KEY (castingID)
);

CREATE TABLE DIRECTOR (
    directorID INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (directorID)
);

CREATE TABLE DIRECTOR_SEAT (
    directorSeatID INT NOT NULL,
    movieID INT NOT NULL,
    directorID INT NOT NULL,
    
    FOREIGN KEY (movieID) REFERENCES MOVIE(movieID) ON DELETE CASCADE,
    FOREIGN KEY (directorID) REFERENCES DIRECTOR(directorID) ON DELETE CASCADE,
    PRIMARY KEY (directorSeatID)
);