/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.newTest;

import ibfb.domain.core.Measurement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.LevelC;
import org.cimmyt.cril.ibwb.domain.LevelCPK;
import org.cimmyt.cril.ibwb.domain.LevelN;
import org.cimmyt.cril.ibwb.domain.LevelNPK;
import org.cimmyt.cril.ibwb.domain.Measuredin;

/**
 *
 * @author tippsgo
 */
public class TestTrialRandomization extends TestService {
    
    public void testGetTrialRandomizationVeryFast() throws Exception {
        System.out.println("testGetTrialRandomizationVeryFast");
        int studyid = 5739;  //represNo = 5798
        int trialFactorId = 0;
        List<String> factoresPrincipales = Arrays.asList("SETNO", "ENTNO", "PLOTNO");
        List<String> factoresSalida = Arrays.asList("SETNO", "ENTNO", "PLOTNO");
        String nombreTrial = "";
        List<Measurement> measurementList = 
                servicios.getCentralCommonService()
                    .getTrialRandomizationVeryFast(
                            studyid
                            , trialFactorId
                            , factoresPrincipales
                            , factoresSalida
                            , nombreTrial);
        
        if (measurementList != null && measurementList.size() > 0) {
            for (Measurement measurement : measurementList) {
                System.out.println(measurement);
            }
        } else {
            System.out.println("No measurement for getTrialRandomizationVeryFast");
        }
    }
    
    public void testGetTrialRandomization() {
        System.out.println("testGetTrialRandomization");
        int studyid = 5739;  //represNo = 5798
        int trialFactorId = 0;
        List<String> factoresPrincipales = Arrays.asList("SETNO");
        List<String> factoresSalida = Arrays.asList("SETNO");
        //List<String> factoresPrincipales = Arrays.asList("SETNO", "ENTNO", "PLOTNO");
        //List<String> factoresSalida = Arrays.asList("SETNO", "ENTNO", "PLOTNO");
        String nombreTrial = "";
        ResultSet rs = 
                servicios.getCentralCommonService()
                    .getTrialRandomization(
                            studyid
                            , trialFactorId
                            , factoresPrincipales
                            , factoresSalida
                            , nombreTrial);
        
        if (rs != null) {
            try {
                while (rs.next()) {
                    for (String columnName : factoresSalida) {
                        System.out.println(rs.getString(columnName));
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No ResultSet for getTrialRandomization");
        }
    }
    
    public void testGetScaleDisByMeasuredIn() {
        System.out.println(servicios.getCentralCommonService().getScaleDisByMeasuredinId(8));
    }
    
    public void testGetScaleConByMeasuredIn() {
        System.out.println(servicios.getCentralCommonService().getScaleConByMeasuredinId(8));
    }
    
    public void testGtMeasuredinByTraitidScaleidTmethid() {
        Measuredin measuredin = new Measuredin();
        measuredin.setTraitid(2010);
        measuredin.setScaleid(6000);
        measuredin.setTmethid(4030);
        System.out.println(servicios.getCentralCommonService().getMeasuredinByTraitidScaleidTmethid(measuredin));
    }
    
    public void testGetListLevelC() {
        System.out.println("testGetListLevelC");
        LevelC levelC = new LevelC(true);
        levelC.setLevelCPK(new LevelCPK());
        levelC.setFactorid(517468);
        List<LevelC> list = servicios.getCentralCommonService().getListLevelC(levelC, 1, 10, true);
        if (list != null && list.size() > 0) {
            System.out.println("SIZE : " + list.size());
            for (LevelC level : list) {
                System.out.println(level);
            }
        } else {
            System.out.println("NO LEVEL C DATA FOUND");
        }
    }
    
    public void testGetListLevelN() {
        System.out.println("testGetListLevelN");
        LevelN levelN = new LevelN(true);
        levelN.setLevelNPK(new LevelNPK());
        levelN.setFactorid(517468);
        List<LevelN> list = servicios.getCentralCommonService().getListLevelN(levelN, 0, 10, true);
        if (list != null && list.size() > 0) {
            System.out.println("SIZE : " + list.size());
            for (LevelN level : list) {
                System.out.println(level);
            }
        } else {
            System.out.println("NO LEVEL N DATA FOUND");
        }
    }

    public void testGetLevelsCByLabelid() {
        System.out.println("testGetLevelsCByLabelid");
        List<LevelC> list = servicios.getCentralCommonService().getLevelsCByLabelid(10012);
        if (list != null && list.size() > 0) {
            System.out.println("SIZE : " + list.size());
            for (LevelC level : list) {
                System.out.println(level);
            }
        } else {
            System.out.println("NO LEVEL C DATA FOUND");
        }
    }

    public void testGetLevelsNByLabelid() {
        System.out.println("testGetLevelsNByLabelid");
        List<LevelN> list = servicios.getCentralCommonService().getLevelnByLabelid(10016);
        if (list != null && list.size() > 0) {
            System.out.println("SIZE : " + list.size());
            for (LevelN level : list) {
                System.out.println(level);
            }
        } else {
            System.out.println("NO LEVEL N DATA FOUND");
        }
    }

    public static void main(String[] args) {
        try {
            TestTrialRandomization test = new TestTrialRandomization();

            //test.testGetTrialRandomizationVeryFast();
            //test.testGetTrialRandomization();
            //test.testGetScaleDisByMeasuredIn();
            //test.testGetScaleConByMeasuredIn();
            //test.testGtMeasuredinByTraitidScaleidTmethid();
            //test.testGetListLevelC();
            //test.testGetListLevelN();
            //test.testGetLevelsCByLabelid();
            //test.testGetLevelsNByLabelid();
            

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
