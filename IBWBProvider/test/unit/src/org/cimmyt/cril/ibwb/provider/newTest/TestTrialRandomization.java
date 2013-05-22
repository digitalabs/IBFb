/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.newTest;

import ibfb.domain.core.Measurement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

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
        List<String> factoresPrincipales = Arrays.asList("SETNO", "ENTNO", "PLOTNO");
        List<String> factoresSalida = Arrays.asList("SETNO", "ENTNO", "PLOTNO");
        String nombreTrial = "";
        ResultSet measurementList = 
                servicios.getCentralCommonService()
                    .getTrialRandomization(
                            studyid
                            , trialFactorId
                            , factoresPrincipales
                            , factoresSalida
                            , nombreTrial);
        
        if (measurementList != null) {
            try {
                while (measurementList.next()) {
                    System.out.println(measurementList.getString(0));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No ResultSet for getTrialRandomization");
        }
    }
    
    public static void main(String[] args) {
        try {
            TestTrialRandomization test = new TestTrialRandomization();

            //test.testGetTrialRandomizationVeryFast();
            test.testGetTrialRandomization();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
