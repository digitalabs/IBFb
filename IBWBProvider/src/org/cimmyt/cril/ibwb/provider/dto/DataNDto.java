package org.cimmyt.cril.ibwb.provider.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Efficio.Daniel
 * Date: 5/9/13
 * Time: 7:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataNDto {
    //for response
    private Double value;
    private Integer ounitid;
    private Integer variatid;

    //for query
    private Integer effectid;
    private Integer central;

    public DataNDto(){

    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getOunitid() {
        return ounitid;
    }

    public void setOunitid(Integer ounitid) {
        this.ounitid = ounitid;
    }

    public Integer getVariatid() {
        return variatid;
    }

    public void setVariatid(Integer variatid) {
        this.variatid = variatid;
    }

    public Integer getEffectid() {
        return effectid;
    }

    public void setEffectid(Integer effectid) {
        this.effectid = effectid;
    }

    public Integer getCentral() {
        return central;
    }

    public void setCentral(Integer central) {
        this.central = central;
    }
}
