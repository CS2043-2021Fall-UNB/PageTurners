/* SQL script for constructing the PageTurners database */

-- Drop existing tables
DROP TABLE IF EXISTS UserComment;
DROP TABLE IF EXISTS UserPost;
DROP TABLE IF EXISTS UserCategory;
DROP TABLE IF EXISTS UserRecord;
DROP TABLE IF EXISTS AdminRecord;

-- Create UserRecord table
CREATE TABLE UserRecord (
    UserID INT NOT NULL AUTO_INCREMENT,
    UserName VARCHAR(16) NOT NULL,
    Password VARCHAR(64) NOT NULL,
    IsDeleted BOOLEAN,
    AccountCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    IsMod BOOLEAN NOT NULL DEFAULT FALSE,
    IsMuted BOOLEAN NOT NULL DEFAULT FALSE,
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

-- Create UserCategory table
CREATE TABLE UserCategory (
    CategoryID INT NOT NULL AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL,
    PRIMARY KEY (CategoryID)
);

-- Create UserPost table
CREATE TABLE UserPost (
    PostID INT NOT NULL AUTO_INCREMENT,
    CategoryID INT NOT NULL,
    UserID INT NOT NULL,
    Title VARCHAR(50),
    Contents VARCHAR(1024),
    IsDeleted BOOLEAN,
    PostDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (PostID),
    FOREIGN KEY (CategoryID) REFERENCES UserCategory(CategoryID),
    FOREIGN KEY (UserID) REFERENCES UserRecord(UserID)
);

-- Create UserComment table
CREATE TABLE UserComment (
    CommentID INT NOT NULL AUTO_INCREMENT,
    PostID INT NOT NULL,
    UserID INT NOT NULL,
    Content VARCHAR(512),
    CommentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (CommentID),
    FOREIGN KEY (PostID) REFERENCES UserPost(PostID),
    FOREIGN KEY (UserID) REFERENCES UserRecord(UserID)
);
