package org.cimmyt.cril.ibwb.model;

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
@Table(name = "stockprop")
public class Stockprop extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "stockprop_id")
    private Integer stockpropid;
    @Basic(optional = false)
    @Column(name = "stock_id")
    private Integer stockid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    @Basic(optional = true)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "rank")
    private Integer rank;

    public Stockprop() {
    	setDefault();
    }

    public Stockprop(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setStockpropid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stockprop other = (Stockprop) obj;
        if (this.getStockpropid() != other.getStockpropid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getStockpropid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.model.Stockprop[stockpropPK=" + getStockpropid() + "]";
    }

    /**
     * @return the stockpropid
     */
    public Integer getStockpropid() {
        return stockpropid;
    }

    /**
     * @param stockpropid the stockpropid to set
     */
    public void setStockpropid(Integer stockpropid) {
        this.stockpropid = stockpropid;
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

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }
    
    

}
