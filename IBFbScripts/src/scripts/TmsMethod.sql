delimiter $$
drop procedure if exists `getListTmsMethod`$$

CREATE PROCEDURE `getListTmsMethod` (
IN v_tmethid int,
IN v_tmname varchar(50),
IN v_tmdesc varchar(255))
begin
	SET @sql := CONCAT("SELECT DISTINCT cvm.cvterm_id AS tmethid, ", 
				"cvm.name AS tmname, ",
				"cvm.definition AS tmdesc ",
				"FROM cvterm_relationship cvr ",
				"INNER JOIN cvterm cvm ON cvm.cvterm_id = cvr.object_id ",
				"WHERE cvr.type_id = 1210 ");

	IF(v_tmethid IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND cvm.cvterm_id = ", v_tmethid);
    END IF;

	IF(v_tmname IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND cvm.name = '", v_tmname, "' ");
    END IF;
	
	IF(v_tmdesc IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND cvm.definition = '", v_tmdesc, "' ");
    END IF;

	SET @sql = CONCAT(@sql, " ORDER BY tmethid; ");
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
end$$
