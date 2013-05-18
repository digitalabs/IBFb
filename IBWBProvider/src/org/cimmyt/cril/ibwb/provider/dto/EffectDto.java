package org.cimmyt.cril.ibwb.provider.dto;

/**
 * Created with IntelliJ IDEA.
 * User: DanielV
 * Date: 5/10/13
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class EffectDto {
    private Integer represNo;
    private Integer factorId;
    private Integer effectId;

    public EffectDto() {
    }

    public EffectDto(Integer represNo, Integer factorId, Integer effectId) {
        this.represNo = represNo;
        this.factorId = factorId;
        this.effectId = effectId;
    }

    public Integer getRepresNo() {
        return represNo;
    }

    public void setRepresNo(Integer represNo) {
        this.represNo = represNo;
    }

    public Integer getFactorId() {
        return factorId;
    }

    public void setFactorId(Integer factorId) {
        this.factorId = factorId;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }
}
