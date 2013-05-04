package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdExperimentPhenotype;

import org.hibernate.criterion.DetachedCriteria;

public class NdExperimentPhenotypeDAO extends AbstractDAO<NdExperimentPhenotype, Integer> {

    private static Logger log = Logger.getLogger(NdExperimentPhenotypeDAO.class);
    
    public NdExperimentPhenotypeDAO() {
	super(NdExperimentPhenotype.class);
    }
    
    @Override
    public NdExperimentPhenotype prepareToCreate(NdExperimentPhenotype ndExperimentPhenotype) {
        if (isLocal()) {
            ndExperimentPhenotype.setNdexperimentphenotypeid(getNextMin());
        }
        if (isCentral()) {
            ndExperimentPhenotype.setNdexperimentphenotypeid(getNextMax());
        }
        return ndExperimentPhenotype;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdExperimentPhenotype filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndexperimentphenotypeid";
    }

    @Override
    public String getConsulta(NdExperimentPhenotype filtro) {
        return null;
    }
}