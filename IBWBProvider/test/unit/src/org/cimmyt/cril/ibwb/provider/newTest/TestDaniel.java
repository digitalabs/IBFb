/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.newTest;

import java.util.ArrayList;
import java.util.List;

import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.provider.dao.UtilityDAO;

import javax.swing.*;

/**
 *
 * @author TMSANCHEZ
 */
public class TestDaniel extends TestService {
    

    public void testGetMinMax(){
        Obsunit o = new Obsunit();
        o.setEffectid(55);
        servicios.getLocalCommonService().addObsunit(o);
    }

    public void testAddLevelN(){

        Integer stockId = servicios.getLocalCommonService().addStock();
        System.out.println(stockId);
    }

    public void testTmsMethodList(){
        List<TmsMethod> tmsMethodList = servicios.getCentralCommonService().getTmsMethodList();
        for(int i = 0 ; i < tmsMethodList.size() ; i++){
            TmsMethod tmsMethod = tmsMethodList.get(i);
            System.out.println(tmsMethod.getTmname());
        }
    }

    public static void main(String[] args) {
        TestDaniel tl = new TestDaniel();

        //tl.testGetMinMax();
        //tl.testTmsMethodList();
        tl.testAddLevelN();
    }
}
