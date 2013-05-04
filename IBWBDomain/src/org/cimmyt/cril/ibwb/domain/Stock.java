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
@Table(name = "stock")
public class Stock extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "stock_id")
    private Integer stockid;
    @Basic(optional = true)
    @Column(name = "dbxref_id")
    private Integer dbxrefid;
    @Basic(optional = true)
    @Column(name = "organism_id")
    private Integer organismid;
    @Basic(optional = true)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "uniquename")
    private String uniquename;
    @Basic(optional = true)
    @Column(name = "value")
    private String value;
    @Basic(optional = true)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    @Basic(optional = false)
    @Column(name = "is_obsolete")
    private Integer isobsolete;
    

    public Stock() {
    	setDefault();
    }

    public Stock(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setStockid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
        if (this.getStockid() != other.getStockid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getStockid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.Stock[stockPK=" + getStockid() + "]";
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
     * @return the dbxrefid
     */
    public Integer getDbxrefid() {
        return dbxrefid;
    }

    /**
     * @param dbxrefid the dbxrefid to set
     */
    public void setDbxrefid(Integer dbxrefid) {
        this.dbxrefid = dbxrefid;
    }

    /**
     * @return the organismid
     */
    public Integer getOrganismid() {
        return organismid;
    }

    /**
     * @param organismid the organismid to set
     */
    public void setOrganismid(Integer organismid) {
        this.organismid = organismid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the uniquename
     */
    public String getUniquename() {
        return uniquename;
    }

    /**
     * @param uniquename the uniquename to set
     */
    public void setUniquename(String uniquename) {
        this.uniquename = uniquename;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the isobsolete
     */
    public Integer getIsobsolete() {
        return isobsolete;
    }

    /**
     * @param isobsolete the isobsolete to set
     */
    public void setIsobsolete(Integer isobsolete) {
        this.isobsolete = isobsolete;
    }

    
}
