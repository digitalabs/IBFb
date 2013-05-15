delimiter $$

drop procedure if exists `addOrUpdateStudy`$$

CREATE PROCEDURE `addOrUpdateStudy`(
IN v_studyid int,
IN v_sname varchar(50), 
IN v_pmkey int,
IN v_title varchar(255), 
IN v_objectiv varchar(255),
IN v_investid int,
IN v_stype varchar(1),
IN v_sdate int,
IN v_edate int,
IN v_userid int,
IN v_sstatus int,
IN v_shierarchy int)
begin

INSERT INTO project(project_id,name,description)
VALUES(v_studyid,v_sname,v_title);

end$$

drop procedure if exists `getStudyList`$$

CREATE PROCEDURE `getStudyList`()
begin

	SELECT distinct p.project_id as studyid, p.name as sname, p.description as title, pr.object_project_id AS shierarchy 
	,GROUP_CONCAT(if(ct.name = 'PM_KEY', value.value, NULL)) AS 'pmkey' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_OBJECTIVE', value.value, NULL)) AS 'objectiv' 
	,GROUP_CONCAT(if(ct.name = 'Invest_UID', value.value, NULL)) AS 'investid' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_TYPE', ct2.name, NULL)) AS 'stype' 
	,GROUP_CONCAT(if(ct.name = 'START_DATE', value.value, NULL)) AS 'sdate' 
	,GROUP_CONCAT(if(ct.name = 'END_DATE', value.value, NULL)) AS 'edate' 
	,GROUP_CONCAT(if(ct.name = 'Study_UID', value.value, NULL)) AS 'userid' 
	,GROUP_CONCAT(if(ct.name = 'STATUS', ct2.name, NULL)) AS 'sstatus' 
	FROM project p 
	INNER JOIN project_relationship pr ON pr.subject_project_id = p.project_id 
	,projectprop label, cvterm ct, projectprop type, projectprop value 
	LEFT JOIN cvterm ct2 ON ct2.cvterm_id = value.value 
	WHERE label.rank = type.rank 
	AND type.rank = value.rank 
	AND label.project_id = p.project_id 
	AND label.project_id = type.project_id 
	AND type.project_id = value.project_id 
	AND type.type_id =1070 
	AND type.value = value.type_id 
	AND label.type_id = ct.cvterm_id 
	AND ct.name 
	IN ('STATUS','PM_KEY','STUDY_OBJECTIVE','Invest_UID','STUDY_TYPE','START_DATE','END_DATE','Study_UID') 
	GROUP BY p.project_id;

end$$

drop procedure if exists `getStudyById`$$

CREATE PROCEDURE `getStudyById`(IN v_studyid int)
begin

	SELECT distinct p.project_id as studyid, p.name as sname, p.description as title, pr.object_project_id AS shierarchy 
	,GROUP_CONCAT(if(ct.name = 'PM_KEY', value.value, NULL)) AS 'pmkey' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_OBJECTIVE', value.value, NULL)) AS 'objectiv' 
	,GROUP_CONCAT(if(ct.name = 'Invest_UID', value.value, NULL)) AS 'investid' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_TYPE', ct2.name, NULL)) AS 'stype' 
	,GROUP_CONCAT(if(ct.name = 'START_DATE', value.value, NULL)) AS 'sdate' 
	,GROUP_CONCAT(if(ct.name = 'END_DATE', value.value, NULL)) AS 'edate' 
	,GROUP_CONCAT(if(ct.name = 'Study_UID', value.value, NULL)) AS 'userid' 
	,GROUP_CONCAT(if(ct.name = 'STATUS', ct2.name, NULL)) AS 'sstatus' 
	FROM project p 
	INNER JOIN project_relationship pr ON pr.subject_project_id = p.project_id 
	,projectprop label, cvterm ct, projectprop type, projectprop value 
	LEFT JOIN cvterm ct2 ON ct2.cvterm_id = value.value 
	WHERE label.rank = type.rank 
	AND type.rank = value.rank 
	AND label.project_id = p.project_id 
	AND label.project_id = type.project_id 
	AND type.project_id = value.project_id 
	AND type.type_id =1070 
	AND type.value = value.type_id 
	AND label.type_id = ct.cvterm_id 
	AND ct.name 
	IN ('STATUS','PM_KEY','STUDY_OBJECTIVE','Invest_UID','STUDY_TYPE','START_DATE','END_DATE','Study_UID') 
	AND p.project_id = v_studyid;
	
end$$

drop procedure if exists `getStudy`$$

CREATE PROCEDURE `getStudy`(
IN v_studyid int,
IN v_sname varchar(50), 
IN v_pmkey int,
IN v_title varchar(255), 
IN v_objectiv varchar(255),
IN v_investid int,
IN v_stype varchar(1),
IN v_sdate int,
IN v_edate int,
IN v_userid int,
IN v_sstatus int,
IN v_shierarchy int)
begin

	SET @sql := CONCAT("SELECT distinct p.project_id as studyid, p.name as sname, p.description as title, pr.object_project_id AS shierarchy", 
	",GROUP_CONCAT(if(ct.name = 'PM_KEY', value.value, NULL)) AS 'pmkey'", 
	",GROUP_CONCAT(if(ct.name = 'STUDY_OBJECTIVE', value.value, NULL)) AS 'objectiv'",  
	",GROUP_CONCAT(if(ct.name = 'Invest_UID', value.value, NULL)) AS 'investid'",  
	",GROUP_CONCAT(if(ct.name = 'STUDY_TYPE', ct2.name, NULL)) AS 'stype'",  
	",GROUP_CONCAT(if(ct.name = 'START_DATE', value.value, NULL)) AS 'sdate'",  
	",GROUP_CONCAT(if(ct.name = 'END_DATE', value.value, NULL)) AS 'edate'",  
	",GROUP_CONCAT(if(ct.name = 'Study_UID', value.value, NULL)) AS 'userid'",  
	",GROUP_CONCAT(if(ct.name = 'STATUS', ct2.name, NULL)) AS 'sstatus'",  
	"FROM project p ", 
	"INNER JOIN project_relationship pr ON pr.subject_project_id = p.project_id ",  
	",projectprop label, cvterm ct, projectprop type, projectprop value ", 
	"LEFT JOIN cvterm ct2 ON ct2.cvterm_id = value.value ", 
	"WHERE label.rank = type.rank ", 
	"AND type.rank = value.rank ", 
	"AND label.project_id = p.project_id ",  
	"AND label.project_id = type.project_id ", 
	"AND type.project_id = value.project_id ", 
	"AND type.type_id =1070 ", 
	"AND type.value = value.type_id ",  
	"AND label.type_id = ct.cvterm_id ",  
	"AND ct.name ", 
	"IN ('STATUS','PM_KEY','STUDY_OBJECTIVE','Invest_UID','STUDY_TYPE','START_DATE','END_DATE','Study_UID')" ,
	"GROUP BY p.project_id HAVING 1=1 ");
	IF(v_studyid IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND studyid = ",v_studyid);
	END IF;
	IF(v_sname IS NOT NULL) THEN
    SET @sql = CONCAT(@sql," AND sname = '",v_sname,"'");
    END IF;
	IF(v_title IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND title = '",v_title,"'");
	END IF;
	IF(v_shierarchy IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND shierarchy = ",v_shierarchy);
	END IF;
	IF(v_pmkey IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND pmkey = ",v_pmkey);
	END IF;
	IF(v_objectiv IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND objectiv = '",v_objectiv,"'");
	END IF;
	IF(v_investid IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND investid = ",v_investid);
	END IF;
	IF(v_stype IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND stype = '",v_stype,"'");
	END IF;
	IF(v_sdate IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND sdate = ",v_sdate);
	END IF;
	IF(v_edate IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND edate = ",v_edate);
	END IF;
	IF(v_userid IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND userid = ",v_userid);
	END IF;
	IF(v_sstatus IS NOT NULL) THEN
	SET @sql = CONCAT(@sql," AND sstatus = ",v_sstatus);
	END IF;
	
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$

drop procedure if exists `deleteStudy`$$

CREATE PROCEDURE `deleteStudy`(IN v_studyid int)
begin
	
	update cvterm cvalue
	set name = 0
	where exists
	(select 1
	from projectprop pp
	,projectprop type
	,projectprop label
	,cvterm ctype
	where pp.project_id = v_studyid
	and pp.value = cvalue.cvterm_id
	and type.value = pp.type_id
	and type.project_id = pp.project_id
	and type.rank = pp.rank
	and type.type_id = 1070
	and label.rank = type.rank
	and label.project_id = type.project_id
	and label.type_id = ctype.cvterm_id
	and ctype.name = 'STATUS');

end$$ 