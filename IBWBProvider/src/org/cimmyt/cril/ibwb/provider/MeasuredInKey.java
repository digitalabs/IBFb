/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

/**
 *
 * @author tippsgo
 */
public class MeasuredInKey {
    private Integer traitId;
    private Integer scaleId;
    private Integer methodId;
    
    public MeasuredInKey(Integer traitId, Integer scaleId, Integer methodId) {
        this.traitId = traitId;
        this.scaleId = scaleId;
        this.methodId = methodId;
    }

    public Integer getTraitId() {
        return traitId;
    }

    public void setTraitId(Integer traitId) {
        this.traitId = traitId;
    }

    public Integer getScaleId() {
        return scaleId;
    }

    public void setScaleId(Integer scaleId) {
        this.scaleId = scaleId;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MeasuredInKey other = (MeasuredInKey) obj;
        if (this.traitId != other.traitId && (this.traitId == null || !this.traitId.equals(other.traitId))) {
            return false;
        }
        if (this.scaleId != other.scaleId && (this.scaleId == null || !this.scaleId.equals(other.scaleId))) {
            return false;
        }
        if (this.methodId != other.methodId && (this.methodId == null || !this.methodId.equals(other.methodId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.traitId != null ? this.traitId.hashCode() : 0);
        hash = 61 * hash + (this.scaleId != null ? this.scaleId.hashCode() : 0);
        hash = 61 * hash + (this.methodId != null ? this.methodId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "MeasuredInKey{" + "traitId=" + traitId + ", scaleId=" + scaleId + ", methodId=" + methodId + '}';
    }
    
    
}
