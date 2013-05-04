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
@Table(name = "cvtermprop")
public class Cvtermprop extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cvtermprop_id")
    private Integer cvtermpropid;
    @Basic(optional = false)
    @Column(name = "cvterm_id")
    private Integer cvtermid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "rank")
    private Integer rank;

    public Cvtermprop() {
    	setDefault();
    }

    public Cvtermprop(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setCvtermpropid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cvtermprop other = (Cvtermprop) obj;
        if (this.getCvtermpropid() != other.getCvtermpropid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getCvtermpropid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.Cvtermprop[cvtermpropPK=" + getCvtermpropid() + "]";
    }

    /**
     * @return the cvtermpropid
     */
    public Integer getCvtermpropid() {
        return cvtermpropid;
    }

    /**
     * @param cvtermpropid the cvtermpropid to set
     */
    public void setCvtermpropid(Integer cvtermpropid) {
        this.cvtermpropid = cvtermpropid;
    }

    /**
     * @return the cvtermid
     */
    public Integer getCvtermid() {
        return cvtermid;
    }

    /**
     * @param cvtermid the cvtermid to set
     */
    public void setCvtermid(Integer cvtermid) {
        this.cvtermid = cvtermid;
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
