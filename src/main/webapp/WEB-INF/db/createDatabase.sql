
-- Database: "webUI2008"
CREATE DATABASE $(db_name)
	ON (NAME=$(db_name), FILENAME='$(db_path)\$(db_name).mdf')
	LOG ON (NAME=$(db_name)Log, FILENAME='$(db_path)\$(db_name).ldf');

ALTER DATABASE $(db_name) MODIFY FILE (NAME = $(db_name), SIZE = 30MB, FILEGROWTH = 10%);
ALTER DATABASE $(db_name) COLLATE Latin1_General_CI_AS;
ALTER DATABASE $(db_name) SET READ_COMMITTED_SNAPSHOT ON;
ALTER DATABASE $(db_name) SET ALLOW_SNAPSHOT_ISOLATION ON;
ALTER DATABASE $(db_name) SET RECURSIVE_TRIGGERS ON;
ALTER DATABASE $(db_name) SET RECOVERY SIMPLE;
GO

EXEC sp_addlogin $(db_user), $(db_password), $(db_name);
GO

USE $(db_name);
GO
EXEC sp_changedbowner $(db_user);
GO
