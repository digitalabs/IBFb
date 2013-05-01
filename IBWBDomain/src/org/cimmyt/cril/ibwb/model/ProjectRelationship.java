package org.cimmyt.cril.ibwb.model;

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
    	projectrelationshipid = 0;
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
        if (this.projectrelationshipid != other.projectrelationshipid) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.projectrelationshipid;
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.ProjectRelationship[projectrelationshipPK=" + projectrelationshipid + "]";
    }

}
