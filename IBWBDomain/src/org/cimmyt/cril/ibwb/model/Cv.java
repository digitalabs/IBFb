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
@Table(name = "cv")
public class Cv extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cv_id")
    private Integer cvid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = true)
    @Column(name = "definition")
    private String definition;

    public Cv() {
    	setDefault();
    }

    public Cv(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setCvid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cv other = (Cv) obj;
        if (this.getCvid() != other.getCvid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getCvid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.Cv[cvPK=" + getCvid() + "]";
    }

    /**
     * @return the cvid
     */
    public Integer getCvid() {
        return cvid;
    }

    /**
     * @param cvid the cvid to set
     */
    public void setCvid(Integer cvid) {
        this.cvid = cvid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * @param definition the definition to set
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
    
    

}
