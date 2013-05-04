package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Cv;

import org.hibernate.criterion.DetachedCriteria;

public class CvDAO extends AbstractDAO<Cv, Integer> {

    private static Logger log = Logger.getLogger(CvDAO.class);
    
    public CvDAO() {
	super(Cv.class);
    }
    
    @Override
    public Cv prepareToCreate(Cv cv) {
        if (isLocal()) {
            cv.setCvid(getNextMin());
        }
        if (isCentral()) {
            cv.setCvid(getNextMax());
        }
        return cv;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Cv filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "cvid";
    }

    @Override
    public String getConsulta(Cv filtro) {
        return null;
    }
}