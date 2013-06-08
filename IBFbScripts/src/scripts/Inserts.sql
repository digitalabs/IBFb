insert into cv(cv_id,name,definition)
select * from (select 1000 as cv_id,'IBDB TERMS' as name,
'CV of terms used to annotate relationships and identify objects in the ibdb database' as definition) as tmp
where not exists (select cv_id from cv where cv_id = 1000) limit 1;

insert into cv(cv_id,name,definition)
select * from (select 2005 as cv_id,'8006' as name,
'Study status - Assigned (code)' as definition) as tmp
where not exists (select cv_id from cv where cv_id = 2005) limit 1;

insert into cv(cv_id,name,definition)
select * from (select 2010 as cv_id,'8070' as name,
'Study type - assigned (type)' as definition) as tmp
where not exists (select cv_id from cv where cv_id = 2010) limit 1;		

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 12960 as cvterm_id,2005 as cv_id,'1' as name,
'Active study visable to all users with access' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 12960) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 12970 as cvterm_id,2005 as cv_id,'2' as name,
'Active study visable to owner only' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 12970) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 12980 as cvterm_id,2005 as cv_id,'3' as name,
'Locked study visable to all users with access' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 12980) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 12990 as cvterm_id,2005 as cv_id,'9' as name,
'Deleted study' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 12990) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10000 as cvterm_id,2010 as cv_id,'N' as name,
'Nursery' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10000) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10001 as cvterm_id,2010 as cv_id,'HB' as name,
'Hybridization nursery' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10001) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10002 as cvterm_id,2010 as cv_id,'PN' as name,
'Pedigree nursery' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10002) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10003 as cvterm_id,2010 as cv_id,'CN' as name,
'Characterization nursery' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10003) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10005 as cvterm_id,2010 as cv_id,'OYT' as name,
'Observational yield trial' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10005) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10007 as cvterm_id,2010 as cv_id,'BON' as name,
'BULU observational nursery' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10007) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10010 as cvterm_id,2010 as cv_id,'T' as name,
'Trial' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10010) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10015 as cvterm_id,2010 as cv_id,'RYT' as name,
'Replicated yield trial' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10015) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10017 as cvterm_id,2010 as cv_id,'OFT' as name,
'On farm trial' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10017) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10020 as cvterm_id,2010 as cv_id,'S' as name,
'Survey' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10020) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 10030 as cvterm_id,2010 as cv_id,'E' as name,
'Experiment' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 10030) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1110 as cvterm_id,1000 as cv_id,'Numeric variable' as name,
'Variable with numeric values either continuous or integer' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1110) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1113 as cvterm_id,1000 as cv_id,'Minimum value' as name,
'Minimum value allowed for a numeric variable' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1113) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1115 as cvterm_id,1000 as cv_id,'Maximum value' as name,
'Maximum value allowed for a numeric variable' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1115) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1117 as cvterm_id,1000 as cv_id,'Date variable' as name,
'Date - numeric value in format yyyymmdd with least significant parts set to zero acording to precision' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1117) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1118 as cvterm_id,1000 as cv_id,'Numeric DBID  variable' as name,
'Integer database ID (may be negative)' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1118) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1120 as cvterm_id,1000 as cv_id,'Character  variable' as name,
'Variable with character values' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1120) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1125 as cvterm_id,1000 as cv_id,'Timestamp  variable' as name,
'Character variable in format yyyy-mm-dd:hh:mm:ss:nnn with least significant parts omitted acording to precision' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1125) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1128 as cvterm_id,1000 as cv_id,'Character DBID  variable' as name,
'Character database ID' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1128) limit 1;

insert into cvterm(cvterm_id,cv_id,name,definition,is_obsolete,is_relationshiptype)
select * from (select 1130 as cvterm_id,1000 as cv_id,'Categorical  variable' as name,
'Variable with discrete class values (numeric or character all treated as character)' as definition,0 as is_obsolete,0 as is_relationshiptype) as tmp
where not exists (select cvterm_id from cvterm where cvterm_id = 1130) limit 1;


INSERT INTO nd_geolocation (nd_geolocation_id, description) 
SELECT * FROM (SELECT 1, '0' FROM nd_geolocation) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM nd_geolocation WHERE nd_geolocation_id = 1) LIMIT 1;

INSERT INTO project(project_id,name,description)
SELECT * FROM (SELECT -1 as project_id,'Folder' as name,'Folder' as description) as tmp
WHERE NOT EXISTS (SELECT project_id from project where project_id = -1) limit 1;
	
INSERT INTO project_relationship(project_relationship_id,type_id,object_project_id,subject_project_id)
SELECT * FROM (SELECT -1 as project_id,1140 as type_id,1 as object_project_id,-1 as subject_project_id) as tmp
WHERE NOT EXISTS (SELECT project_relationship_id from project_relationship where subject_project_id = -1) limit 1;





