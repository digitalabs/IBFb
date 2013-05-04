package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Stockprop;

import org.hibernate.criterion.DetachedCriteria;

public class StockpropDAO extends AbstractDAO<Stockprop, Integer> {

    private static Logger log = Logger.getLogger(StockpropDAO.class);
    
    public StockpropDAO() {
	super(Stockprop.class);
    }
    
    @Override
    public Stockprop prepareToCreate(Stockprop stockprop) {
        if (isLocal()) {
            stockprop.setStockpropid(getNextMin());
        }
        if (isCentral()) {
            stockprop.setStockpropid(getNextMax());
        }
        return stockprop;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Stockprop filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "stockpropid";
    }

    @Override
    public String getConsulta(Stockprop filtro) {
        return null;
    }
}