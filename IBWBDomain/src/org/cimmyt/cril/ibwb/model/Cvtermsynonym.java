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
@Table(name = "cvtermsynonym")
public class Cvtermsynonym extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cvtermsynonym_id")
    private Integer cvtermsynonymid;
    @Basic(optional = false)
    @Column(name = "cvterm_id")
    private Integer cvtermid;
    @Basic(optional = false)
    @Column(name = "synonym")
    private String synonym;
    @Basic(optional = true)
    @Column(name = "type_id")
    private Integer typeid;
    

    public Cvtermsynonym() {
    	setDefault();
    }

    public Cvtermsynonym(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setCvtermsynonymid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cvtermsynonym other = (Cvtermsynonym) obj;
        if (this.getCvtermsynonymid() != other.getCvtermsynonymid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getCvtermsynonymid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.Cvtermsynonym[cvtermsynonymPK=" + getCvtermsynonymid() + "]";
    }

    /**
     * @return the cvtermsynonymid
     */
    public Integer getCvtermsynonymid() {
        return cvtermsynonymid;
    }

    /**
     * @param cvtermsynonymid the cvtermsynonymid to set
     */
    public void setCvtermsynonymid(Integer cvtermsynonymid) {
        this.cvtermsynonymid = cvtermsynonymid;
    }

    /**
     * @return the cvtermid
     */
    public Integer getCvtermid() {
        return cvtermid;
    }

    /**
     * @param cvtermid the cvtermid to set
     */
    public void setCvtermid(Integer cvtermid) {
        this.cvtermid = cvtermid;
    }

    /**
     * @return the synonym
     */
    public String getSynonym() {
        return synonym;
    }

    /**
     * @param synonym the synonym to set
     */
    public void setSynonym(String synonym) {
        this.synonym = synonym;
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
