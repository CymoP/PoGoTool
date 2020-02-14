CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'B3@fcake9192';
GRANT ALL PRIVILEGES ON pokemondb.* TO 'dbuser'@'localhost';
ALTER USER 'dbuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'B3@fcake9192';