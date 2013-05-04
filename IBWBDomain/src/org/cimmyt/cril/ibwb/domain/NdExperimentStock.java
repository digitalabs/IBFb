package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author mturiana
 */
@Entity
@Table(name = "nd_experiment_stock")
public class NdExperimentStock extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_experiment_stock_id")
    private Integer ndexperimentstockid;
    @Basic(optional = false)
    @Column(name = "nd_experiment_id")
    private Integer ndexperimentid;
    @Basic(optional = false)
    @Column(name = "stock_id")
    private Integer stockid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    
    public NdExperimentStock() {
    	setDefault();
    }

    public NdExperimentStock(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdexperimentstockid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdExperimentStock other = (NdExperimentStock) obj;
        if (this.getNdexperimentstockid() != other.getNdexperimentstockid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdexperimentstockid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.NdExperimentStock[ndexperimentstockPK=" + getNdexperimentstockid() + "]";
    }

    /**
     * @return the ndexperimentstockid
     */
    public Integer getNdexperimentstockid() {
        return ndexperimentstockid;
    }

    /**
     * @param ndexperimentstockid the ndexperimentstockid to set
     */
    public void setNdexperimentstockid(Integer ndexperimentstockid) {
        this.ndexperimentstockid = ndexperimentstockid;
    }

    /**
     * @return the ndexperimentid
     */
    public Integer getNdexperimentid() {
        return ndexperimentid;
    }

    /**
     * @param ndexperimentid the ndexperimentid to set
     */
    public void setNdexperimentid(Integer ndexperimentid) {
        this.ndexperimentid = ndexperimentid;
    }

    /**
     * @return the stockid
     */
    public Integer getStockid() {
        return stockid;
    }

    /**
     * @param stockid the stockid to set
     */
    public void setStockid(Integer stockid) {
        this.stockid = stockid;
    }

    /**
     * @return the typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * @param typeid the typeid to set
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
    
    

}
