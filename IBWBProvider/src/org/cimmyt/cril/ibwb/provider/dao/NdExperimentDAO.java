package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdExperiment;

import org.hibernate.criterion.DetachedCriteria;

public class NdExperimentDAO extends AbstractDAO<NdExperiment, Integer> {

    private static Logger log = Logger.getLogger(NdExperimentDAO.class);
    
    public NdExperimentDAO() {
	super(NdExperiment.class);
    }
    
    @Override
    public NdExperiment prepareToCreate(NdExperiment ndExperiment) {
        if (isLocal()) {
            ndExperiment.setNdexperimentid(getNextMin());
        }
        if (isCentral()) {
            ndExperiment.setNdexperimentid(getNextMax());
        }
        return ndExperiment;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdExperiment filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndexperimentid";
    }

    @Override
    public String getConsulta(NdExperiment filtro) {
        return null;
    }
}