delimiter $$
drop procedure if exists `getListMeasuredIn`$$

CREATE PROCEDURE `getListMeasuredIn` (
IN v_measuredinid int,
IN v_traitid int,
IN v_tmethid int,
IN v_scaleid int,
IN v_standardscale int)
begin
	SET @sql := CONCAT("SELECT cvt.cvterm_id AS measuredinid, ",
				"crp.object_id AS traitid, ",
				"crm.object_id AS tmethid, ",
				"crs.object_id AS scaleid, ",
				"crs.object_id AS standardscale ",
				"FROM cvterm cvt ",
				"INNER JOIN cvterm_relationship crp ON crp.subject_id = cvt.cvterm_id ",
				"INNER JOIN cvterm_relationship crm ON crm.subject_id = cvt.cvterm_id ",
				"INNER JOIN cvterm_relationship crs ON crs.subject_id = cvt.cvterm_id ",
				"WHERE cvt.cv_id = 1040 ",
				"AND crp.type_id = 1200 ",
				"AND crm.type_id = 1210 ",
				"AND crs.type_id = 1220 ");

	IF(v_measuredinid IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND cvt.cvterm_id = ",v_measuredinid);
    END IF;

	IF(v_traitid IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND crp.object_id = ",v_traitid);
    END IF;

	IF(v_tmethid IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND crm.object_id = ",v_tmethid);
    END IF;

	IF(v_scaleid IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND crs.object_id = ",v_scaleid);
    END IF;
	
	IF(v_standardscale IS NOT NULL) THEN
		SET @sql = CONCAT(@sql," AND crs.object_id = ",v_standardscale);
    END IF;

	SET @sql = CONCAT(@sql, " ORDER BY measuredinid; ");
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
end$$

CREATE PROCEDURE `getMeasuredinByTraitidScaleidTmethid`(IN traitId int, IN scaleId int, IN tmethId int)
BEGIN

	SELECT
	var.cvterm_id AS measuredinid
	, traitrel.object_id AS traitid
	, scalerel.object_id AS scaleid
	, scalerel.object_id AS standardscale
	, NULL AS report
	, NULL AS formula
	, methrel.object_id AS tmethid
	FROM cvterm var
	INNER JOIN cvterm_relationship traitrel ON traitrel.subject_id = var.cvterm_id AND traitrel.type_id = 1200
	INNER JOIN cvterm_relationship methrel ON methrel.subject_id = var.cvterm_id AND methrel.type_id = 1210
	INNER JOIN cvterm_relationship scalerel ON scalerel.subject_id = var.cvterm_id AND scalerel.type_id = 1220
	WHERE var.cv_id = 1040
	AND traitrel.object_id = traitId
	AND methrel.object_id = tmethId
	AND scalerel.object_id = scaleId
	limit 1;

END$$