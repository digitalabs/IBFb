package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdGeolocation;

import org.hibernate.criterion.DetachedCriteria;

public class NdGeolocationDAO extends AbstractDAO<NdGeolocation, Integer> {

    private static Logger log = Logger.getLogger(NdGeolocationDAO.class);
    
    public NdGeolocationDAO() {
	super(NdGeolocation.class);
    }
    
    @Override
    public NdGeolocation prepareToCreate(NdGeolocation ndGeolocation) {
        if (isLocal()) {
            ndGeolocation.setNdgeolocationid(getNextMin());
        }
        if (isCentral()) {
            ndGeolocation.setNdgeolocationid(getNextMax());
        }
        return ndGeolocation;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdGeolocation filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndgeolocationid";
    }

    @Override
    public String getConsulta(NdGeolocation filtro) {
        return null;
    }
}