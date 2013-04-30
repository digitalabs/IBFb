/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ace.test;

import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Location;

/**
 *
 * @author Pioneer4007
 */
public class Test {
    public static void main(String[] args){
     
         List< Location> locationList = AppServicesProxy.getDefault().appServices().getLocationList();
         for (Location location : locationList) {
              System.out.println(location.getLname()+" - "+location.getLocid());
         }
         
         
    }
}
