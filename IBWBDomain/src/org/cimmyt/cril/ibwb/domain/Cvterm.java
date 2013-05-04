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
@Table(name = "cvterm")
public class Cvterm extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cvterm_id")
    private Integer cvtermid;
    @Basic(optional = false)
    @Column(name = "cv_id")
    private Integer cvid;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = true)
    @Column(name = "definition")
    private String definition;
    @Basic(optional = true)
    @Column(name = "dbxref_id")
    private Integer dbxrefid;
    @Basic(optional = false)
    @Column(name = "is_obsolete")
    private Integer isobsolete;
    @Basic(optional = false)
    @Column(name = "is_relationshiptype")
    private Integer isrelationshiptype;

    public Cvterm() {
    	setDefault();
    }

    public Cvterm(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setCvtermid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cvterm other = (Cvterm) obj;
        if (this.getCvtermid() != other.getCvtermid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getCvtermid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.Cvterm[cvtermPK=" + getCvtermid() + "]";
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

    /**
     * @return the dbxrefid
     */
    public Integer getDbxrefid() {
        return dbxrefid;
    }

    /**
     * @param dbxrefid the dbxrefid to set
     */
    public void setDbxrefid(Integer dbxrefid) {
        this.dbxrefid = dbxrefid;
    }

    /**
     * @return the isobsolete
     */
    public Integer getIsobsolete() {
        return isobsolete;
    }

    /**
     * @param isobsolete the isobsolete to set
     */
    public void setIsobsolete(Integer isobsolete) {
        this.isobsolete = isobsolete;
    }

    /**
     * @return the isrelationshiptype
     */
    public Integer getIsrelationshiptype() {
        return isrelationshiptype;
    }

    /**
     * @param isrelationshiptype the isrelationshiptype to set
     */
    public void setIsrelationshiptype(Integer isrelationshiptype) {
        this.isrelationshiptype = isrelationshiptype;
    }

    
}
