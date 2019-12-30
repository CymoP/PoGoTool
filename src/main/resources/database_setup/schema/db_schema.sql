CREATE TABLE ConfigurationOptions (
ConfigurationOptionID int(10) NOT NULL AUTO_INCREMENT,
BattleSimulator tinyint(1) DEFAULT 0 NOT NULL,
UserMaintenance tinyint(1) DEFAULT 0 NOT NULL,
DataMaintenance tinyint(1) DEFAULT 0 NOT NULL,
PRIMARY KEY (ConfigurationOptionID));

CREATE TABLE Move (
MoveID int(10) NOT NULL AUTO_INCREMENT,
MoveName varchar(32) NOT NULL,
TypeName varchar(32) NOT NULL,
MoveUsage varchar(32) NOT NULL,
DPS int(10) NOT NULL,
EnergyPvP int(10) NOT NULL,
CastTime int(10) NOT NULL,
CoolDown int(10) NOT NULL,
DPT int(10) NOT NULL,
EPT int(10) NOT NULL,
PRIMARY KEY (MoveID));

CREATE TABLE Pokemon (
PokemonID int(10) NOT NULL AUTO_INCREMENT,
PokemonName varchar(32) NOT NULL UNIQUE,
Generation int(10) NOT NULL UNIQUE,
TypeName varchar(32) NOT NULL,
DualTypeName2 varchar(32),
BaseAttack int(11) NOT NULL,
BaseDefence int(11) NOT NULL,
BaseStamina int(11) NOT NULL,
PRIMARY KEY (PokemonID));

CREATE TABLE PokemonMove (
PokemonID int(10) NOT NULL,
MoveID int(10) NOT NULL);

CREATE TABLE Role (
RoleID int(10) NOT NULL AUTO_INCREMENT,
RoleName varchar(32) NOT NULL UNIQUE,
PRIMARY KEY (RoleID));

CREATE TABLE Type (
TypeName varchar(32) NOT NULL,
MultiplierBug int(10),
MultiplierDark int(11),
MultiplierDragon int(11),
MultiplierElectric int(11),
MultiplierFairy int(11),
MultiplierFighting int(11),
MultiplierFire int(11),
MultiplierFlying int(11),
MultiplierGhost int(11),
MultiplierGrass int(11),
MultiplierGround int(11),
MultiplierIce int(11),
MultiplierNormal int(11),
MultiplierPoison int(11),
MultiplierPsychic int(11),
MultiplierRock int(11),
MultiplierSteel int(11),
MultiplierWater int(11),
PRIMARY KEY (TypeName));

CREATE TABLE User (
UserID int(10) NOT NULL AUTO_INCREMENT,
ConfigurationOptionID int(10),
UserName varchar(32) NOT NULL,
UserPassword varchar(32) NOT NULL,
PRIMARY KEY (UserID));

CREATE TABLE UserRole (
RoleID int(10) NOT NULL,
UserID int(10) NOT NULL);

ALTER TABLE PokemonMove
ADD CONSTRAINT FKPokemonMov894181
FOREIGN KEY (PokemonID)
REFERENCES Pokemon (PokemonID);

ALTER TABLE PokemonMove
ADD CONSTRAINT FKPokemonMov82480
FOREIGN KEY (MoveID)
REFERENCES Move (MoveID);

ALTER TABLE Move
ADD CONSTRAINT FKMove701165
FOREIGN KEY (TypeName)
REFERENCES Type (TypeName);

ALTER TABLE Pokemon
ADD CONSTRAINT FKPokemon448323
FOREIGN KEY (TypeName)
REFERENCES Type (TypeName);

ALTER TABLE Pokemon
ADD CONSTRAINT FKPokemon415334
FOREIGN KEY (DualTypeName2)
REFERENCES Type (TypeName);

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