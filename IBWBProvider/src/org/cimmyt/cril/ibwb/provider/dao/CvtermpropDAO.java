package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Cvtermprop;

import org.hibernate.criterion.DetachedCriteria;

public class CvtermpropDAO extends AbstractDAO<Cvtermprop, Integer> {

    private static Logger log = Logger.getLogger(CvtermpropDAO.class);
    
    public CvtermpropDAO() {
	super(Cvtermprop.class);
    }
    
    @Override
    public Cvtermprop prepareToCreate(Cvtermprop cv) {
        if (isLocal()) {
            cv.setCvtermpropid(getNextMin());
        }
        if (isCentral()) {
            cv.setCvtermpropid(getNextMax());
        }
        return cv;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Cvtermprop filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "cvtermpropid";
    }

    @Override
    public String getConsulta(Cvtermprop filtro) {
        return null;
    }
}