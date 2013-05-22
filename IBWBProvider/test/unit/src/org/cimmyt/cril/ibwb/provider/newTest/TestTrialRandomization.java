/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.newTest;

import ibfb.domain.core.Measurement;
import java.util.Arrays;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.TmsMethod;

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
    
   public static void main(String[] args) {
        try {
            TestTrialRandomization test = new TestTrialRandomization();

            test.testGetTrialRandomizationVeryFast();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
