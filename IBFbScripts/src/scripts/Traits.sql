delimiter $$

drop procedure if exists `getTraitGroups`$$

CREATE PROCEDURE `getTraitGroups`()
begin
	SELECT DISTINCT trg.name AS traitgroup
	  FROM cvterm trg
	 WHERE EXISTS (
			SELECT NULL
			  FROM cvterm_relationship tr
			 INNER JOIN cvterm_relationship isa ON isa.subject_id = tr.object_id
			 WHERE tr.type_id = 1200 -- get traits
			   AND isa.type_id = 1225 -- get "is a" to get groupname
			   AND isa.object_id = trg.cvterm_id
		);
end$$
