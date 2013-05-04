package org.cimmyt.cril.ibwb.domain;

import org.cimmyt.cril.ibwb.domain.*;
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
@Table(name = "project_relationship")
public class ProjectRelationship extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "project_relationship_id")
    private Integer projectrelationshipid;
    @Basic(optional = false)
    @Column(name = "subject_project_id")
    private Integer subjectprojectid;
    @Basic(optional = false)
    @Column(name = "object_project_id")
    private Integer objectprojectid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;

    public ProjectRelationship() {
    	setDefault();
    }

    public ProjectRelationship(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setProjectrelationshipid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProjectRelationship other = (ProjectRelationship) obj;
        if (this.getProjectrelationshipid() != other.getProjectrelationshipid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getProjectrelationshipid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.ProjectRelationship[projectrelationshipPK=" + getProjectrelationshipid() + "]";
    }

    /**
     * @return the projectrelationshipid
     */
    public Integer getProjectrelationshipid() {
        return projectrelationshipid;
    }

    /**
     * @param projectrelationshipid the projectrelationshipid to set
     */
    public void setProjectrelationshipid(Integer projectrelationshipid) {
        this.projectrelationshipid = projectrelationshipid;
    }

    /**
     * @return the subjectprojectid
     */
    public Integer getSubjectprojectid() {
        return subjectprojectid;
    }

    /**
     * @param subjectprojectid the subjectprojectid to set
     */
    public void setSubjectprojectid(Integer subjectprojectid) {
        this.subjectprojectid = subjectprojectid;
    }

    /**
     * @return the objectprojectid
     */
    public Integer getObjectprojectid() {
        return objectprojectid;
    }

    /**
     * @param objectprojectid the objectprojectid to set
     */
    public void setObjectprojectid(Integer objectprojectid) {
        this.objectprojectid = objectprojectid;
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
