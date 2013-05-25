package org.cimmyt.cril.ibwb.provider.dto;

/**
 * Created with IntelliJ IDEA.
 * User: DanielV
 * Date: 5/25/13
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CVTermDTO {
    public Integer cvtermid;
    public String cvname;
    public Integer cvid;

    public static final int SCALE_CV_ID = 1030;
    public static final int TRAITS_CV_ID = 1010;
    public static final int METHOD_CV_ID = 1020;

    public CVTermDTO() {
    }

    public CVTermDTO(Integer cvtermid, String cvname, Integer cvid) {
        this.cvtermid = cvtermid;
        this.cvname = cvname;
        this.cvid = cvid;
    }

    public Integer getCvtermid() {
        return cvtermid;
    }

    public void setCvtermid(Integer cvtermid) {
        this.cvtermid = cvtermid;
    }

    public String getCvname() {
        return cvname;
    }

    public void setCvname(String cvname) {
        this.cvname = cvname;
    }

    public Integer getCvid() {
        return cvid;
    }

    public void setCvid(Integer cvid) {
        this.cvid = cvid;
    }
}
