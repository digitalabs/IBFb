package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdExperimentProject;

import org.hibernate.criterion.DetachedCriteria;

public class NdExperimentProjectDAO extends AbstractDAO<NdExperimentProject, Integer> {

    private static Logger log = Logger.getLogger(NdExperimentProjectDAO.class);
    
    public NdExperimentProjectDAO() {
	super(NdExperimentProject.class);
    }
    
    @Override
    public NdExperimentProject prepareToCreate(NdExperimentProject ndExperimentProject) {
        if (isLocal()) {
            ndExperimentProject.setNdexperimentprojectid(getNextMin());
        }
        if (isCentral()) {
            ndExperimentProject.setNdexperimentprojectid(getNextMax());
        }
        return ndExperimentProject;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdExperimentProject filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndexperimentprojectid";
    }

    @Override
    public String getConsulta(NdExperimentProject filtro) {
        return null;
    }
}