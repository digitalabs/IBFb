/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.helpers;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Measurement;
import ibfb.domain.core.Workbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.provider.KeyCacheUtil;
import org.cimmyt.cril.ibwb.provider.TableEnum;
import org.cimmyt.cril.ibwb.provider.utils.ChadoSchemaUtil;
import org.cimmyt.cril.ibwb.provider.utils.ConverterDomainToDTO;

/**
 *
 * @author gamaliel
 */
public class HelperFactor {

    private static Logger log = Logger.getLogger(HelperFactor.class);
    public static final String NUMERIC_TYPE = "N";

    public static Factor getFactorFillingFull(
            Factor factorDto,
            AppServices appServices,
            Integer dmsaType) {
        log.info("Factor Name: " + factorDto.getFname() + " " + "  FactorId: " + factorDto.getFactorid());

//        Dmsattr dmsattrFilter = new Dmsattr(null, dmsaType, null, factorDto.getLabelid(), null);
//        factorDto.setDmsattr(appServices.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));

        log.info("Cargando Measuredin con tid: " + factorDto.getTraitid() + " scaleid: " + factorDto.getScaleid() + " tmethid: " + factorDto.getTmethid());
        Measuredin measuredin = appServices.getMeasuredinByTraitidScaleidTmethid(factorDto.getTraitid(), factorDto.getScaleid(), factorDto.getTmethid());
        factorDto.setMeasuredin(measuredin);

        log.info("Cargando levels del factor: " + factorDto.getFname() + " labelid: " + factorDto.getLabelid());
        switch (factorDto.getLtype().charAt(0)) {
            case 'C':
                log.info("Level tipo C.");
                List<LevelC> levelsC = appServices.getLevelsCByLabelid(factorDto.getLabelid(), factorDto.getFactorid(), factorDto.getTid());
                if (levelsC == null) {
                    factorDto.setLevelC(null);
                } else if (levelsC.isEmpty()) {
                    factorDto.setLevelC(null);
                } else {
                    factorDto.setLevelC(levelsC.get(0));
                    factorDto.setLevelsC(levelsC);
                }
                break;
            case 'N':
                log.info("Level tipo N.");
                List<LevelN> levelsN = appServices.getLevelnByLabelid(factorDto.getLabelid(), factorDto.getFactorid(), factorDto.getTid());
                if (levelsN == null) {
                    factorDto.setLevelN(null);
                } else if (levelsN.isEmpty()) {
                    factorDto.setLevelN(null);
                } else {
                    factorDto.setLevelN(levelsN.get(0));
                    factorDto.setLevelsN(levelsN);
                }
                break;
            default:
                log.error("Not recognizing the kind of level");
                break;
        }
        return factorDto;
    }

    public static Factor getFactorFillingFullWhitoutLevels(
            Factor factorDto,
            AppServices appServices,
            Integer dmsaType) {
        log.info("Factor Name: " + factorDto.getFname() + " " + "  FactorId: " + factorDto.getFactorid());

//        Dmsattr dmsattrFilter = new Dmsattr(null, dmsaType, null, factorDto.getLabelid(), null);
//        factorDto.setDmsattr(appServices.getDmsattrByDmsatrecAndDmsatype(dmsattrFilter));
        log.info("Cargando Measuredin con tid: " + factorDto.getTraitid() + " scaleid: " + factorDto.getScaleid() + " tmethid: " + factorDto.getTmethid());
        Measuredin measuredin = appServices.getMeasuredinByTraitidScaleidTmethid(factorDto.getTraitid(), factorDto.getScaleid(), factorDto.getTmethid());
        factorDto.setMeasuredin(measuredin);

        return factorDto;
    }

    /**
     * Saves a FIXED Study factor in FACTOR table
     * @param study
     * @param appServices
     * @param serviceLocal
     * @return
     */
    public static Factor saveFactorStudy(
            Study study,
            AppServices appServices,
            CommonServices serviceLocal) {

        ibfb.domain.core.Condition condition = new Condition();

        condition.setConditionName("STUDY");
        condition.setDescription("STUDY");
        condition.setProperty("STUDY");
        condition.setScale("DBCV");
        condition.setMethod("ASSIGNED");
        condition.setDataType("C");
        condition.setValue("");
        condition.setLabel("STUDY");

        Factor factor = saveFactor(
                study,
                condition,
                0,
                'F',
                801,
                "FACTOR",
                appServices,
                serviceLocal);
        return factor;
    }

    public static Factor saveFactor(
            Study study,
            ibfb.domain.core.Condition condition,
            Integer factorCabecera,
            char traitsType,
            Integer dmsatype,
            String dmsatab,
            AppServices appServices,
            CommonServices serviceLocal) {

        TmsMethod tmsMethod;
        Scales scales;
        Traits traits = new Traits();
        Measuredin measuredin;
        Factor factor;
        Dmsattr dmsattr;

        //Verificar existencia de tmethod
        TmsMethod tmsMethodFilter = new TmsMethod(true);
        tmsMethodFilter.setTmname(condition.getMethod());
        List<TmsMethod> tmsMethodsList = appServices.getListTmsMethod(tmsMethodFilter, 0, 0, false);
        if (!tmsMethodsList.isEmpty()) {
            tmsMethod = tmsMethodsList.get(0);
        } else {
            tmsMethod = ConverterDomainToDTO.getTmsMethod(condition.getMethod());
            serviceLocal.addTmsMethod(tmsMethod);
        }

        //Verificar existencia de scales
        Scales scalesFilter = new Scales(true);
        scalesFilter.setScname(condition.getScale());
        List<Scales> scalesList = appServices.getListScales(scalesFilter, 0, 0, false);
        if (!scalesList.isEmpty()) {
            scales = scalesList.get(0);
        } else {
            scales = ConverterDomainToDTO.getScales(condition.getScale(), '-');
            serviceLocal.addScales(scales);
        }

        //Verificar existencia de traits
        Traits traitsFilter = new Traits(true);
        traitsFilter.setTrname(condition.getProperty());
        List<Traits> traitsList = appServices.getListTraitsOnly(traitsFilter, 0, 0, false);
        if (!traitsList.isEmpty()) {
            traits = traitsList.get(0);
            traits.setTid(appServices.getStoredInId(traits.getTraitid(), scales.getScaleid(), tmsMethod.getTmethid(), false));
        } else {
            traits = ConverterDomainToDTO.getTraits(condition.getProperty());
            traits.setTraittype(String.valueOf(traitsType));
            traits.setTid(appServices.getStoredInId(traits.getTraitid(), scales.getScaleid(), tmsMethod.getTmethid(), false));
            serviceLocal.addTraits(traits);
        }

        //TODO agregar algoritmo para determinacion del standard scale

        //Verificar existencia de measuredin
        Measuredin measuredinFilter = new Measuredin(true);
        measuredinFilter.setScaleid(scales.getScaleid());
        measuredinFilter.setTraitid(traits.getTraitid());
        measuredinFilter.setTmethid(tmsMethod.getTmethid());
        measuredinFilter.setStoredinid(traits.getTid());
        measuredinFilter.setName(condition.getConditionName());
        List<Measuredin> measuredinList = appServices.getListMeasuredin(measuredinFilter, 0, 0, false, false);
        if (!measuredinList.isEmpty()) {
            measuredin = measuredinList.get(0);
        } else {
            measuredin = ConverterDomainToDTO.getMeasuredin(traits, scales, scales.getScaleid(), tmsMethod,
                    condition.getConditionName(), condition.getDescription(), condition.getDataType());
            measuredin.setStoredinid(ChadoSchemaUtil.STUDY_VAR_TYPE);
            traits.setTid(measuredin.getStoredinid());
            serviceLocal.addMeasuredin(measuredin);
        }

        //Asignando el measuredin en el traits
        traits.setMeasuredin(measuredin);
        measuredin.setScales(scales);
        measuredin.setTmsMethod(tmsMethod);
        traits.setTid(measuredin.getStoredinid());
        
        //Verificar factor
        factor = ConverterDomainToDTO.getFactor(condition.getConditionName(), condition.getDescription(), condition.getDataType(), study, traits, tmsMethod);
        factor.setFactorid(factorCabecera);//Asignando el factorid
//        serviceLocal.addFactor(factor);

        //Verificar si es factor encabezado
        if (condition.getConditionName().equals(condition.getLabel())) {
            //factorCabecera = factor.getLabelid();
            //factor.setFactorid(factorCabecera);
            //serviceLocal.updateFactor(factor);
        }

        //Verificar dmsattr
        if (condition.getDescription() != null) {
//            dmsattr = ConverterDomainToDTO.getDmsattr(dmsatype, dmsatab, factor.getLabelid(), condition.getDescription());
//            serviceLocal.addDmsattr(dmsattr);
        }

        return factor;
    }

    public static void addLevels(
            Integer factirId,
            Integer levelNo,
            CommonServices serviciosLocal) {
        Levels levels = new Levels();
        levels.setFactorid(factirId);
        levels.setLevelno(levelNo);
        serviciosLocal.addLevels(levels);
    }

    /**
     * Save all LEVELS for TRIAL Section in template
     * @param mapTrials
     * @param conditionsData Conditions with all values to store
     * @param numberRepeticion Number of trials
     * @param levelNo
     * @param serviceLocal
     * @return
     */
    public static List<Integer> saveLavelsFactorTrials(
            Map mapTrials,
            List<Condition> conditionsData,
            Integer numberRepeticion,
            List<Integer> levelNos,
            CommonServices serviceLocal) {

        int instance = 0;
        //int levelNoTemporal = levelNo + 1;
        String nameFactorInitial = "";

        //Guardar los levels del trial

        if (conditionsData.size() > 0) {
            nameFactorInitial = conditionsData.get(0).getConditionName();
        }
//        levelNoTemporal++;
        Integer levelNo = null;
        for (Condition conditionData : conditionsData) {
            Factor factorTemp = (Factor) mapTrials.get(conditionData.getConditionName());
            /*
             if (nameFactorInitial.equals(conditionData.getConditionName())) {
             instance++;
             levelNoTemporal--;
             addLevels(factorTemp.getFactorid(), levelNoTemporal, serviceLocal);
             }
             */
            //System.out.println("Instance: " + instance + " levelNo: " + levelNo);
            log.info("Savin level for factor: " + conditionData.getConditionName() + "  with value: " + conditionData.getValue());
            if (conditionData.getDataType().equals(NUMERIC_TYPE)) {
                LevelN levelN = new LevelN();
                levelN.setFactorid(factorTemp.getFactorid());
                if (conditionData.getConditionName().equals(nameFactorInitial)) {
                    Integer tempInstance = conditionData.getInstance();
                    levelN.setLvalue(castingToDouble(tempInstance));
                    levelNo = serviceLocal.addNdGeolocation(tempInstance.toString());
                    levelNos.add(levelNo);
                } else {
                    if (conditionData.getValue() != null) {
                        levelN.setLvalue(castingToDouble(conditionData.getValue()));
                    }
                }
                LevelNPK levelNPK = new LevelNPK();
                levelNPK.setLabelid(factorTemp.getLabelid());
                levelNPK.setLevelno(levelNo);
                levelN.setLevelNPK(levelNPK);
                levelN.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelN(levelN);
                factorTemp.getLevelsN().add(levelN);
            } else {
                LevelC levelC = new LevelC();
                levelC.setFactorid(factorTemp.getFactorid());

                if (conditionData.getValue() != null) {
                    if (conditionData.getValue().toString().isEmpty()) {
                        levelC.setLvalue(" ");
                    } else {
                        levelC.setLvalue((String) conditionData.getValue());
                    }
                } else if (conditionData.getValue() == null) {
                    levelC.setLvalue(" ");
                }
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(factorTemp.getLabelid());
                levelCPK.setLevelno(levelNo);
                levelC.setLevelCPK(levelCPK);
                levelC.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelC(levelC);
                factorTemp.getLevelsC().add(levelC);
            }

            if (nameFactorInitial.equals(conditionData.getConditionName())) {
                //addLevels(factorTemp.getFactorid(), levelNoTemporal, serviceLocal);
            }

        }
        if (instance != numberRepeticion) {
            System.out.println("Error al comparar el numero de instancias");
        }

        //levelNo--;
        List<Integer> trialNdExperimentIds = new ArrayList<Integer>();
        Integer experimentId = KeyCacheUtil.getKey(TableEnum.EXPERIMENT);
        if (experimentId == null){
            experimentId = serviceLocal.getNextMin(TableEnum.EXPERIMENT.getName());
            experimentId++; //increment to get last used ID
        }
        for (Integer alevelNo : levelNos) {
                serviceLocal.addNdExperiment(--experimentId, alevelNo, 1020);
        	trialNdExperimentIds.add(experimentId);
        	System.out.println("saveLavelsFactorTrials - new ndExperimentId: "+ experimentId);
        }
        KeyCacheUtil.setKey(TableEnum.EXPERIMENT, experimentId);
        return trialNdExperimentIds;
         
    }

    public static Integer saveLavelsFactorTrialsOld(
            Map mapTrials,
            List<Condition> conditionsData,
            Integer numberRepeticion,
            Integer levelNo,
            CommonServices serviceLocal) {

        int instance = 0;
        int levelNoTemporal = levelNo + 1;
        String nameFactorInitial = "";

        //Guardar los levels del trial

        if (conditionsData.size() > 0) {
            nameFactorInitial = conditionsData.get(0).getConditionName();
        }
        //        levelNoTemporal++;
        for (Condition conditionData : conditionsData) {
            Factor factorTemp = (Factor) mapTrials.get(conditionData.getConditionName());

            if (nameFactorInitial.equals(conditionData.getConditionName())) {
                instance++;
                levelNoTemporal--;
                addLevels(factorTemp.getFactorid(), levelNoTemporal, serviceLocal);
            }
            System.out.println("Instance: " + instance + " levelNo: " + levelNoTemporal);
            log.info("Savin level for factor: " + conditionData.getConditionName() + "  with value: " + conditionData.getValue());
            if (conditionData.getDataType().equals(NUMERIC_TYPE)) {
                LevelN levelN = new LevelN();
                levelN.setFactorid(factorTemp.getFactorid());
                if (conditionData.getConditionName().equals(nameFactorInitial)) {
                    Integer tempInstance = conditionData.getInstance();
                    levelN.setLvalue(castingToDouble(tempInstance));
                } else {
                    if (conditionData.getValue() != null) {
                        levelN.setLvalue(castingToDouble(conditionData.getValue()));
                    }
                }
                LevelNPK levelNPK = new LevelNPK();
                levelNPK.setLabelid(factorTemp.getLabelid());
                levelNPK.setLevelno(levelNoTemporal);
                levelN.setLevelNPK(levelNPK);
                levelN.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelN(levelN);
                factorTemp.getLevelsN().add(levelN);
            } else {
                LevelC levelC = new LevelC();
                levelC.setFactorid(factorTemp.getFactorid());

                if (conditionData.getValue() != null) {
                    if (conditionData.getValue().toString().isEmpty()) {
                        levelC.setLvalue(" ");
                    } else {
                        levelC.setLvalue((String) conditionData.getValue());
                    }
                } else if (conditionData.getValue() == null) {
                    levelC.setLvalue(" ");
                }
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(factorTemp.getLabelid());
                levelCPK.setLevelno(levelNoTemporal);
                levelC.setLevelCPK(levelCPK);
                levelC.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelC(levelC);
                factorTemp.getLevelsC().add(levelC);
            }

            if (nameFactorInitial.equals(conditionData.getConditionName())) {
                //addLevels(factorTemp.getFactorid(), levelNoTemporal, serviceLocal);
            }

        }
        if (instance != numberRepeticion) {
            System.out.println("Error al comparar el numero de instancias");
        }

        levelNoTemporal--;
        return levelNoTemporal;
    }

    public static void saveLavelsFactors(
            Map mapTrials,
            List<ibfb.domain.core.Factor> factorsData,
            Integer numberRepeticion,
            CommonServices serviceLocal) {

        int instance = 0;
        String nameFactorInitial = "";
        if (factorsData.size() > 0) {
            nameFactorInitial = factorsData.get(0).getFactorName();
        }
        for (ibfb.domain.core.Factor factorDomain : factorsData) {
            Factor factorTemp = (Factor) mapTrials.get(factorDomain.getFactorName());

            if (nameFactorInitial.equals(factorDomain.getFactorName())) {
                instance++;
            }

            if (factorDomain.getDataType().equals(NUMERIC_TYPE)) {
                LevelN levelN = new LevelN();
                levelN.setFactorid(factorTemp.getFactorid());
                levelN.setLvalue((Double) factorDomain.getValue());
                LevelNPK levelNPK = new LevelNPK();
                levelNPK.setLabelid(factorTemp.getLabelid());
                levelNPK.setLevelno(instance);
                levelN.setLevelNPK(levelNPK);
                levelN.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelN(levelN);
                factorTemp.getLevelsN().add(levelN);
            } else {
                LevelC levelC = new LevelC();
                levelC.setFactorid(factorTemp.getFactorid());
                levelC.setLvalue((String) factorDomain.getValue());
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(factorTemp.getLabelid());
                levelCPK.setLevelno(instance);
                levelC.setLevelCPK(levelCPK);
                levelC.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelC(levelC);
                factorTemp.getLevelsC().add(levelC);
            }
        }
        if (instance != numberRepeticion) {
            System.out.println("Error al comparar el numero de instancias");
        }
    }

    public static void saveLavelsConstants(
            Map mapTrials,
            List<ibfb.domain.core.Constant> constantsData,
            Integer numberRepeticion,
            CommonServices serviceLocal) {

        int instance = 0;
        String nameFactorInitial = "";
        if (constantsData.size() > 0) {
            nameFactorInitial = constantsData.get(0).getConstantName();
        }
        for (ibfb.domain.core.Constant constantDomain : constantsData) {
            Factor factorTemp = (Factor) mapTrials.get(constantDomain.getConstantName());

            if (nameFactorInitial.equals(constantDomain.getConstantName())) {
                instance++;
            }

            if (constantDomain.getDataType().equals(NUMERIC_TYPE)) {
                LevelN levelN = new LevelN();
                levelN.setFactorid(factorTemp.getFactorid());
                levelN.setLvalue((Double) constantDomain.getValue());
                LevelNPK levelNPK = new LevelNPK();
                levelNPK.setLabelid(factorTemp.getLabelid());
                levelNPK.setLevelno(instance);
                levelN.setLevelNPK(levelNPK);
                levelN.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelN(levelN);
                factorTemp.getLevelsN().add(levelN);
            } else {
                LevelC levelC = new LevelC();
                levelC.setFactorid(factorTemp.getFactorid());
                levelC.setLvalue((String) constantDomain.getValue());
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(factorTemp.getLabelid());
                levelCPK.setLevelno(instance);
                levelC.setLevelCPK(levelCPK);
                levelC.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelC(levelC);
                factorTemp.getLevelsC().add(levelC);
            }
        }
        if (instance != numberRepeticion) {
            System.out.println("Error al comparar el numero de instancias");
        }
    }

    /**
     * Save list of entry (genotypes)
     * @param listEntryFactors
     * @param germplasmData
     * @param levelNo
     * @param serviceLocal
     * @return
     */
    public static int saveLavelsFactorsEntrys(
            List<Factor> listEntryFactors,
            List<List<Object>> germplasmData,
            List<Integer> ndExperimentIds,
            int index,
            //Integer levelNoNdGeolocationId,
            List<Integer> ndGeolocationIds,
            CommonServices serviceLocal,
            List<Measurement> measurementsRep) {

        Factor factorTemp = new Factor();
        boolean createNdExperiment = ndExperimentIds==null || ndExperimentIds.isEmpty();

        Map<String, Integer> stockMap = new HashMap<String, Integer>();
        
        long startTime = System.nanoTime();
        Integer stockId = KeyCacheUtil.getKey(TableEnum.STOCK);
        if (stockId == null){
            stockId = serviceLocal.getNextMin(TableEnum.STOCK.getName());
            stockId++; //increment to get last used ID
        }
        int firstStockId = stockId - 1;
        Map<Integer, StringBuffer> stockprops = new HashMap<Integer, StringBuffer>();
        
        //GCP NEW SCHEMA, create the stock and stockprop values
        StringBuffer uniquenames = new StringBuffer();
        StringBuffer dbxrefs = new StringBuffer();
        StringBuffer names = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (List<Object> objectList : germplasmData) {
        	String uniquename = null;
        	String dbxref_id = null;
        	String name = null;
        	String svalue = null;
                String stockpropValue = null;
        	for (int i = 0; i < listEntryFactors.size(); i++) {
        		factorTemp = listEntryFactors.get(i);
        		if(factorTemp.getTid().equals(new Integer(1041))) {
        			uniquename = castingToString(objectList.get(i));
                                if (uniquenames.length() > 0) {
                                    uniquenames.append(HelperWorkbook.DELIMITER);
                                }
                                uniquenames.append(uniquename);
        		} else if(factorTemp.getTid().equals(new Integer(1042))) {
        			dbxref_id = castingToString(objectList.get(i));
                                if (dbxrefs.length() > 0) {
                                    dbxrefs.append(HelperWorkbook.DELIMITER);
                                }
                                dbxrefs.append(dbxref_id);
        		} else if(factorTemp.getTid().equals(new Integer(1046))) {
        			name = castingToString(objectList.get(i));
                                if (names.length() > 0) {
                                    names.append(HelperWorkbook.DELIMITER);
                                }
                                names.append(name);
        		} else if(factorTemp.getTid().equals(new Integer(1047))) {
        			svalue = castingToString(objectList.get(i));
                                if (values.length() > 0) {
                                    values.append(HelperWorkbook.DELIMITER);
                                }
                                values.append(svalue);
        		} else if(factorTemp.getTid().equals(new Integer(1040))){
                                stockpropValue = castingToString(objectList.get(i));
                                Integer labelId = factorTemp.getLabelid();
                                StringBuffer sb = stockprops.get(labelId);
                                if (sb == null){
                                    sb = new StringBuffer();
                                    sb.append(stockpropValue);
                                    stockprops.put(labelId, sb);
                                } else {
                                    sb.append(HelperWorkbook.DELIMITER);
                                    sb.append(stockpropValue);
                                }
                        }    
        	}
        	//we need to add new stock for every new germplasm entry values
                //serviceLocal.addStock(--stockId, uniquename,dbxref_id,name,svalue);
//                int[] stockStoredIns = {1041, 1042, 1046, 1047};    
//                //creating stock prop records
//                for (int i = 0; i < objectList.size(); i++) {
//                    //we set the level no to the new stockId
//                    Integer levelNo = stockId;
//
//                    if (i < listEntryFactors.size()) {
//                        Object value = objectList.get(i);
//                        factorTemp = listEntryFactors.get(i);
////                        log.info("Saving level for ENTRY factor: " + factorTemp.getFname() + "  with value: " + value);
//                        
//                        int storedIn = factorTemp.getTid().intValue();
//                        
//                        if (!ArrayUtils.contains(stockStoredIns, storedIn)){
//                            if (factorTemp.getLtype().equals(NUMERIC_TYPE)) {
//                                LevelN levelN = new LevelN();
//                                levelN.setFactorid(factorTemp.getFactorid());
//
//                                levelN.setLvalue(castingToDouble(value));
//                                LevelNPK levelNPK = new LevelNPK();
//                                levelNPK.setLabelid(factorTemp.getLabelid());
//                                levelNPK.setLevelno(levelNo);
//                                levelN.setLevelNPK(levelNPK);
//                                levelN.setStoredinid(factorTemp.getTid());
//                                serviceLocal.addLevelN(levelN);
//                                factorTemp.getLevelsN().add(levelN);
//                            } else {
//                                LevelC levelC = new LevelC();
//                                levelC.setFactorid(factorTemp.getFactorid());
//                                String valueToSave = castingToString(value);
//                                if (valueToSave != null) {
//                                    if (valueToSave.trim().isEmpty()) {
//                                        levelC.setLvalue(" ");
//                                    } else {
//                                        levelC.setLvalue(valueToSave);
//                                    }
//                                } else {
//                                    levelC.setLvalue(" ");
//                                }
//                                LevelCPK levelCPK = new LevelCPK();
//                                levelCPK.setLabelid(factorTemp.getLabelid());
//                                levelCPK.setLevelno(levelNo);
//                                levelC.setLevelCPK(levelCPK);
//                                levelC.setStoredinid(factorTemp.getTid());
//                                serviceLocal.addLevelC(levelC);
//                                factorTemp.getLevelsC().add(levelC);
//                            }
//                        }
//                        
//                    }
//                }

                stockMap.put(uniquename, --stockId);
        }
        serviceLocal.addStocks(firstStockId, uniquenames.toString(), dbxrefs.toString(), names.toString(), values.toString());
        System.out.println("Elapsed time for saving stock: " + ((double)((System.nanoTime() - startTime)/1000000000)) + " sec");
        
        startTime = System.nanoTime();
        // add stockprop by batch
        for (Integer labelId : stockprops.keySet()){
            serviceLocal.addLevelsForFactor(labelId, 1040, stockprops.get(labelId).toString(), firstStockId);
        }
        KeyCacheUtil.setKey(TableEnum.STOCK, stockId);
        System.out.println("Elapsed time for saving stockprop: " + ((double)((System.nanoTime() - startTime)/1000000000)) + " sec");
        
        startTime = System.nanoTime();
        Integer experimentId = KeyCacheUtil.getKey(TableEnum.EXPERIMENT);
        Integer lastStockExpId = KeyCacheUtil.getKey(TableEnum.EXPERIMENT_STOCK);
        if (lastStockExpId == null){
            lastStockExpId = serviceLocal.getNextMin(TableEnum.EXPERIMENT_STOCK.getName());
            lastStockExpId++; // get last ID used
        }
        Integer firstStockExpId = lastStockExpId - 1;
        StringBuffer geolocationIdsStr = new StringBuffer();
        StringBuffer expStockStr = new StringBuffer();
        for (Measurement measurement : measurementsRep) {
            if (createNdExperiment){
                if (geolocationIdsStr.length() > 0) {
                    geolocationIdsStr.append(HelperWorkbook.DELIMITER);
                }
                geolocationIdsStr.append(ndGeolocationIds.get(measurement.getTrial() - 1));
                if (ndExperimentIds == null){
                    ndExperimentIds = new ArrayList<Integer>();
                }
                ndExperimentIds.add(--experimentId);
            } 
            
            String entryNo = measurement.getEntry().toString();
            int levelNoStockId = stockMap.get(entryNo);

           if (expStockStr.length() > 0){
               expStockStr.append(HelperWorkbook.DELIMITER);
           }
           expStockStr.append(levelNoStockId);
           --lastStockExpId;
        }
        if (createNdExperiment){
            serviceLocal.addExperiments(experimentId-1, geolocationIdsStr.toString());
        }
        serviceLocal.addExperimentStocks(firstStockExpId, ndExperimentIds.get(0), expStockStr.toString());
        System.out.println("Elapsed time for experiment_stocks:" + ((double)((System.nanoTime() - startTime)/1000000000)) + " sec");
        KeyCacheUtil.setKey(TableEnum.EXPERIMENT, experimentId);
        KeyCacheUtil.setKey(TableEnum.EXPERIMENT_STOCK, lastStockExpId);
        //return levelNo;
        return index;
    }

    public static Integer saveLavelsFactorsEntrysOld(
            List<Factor> listEntryFactors,
            List<List<Object>> germplasmData,
            Integer levelNo,
            CommonServices serviceLocal) {
        Factor factorTemp = new Factor();
        for (List<Object> objectList : germplasmData) {
            for (int i = 0; i < objectList.size(); i++) {
                if (i < listEntryFactors.size()) {
                    Object value = objectList.get(i);
                    factorTemp = listEntryFactors.get(i);
                    log.info("Saving level for ENTRY factor: " + factorTemp.getFname() + "  with value: " + value);
                    if (factorTemp.getLtype().equals(NUMERIC_TYPE)) {
                        LevelN levelN = new LevelN();
                        levelN.setFactorid(factorTemp.getFactorid());

                        levelN.setLvalue(castingToDouble(value));
                        LevelNPK levelNPK = new LevelNPK();
                        levelNPK.setLabelid(factorTemp.getLabelid());
                        levelNPK.setLevelno(levelNo);
                        levelN.setLevelNPK(levelNPK);
                        levelN.setStoredinid(factorTemp.getTid());
                        serviceLocal.addLevelN(levelN);
                        factorTemp.getLevelsN().add(levelN);
                    } else {
                        LevelC levelC = new LevelC();
                        levelC.setFactorid(factorTemp.getFactorid());
                        String valueToSave = castingToString(value);
                        if (valueToSave != null) {
                            if (valueToSave.trim().isEmpty()) {
                                levelC.setLvalue(" ");
                            } else {
                                levelC.setLvalue(valueToSave);
                            }
                        } else {
                            levelC.setLvalue(" ");
                        }
                        LevelCPK levelCPK = new LevelCPK();
                        levelCPK.setLabelid(factorTemp.getLabelid());
                        levelCPK.setLevelno(levelNo);
                        levelC.setLevelCPK(levelCPK);
                        levelC.setStoredinid(factorTemp.getTid());
                        serviceLocal.addLevelC(levelC);
                        factorTemp.getLevelsC().add(levelC);
                    }
                }
            }
            addLevels(factorTemp.getFactorid(), levelNo, serviceLocal);
            levelNo--;
        }
        return levelNo;
    }

    /**
     * Save one record into LEVEL_C and LEVEL_N according to DATATYPE
     * @param factor FACTOR DTO to extract LABELID and FACTORID values
     * @param levelNo Deep of level for
     * @param value current value for LVALUE column
     * @param serviceLocal Service to manage local database
     */
    public static void saveLevel(Factor factor,
            Integer levelNo,
            Object value,
            CommonServices serviceLocal) {

        if (factor != null && factor.getLtype() != null) {
            if (factor.getLtype().equals(NUMERIC_TYPE)) {
                LevelN levelN = new LevelN();
                levelN.setFactorid(factor.getFactorid());
                levelN.setLvalue(castingToDouble(value));
                LevelNPK levelNPK = new LevelNPK();
                levelNPK.setLabelid(factor.getLabelid());
                levelNPK.setLevelno(levelNo);
                levelN.setLevelNPK(levelNPK);
                levelN.setStoredinid(factor.getTid());
                serviceLocal.addLevelN(levelN);
                factor.getLevelsN().add(levelN);
            } else {
                LevelC levelC = new LevelC();
                levelC.setFactorid(factor.getFactorid());
                levelC.setLvalue(castingToString(value));
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(factor.getLabelid());
                levelCPK.setLevelno(levelNo);
                levelC.setLevelCPK(levelCPK);
                levelC.setStoredinid(factor.getTid());
                serviceLocal.addLevelC(levelC);
                factor.getLevelsC().add(levelC);
            }
        }
    }

    private static boolean isInteger(Object value) {
        try {
            Integer valor = (Integer) value;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isString(Object value) {
        try {
            String valor = (String) value;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isDouble(Object value) {
        try {
            Double valor = (Double) value;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Double castingToDouble(Object value) {
        if (value == null) {
            return new Double("0");
        }
        if (isInteger(value)) {
            Integer valueTemp = (Integer) value;
            return new Double(valueTemp.toString());
        } else if (isDouble(value)) {
            return (Double) value;
        } else if (isString(value)) {
            String valueTemp = (String) value;
            if (valueTemp.trim().isEmpty()) {
                return new Double("0");
            } else {
                return new Double(valueTemp);
            }
        } else {
            System.out.println("Error tipo no reconocido para casting");
            return new Double("0");
        }
    }

    public static String castingToString(Object value) {
        if (isInteger(value)) {
            Integer valueTemp = (Integer) value;
            if (valueTemp != null) {
                return valueTemp.toString();
            } else {
                return " ";
            }
        } else if (isDouble(value)) {
            return value.toString();
        } else if (isString(value)) {
            return (String) value;
        } else {
            System.out.println("Error tipo no reconocido para casting");
            return " ";
        }
    }

    public static void saveLavelsTrialsGermoplasm(
            Map mapTrials,
            List<Condition> conditionsData,
            Integer numberRepeticion,
            CommonServices serviceLocal) {

        int instance = 0;
        String nameFactorInitial = "";
        if (conditionsData.size() > 0) {
            nameFactorInitial = conditionsData.get(0).getConditionName();
        }
        for (Condition conditionData : conditionsData) {
            Factor factorTemp = (Factor) mapTrials.get(conditionData.getConditionName());

//            if(nameFactorInitial.equals(conditionData.getConditionName())){
//                instance++;
//            }

            if (conditionData.getDataType().equals(NUMERIC_TYPE)) {
                LevelN levelN = new LevelN();
                levelN.setFactorid(factorTemp.getFactorid());
                levelN.setLvalue((Double) conditionData.getValue());
                LevelNPK levelNPK = new LevelNPK();
                levelNPK.setLabelid(factorTemp.getLabelid());
                levelNPK.setLevelno(conditionData.getInstance());
                levelN.setLevelNPK(levelNPK);
                levelN.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelN(levelN);
                factorTemp.getLevelsN().add(levelN);
            } else {
                LevelC levelC = new LevelC();
                levelC.setFactorid(factorTemp.getFactorid());
                levelC.setLvalue((String) conditionData.getValue());
                LevelCPK levelCPK = new LevelCPK();
                levelCPK.setLabelid(factorTemp.getLabelid());
                levelCPK.setLevelno(conditionData.getInstance());
                levelC.setLevelCPK(levelCPK);
                levelC.setStoredinid(factorTemp.getTid());
                serviceLocal.addLevelC(levelC);
                factorTemp.getLevelsC().add(levelC);
            }
        }
        if (instance != numberRepeticion) {
            System.out.println("Error al comparar el numero de instancias");
        }
    }

    public static List<Factor> getFactorsByEffectid(Integer effectId, AppServices servicios) {
        List<Integer> effectIds = new ArrayList<Integer>();
        effectIds.add(effectId);
        List<Effect> effects = servicios.getEffectsByEffectsids(effectIds);
        List<Integer> factorsIds = new ArrayList<Integer>();
        for(Effect effect : effects){
            factorsIds.add(effect.getEffectPK().getFactorid());
        }
        List<Factor> factors = servicios.getFactorsByFactorsids(factorsIds);
        return factors;
    }

    public static List<Integer> getTrialsForStudyid(
            AppServices servicios,
            Integer studyId) {
        Factor factorTrial = null;

        List<Factor> factors = servicios.getMainFactorsByStudyid(studyId);
        for(Factor factor : factors){
            factor = HelperFactor.getFactorFillingFull(factor, servicios, 801);
            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
            if( Workbook.TRIAL_INSTANCE_NUMBER.equals( Workbook.getStringWithOutBlanks( traitScale ) ) ){
                factorTrial = factor;
                break;
            }
        }

        List<Integer> instancias = new ArrayList<Integer>();
        if(factorTrial != null){
            for (LevelN levelN : factorTrial.getLevelsN()) {
                instancias.add(levelN.getLvalue().intValue());
            }
        } else {
            log.error("Factor trial not found.");
        }
        return instancias;
    }

    public static StudySearch loadFactors(StudySearch studySearch, AppServices appServices) {
        List<Factor> factors = appServices.getMainFactorsByStudyid(studySearch.getStudyId());
        Factor factorEntry = null;
        for (Factor factor : factors) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);
            String trait = factor.getMeasuredin().getTraits().getTrname();
            String traitScale = trait + factor.getMeasuredin().getScales().getScname();
            if (Workbook.STUDY_TITLE.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                studySearch.setNameStudy(factor.getFname());
            } else if (Workbook.TRIAL_INSTANCE_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                studySearch.setNameTrial(factor.getFname());
            } else if (Workbook.GERMPLASM_ENTRY_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                factorEntry = factor;
                studySearch.setNameEntry(factor.getFname());
            } else if (Workbook.FIELD_PLOT_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))
                    || Workbook.FIELD_PLOT_NESTED_NUMBER.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                studySearch.setNamePlot(factor.getFname());
            }
        }
        List<Factor> factorsEntry = new ArrayList<Factor>();
        if (factorEntry != null) {
            factorsEntry = appServices.getGroupFactorsByStudyidAndFactorid(studySearch.getStudyId(), factorEntry.getFactorid());
        }
        for (Factor factor : factorsEntry) {
            factor = HelperFactor.getFactorFillingFullWhitoutLevels(factor, appServices, 801);

            String traitScale = factor.getMeasuredin().getTraits().getTrname() + factor.getMeasuredin().getScales().getScname();
            if (Workbook.GERMPLASM_ID_DBID.equals(Workbook.getStringWithOutBlanks(traitScale))) {
                studySearch.setNameGid(factor.getFname());
            }
        }
        return studySearch;
    }
}