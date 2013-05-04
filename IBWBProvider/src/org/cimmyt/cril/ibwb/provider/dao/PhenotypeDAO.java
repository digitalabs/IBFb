package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Phenotype;

import org.hibernate.criterion.DetachedCriteria;

public class PhenotypeDAO extends AbstractDAO<Phenotype, Integer> {

    private static Logger log = Logger.getLogger(PhenotypeDAO.class);
    
    public PhenotypeDAO() {
	super(Phenotype.class);
    }
    
    @Override
    public Phenotype prepareToCreate(Phenotype phenotype) {
        if (isLocal()) {
            phenotype.setPhenotypeid(getNextMin());
        }
        if (isCentral()) {
            phenotype.setPhenotypeid(getNextMax());
        }
        return phenotype;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Phenotype filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "phenotypeid";
    }

    @Override
    public String getConsulta(Phenotype filtro) {
        return null;
    }
}