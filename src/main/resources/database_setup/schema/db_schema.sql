CREATE DATABASE pokemondb;

USE pokemondb;

CREATE TABLE ConfigurationOptions (
ConfigurationOptionID int(10) NOT NULL AUTO_INCREMENT,
UserID int(10) NOT NULL,
BattleSimulator tinyint(1) DEFAULT 0 NOT NULL,
TierList tinyint(1) DEFAULT 0 NOT NULL,
UserMaintenance tinyint(1) DEFAULT 0 NOT NULL,
DataMaintenance tinyint(1) DEFAULT 0 NOT NULL,
PRIMARY KEY (ConfigurationOptionID));

CREATE TABLE ChargedMove (
ChargedMoveID int(10) NOT NULL AUTO_INCREMENT,
MoveName varchar(32) NOT NULL,
TypeName varchar(32) NOT NULL,
DamagePvP int(10) NOT NULL,
EnergyPvP int(10) NOT NULL,
PRIMARY KEY (ChargedMoveID));

CREATE TABLE FastMove (
FastMoveID int(10) NOT NULL AUTO_INCREMENT,
MoveName varchar(32) NOT NULL,
TypeName varchar(32) NOT NULL,
DamagePvP int(10) NOT NULL,
EnergyPvP int(10) NOT NULL,
Duration int(10) NOT NULL,
PRIMARY KEY (FastMoveID));

CREATE TABLE Pokemon (
PokemonID int(10) NOT NULL AUTO_INCREMENT,
PokemonName varchar(32) NOT NULL UNIQUE,
Generation int(10) NOT NULL,
TypeName varchar(32) NOT NULL,
DualTypeName varchar(32),
BaseAttack int(11) NOT NULL,
BaseDefense int(11) NOT NULL,
BaseStamina int(11) NOT NULL,
PRIMARY KEY (PokemonID));

CREATE TABLE PokemonChargedMove (
PokemonID int(10) NOT NULL,
MoveID int(10) NOT NULL);

CREATE TABLE PokemonFastMove (
PokemonID int(10) NOT NULL,
MoveID int(10) NOT NULL);

CREATE TABLE Role (
RoleID int(10) NOT NULL AUTO_INCREMENT,
RoleName varchar(32) NOT NULL UNIQUE,
PRIMARY KEY (RoleID));

CREATE TABLE User (
UserID int(10) NOT NULL AUTO_INCREMENT,
ConfigurationOptionID int(10),
UserName varchar(32) NOT NULL,
UserPassword varchar(32) NOT NULL,
PRIMARY KEY (UserID));

CREATE TABLE UserRole (
RoleID int(10) NOT NULL,
UserID int(10) NOT NULL);

ALTER TABLE PokemonChargedMove
ADD CONSTRAINT FKPokemonMov82480
FOREIGN KEY (MoveID)
REFERENCES ChargedMove (ChargedMoveID);

ALTER TABLE PokemonFastMove
ADD CONSTRAINT FKPokemonMov82481
FOREIGN KEY (MoveID)
REFERENCES FastMove (FastMoveID);

ALTER TABLE UserRole
ADD CONSTRAINT FKUserRole532117
FOREIGN KEY (RoleID)
REFERENCES Role (RoleID);

ALTER TABLE UserRole
ADD CONSTRAINT FKUserRole396295
FOREIGN KEY (UserID)
REFERENCES User (UserID);

ALTER TABLE User
ADD CONSTRAINT FKUser400447
FOREIGN KEY (ConfigurationOptionID)
REFERENCES ConfigurationOptions (ConfigurationOptionID);

ALTER TABLE UserRole
ADD CONSTRAINT FKConfigurationOptions458328
FOREIGN KEY (UserID)
REFERENCES User (UserID);

ALTER TABLE User
ADD UNIQUE (UserName);