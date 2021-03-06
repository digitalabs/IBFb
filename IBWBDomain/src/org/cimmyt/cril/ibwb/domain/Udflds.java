/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "udflds")
public class Udflds extends BaseFilter implements Serializable {
    
    public static final String NAMES_TABLE = "NAMES";
    public static final String NAMES_FIELD = "NAME";
    public static final int NAMES_DEFAULT_NAME = 13;
    
    
    public static final String LISTNMS_TABLE = "LISTNMS";
    public static final String LISTNMS_FIELD = "LISTTYPE";
    
    public static final int LISTNMS_DEFAULT_TYPE = 700;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "fldno")
    private Integer fldno;
    @Basic(optional = false)
    @Column(name = "ftable")
    private String ftable;
    @Basic(optional = false)
    @Column(name = "ftype")
    private String ftype;
    @Basic(optional = false)
    @Column(name = "fcode")
    private String fcode;
    @Basic(optional = false)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @Column(name = "ffmt")
    private String ffmt;
    @Basic(optional = false)
    @Column(name = "fdesc")
    private String fdesc;
    @Basic(optional = false)
    @Column(name = "lfldno")
    private Integer lfldno;
    @Basic(optional = false)
    @Column(name = "fuid")
    private Integer fuid;
    @Basic(optional = false)
    @Column(name = "fdate")
    private Integer fdate;
    @Column(name = "scaleid")
    private Integer scaleid;

    public Udflds() {
    	setDefault();
    }
    
    public Udflds(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	fldno = 0;
    	ftable = "-";
    	ftype = "-";
    	fcode = "-";
    	fname = "-";
    	ffmt = "-";
    	fdesc = "-";
    	lfldno = 0;
    	fuid = 0;
    	fdate = 0;
    	scaleid = 0;
    }

    public Udflds(Integer fldno) {
        this.fldno = fldno;
    }

    public Udflds(Integer fldno, String ftable, String ftype, String fcode, String fname, String ffmt, String fdesc, Integer lfldno, Integer fuid, Integer fdate) {
        this.fldno = fldno;
        this.ftable = ftable;
        this.ftype = ftype;
        this.fcode = fcode;
        this.fname = fname;
        this.ffmt = ffmt;
        this.fdesc = fdesc;
        this.lfldno = lfldno;
        this.fuid = fuid;
        this.fdate = fdate;
    }

    public Integer getFldno() {
        return fldno;
    }

    public void setFldno(Integer fldno) {
        this.fldno = fldno;
    }

    public String getFtable() {
        return ftable;
    }

    public void setFtable(String ftable) {
        this.ftable = ftable;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFfmt() {
        return ffmt;
    }

    public void setFfmt(String ffmt) {
        this.ffmt = ffmt;
    }

    public String getFdesc() {
        return fdesc;
    }

    public void setFdesc(String fdesc) {
        this.fdesc = fdesc;
    }

    public Integer getLfldno() {
        return lfldno;
    }

    public void setLfldno(Integer lfldno) {
        this.lfldno = lfldno;
    }

    public Integer getFuid() {
        return fuid;
    }

    public void setFuid(Integer fuid) {
        this.fuid = fuid;
    }

    public Integer getFdate() {
        return fdate;
    }

    public void setFdate(Integer fdate) {
        this.fdate = fdate;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fldno != null ? fldno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Udflds)) {
            return false;
        }
        Udflds other = (Udflds) object;
        if ((this.fldno == null && other.fldno != null) || (this.fldno != null && !this.fldno.equals(other.fldno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.cimmyt.cril.ibworkbench.services.beans.Udflds[fldno=" + fldno + "]";
        return this.fname;
    }

}
