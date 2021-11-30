/* SQL script for constructing the PageTurners database */

-- Create UserCategory table
CREATE TABLE UserCategory (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL
);

-- Create UserRecord table
CREATE TABLE UserRecord (
    UserID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    AccountCreated DATE,
    IsMod BOOLEAN,
    IsMuted BOOLEAN
);

-- Create AdminRecordTable
CREATE TABLE AdminRecord (
    AdminID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    AccountCreated DATE
);
