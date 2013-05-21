delimiter $$

drop procedure if exists `getMainFactorsByStudyid`$$

CREATE PROCEDURE `getMainFactorsByStudyid`(IN v_studyid int, IN v_islocal int)
begin

	SET @sql := CONCAT("select labelid, studyid, fname, factorid ", 
	",GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'traitid' ", 
	",GROUP_CONCAT(if(relationship = 'has scale', ontology_id, NULL)) AS 'scaleid' ",
	",GROUP_CONCAT(if(relationship = 'has method', ontology_id, NULL)) AS 'tmethid' ",
	",GROUP_CONCAT(if(relationship = 'has type', if(ontology_value = 1120, 'C', 'N') , NULL)) AS 'ltype' ", 
	",GROUP_CONCAT(if(relationship = 'stored in', ontology_id, NULL)) AS 'tid' ", 
	"FROM ",
	"(SELECT pp.projectprop_id as labelid ", 
	",label.value as fname ",
	",cvt2.name as relationship ", 
	",cvt3.cvterm_id as ontology_id ", 
	",cvt3.name as ontology_value ",	
	",pr.object_project_id as studyid ", 
	"FROM cvterm cvt1 ",
	"INNER JOIN cvterm_relationship cvtr ON cvt1.cvterm_id = cvtr.subject_id ", 
	"INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id ",
	"INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id ",
	"INNER JOIN projectprop pp ON pp.value = cvt1.cvterm_id ",
	"INNER JOIN projectprop label ON label.project_id = pp.project_id AND label.rank = pp.rank ", 
	"INNER JOIN project_relationship pr ON pr.subject_project_id = pp.project_id AND pr.type_id = 1150 ",
	"WHERE pp.type_id = 1070 ",
	"and label.type_id in (1011, 1016, 1017, 1021, 1030, 1041) ", 
	"and pp.project_id = ",v_studyid,
	" AND NOT EXISTS ( select 1 from phenotype ph where ph.observable_id = pp.value ) ",
	") factor left join v_factor v on factor.labelid = v.projectprop_id ",
	"WHERE factor.labelid = v.factorid ",
	"GROUP BY labelid ");
	IF(v_islocal = 1) THEN
		SET @sql = CONCAT(@sql,"ORDER BY labelid DESC");
	ELSE
		SET @sql = CONCAT(@sql,"ORDER BY labelid ASC");
	END IF;
	
	PREPARE stmt FROM @sql;
	EXECUTE stmt;
	
end$$ 

drop procedure if exists `getFactorByStudyidAndFname`$$

CREATE PROCEDURE `getFactorByStudyidAndFname`(IN p_studyid int, IN p_fname varchar(255))
begin

SET @sql := CONCAT("select labelid, studyid, fname ",
",GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'factorid'  ",
",GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'traitid'  ",
",GROUP_CONCAT(if(relationship = 'has scale', ontology_id, NULL)) AS 'scaleid'  ",
",GROUP_CONCAT(if(relationship = 'has method', ontology_id, NULL)) AS 'tmethid'  ",
",GROUP_CONCAT(if(relationship = 'has type', if(ontology_value = 1120, 'C', 'N') , NULL)) AS 'ltype'  ",
",GROUP_CONCAT(if(relationship = 'stored in', ontology_id, NULL)) AS 'tid' ", 
"FROM ", 
"(SELECT pp.projectprop_id as labelid  ",
",label.value as fname ", 
",cvt2.name as relationship  ",
",cvt3.cvterm_id as ontology_id  ",
",cvt3.name as ontology_value ",	
",pp.project_id as studyid ", 
"FROM cvterm cvt1 ", 
"INNER JOIN cvterm_relationship cvtr ON cvt1.cvterm_id = cvtr.subject_id  ",
"INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id ", 
"INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id ", 
"INNER JOIN projectprop pp ON pp.value = cvt1.cvterm_id ", 
"INNER JOIN projectprop label ON label.project_id = pp.project_id AND label.rank = pp.rank  ",
"WHERE pp.type_id = 1070 ", 
"and label.type_id in (1010, 1011, 1012, 1015, 1016, 1017, 1020, 1021, 1022, 1023, 1024, 1025, 1030, 1040, 1041, 1042, 1046, 1047)  ",
"and pp.project_id = ? ",
"AND NOT EXISTS ( select 1 from phenotype ph where ph.observable_id = pp.value )  ",
") factor ", 
"WHERE fname = ? ",
"GROUP BY labelid  ",
"LIMIT 1 ");

PREPARE stmt FROM @sql;
SET @studyid = p_studyid;
SET @fname = p_fname;
EXECUTE stmt USING @studyid, @fname;
	
end$$ 


drop procedure if exists `getGroupFactorsByStudyidAndFactorid`$$

CREATE PROCEDURE `getGroupFactorsByStudyidAndFactorid`(IN p_studyid int, IN p_factorid int)
begin

SET @sql := CONCAT("select labelid, studyid, fname ",
",GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'factorid'  ",
",GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'traitid'  ",
",GROUP_CONCAT(if(relationship = 'has scale', ontology_id, NULL)) AS 'scaleid'  ",
",GROUP_CONCAT(if(relationship = 'has method', ontology_id, NULL)) AS 'tmethid'  ",
",GROUP_CONCAT(if(relationship = 'has type', if(ontology_value = 1120, 'C', 'N') , NULL)) AS 'ltype'  ",
",GROUP_CONCAT(if(relationship = 'stored in', ontology_id, NULL)) AS 'tid' ", 
"FROM ", 
"(SELECT pp.projectprop_id as labelid  ",
",label.value as fname ", 
",cvt2.name as relationship  ",
",cvt3.cvterm_id as ontology_id  ",
",cvt3.name as ontology_value ",	
",pp.project_id as studyid ", 
"FROM cvterm cvt1 ", 
"INNER JOIN cvterm_relationship cvtr ON cvt1.cvterm_id = cvtr.subject_id  ",
"INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id ", 
"INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id ", 
"INNER JOIN projectprop pp ON pp.value = cvt1.cvterm_id ", 
"INNER JOIN projectprop label ON label.project_id = pp.project_id AND label.rank = pp.rank  ",
"WHERE pp.type_id = 1070 ", 
"and label.type_id in (1010, 1011, 1012, 1015, 1016, 1017, 1020, 1021, 1022, 1023, 1024, 1025, 1030, 1040, 1041, 1042, 1046, 1047)  ",
"and pp.project_id = ? ",
"AND NOT EXISTS ( select 1 from phenotype ph where ph.observable_id = pp.value )  ",
") factor ", 
"WHERE ontology_id = ? AND relationship = 'has property' ",
"GROUP BY labelid  ");

PREPARE stmt FROM @sql;
SET @studyid = p_studyid;
SET @factorid = p_factorid;
EXECUTE stmt USING @studyid, @factorid;
	
end$$

drop procedure if EXISTS addFactor$$

CREATE PROCEDURE addFactor(
IN v_labelid int,
IN v_factorid int,
IN v_studyid int,
IN v_fname varchar(50),
IN v_traitid int,
IN v_scaleid int,
IN v_tmethid int,
IN v_ltype varchar(1),
IN v_tid int)
begin

DECLARE v_project_id int;
DECLARE v_projectprop_id int;
DECLARE v_rank int;
DECLARE v_type_id int;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK; 

START TRANSACTION;

	SELECT MAX(rank) + 1 as rank INTO v_rank 
	FROM projectprop pp
	WHERE pp.project_id = v_studyid;
	
	IF(v_rank IS NULL) THEN
	SET v_rank := 1;
	END IF;
	
	SET v_project_id := v_studyid; 
	
	CALL getNextMinReturn('projectprop',v_projectprop_id);

	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_project_id,v_tid,v_fname,v_rank);
	
	CALL getNextMinReturn('projectprop',v_projectprop_id);
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_project_id,1060,v_fname,v_rank);
	
	SELECT cvt1.cvterm_id into v_type_id
    FROM cvterm cvt1 
    WHERE EXISTS (
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id 
 	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvt2.name = 'stored in' AND cvt3.cvterm_id = v_tid)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id 
 	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
     AND (cvt2.name = 'has type' AND cvt3.cvterm_id = v_ltype)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id 
 	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvt2.name = 'has property' AND cvt3.cvterm_id = v_traitid)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id 
 	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvt2.name = 'has scale' AND cvt3.cvterm_id = v_scaleid)
    ) AND EXISTS ( 
    SELECT 1 
    FROM cvterm_relationship cvtr
	INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id 
 	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
    WHERE cvt1.cvterm_id = cvtr.subject_id 
    AND (cvt2.name = 'has method' AND cvt3.cvterm_id = v_tmethid)
    );
    
	CALL getNextMinReturn('projectprop',v_projectprop_id);
	
	INSERT INTO projectprop(projectprop_id,project_id,type_id,value,rank)
	VALUES(v_projectprop_id,v_project_id,1070,v_type_id,v_rank);
	
	SELECT v_projectprop_id;

COMMIT;	
	
end$$

delimiter $$

drop procedure if exists `getFactoridByLabelid`$$

CREATE PROCEDURE `getFactoridByLabelid`(IN v_labelid int)
begin

	SELECT factorid
	FROM v_factor 
	WHERE projectprop_id = v_labelid;
	
end$$ 


