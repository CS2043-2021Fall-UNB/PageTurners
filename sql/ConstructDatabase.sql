/* SQL script for constructing the PageTurners database */

-- Drop existing tables
DROP TABLE IF EXISTS UserPost;
DROP TABLE IF EXISTS UserCategory;

-- Create UserCategory table
CREATE TABLE UserCategory (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL,
    PRIMARY KEY (CategoryID)
);

-- Create UserPost table
CREATE TABLE UserPost (
    ID INT NOT NULL AUTO_INCREMENT,
    CategoryID INT NOT NULL,
    Title VARCHAR(50),
    Contents VARCHAR(1024),
    AuthorID INT NOT NULL,
    PostDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (CategoryID) REFERENCES UserCategory(CategoryID)
);

-- Create UserRecord table
CREATE TABLE UserRecord (
    UserID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    AccountCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    IsMod BOOLEAN NOT NULL DEFAULT FALSE,
    IsMuted BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (UserID)
);

-- Create AdminRecordTable
CREATE TABLE AdminRecord (
    AdminID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    AccountCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (AdminID)
);