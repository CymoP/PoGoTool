INSERT INTO USER (username, userpassword) values ('test1', 'test2');

INSERT INTO UserRole (RoleID, UserID)
values ((SELECT RoleID FROM Role WHERE RoleName = 'user'), (SELECT UserID FROM User WHERE UserName = 'test1' AND UserPassword = 'test2'));