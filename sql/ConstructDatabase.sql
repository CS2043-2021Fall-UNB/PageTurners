/* SQL script for constructing the PageTurners database */

-- Create UserCategory table
CREATE TABLE UserCategory (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL
);

CREATE TABLE UserPost (
    ID INT NOT NULL, Title varchar(50), authorID INT NOT NULL,
    postDate date, primary key (ID)
);

