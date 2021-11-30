/* SQL script for constructing the PageTurners database */

-- Drop existing tables
DROP TABLE IF EXISTS UserCategory;
DROP TABLE IF EXISTS UserPost;

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
    PostDate DATE,
    PRIMARY KEY (ID),
    FOREIGN KEY (CategoryID) REFERENCES UserCategory(CategoryID)
);
