package org.cimmyt.cril.ibwb.provider.dao;

import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Stock;

import org.hibernate.criterion.DetachedCriteria;

public class StockDAO extends AbstractDAO<Stock, Integer> {

    private static Logger log = Logger.getLogger(StockDAO.class);
    
    public StockDAO() {
	super(Stock.class);
    }
    
    @Override
    public Stock prepareToCreate(Stock stock) {
        if (isLocal()) {
            stock.setStockid(getNextMin());
        }
        if (isCentral()) {
            stock.setStockid(getNextMax());
        }
        return stock;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Stock filter) {
		// TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
	return "stockid";
    }

    @Override
    public String getConsulta(Stock filtro) {
        return null;
    }
}