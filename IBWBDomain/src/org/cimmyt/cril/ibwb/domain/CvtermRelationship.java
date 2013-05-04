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
@Table(name = "cvterm_relationship")
public class CvtermRelationship extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cvterm_relationship_id")
    private Integer cvtermrelationshipid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    @Basic(optional = false)
    @Column(name = "subject_id")
    private Integer subjectid;
    @Basic(optional = false)
    @Column(name = "object_id")
    private Integer objectid;

    public CvtermRelationship() {
    	setDefault();
    }

    public CvtermRelationship(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setCvtermrelationshipid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CvtermRelationship other = (CvtermRelationship) obj;
        if (this.getCvtermrelationshipid() != other.getCvtermrelationshipid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getCvtermrelationshipid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.CvtermRelationship[cvtermrelationshipPK=" + getCvtermrelationshipid() + "]";
    }

    /**
     * @return the cvtermrelationshipid
     */
    public Integer getCvtermrelationshipid() {
        return cvtermrelationshipid;
    }

    /**
     * @param cvtermrelationshipid the cvtermrelationshipid to set
     */
    public void setCvtermrelationshipid(Integer cvtermrelationshipid) {
        this.cvtermrelationshipid = cvtermrelationshipid;
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
     * @return the subjectid
     */
    public Integer getSubjectid() {
        return subjectid;
    }

    /**
     * @param subjectid the subjectid to set
     */
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    /**
     * @return the objectid
     */
    public Integer getObjectid() {
        return objectid;
    }

    /**
     * @param objectid the objectid to set
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    
}
