CREATE DATABASE shopDB;
GO
USE shopDB;
GO

CREATE TABLE t_Addresses (
    cAddressId INT IDENTITY(1,1),
     cAddressCity VARCHAR(255),
     cAddressCountry VARCHAR(255),
     cAddressPostCode VARCHAR(255),
     cAddressStreet VARCHAR(255),
     CONSTRAINT PK_Address PRIMARY KEY (cAddressId)
)

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
  cRoleRightRoleId INT NOT NULL,
  cRoleRoleRightId INT NOT NULL,
  CONSTRAINT PK_RoleRight PRIMARY KEY (cRoleRightRoleId,cRoleRoleRightId),
  CONSTRAINT FK_RoleRights_Rights FOREIGN KEY (cRoleRoleRightId) REFERENCES t_Rights (cRightId),
  CONSTRAINT FK_RoleRights_Roles FOREIGN KEY (cRoleRightRoleId) REFERENCES t_Roles (cRoleId)
)

CREATE TABLE t_Users (
  cUserId INT IDENTITY(1,1),
  cUserEmail VARCHAR(36) NOT NULL UNIQUE,
  cUserFirstName VARCHAR(36) NOT NULL,
  cUserLastName VARCHAR(36) NOT NULL,
  cUserPassword VARCHAR(512) NOT NULL,
  cUserCreatedAt DATETIME2 NOT NULL DEFAULT GETDATE(),
  cUserActive BIT NOT NULL DEFAULT 1,
  cUserAddressId INT NULL,
  CONSTRAINT PK_User PRIMARY KEY (cUserId),
   CONSTRAINT FK_Users_Addresses FOREIGN KEY (cUserAddressId) REFERENCES t_Addresses (cAddressId)
)

CREATE TABLE t_UserRoles (
  cUserRoleUserId INT NOT NULL,
  cUserRoleRoleId INT NOT NULL,
  CONSTRAINT PK_UserRole PRIMARY KEY (cUserRoleUserId,cUserRoleRoleId),
  CONSTRAINT FK_UserRoles_Roles FOREIGN KEY (cUserRoleRoleId) REFERENCES t_Roles (cRoleId),
  CONSTRAINT FK_UserRoles_Users FOREIGN KEY (cUserRoleUserId) REFERENCES t_Users (cUserId)
)

CREATE TABLE t_Products (
  cProductId INT IDENTITY(1,1),
  cProductName VARCHAR(36) NOT NULL,
  cProductPrice DECIMAL(10,2) NOT NULL,
  cProductQuantity INT NOT NULL DEFAULT 0,
  cProductDescription VARCHAR(MAX) NULL,
  cProductImageUrl VARCHAR(MAX) NULL,
  CONSTRAINT PK_Product PRIMARY KEY (cProductId)
)

CREATE TABLE t_Categories (
  cCategoryId INT IDENTITY(1,1),
  cCategoryName VARCHAR(36) NOT NULL,
  CONSTRAINT PK_Category PRIMARY KEY (cCategoryId)
)

CREATE TABLE t_Orders (
  cOrderId INT IDENTITY(1,1),
  cOrderStatus INT NOT NULL,
  cOrderOrderedOn DATETIME2 NULL,
  cOrderDeliveredOn DATETIME2 NULL,
  cOrderUserId INT NOT NULL,
  CONSTRAINT PK_Order PRIMARY KEY (cOrderId),
  CONSTRAINT FK_Orders_Users FOREIGN KEY (cOrderUserId) REFERENCES t_Users (cUserId),
)

CREATE TABLE t_ProductCategories (
  cProductCategoryProductId INT NOT NULL,
  cProductCategoryCategoryId INT NOT NULL,
  CONSTRAINT PK_ProductCategory PRIMARY KEY (cProductCategoryProductId,cProductCategoryCategoryId),
  CONSTRAINT FK_ProductCategories_Products FOREIGN KEY (cProductCategoryProductId) REFERENCES t_Products (cProductId),
  CONSTRAINT FK_ProductCategories_Categories FOREIGN KEY (cProductCategoryCategoryId) REFERENCES t_Categories (cCategoryId)
)

CREATE TABLE t_ProductOrders (
  cProductOrderProductId INT NOT NULL,
  cProductOrderOrderId INT NOT NULL,
  cProductOrderQuantity INT NOT NULL CHECK(cProductOrderQuantity > 0)
  CONSTRAINT PK_ProductOrder PRIMARY KEY (cProductOrderProductId,cProductOrderOrderId),
  CONSTRAINT FK_ProductOrders_Products FOREIGN KEY (cProductOrderProductId) REFERENCES t_Products (cProductId),
  CONSTRAINT FK_ProductOrders_Orders FOREIGN KEY (cProductOrderOrderId) REFERENCES t_Orders (cOrderId)
)

/*Create Login and User for 'traineeDB' and grant permissions*/

-- CREATE LOGIN trainee WITH PASSWORD = '123456';
CREATE USER traineeUser FOR LOGIN trainee;

USE shopDB
GO
GRANT CONTROL ON t_Users TO traineeUser;
GRANT CONTROL ON t_Roles TO traineeUser;
GRANT CONTROL ON t_Rights TO traineeUser;
GRANT CONTROL ON t_RoleRights TO traineeUser;
GRANT CONTROL ON t_UserRoles TO traineeUser;
GRANT CONTROL ON t_Products TO traineeUser;
GRANT CONTROL ON t_Categories TO traineeUser;
GRANT CONTROL ON t_Orders TO traineeUser;
GRANT CONTROL ON t_ProductCategories TO traineeUser;
GRANT CONTROL ON t_ProductOrders TO traineeUser;
GRANT CONTROL ON t_Addresses TO traineeUser;


INSERT INTO t_Categories(cCategoryName)
VALUES('Electronics'),('Clothing'),('Food'),('Furniture'),('Housewares'),('Autoparts')

INSERT INTO t_Roles(cRoleName)
VALUES('NormalUser'),('Admin')

INSERT INTO t_Users(cUserEmail,cUserFirstName,cUserLastName,cUserPassword,cUserActive)
VALUES('admin@abv.bg','Ivan','Petrov','123456',1)

INSERT INTO t_Users(cUserEmail,cUserFirstName,cUserLastName,cUserPassword,cUserActive)
VALUES('gosho@abv.bg','Gosho','Ivanov','123',1)

INSERT INTO t_UserRoles(cUserRoleUserId,cUserRoleRoleId)
VALUES(1,2),(2,1)
ALTER TABLE t_Products
ALTER COLUMN cProductImageUrl varchar(max);

ALTER TABLE t_Products
ALTER COLUMN cProductDescription varchar(max);