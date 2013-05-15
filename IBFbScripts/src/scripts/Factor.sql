delimiter $$

drop procedure if exists `getDataNByEffectId`$$

CREATE PROCEDURE `getMainFactorsByStudyid`(IN v_studyid int)
begin

	select labelid, studyid, fname 
	,GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'factorid' 
	,GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'traitid' 
	,GROUP_CONCAT(if(relationship = 'has scale', ontology_id, NULL)) AS 'scaleid' 
	,GROUP_CONCAT(if(relationship = 'has method', ontology_id, NULL)) AS 'tmethid'
	,GROUP_CONCAT(if(relationship = 'has type', if(ontology_value = 1120, 'C', 'N') , NULL)) AS 'ltype' 
	,GROUP_CONCAT(if(relationship = 'stored in', ontology_id, NULL)) AS 'tid' 
	FROM 
	(SELECT pp.projectprop_id as labelid 
	,label.value as fname 
	,cvt2.name as relationship 
	,cvt3.cvterm_id as ontology_id 
	,cvt3.name as ontology_value	
	,pp.project_id as studyid 
	FROM cvterm cvt1
	INNER JOIN cvterm_relationship cvtr ON cvt1.cvterm_id = cvtr.subject_id 
	INNER JOIN cvterm cvt2 ON cvt2.cvterm_id = cvtr.type_id 
	INNER JOIN cvterm cvt3 ON cvtr.object_id = cvt3.cvterm_id 
	INNER JOIN projectprop pp ON pp.value = cvt1.cvterm_id
	INNER JOIN projectprop label ON label.project_id = pp.project_id AND label.rank = pp.rank 
	WHERE pp.type_id = 1070 
	and label.type_id in (1010, 1011, 1012, 1015, 1016, 1017, 1020, 1021, 1022, 1023, 1024, 1025, 1030, 1040, 1041, 1042, 1046, 1047) 
	and pp.project_id = v_studyid 
	AND NOT EXISTS ( select 1 from phenotype ph where ph.observable_id = pp.value )
	) factor
	GROUP BY labelid 
	
	
end$$ 