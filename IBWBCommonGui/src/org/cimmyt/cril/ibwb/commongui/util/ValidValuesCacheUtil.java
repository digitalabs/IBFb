/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui.util;

import java.util.Map;
import java.util.Set;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import static org.cimmyt.cril.ibwb.domain.util.ValidValuesCache.*;

/**
 *  Cache for categorical values.
 */
public class ValidValuesCacheUtil {
    
    //use this method only if the data type is unidentified, usually for new measurements
    public static boolean isValidValue(String property, String scale, String method, String value) {
        if (value != null && !"".equals(value)) {
            Integer varId = getVariateId(property, scale, method);
            if (varId != null) { //for new variables, there will be no categorical values defined yet.
                if (getCategoricalValues(varId) != null) {
                    return checkCategoricalValue(property, scale, method, value);
                }
                else if (getNumericRange(varId) != null && isNumeric(value)) {
                    return checkNumericRange(property, scale, method, value);
                }

                Integer dataType = AppServicesProxy.getDefault().appServices().getObjectInRelationship(varId, 1105);
                if (dataType != null) {
                    if (dataType == 1130) {
                        return checkCategoricalValue(property, scale, method, value);
                    }
                    else if (dataType == 1110 && isNumeric(value)) {
                        return checkNumericRange(property, scale, method, value);
                    }
                }
            }
        }
        return true;
    }
    
    public static boolean checkCategoricalValue(String property, String scale, String method, String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        Integer varId = getVariateId(property, scale, method);
        if (varId != null) {
            Set<String> validValues = getCategoricalValues(varId);
            if (validValues == null) {
                AppServicesProxy.getDefault().appServices();
                Map<String, Integer> validValuesMap = AppServicesProxy.getDefault().appServices()
                                                            .getValidValues(varId);
                validValues = validValuesMap.keySet();
                putCategoricalValues(varId, validValues);
                for (String aValue : validValues) {
                    putValueId(varId, aValue, validValuesMap.get(aValue));
                }
            }
            if (validValues != null) {
                for (String validValue : validValues) {
                    if (validValue.equalsIgnoreCase(value)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkNumericRange(String property, String scale, String method, String value) {
        if (value != null && !value.isEmpty()) {
            if (!isNumeric(value)) {
                return false;
            }
            Integer varId = getVariateId(property, scale, method);
            if (varId == null) {
                return false;
            }
            Double[] range = getNumericRange(varId);
            if (range == null) {
                range = AppServicesProxy.getDefault().appServices().getNumericRange(varId);
                putNumericRange(varId, range);
            }
            Double numericValue = Double.parseDouble(value);
            if (range != null && range[0] != null && range[1] != null) {
                return range[0].doubleValue() <= numericValue && range[1].doubleValue() >= numericValue;
            } //else no range is defined
        }
        return true;
    }
    
    private static boolean isNumeric(String value) {
        return value.matches("[-+]?\\d*\\.?\\d+");
    }
    
    private static Integer getVariateId(String property, String scale, String method) {
        Integer varId = getVariateIdByPsm(property, scale, method);
        if (varId == null) {
            varId = AppServicesProxy.getDefault().appServices().getVariableIdByPSM(property, scale, method);
            putVariateIdByPsm(property, scale, method, varId);
        }
        return varId;
    }
}
