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
@Table(name = "nd_experiment_phenotype")
public class NdExperimentPhenotype extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_experiment_phenotype_id")
    private Integer ndexperimentphenotypeid;
    @Basic(optional = false)
    @Column(name = "nd_experiment_id")
    private Integer ndexperimentid;
    @Basic(optional = false)
    @Column(name = "phenotype_id")
    private Integer phenotypeid;

    public NdExperimentPhenotype() {
    	setDefault();
    }

    public NdExperimentPhenotype(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdexperimentphenotypeid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdExperimentPhenotype other = (NdExperimentPhenotype) obj;
        if (this.getNdexperimentphenotypeid() != other.getNdexperimentphenotypeid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdexperimentphenotypeid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.NdExperimentPhenotype[ndexperimentphenotypePK=" + getNdexperimentphenotypeid() + "]";
    }

    /**
     * @return the ndexperimentphenotypeid
     */
    public Integer getNdexperimentphenotypeid() {
        return ndexperimentphenotypeid;
    }

    /**
     * @param ndexperimentphenotypeid the ndexperimentphenotypeid to set
     */
    public void setNdexperimentphenotypeid(Integer ndexperimentphenotypeid) {
        this.ndexperimentphenotypeid = ndexperimentphenotypeid;
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
     * @return the phenotypeid
     */
    public Integer getPhenotypeid() {
        return phenotypeid;
    }

    /**
     * @param phenotypeid the phenotypeid to set
     */
    public void setPhenotypeid(Integer phenotypeid) {
        this.phenotypeid = phenotypeid;
    }
    
    

}
