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
@Table(name = "phenotype")
public class Phenotype extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "phenotype_id")
    private Integer phenotypeid;
    @Basic(optional = false)
    @Column(name = "uniquename")
    private String uniquename;
    @Basic(optional = true)
    @Column(name = "name")
    private String name;
    @Basic(optional = true)
    @Column(name = "observable_id")
    private Integer observableid;
    @Basic(optional = true)
    @Column(name = "attr_id")
    private Integer attrid;
    @Basic(optional = true)
    @Column(name = "value")
    private String value;
    @Basic(optional = true)
    @Column(name = "cvalue_id")
    private Integer cvalueid;
    @Basic(optional = true)
    @Column(name = "assay_id")
    private Integer assayid;
    
    

    public Phenotype() {
    	setDefault();
    }

    public Phenotype(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setPhenotypeid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Phenotype other = (Phenotype) obj;
        if (this.getPhenotypeid() != other.getPhenotypeid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getPhenotypeid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.Phenotype[phenotypePK=" + getPhenotypeid() + "]";
    }

    /**
     * @return the phenotypeid
     */
    public Integer getPhenotypeid() {
        return phenotypeid;
    }

    /**
     * @param phenotypeid the phenotypeid to set
     */
    public void setPhenotypeid(Integer phenotypeid) {
        this.phenotypeid = phenotypeid;
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
     * @return the observableid
     */
    public Integer getObservableid() {
        return observableid;
    }

    /**
     * @param observableid the observableid to set
     */
    public void setObservableid(Integer observableid) {
        this.observableid = observableid;
    }

    /**
     * @return the attrid
     */
    public Integer getAttrid() {
        return attrid;
    }

    /**
     * @param attrid the attrid to set
     */
    public void setAttrid(Integer attrid) {
        this.attrid = attrid;
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
     * @return the cvalueid
     */
    public Integer getCvalueid() {
        return cvalueid;
    }

    /**
     * @param cvalueid the cvalueid to set
     */
    public void setCvalueid(Integer cvalueid) {
        this.cvalueid = cvalueid;
    }

    /**
     * @return the assayid
     */
    public Integer getAssayid() {
        return assayid;
    }

    /**
     * @param assayid the assayid to set
     */
    public void setAssayid(Integer assayid) {
        this.assayid = assayid;
    }
    
    

}
