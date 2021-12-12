DELETE FROM AdminRecord;
DELETE FROM UserRecord;
DELETE FROM UserCategory;
DELETE FROM UserPost;
DELETE FROM UserComment;

-- Insert Testing Data
INSERT INTO UserCategory (CategoryName) VALUES
    ('Fiction'),
    ('Non-Fiction');

INSERT INTO AdminRecord (UserName, Password) VALUES
    ('admin', sha1('admin'));

INSERT INTO UserRecord (UserName, Password) VALUES
    ('stephen', sha1('password1')),
    ('nathan', sha1('password2'));