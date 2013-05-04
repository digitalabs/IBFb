package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Projectprop;

import org.hibernate.criterion.DetachedCriteria;

public class ProjectpropDAO extends AbstractDAO<Projectprop, Integer> {

    private static Logger log = Logger.getLogger(ProjectpropDAO.class);
    
    public ProjectpropDAO() {
	super(Projectprop.class);
    }
    
    @Override
    public Projectprop prepareToCreate(Projectprop projectprop) {
        if (isLocal()) {
            projectprop.setProjectpropid(getNextMin());
        }
        if (isCentral()) {
            projectprop.setProjectpropid(getNextMax());
        }
        return projectprop;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Projectprop filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "projectpropid";
    }

    @Override
    public String getConsulta(Projectprop filtro) {
        return null;
    }
}