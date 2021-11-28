/* SQL script for constructing the PageTurners database */

-- Drop existing tables
DROP TABLE IF EXISTS UserCategory;

-- Create UserCategory table
CREATE TABLE UserCategory (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL,
    PRIMARY KEY (CategoryID)
);