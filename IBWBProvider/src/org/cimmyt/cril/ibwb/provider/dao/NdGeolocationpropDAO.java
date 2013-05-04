package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.NdGeolocationprop;

import org.hibernate.criterion.DetachedCriteria;

public class NdGeolocationpropDAO extends AbstractDAO<NdGeolocationprop, Integer> {

    private static Logger log = Logger.getLogger(NdGeolocationpropDAO.class);
    
    public NdGeolocationpropDAO() {
	super(NdGeolocationprop.class);
    }
    
    @Override
    public NdGeolocationprop prepareToCreate(NdGeolocationprop ndGeolocationprop) {
        if (isLocal()) {
            ndGeolocationprop.setNdgeolocationpropid(getNextMin());
        }
        if (isCentral()) {
            ndGeolocationprop.setNdgeolocationpropid(getNextMax());
        }
        return ndGeolocationprop;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, NdGeolocationprop filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "ndgeolocationpropid";
    }

    @Override
    public String getConsulta(NdGeolocationprop filtro) {
        return null;
    }
}