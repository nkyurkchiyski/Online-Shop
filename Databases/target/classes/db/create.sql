/*Create 'traineeDB' database and tables */

CREATE DATABASE traineeDB;
USE traineeDB;

CREATE TABLE t_Rights (
  cRightId INT IDENTITY(1,1),
  cRightName VARCHAR(36) NOT NULL UNIQUE,
  CONSTRAINT PK_Right PRIMARY KEY (cRightId)
)

CREATE TABLE t_Roles (
  cRoleId INT IDENTITY(1,1),
  cRoleName VARCHAR(36) NOT NULL UNIQUE,
  CONSTRAINT PK_Role PRIMARY KEY (cRoleId)
)

CREATE TABLE t_RoleRights (
  cRoleRightsRoleId INT NOT NULL,
  cRoleRightsRightId INT NOT NULL,
  CONSTRAINT PK_RoleRight PRIMARY KEY (cRoleRightsRoleId,cRoleRightsRightId),
  CONSTRAINT FK_RoleRights_Rights FOREIGN KEY (cRoleRightsRightId) REFERENCES t_Rights (cRightId),
  CONSTRAINT FK_RoleRights_Roles FOREIGN KEY (cRoleRightsRoleId) REFERENCES t_Roles (cRoleId)
)

CREATE TABLE t_Users (
  cUserId INT IDENTITY(1,1),
  cUserName VARCHAR(36) NOT NULL UNIQUE,
  cUserPassword VARCHAR(512) NOT NULL,
  cUserCreatedAt DATETIME2 NOT NULL DEFAULT GETDATE(),
  cUserLastModifiedAt DATETIME2  NOT NULL DEFAULT GETDATE(),
  cUserActive BIT NOT NULL DEFAULT 1,
  cUserPicture VARBINARY(MAX) NOT NULL,
  CONSTRAINT PK_User PRIMARY KEY (cUserId)
)

CREATE TABLE t_UserRoles (
  cUserRolesUserId INT NOT NULL,
  cUserRolesRoleId INT NOT NULL,
  CONSTRAINT PK_UserRole PRIMARY KEY (cUserRolesUserId,cUserRolesRoleId),
  CONSTRAINT FK_UserRoles_Roles FOREIGN KEY (cUserRolesRoleId) REFERENCES t_Roles (cRoleId),
  CONSTRAINT FK_UserRoles_Users FOREIGN KEY (cUserRolesUserId) REFERENCES t_Users (cUserId)
)

/*Create Login and User for 'traineeDB' and grant permissions*/

CREATE LOGIN trainee WITH PASSWORD = '123456';
CREATE USER traineeUser FOR LOGIN trainee;
USE traineeDB
GO
GRANT CONTROL ON t_Users TO traineeUser;
GRANT CONTROL ON t_Roles TO traineeUser;
GRANT CONTROL ON t_Rights TO traineeUser;
GRANT CONTROL ON t_UserRoles TO traineeUser;
GRANT CONTROL ON t_RoleRights TO traineeUser;
