package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Project;

import org.hibernate.criterion.DetachedCriteria;

public class ProjectDAO extends AbstractDAO<Project, Integer> {

    private static Logger log = Logger.getLogger(ProjectDAO.class);
    
    public ProjectDAO() {
	super(Project.class);
    }
    
    @Override
    public Project prepareToCreate(Project project) {
        if (isLocal()) {
            project.setProjectid(getNextMin());
        }
        if (isCentral()) {
            project.setProjectid(getNextMax());
        }
        return project;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Project filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "projectid";
    }

    @Override
    public String getConsulta(Project filtro) {
        return null;
    }
}