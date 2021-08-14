REM   Script: Sam's Silly Books
REM   List of books

CREATE TABLE books (isbn VARCHAR(13) PRIMARY KEY,
    authID INT NOT NULL,
    title VARCHAR(80) NOT NULL,
    genre VARCHAR(20) NOT NULL,
    publishedYear NUMBER(4,0) NOT NULL);

CREATE INDEX book_title ON books (title);

CREATE INDEX book_genre ON books (genre);

INSERT INTO
    books(isbn, authID, title, genre, publishedYear)
VALUES(9781118901373,
    3,
    'Security+ Review Guide',
    'Non-Fiction',
    2014);

INSERT INTO
    books(isbn, authID, title, genre, publishedYear)
VALUES(9781119021247,
    3,
    'Network+ Study Guide',
    'Non-Fiction',
    2015);

INSERT INTO
    books(isbn, authID, title, genre, publishedYear)
VALUES(9780590353427,
    2,
    'Harry Pottery and the Sorcerer''s Stone',
    'Fantasy',
    1997);

INSERT INTO
    books(isbn, authID, title, genre, publishedYear)
VALUES(9781455527441,
    1,
    'Spring Chicken: Stay Young Forever (or die trying)',
    'Self-Care',
    2015);

CREATE TABLE authors (authID INT PRIMARY KEY,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
born INT NOT NULL,
books_published INT NOT NULL);

INSERT INTO
    authors (authID, first_name, last_name, born, books_published)
VALUES(1,
    'Bill',
    'Gifford',
    1965,
     3
);

INSERT INTO
    authors (authID, first_name, last_name, born, books_published)
VALUES(2,
    'J.K.',
    'Rowling',
    1965,
     21
);

INSERT INTO
    authors (authID, first_name, last_name, born, books_published)
VALUES(3,
    'SYBEX',
    'NA',
    1976,
     171
);


SELECT * FROM books;
SELECT * FROM authors;

SELECT ISBN, TITLE, GENRE, PUBLISHEDYEAR, FIRST_NAME, LAST_NAME FROM books
    JOIN authors
    ON books.authID = authors.authID;


