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
@Table(name = "projectprop")
public class Projectprop extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "projectprop_id")
    private Integer projectpropid;
    @Basic(optional = false)
    @Column(name = "project_id")
    private Integer projectid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;

    public Projectprop() {
    	setDefault();
    }

    public Projectprop(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setProjectpropid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projectprop other = (Projectprop) obj;
        if (this.getProjectpropid() != other.getProjectpropid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getProjectpropid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.Projectprop[projectpropPK=" + getProjectpropid() + "]";
    }

    /**
     * @return the projectpropid
     */
    public Integer getProjectpropid() {
        return projectpropid;
    }

    /**
     * @param projectpropid the projectpropid to set
     */
    public void setProjectpropid(Integer projectpropid) {
        this.projectpropid = projectpropid;
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
