package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.ProjectRelationship;

import org.hibernate.criterion.DetachedCriteria;

public class ProjectRelationshipDAO extends AbstractDAO<ProjectRelationship, Integer> {

    private static Logger log = Logger.getLogger(ProjectRelationshipDAO.class);
    
    public ProjectRelationshipDAO() {
	super(ProjectRelationship.class);
    }
    
    @Override
    public ProjectRelationship prepareToCreate(ProjectRelationship projectrelationship) {
        if (isLocal()) {
            projectrelationship.setProjectrelationshipid(getNextMin());
        }
        if (isCentral()) {
            projectrelationship.setProjectrelationshipid(getNextMax());
        }
        return projectrelationship;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ProjectRelationship filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "projectrelationshipid";
    }

    @Override
    public String getConsulta(ProjectRelationship filtro) {
        return null;
    }
}