package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdExperimentprop;

import org.hibernate.criterion.DetachedCriteria;

public class NdExperimentpropDAO extends AbstractDAO<NdExperimentprop, Integer> {

    private static Logger log = Logger.getLogger(NdExperimentpropDAO.class);
    
    public NdExperimentpropDAO() {
	super(NdExperimentprop.class);
    }
    
    @Override
    public NdExperimentprop prepareToCreate(NdExperimentprop ndExperimentprop) {
        if (isLocal()) {
            ndExperimentprop.setNdexperimentpropid(getNextMin());
        }
        if (isCentral()) {
            ndExperimentprop.setNdexperimentpropid(getNextMax());
        }
        return ndExperimentprop;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdExperimentprop filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndexperimentpropid";
    }

    @Override
    public String getConsulta(NdExperimentprop filtro) {
        return null;
    }
}