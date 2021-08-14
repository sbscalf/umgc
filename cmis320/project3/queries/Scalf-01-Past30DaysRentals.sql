SELECT R.*, MR.title
FROM RENTAL R
INNER JOIN (
    SELECT MR.*, I.title
    FROM MOVIE_RENTAL MR
    INNER JOIN (
        SELECT I.*, CE.title
        FROM INVENTORY_MOVIE I
        INNER JOIN (
            SELECT CE.*, M.title
            FROM CATALOG_ENTRY CE
            INNER JOIN (
                SELECT movieID, title FROM MOVIE
            ) M
            ON M.movieID = CE.movieID
        ) CE
        ON CE.catalogEntryID = I.catalogEntryID
    ) I
    ON I.inventoryID = MR.inventoryID
) MR
ON MR.rentalID = R.rentalID
WHERE R.dateRented >= sysdate-30
ORDER BY R.dateRented;