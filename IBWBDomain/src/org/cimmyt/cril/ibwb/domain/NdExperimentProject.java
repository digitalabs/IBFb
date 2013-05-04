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
@Table(name = "nd_experiment_project")
public class NdExperimentProject extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_experiment_project_id")
    private Integer ndexperimentprojectid;
    @Basic(optional = false)
    @Column(name = "project_id")
    private Integer projectid;
    @Basic(optional = false)
    @Column(name = "nd_experiment_id")
    private Integer ndexperimentid;
    
    

    public NdExperimentProject() {
    	setDefault();
    }

    public NdExperimentProject(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdexperimentprojectid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdExperimentProject other = (NdExperimentProject) obj;
        if (this.getNdexperimentprojectid() != other.getNdexperimentprojectid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdexperimentprojectid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.NdExperimentProject[ndexperimentprojectPK=" + getNdexperimentprojectid() + "]";
    }

    /**
     * @return the ndexperimentprojectid
     */
    public Integer getNdexperimentprojectid() {
        return ndexperimentprojectid;
    }

    /**
     * @param ndexperimentprojectid the ndexperimentprojectid to set
     */
    public void setNdexperimentprojectid(Integer ndexperimentprojectid) {
        this.ndexperimentprojectid = ndexperimentprojectid;
    }

    /**
     * @return the projectid
     */
    public Integer getProjectid() {
        return projectid;
    }

    /**
     * @param projectid the projectid to set
     */
    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
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

    
}
