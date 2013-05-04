package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.CvtermRelationship;

import org.hibernate.criterion.DetachedCriteria;

public class CvtermRelationshipDAO extends AbstractDAO<CvtermRelationship, Integer> {

    private static Logger log = Logger.getLogger(CvtermRelationshipDAO.class);
    
    public CvtermRelationshipDAO() {
	super(CvtermRelationship.class);
    }
    
    @Override
    public CvtermRelationship prepareToCreate(CvtermRelationship cv) {
        if (isLocal()) {
            cv.setCvtermrelationshipid(getNextMin());
        }
        if (isCentral()) {
            cv.setCvtermrelationshipid(getNextMax());
        }
        return cv;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, CvtermRelationship filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "cvtermrelationshipid";
    }

    @Override
    public String getConsulta(CvtermRelationship filtro) {
        return null;
    }
}