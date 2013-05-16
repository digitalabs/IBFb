/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.newTest;

import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.cimmyt.cril.ibwb.domain.Obsunit;
import org.cimmyt.cril.ibwb.provider.dao.UtilityDAO;

/**
 *
 * @author TMSANCHEZ
 */
public class TestDaniel extends TestService {
    

    public void testGetMinMax(){
        Obsunit o = new Obsunit();
        servicios.getLocalCommonService().addObsunit(o);
    }

    public static void main(String[] args) {
        TestDaniel tl = new TestDaniel();
//        tl.testAddListnms();
//        tl.testAddNewsGermplasm();
        tl.testGetMinMax();
    }
}
