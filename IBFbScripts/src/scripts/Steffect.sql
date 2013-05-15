delimiter $$

drop procedure if exists `getEffectidsByStudyid`$$

CREATE PROCEDURE `getEffectidsByStudyid`(IN studyid int, IN iscentral int)
begin



IF iscentral = 1 then

	select pr.subject_project_id as effectid
	from project_relationship pr where pr.type_id = 1150
	and pr.object_project_id = studyid
	order by s.effectid asc

else

	select pr.subject_project_id as effectid
	from project_relationship pr where pr.type_id = 1150
	and pr.object_project_id = studyid
	order by s.effectid desc

	
end if;
end$$

drop procedure if exists `getListSteffect`$$

CREATE PROCEDURE `getListSteffect`(IN studyid int)
begin

	select pr.subject_project_id as effectid, pr.object_project_id as studyid, "" as effectname
	from project_relationship pr where pr.type_id = 1150
	and pr.object_project_id = studyid

end$$


