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
@Table(name = "nd_geolocationprop")
public class NdGeolocationprop extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_geolocationprop_id")
    private Integer ndgeolocationpropid;
    @Basic(optional = false)
    @Column(name = "nd_geolocation_id")
    private Integer ndgeolocationid;
    @Basic(optional = false)
    @Column(name = "type_id")
    private Integer typeid;
    @Basic(optional = true)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "rank")
    private Integer rank;

    public NdGeolocationprop() {
    	setDefault();
    }

    public NdGeolocationprop(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdgeolocationpropid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdGeolocationprop other = (NdGeolocationprop) obj;
        if (this.getNdgeolocationpropid() != other.getNdgeolocationpropid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdgeolocationpropid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.NdGeolocationprop[ndgeolocationpropPK=" + getNdgeolocationpropid() + "]";
    }

    /**
     * @return the ndgeolocationpropid
     */
    public Integer getNdgeolocationpropid() {
        return ndgeolocationpropid;
    }

    /**
     * @param ndgeolocationpropid the ndgeolocationpropid to set
     */
    public void setNdgeolocationpropid(Integer ndgeolocationpropid) {
        this.ndgeolocationpropid = ndgeolocationpropid;
    }

    /**
     * @return the ndgeolocationid
     */
    public Integer getNdgeolocationid() {
        return ndgeolocationid;
    }

    /**
     * @param ndgeolocationid the ndgeolocationid to set
     */
    public void setNdgeolocationid(Integer ndgeolocationid) {
        this.ndgeolocationid = ndgeolocationid;
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
