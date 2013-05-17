delimiter $$

drop procedure if exists `getNextMin`$$

CREATE PROCEDURE `getNextMin`(
IN tableName varchar(255))
begin

SET @sql := CONCAT("select IF(min(",tableName,"_id) is NULL, -1, min(",tableName,"_id) -1)  as id from ",tableName);
PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$

drop procedure if exists `getNextMinReturn`$$

CREATE PROCEDURE `getNextMinReturn`(
IN tableName varchar(255), OUT idnew INT)
begin
set @c1 = 0;
SET @sql := CONCAT("select IF(min(",tableName,"_id) is NULL, -1, min(",tableName,"_id) -1) into @c1 from ",tableName);
PREPARE stmt FROM @sql;
	EXECUTE stmt;
set idnew = @c1; 	
	
end$$
