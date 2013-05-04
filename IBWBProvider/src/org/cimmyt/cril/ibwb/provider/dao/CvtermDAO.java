package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Cvterm;

import org.hibernate.criterion.DetachedCriteria;

public class CvtermDAO extends AbstractDAO<Cvterm, Integer> {

    private static Logger log = Logger.getLogger(CvtermDAO.class);
    
    public CvtermDAO() {
	super(Cvterm.class);
    }
    
    @Override
    public Cvterm prepareToCreate(Cvterm cvterm) {
        if (isLocal()) {
            cvterm.setCvtermid(getNextMin());
        }
        if (isCentral()) {
            cvterm.setCvtermid(getNextMax());
        }
        return cvterm;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Cvterm filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "cvtermid";
    }

    @Override
    public String getConsulta(Cvterm filtro) {
        return null;
    }
}