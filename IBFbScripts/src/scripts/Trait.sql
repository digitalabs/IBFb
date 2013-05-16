delimiter $$

drop procedure if exists `getTraitList`$$

CREATE PROCEDURE `getTraitList`()
begin
	SELECT DISTINCT
			cvt.cvterm_id AS tid,
			cvt.cvterm_id AS traitid,
			cvt.name AS trname,
			cvt.definition AS trdesc,
			1 AS nstat, 
			grp.name AS traitgroup
	  FROM cvterm cvt
	 INNER JOIN cvterm_relationship cvr ON cvr.object_id = cvt.cvterm_id
	 INNER JOIN cvterm_relationship gcvr ON gcvr.subject_id = cvt.cvterm_id
	 INNER JOIN cvterm grp ON grp.cvterm_id = gcvr.object_id
	 WHERE gcvr.type_id = 1225 -- get "is a" relationship to get group name
	   AND cvr.type_id = 1200 -- get "has property" relationships
	 ORDER BY traitid;
end$$
