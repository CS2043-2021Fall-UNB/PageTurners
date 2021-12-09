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
    AuthorID INT NOT NULL,
    PostDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    IsDeleted BOOLEAN,
    PRIMARY KEY (ID),
    FOREIGN KEY (UserID) REFERENCES UserRecord(UserID),
    FOREIGN KEY (CategoryID) REFERENCES UserCategory(CategoryID)
);

-- Create UserRecord table
CREATE TABLE UserRecord (
    UserID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    AccountCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    IsMod BOOLEAN,
    IsMuted BOOLEAN,
    IsDeleted BOOLEAN,
    PRIMARY KEY (UserID)
);

-- Create AdminRecord table
CREATE TABLE AdminRecord (
    AdminID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    AccountCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (AdminID)
);

-- Create UserComment table
CREATE TABLE UserComment (
    CommentID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    Content VARCHAR(512),
    CommentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (CommentID),
    FOREIGN KEY (UserID) REFERENCES UserPost
);