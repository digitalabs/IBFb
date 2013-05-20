delimiter $$

drop procedure if exists `addStudy`$$

CREATE PROCEDURE `addStudy`(
IN v_studyid int,
IN v_sname varchar(50), 
IN v_pmkey int,
IN v_title varchar(255), 
IN v_objectiv varchar(255),
IN v_investid int,
IN v_stype varchar(5),
IN v_sdate int,
IN v_edate int,
IN v_userid int,
IN v_sstatus int,
IN v_shierarchy int)
begin

DECLARE v_projectprop_id int;
DECLARE v_project_relationship_id int;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;
	
	INSERT INTO project(project_id,name,description)
	VALUES(v_studyid,v_sname,v_title);
	
	CALL getNextMinReturn('project_relationship',v_project_relationship_id);
	
	INSERT INTO project_relationship(project_relationship_id,type_id,object_project_id,subject_project_id)
	VALUE(v_project_relationship_id,1145,v_shierarchy,v_studyid);
	
	CALL getNextMinReturn('projectprop',v_projectprop_id);
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1011,'STUDY',1);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'STUDY NAME',1);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 1 as rank
	FROM cvterm
	WHERE name = 'STUDY_NAME';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_sname as value, 1 as rank
	FROM cvterm
	WHERE name = 'STUDY_NAME';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1012,'TITLE',2);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'TITLE ASSIGNED',2);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 2 as rank
	FROM cvterm
	WHERE name = 'STUDY_TITLE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_title as value, 2 as rank
	FROM cvterm
	WHERE name = 'STUDY_TITLE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'PMKEY',3);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'PROJECT MANAGEMENT KEY',3);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 3 as rank
	FROM cvterm
	WHERE name = 'PM_KEY';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_pmkey as value, 3 as rank
	FROM cvterm
	WHERE name = 'PM_KEY';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'OBJECTIVE',4);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'OBJECTIVE DESCRIBED',4);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 4 as rank
	FROM cvterm
	WHERE name = 'STUDY_OBJECTIVE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_objectiv as value, 4 as rank
	FROM cvterm
	WHERE name = 'STUDY_OBJECTIVE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'START DATE',5);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'STUDY START DATE',5);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 5 as rank
	FROM cvterm
	WHERE name = 'START_DATE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_sdate as value, 5 as rank
	FROM cvterm
	WHERE name = 'START_DATE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'END DATE',6);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'STUDY END DATE',6);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 6 as rank
	FROM cvterm
	WHERE name = 'END_DATE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_edate as value, 6 as rank
	FROM cvterm
	WHERE name = 'END_DATE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'STUDY TYPE',7);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'TYPE OF STUDY',7);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 7 as rank
	FROM cvterm
	WHERE name = 'STUDY_TYPE';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvt1.cvterm_id as type_id, cvt2.cvterm_id as value, 7 as rank
	FROM cvterm cvt1, cvterm cvt2
	WHERE cvt1.name = 'STUDY_TYPE'
	AND cvt2.name = v_stype
  	AND cvt2.cv_id = 2010;
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'PIID',8);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'ID OF PRINCIPAL INVESTIGATOR',8);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 8 as rank
	FROM cvterm
	WHERE name = 'PI_ID';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_investid as value, 8 as rank
	FROM cvterm
	WHERE name = 'PI_ID';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1010,'STUDY UID',9);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'STUDY USER ID',9);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 9 as rank
	FROM cvterm
	WHERE name = 'Study_UID';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, v_userid as value, 9 as rank
	FROM cvterm
	WHERE name = 'Study_UID';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1011,'STUDY_STATUS',10);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_studyid,1060,'Study status (1=active, 2=private, 3=locked, 9=deleted)',10);
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, 1070, cvterm_id as value, 10 as rank
	FROM cvterm
	WHERE name = 'STUDY_STATUS';
	
	SET v_projectprop_id := v_projectprop_id - 1;
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	SELECT v_projectprop_id AS projectprop_id, v_studyid AS project_id, cvterm_id as type_id, 1 as value, 10 as rank
	FROM cvterm
	WHERE name = 'STUDY_STATUS';

COMMIT;

end$$

drop procedure if exists `updateStudy`$$

CREATE PROCEDURE `updateStudy`(
IN v_studyid int,
IN v_sname varchar(50), 
IN v_pmkey int,
IN v_title varchar(255), 
IN v_objectiv varchar(255),
IN v_investid int,
IN v_stype varchar(5),
IN v_sdate int,
IN v_edate int,
IN v_userid int,
IN v_sstatus int,
IN v_shierarchy int)
begin

DECLARE v_projectprop_id int;
DECLARE v_project_relationship_id int;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;
	
	UPDATE project
	SET name = v_sname
	,description = v_title
	WHERE project_id = v_studyid;
	
	update projectprop pp
	set value = v_sname
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'STUDY_NAME');
	
	update projectprop pp
	set value = v_title
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'STUDY_TITLE');
	
	update projectprop pp
	set value = v_pmkey
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'PM_KEY');
	
	update projectprop pp
	set value = v_objectiv
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'STUDY_OBJECTIVE');
	
	update projectprop pp
	set value = v_investid
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'PI_ID');	
	
	update projectprop pp
	set value = (select cvterm_id from cvterm where name = v_stype) 
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'STUDY_TYPE');
	
	update projectprop pp
	set value = v_sdate  
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'START_DATE');
	
	update projectprop pp
	set value = v_edate 
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'END_DATE');
	
	update projectprop pp
	set value = v_userid 
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'Study_UID');
	
	update projectprop pp
	set value = 1
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'STUDY_STATUS');
	
	update project_relationship pr
	set object_project_id = v_shierarchy 
	where pr.subject_project_id = v_studyid;
	
COMMIT;

end$$

drop procedure if exists `getStudyList`$$

CREATE PROCEDURE `getStudyList`()
begin

	SELECT distinct p.project_id as studyid, p.name as sname, p.description as title, pr.object_project_id AS shierarchy 
	,GROUP_CONCAT(if(ct.name = 'PM_KEY', value.value, NULL)) AS 'pmkey' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_OBJECTIVE', value.value, NULL)) AS 'objectiv' 
	,GROUP_CONCAT(if(ct.name = 'PI_ID', value.value, NULL)) AS 'investid' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_TYPE', ct2.name, NULL)) AS 'stype' 
	,GROUP_CONCAT(if(ct.name = 'START_DATE', value.value, NULL)) AS 'sdate' 
	,GROUP_CONCAT(if(ct.name = 'END_DATE', value.value, NULL)) AS 'edate' 
	,GROUP_CONCAT(if(ct.name = 'Study_UID', value.value, NULL)) AS 'userid' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_STATUS', value.value, NULL)) AS 'sstatus' 
	FROM project p 
	INNER JOIN project_relationship pr ON pr.subject_project_id = p.project_id 
	,cvterm ct, projectprop value 
	LEFT JOIN cvterm ct2 ON ct2.cvterm_id = value.value 
	WHERE value.project_id = p.project_id 
  	AND value.type_id = ct.cvterm_id 
	AND ct.name 
	IN ('STUDY_STATUS','PM_KEY','STUDY_OBJECTIVE','PI_ID','STUDY_TYPE','START_DATE','END_DATE','Study_UID') 
	GROUP BY p.project_id
	HAVING (sstatus IS NULL OR sstatus != 9);

end$$

drop procedure if exists `getStudyById`$$

CREATE PROCEDURE `getStudyById`(IN v_studyid int)
begin

	SELECT distinct p.project_id as studyid, p.name as sname, p.description as title, pr.object_project_id AS shierarchy 
	,GROUP_CONCAT(if(ct.name = 'PM_KEY', value.value, NULL)) AS 'pmkey' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_OBJECTIVE', value.value, NULL)) AS 'objectiv' 
	,GROUP_CONCAT(if(ct.name = 'PI_ID', value.value, NULL)) AS 'investid' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_TYPE', ct2.name, NULL)) AS 'stype' 
	,GROUP_CONCAT(if(ct.name = 'START_DATE', value.value, NULL)) AS 'sdate' 
	,GROUP_CONCAT(if(ct.name = 'END_DATE', value.value, NULL)) AS 'edate' 
	,GROUP_CONCAT(if(ct.name = 'Study_UID', value.value, NULL)) AS 'userid' 
	,GROUP_CONCAT(if(ct.name = 'STUDY_STATUS', value.value, NULL)) AS 'sstatus' 
	FROM project p 
	INNER JOIN project_relationship pr ON pr.subject_project_id = p.project_id 
	,cvterm ct, projectprop value 
	LEFT JOIN cvterm ct2 ON ct2.cvterm_id = value.value 
	WHERE value.project_id = p.project_id 
  	AND value.type_id = ct.cvterm_id 
	AND ct.name 
	IN ('STUDY_STATUS','PM_KEY','STUDY_OBJECTIVE','PI_ID','STUDY_TYPE','START_DATE','END_DATE','Study_UID') 
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
	",GROUP_CONCAT(if(ct.name = 'PI_ID', value.value, NULL)) AS 'investid'",  
	",GROUP_CONCAT(if(ct.name = 'STUDY_TYPE', ct2.name, NULL)) AS 'stype'",  
	",GROUP_CONCAT(if(ct.name = 'START_DATE', value.value, NULL)) AS 'sdate'",  
	",GROUP_CONCAT(if(ct.name = 'END_DATE', value.value, NULL)) AS 'edate'",  
	",GROUP_CONCAT(if(ct.name = 'Study_UID', value.value, NULL)) AS 'userid'",  
	",GROUP_CONCAT(if(ct.name = 'STUDY_STATUS', value.value, NULL)) AS 'sstatus'",  
	"FROM project p ", 
	"INNER JOIN project_relationship pr ON pr.subject_project_id = p.project_id ",  
	",cvterm ct, projectprop value ", 
	"LEFT JOIN cvterm ct2 ON ct2.cvterm_id = value.value ",  
	"WHERE value.project_id = p.project_id ", 
  	"AND value.type_id = ct.cvterm_id ", 
	"AND ct.name ", 
	"IN ('STATUS','PM_KEY','STUDY_OBJECTIVE','PI_ID','STUDY_TYPE','START_DATE','END_DATE','Study_UID') ",  
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
	
declare v_prevname varchar(50);
declare v_postfix varchar(10);

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;

	update projectprop pp
	set value = 9
	where pp.project_id = v_studyid
	and exists
	(select 1
	from cvterm ctype
	where ctype.cvterm_id = pp.type_id
	and ctype.name = 'STUDY_STATUS');
    
  	select name into v_prevname from project where project_id = v_studyid;

	select if(INSTR(v_prevname, '#') = 0, 1, max(SUBSTRING_INDEX(name,'#',-1))+1) into v_postfix 
	from project 
	where name like 
	CONCAT(v_prevname,'%');

	update project
	set name = CONCAT(v_prevname,'#',v_postfix)
	where project_id = v_studyid;

COMMIT;

end$$ 
