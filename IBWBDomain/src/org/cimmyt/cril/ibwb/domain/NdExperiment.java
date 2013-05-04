package org.cimmyt.cril.ibwb.domain;

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
@Table(name = "nd_experiment")
public class NdExperiment extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_experiment_id")
    private Integer ndexperimentid;
    @Basic(optional = false)
    @Column(name = "nd_geolocation_id")
    private Integer ndgeolocationid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    
    public NdExperiment() {
    	setDefault();
    }

    public NdExperiment(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdexperimentid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdExperiment other = (NdExperiment) obj;
        if (this.getNdexperimentid() != other.getNdexperimentid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdexperimentid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.NdExperiment[ndexperimentPK=" + getNdexperimentid() + "]";
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
     * @return the ndgeolocationid
     */
    public Integer getNdgeolocationid() {
        return ndgeolocationid;
    }

    /**
     * @param ndgeolocationid the ndgeolocationid to set
     */
    public void setNdgeolocationid(Integer ndgeolocationid) {
        this.ndgeolocationid = ndgeolocationid;
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
    
    

}
