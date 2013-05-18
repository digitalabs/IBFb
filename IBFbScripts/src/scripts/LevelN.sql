DELIMITER $$

DROP PROCEDURE IF EXISTS `addLevelN`$$

CREATE PROCEDURE `addLevelN`(
  IN labelidin int
  , IN factoridin int
  , IN lvaluein double
  , IN levelno_v int)

BEGIN

	declare storedinid INT;
	declare newlevelno int;
	declare projectid INT;
	declare termid varchar(255);
	declare rankint INT; 
	
	select @storedinid = storedinid from v_level where labelid = labelidin limit 1; 
	
	IF(@storedinid = 1010 or @storedinid = 1015) THEN
		call getNextMinReturn('projectprop', @newppid);
		
		select @projectid = projectprop_id, @termid = value, @rankint = rank from projectprop where projectprop_id = labelidin;
		insert into projectprop (projectprop_id,project_id,type_id,value,rank) value ( @newppid, @projectid, @termid, lvaluein, @rankint);
    END IF;
	/*
	IF(@storedinid = 1011) THEN
		-- assumption is info was added when project is added already
    END IF;
	IF(@storedinid = 1012) THEN
		-- assumption is info was added when project is added already
    END IF;
	
	IF(@storedinid = 1016) THEN
		-- assumption is info was added when project is added already
    END IF;
	IF(@storedinid = 1017) THEN
		-- assumption is info was added when project is added already
    END IF;
	*/
	IF(@storedinid = 1020) THEN
		call getNextMinReturn('nd_geolocationprop', @newgeoid);
		
		select @projectid = projectprop_id, @termid = value, @rankint = rank from projectprop where projectprop_id = labelidin;
		
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
		
		select @projectid = projectprop_id, @termid = value, @rankint = rank from projectprop where projectprop_id = labelidin;
		
		insert into nd_experimentprop (nd_experimentprop_id, nd_experiment_id,type_id,value,rank) value (@newexpid, levelno_v, @termid , lvaluein, 0);
		
    END IF;
	IF(@storedinid = 1040) THEN
		call getNextMinReturn('stockprop', @newstockpropid);
		
		select @projectid = projectprop_id, @termid = value, @rankint = rank from projectprop where projectprop_id = labelidin;
		
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
