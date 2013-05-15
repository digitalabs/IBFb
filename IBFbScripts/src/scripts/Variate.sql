delimiter $$

drop procedure if exists getVariateById$$

CREATE PROCEDURE getVariateById(IN v_variatid int)
begin

	select variatid, vname 
	,studyid 
	,GROUP_CONCAT(if(relationship = 'has property', ontology_id, NULL)) AS 'traitid' 
	,GROUP_CONCAT(if(relationship = 'has scale', ontology_id, NULL)) AS 'scaleid' 
	,GROUP_CONCAT(if(relationship = 'has method', ontology_id, NULL)) AS 'tmethid' 
	,GROUP_CONCAT(if(relationship = 'is a', ontology_value, NULL)) AS 'vtype' 
	,GROUP_CONCAT(if(relationship = 'has type', ontology_value, NULL)) AS 'dtype' 
	,GROUP_CONCAT(if(relationship = 'stored in', ontology_id, NULL)) AS 'tid' 
	FROM 
	(SELECT pp.projectprop_id as variatid 
	,label.value as vname 
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
	WHERE pp.type_id = 1070 and label.type_id in (1043,1048) and pp.projectprop_id = v_variatid 
	AND EXISTS ( select 1 from phenotype ph where ph.observable_id = pp.value ) 
	GROUP BY variatid, cvt2.name, cvt3.cvterm_id, cvt3.name, pp.project_id
	) as variate;
	
end$$
