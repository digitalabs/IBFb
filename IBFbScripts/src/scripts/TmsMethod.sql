delimiter $$
drop procedure if exists `getListTmsMethod`$$

CREATE PROCEDURE `getListTmsMethod` (
IN v_tmethid int,
IN v_tmname varchar(200) character set utf8,
IN v_tmdesc varchar(255) character set utf8)
begin
	SET @sql := CONCAT("SELECT cvterm_id AS tmethid, ",
	   			"name AS tmname, ",
       			"definition AS tmdesc ",
  				"FROM cvterm "
 				"WHERE cv_id = 1020 ");

	IF(v_tmethid IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND cvterm_id = ", v_tmethid);
    END IF;

	IF(v_tmname IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND name = '", v_tmname, "' ");
    END IF;
	
	IF(v_tmdesc IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND definition = '", v_tmdesc, "' ");
    END IF;

	SET @sql = CONCAT(@sql, " ORDER BY tmethid; ");
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
end$$

drop procedure if exists `getTmsMethodList`$$

CREATE PROCEDURE `getTmsMethodList` ()
begin
	SET @sql := CONCAT("SELECT cvterm_id AS tmethid, ",
	   			"name AS tmname, ",
       			"definition AS tmdesc ",
  				"FROM cvterm "
 				"WHERE cv_id = 1020 ");
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
end$$
