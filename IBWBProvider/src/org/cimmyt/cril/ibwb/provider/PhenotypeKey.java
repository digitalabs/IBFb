/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

/**
 *
 * @author tippsgo
 */
public class PhenotypeKey {
    private Integer variateId;
    private Integer experimentId;

    
    public PhenotypeKey(Integer variateId, Integer experimentId) {
        this.variateId = variateId;
        this.experimentId = experimentId;
    }

    public Integer getVariateId() {
        return variateId;
    }

    public void seVariateId(Integer variateId) {
        this.variateId = variateId;
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhenotypeKey other = (PhenotypeKey) obj;
        if (this.variateId != other.variateId && (this.variateId == null || !this.variateId.equals(other.variateId))) {
            return false;
        }
        if (this.experimentId != other.experimentId && (this.experimentId == null || !this.experimentId.equals(other.experimentId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.variateId != null ? this.variateId.hashCode() : 0);
        hash = 71 * hash + (this.experimentId != null ? this.experimentId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PhenotypeKey{" + "variateId=" + variateId + ", experimentId=" + experimentId +  '}';
    }
    
    
}
