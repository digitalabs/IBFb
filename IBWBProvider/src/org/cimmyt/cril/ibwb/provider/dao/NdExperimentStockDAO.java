package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdExperimentStock;

import org.hibernate.criterion.DetachedCriteria;

public class NdExperimentStockDAO extends AbstractDAO<NdExperimentStock, Integer> {

    private static Logger log = Logger.getLogger(NdExperimentStockDAO.class);
    
    public NdExperimentStockDAO() {
	super(NdExperimentStock.class);
    }
    
    @Override
    public NdExperimentStock prepareToCreate(NdExperimentStock ndExperimentStock) {
        if (isLocal()) {
            ndExperimentStock.setNdexperimentstockid(getNextMin());
        }
        if (isCentral()) {
            ndExperimentStock.setNdexperimentstockid(getNextMax());
        }
        return ndExperimentStock;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdExperimentStock filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndexperimentstockid";
    }

    @Override
    public String getConsulta(NdExperimentStock filtro) {
        return null;
    }
}