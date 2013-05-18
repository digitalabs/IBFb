DELIMITER $$

DROP PROCEDURE IF EXISTS `addLevelC`$$

CREATE PROCEDURE `addLevelC`(
  IN labelidin int
  , IN factoridin int
  , IN lvaluein varchar(500)
  , IN levelno_v int)

BEGIN

	declare v_storedinid INT;
	
	declare v_projectid INT;
	declare v_termid varchar(255);
	declare v_rankint INT; 
	
	SELECT storedinid 
	  INTO v_storedinid
      FROM v_stdvar
     WHERE projectprop_id = labelidin  limit 1; 
	
	IF(@storedinid = 1010 or @storedinid = 1015) THEN
		call getNextMinReturn('projectprop', @newppid);
		
		select projectprop_id, value, rank into v_projectid, v_termid, v_rankint  from projectprop where projectprop_id = labelidin;
		insert into projectprop (projectprop_id,project_id,type_id,value,rank) value ( @newppid, @projectid, @termid, lvaluein, @rankint);
    END IF;
	/*
	IF(@storedinid = 1011) THEN
		-- assumption is info was added when project is added already
		;
    END IF;
	IF(@storedinid = 1012) THEN
		-- assumption is info was added when project is added already
		;
    END IF;
	
	IF(@storedinid = 1016) THEN
		-- assumption is info was added when project is added already
		;
    END IF;
	IF(@storedinid = 1017) THEN
		-- assumption is info was added when project is added already
		;
    END IF;
	*/
	IF(@storedinid = 1020) THEN
		call getNextMinReturn('nd_geolocationprop', @newgeoid);
		 
		select projectprop_id, value, rank into v_projectid, v_termid, v_rankint  from projectprop where projectprop_id = labelidin;
		
		insert into nd_geolocationprop (nd_geolocationprop_id, nd_geolocation_id, type_id, value, rank) value (@newgeoid, levelno_v, @termid , lvaluein, 0);
    END IF;
	
	IF(@storedinid = 1021) THEN
		update nd_geolocation set description = lvaluein where nd_geolocation_id = levelno_v;
    END IF;
	IF(@storedinid = 1022) THEN
		update nd_geolocation set latitude = lvaluein where nd_geolocation_id = levelno_v;
    END IF;
	IF(@storedinid = 1023) THEN
		update nd_geolocation set longitude = lvaluein where nd_geolocation_id = levelno_v;
    END IF;
	IF(@storedinid = 1024) THEN
		update nd_geolocation set geodetic_datum = lvaluein where nd_geolocation_id = levelno_v;
    END IF;
	IF(@storedinid = 1025) THEN
		update nd_geolocation set altitude = lvaluein where nd_geolocation_id = levelno_v;
    END IF;
	
	IF(@storedinid = 1030) THEN
		call getNextMinReturn('nd_experimentprop', @newexpid);
		
		select projectprop_id, value, rank into v_projectid, v_termid, v_rankint  from projectprop where projectprop_id = labelidin;
		
		insert into nd_experimentprop (nd_experimentprop_id, nd_experiment_id,type_id,value,rank) value (@newexpid, levelno_v, @termid , lvaluein, 0);
		
    END IF;
	IF(@storedinid = 1040) THEN
		call getNextMinReturn('stockprop', @newstockpropid);
		
		select projectprop_id, value, rank into v_projectid, v_termid, v_rankint  from projectprop where projectprop_id = labelidin;
		
		insert into stockprop (stockprop_id,stock_id,type_id,value,rank) value (@newstockpropid, levelno_v, @termid , lvaluein, 0);
    END IF;
	IF(@storedinid = 1041) THEN
			update stock set uniquename = lvaluein where stock_id = levelno_v;
    END IF;
	IF(@storedinid = 1042) THEN
			update stock set dbxref_id = lvaluein where stock_id = levelno_v;
    END IF;
	IF(@storedinid = 1046) THEN
			update stock set name = lvaluein where stock_id = levelno_v;
    END IF;
	IF(@storedinid = 1047) THEN
			update stock set value = lvaluein where stock_id = levelno_v;
    END IF;
	

END$$


DROP PROCEDURE IF EXISTS `updateLevelC`$$

CREATE PROCEDURE `updateLevelC`(
  IN labelidin int
  , IN factoridin int
  , IN levelnoin int
 , IN lvaluein varchar(500))

BEGIN

	DECLARE v_storedinid INT;
	DECLARE projectid INT;
	
	SELECT storedinid 
	  INTO v_storedinid
      FROM v_stdvar
     WHERE projectprop_id = labelidin; 
	
	IF (v_storedinid = 1010 OR v_storedinid = 1015) THEN
		UPDATE v_stdvar stdvar 
	     INNER JOIN projectprop pval ON pval.type_id = stdvar.varid AND pval.project_id = stdvar.project_id AND pval.rank = stdvar.rank
		   SET pval.value = lvaluein
		 WHERE stdvar.projectprop_id = labelidin;
    END IF;
	
	IF (v_storedinid = 1011 OR v_storedinid = 1016) THEN
		-- get project id first
		SELECT @projectid = projectprop_id 
          FROM projectprop 
		 WHERE projectprop_id = labelidin;
		
		UPDATE project
		   SET name = lvaluein
		 WHERE project_id = @projectid;
    END IF;

	IF (v_storedinid = 1012 OR v_storedinid = 1017) THEN
		-- get project id first
		SELECT @projectid = projectprop_id 
          FROM projectprop 
		 WHERE projectprop_id = labelidin;
		
		UPDATE project
		   SET description = lvaluein
		 WHERE project_id = @projectid;
    END IF;
	
	IF (v_storedinid = 1020) THEN
		UPDATE nd_geolocationprop gp
		 INNER JOIN v_stdvar stdvar ON stdvar.varid = gp.type_id
		   SET gp.value = lvaluein
		 WHERE gp.nd_geolocation_id = levelnoin
		   AND stdvar.projectprop_id = labelidin;
    END IF;
	
	IF (v_storedinid = 1021) THEN
		UPDATE nd_geolocation 
           SET description = lvaluein 
         WHERE nd_geolocation_id = levelnoin;
    END IF;

	IF (v_storedinid = 1022) THEN
		UPDATE nd_geolocation 
		   SET latitude = lvaluein 
		 WHERE nd_geolocation_id = levelnoin;
    END IF;

	IF (v_storedinid = 1023) THEN
		UPDATE nd_geolocation 
           SET longitude = lvaluein 
		 WHERE nd_geolocation_id = levelnoin;
    END IF;

	IF (v_storedinid = 1024) THEN
		UPDATE nd_geolocation 
           SET geodetic_datum = lvaluein 
		 WHERE nd_geolocation_id = levelnoin;
    END IF;

	IF (v_storedinid = 1025) THEN
		UPDATE nd_geolocation 
           SET altitude = lvaluein 
         WHERE nd_geolocation_id = levelnoin;
    END IF;
	
	IF (v_storedinid = 1030) THEN
		UPDATE nd_experimentprop exp
		 INNER JOIN v_stdvar stdvar ON stdvar.varid = exp.type_id
		   SET value = lvaluein
		 WHERE exp.nd_experiment_id = levelnoin
		   AND stdvar.projectprop_id = labelidin;	
    END IF;

	IF (v_storedinid = 1040) THEN		
		UPDATE stockprop sp
		 INNER JOIN v_stdvar stdvar ON stdvar.varid = sp.type_id
		   SET sp.value = lvaluein
		 WHERE sp.stock_id = levelnoin
		  and stdvar.projectprop_id = labelidin;
    END IF;

	IF (v_storedinid = 1041) THEN
		UPDATE stock 
           SET uniquename = lvaluein 
		 WHERE stock_id = levelnoin;
    END IF;
	IF(v_storedinid = 1042) THEN
		UPDATE stock 
           SET dbxref_id = lvaluein 
		 WHERE stock_id = levelnoin;
    END IF;

	IF (v_storedinid = 1046) THEN
		UPDATE stock 
           SET name = lvaluein 
		 WHERE stock_id = levelnoin;
    END IF;

	IF (v_storedinid = 1047) THEN
		UPDATE stock 
           SET value = lvaluein 
		 WHERE stock_id = levelnoin;
    END IF;
	

END$$


