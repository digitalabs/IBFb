package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Cvtermsynonym;

import org.hibernate.criterion.DetachedCriteria;

public class CvtermsynonymDAO extends AbstractDAO<Cvtermsynonym, Integer> {

    private static Logger log = Logger.getLogger(CvtermsynonymDAO.class);
    
    public CvtermsynonymDAO() {
	super(Cvtermsynonym.class);
    }
    
    @Override
    public Cvtermsynonym prepareToCreate(Cvtermsynonym cv) {
        if (isLocal()) {
            cv.setCvtermsynonymid(getNextMin());
        }
        if (isCentral()) {
            cv.setCvtermsynonymid(getNextMax());
        }
        return cv;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Cvtermsynonym filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "cvtermsynonymid";
    }

    @Override
    public String getConsulta(Cvtermsynonym filtro) {
        return null;
    }
}