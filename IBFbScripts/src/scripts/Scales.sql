delimiter $$

drop procedure if exists `getScales`$$

CREATE PROCEDURE `getScales`(
IN v_scaleid int,
IN v_scname varchar(50), 
IN v_sctype varchar(1))
begin

	SET @sql := CONCAT("select distinct cvsc.cvterm_id as scaleid, ",
	"cvsc.name as scname, ",
	"if(cvrsb3.object_id = 1120, 'C', 'D') as sctype "
	"from cvterm_relationship cvr ",
	"inner join cvterm_relationship cvrsb on cvrsb.subject_id = cvr.subject_id ",
	"inner join cvterm cvsc on cvsc.cvterm_id = cvrsb.object_id and cvrsb.type_id = 1220 ",
	"inner join cvterm_relationship cvrsb2 on cvrsb2.subject_id = cvr.subject_id ",
	"inner join cvterm cvst on cvst.cvterm_id = cvrsb2.object_id and cvrsb2.type_id = 1200 ",
	"inner join cvterm_relationship cvrsb3 on cvrsb3.subject_id = cvr.subject_id ",
	"inner join cvterm cvsdt on cvsdt.cvterm_id = cvrsb3.type_id and cvrsb3.type_id = 1105 ",
	"having 1=1 ");
	IF(v_scaleid IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND scaleid = ",v_scaleid);
	END IF;
	IF(v_scname IS NOT NULL) THEN
    SET @sql = CONCAT(@sql," AND scname = '",v_scname,"'");
    END IF;
	IF(v_sctype IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND sctype = '",v_sctype,"'");
	END IF;	
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$

drop procedure if exists `searchScales`$$

CREATE PROCEDURE `searchScales`(
IN v_scaleid int,
IN v_scname varchar(50), 
IN v_sctype varchar(1))
begin

	SET @sql := CONCAT("select distinct cvsc.cvterm_id as scaleid, ",
	"cvsc.name as scname, ",
	"if(cvrsb3.object_id = 1120, 'C', 'D') as sctype "
	"from cvterm_relationship cvr ",
	"inner join cvterm_relationship cvrsb on cvrsb.subject_id = cvr.subject_id ",
	"inner join cvterm cvsc on cvsc.cvterm_id = cvrsb.object_id and cvrsb.type_id = 1220 ",
	"inner join cvterm_relationship cvrsb2 on cvrsb2.subject_id = cvr.subject_id ",
	"inner join cvterm cvst on cvst.cvterm_id = cvrsb2.object_id and cvrsb2.type_id = 1200 ",
	"inner join cvterm_relationship cvrsb3 on cvrsb3.subject_id = cvr.subject_id ",
	"inner join cvterm cvsdt on cvsdt.cvterm_id = cvrsb3.type_id and cvrsb3.type_id = 1105 ",
	"having 1=1 ");
	IF(v_scaleid IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND scaleid = ",v_scaleid);
	END IF;
	IF(v_scname IS NOT NULL) THEN
    SET @sql = CONCAT(@sql," AND scname like '%",v_scname,"%'");
    END IF;
	IF(v_sctype IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND sctype like '%",v_sctype,"%'");
	END IF;	
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$