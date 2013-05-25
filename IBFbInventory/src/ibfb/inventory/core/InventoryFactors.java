/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.inventory.core;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import ibfb.domain.core.Factor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TMSANCHEZ
 */
public class InventoryFactors {

    public static List<Factor> getInventoryFactors() {
        List<Factor> inventoryFactors = new ArrayList<Factor>();

        Factor factor = new Factor();
        factor.setFactorName("LOCATION");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        inventoryFactors.add(factor);

        factor = new Factor();
        factor.setFactorName("COMMENT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        inventoryFactors.add(factor);

        factor = new Factor();
        factor.setFactorName("AMOUNT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        inventoryFactors.add(factor);


        factor = new Factor();
        factor.setFactorName("SCALE");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        inventoryFactors.add(factor);

        return inventoryFactors;
    }

    public static List<Factor> getGermplasmAndInventoryFactors() {
        List<Factor> factors = new ArrayList<Factor>();

        Factor factor = new Factor();
        factor.setFactorName("ENTRY");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("DESIGNATION");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBCV");
        factor.setDataType("C");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("GID");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBID");
        factor.setDataType("N");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("LOCATION");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("COMMENT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("AMOUNT");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factors.add(factor);


        factor = new Factor();
        factor.setFactorName("SCALE");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("TEXT");
        factor.setDataType("C");
        factors.add(factor);

        return factors;
    }

    public static List<Factor> getGermplasmFactors() {
        List<Factor> factors = new ArrayList<Factor>();

        Factor factor = new Factor();
        factor = new Factor();
        factor.setFactorName("GID");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBID");
        factor.setDataType("N");
        factors.add(factor);


        factor = new Factor();
        factor.setFactorName("DESIGNATION");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBCV");
        factor.setDataType("C");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("ENTRY CD");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("SOURCE");
        factor.setProperty("SEED SOURCE");
        factor.setScale("NAME");
        factor.setDataType("C");
        factors.add(factor);

        factor = new Factor();
        factor.setFactorName("CROSS");
        factor.setProperty("CROSS NAME");
        factor.setScale("NAME");
        factor.setDataType("C");
        factors.add(factor);

        
        factor = new Factor();
        factor.setFactorName("ENTRY ID");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setDataType("N");
        factors.add(factor);






        return factors;
    }
}
