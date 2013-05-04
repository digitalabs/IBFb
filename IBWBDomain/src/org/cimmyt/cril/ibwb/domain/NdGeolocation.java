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
@Table(name = "nd_geolocation")
public class NdGeolocation extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nd_geolocation_id")
    private Integer ndgeolocationid;
    @Basic(optional = true)
    @Column(name = "description")
    private String description;
    @Basic(optional = true)
    @Column(name = "latitude")
    private Float latitude;
    @Basic(optional = true)
    @Column(name = "longitude")
    private Float longitude;
    @Basic(optional = true)
    @Column(name = "geodetic_datum")
    private String geodeticdatum;
    @Basic(optional = true)
    @Column(name = "altitude")
    private Float altitude;
    
    
    

    public NdGeolocation() {
    	setDefault();
    }

    public NdGeolocation(boolean atrNulls) {
    	if(! atrNulls){
    		setDefault();
    	}
    }
    
    public void setDefault(){
    	setNdgeolocationid((Integer) 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NdGeolocation other = (NdGeolocation) obj;
        if (this.getNdgeolocationid() != other.getNdgeolocationid()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.getNdgeolocationid();
        return hash;
    }

  

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.NdGeolocation[ndgeolocationPK=" + getNdgeolocationid() + "]";
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
     * @return the latitude
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the geodeticdatum
     */
    public String getGeodeticdatum() {
        return geodeticdatum;
    }

    /**
     * @param geodeticdatum the geodeticdatum to set
     */
    public void setGeodeticdatum(String geodeticdatum) {
        this.geodeticdatum = geodeticdatum;
    }

    /**
     * @return the altitude
     */
    public Float getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }
    
    

}
