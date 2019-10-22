CREATE DATABASE shopDB;

USE shopDB;


CREATE TABLE t_Roles (
  cRoleId INT IDENTITY(1,1),
  cRoleName VARCHAR(36) NOT NULL UNIQUE,
  CONSTRAINT PK_Role PRIMARY KEY (cRoleId)
)

CREATE TABLE t_Users (
  cUserId INT IDENTITY(1,1),
  cUserName VARCHAR(36) NOT NULL UNIQUE,
  cUserEmail VARCHAR(36) NOT NULL UNIQUE,
  cUserFirstName VARCHAR(36) NOT NULL,
  cUserLastName VARCHAR(36) NOT NULL,
  cUserPassword VARCHAR(512) NOT NULL,
  cUserCreatedAt DATETIME2 NOT NULL DEFAULT GETDATE(),
  cUserActive BIT NOT NULL DEFAULT 1,
  CONSTRAINT PK_User PRIMARY KEY (cUserId)
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
  CONSTRAINT PK_ProductOrder PRIMARY KEY (cProductOrderProductId,cProductOrderOrderId),
  CONSTRAINT FK_ProductOrders_Products FOREIGN KEY (cProductOrderProductId) REFERENCES t_Products (cProductId),
  CONSTRAINT FK_ProductOrders_Categories FOREIGN KEY (cProductOrderOrderId) REFERENCES t_Orders (cOrderId)
)