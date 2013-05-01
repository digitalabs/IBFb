package org.cimmyt.cril.ibwb.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author mturiana
 */
@Entity
@Table(name = "nd_experimentprop")
public class NdExperimentprop extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_experimentprop_id")
    private Integer ndexperimentpropid;
    @Basic(optional = false)
    @Column(name = "nd_experiment_id")
    private Integer ndexperimentid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    @Basic(optional = true)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "rank")
    private Integer rank;

    public NdExperimentprop() {
    	setDefault();
    }

    public NdExperimentprop(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdexperimentpropid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdExperimentprop other = (NdExperimentprop) obj;
        if (this.getNdexperimentpropid() != other.getNdexperimentpropid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdexperimentpropid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.NdExperimentprop[ndexperimentpropPK=" + getNdexperimentpropid() + "]";
    }

    /**
     * @return the ndexperimentpropid
     */
    public Integer getNdexperimentpropid() {
        return ndexperimentpropid;
    }

    /**
     * @param ndexperimentpropid the ndexperimentpropid to set
     */
    public void setNdexperimentpropid(Integer ndexperimentpropid) {
        this.ndexperimentpropid = ndexperimentpropid;
    }

    /**
     * @return the ndexperimentid
     */
    public Integer getNdexperimentid() {
        return ndexperimentid;
    }

    /**
     * @param ndexperimentid the ndexperimentid to set
     */
    public void setNdexperimentid(Integer ndexperimentid) {
        this.ndexperimentid = ndexperimentid;
    }

    /**
     * @return the typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * @param typeid the typeid to set
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    
}
