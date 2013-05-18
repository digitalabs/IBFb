package org.cimmyt.cril.ibwb.provider.dto;

/**
 * Created with IntelliJ IDEA.
 * User: DanielV
 * Date: 5/18/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class TraitDto {
    private Integer tid;
    private Integer traitId;
    private String traitName;
    private String traitDescription;
    private Integer tnstat;
    private String traitGroup;

    public TraitDto() {
    }

    public TraitDto(Integer tid, Integer traitId, String traitName, String traitDescription, Integer tnstat, String traitGroup) {
        this.tid = tid;
        this.traitId = traitId;
        this.traitName = traitName;
        this.traitDescription = traitDescription;
        this.tnstat = tnstat;
        this.traitGroup = traitGroup;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTraitId() {
        return traitId;
    }

    public void setTraitId(Integer traitId) {
        this.traitId = traitId;
    }

    public String getTraitName() {
        return traitName;
    }

    public void setTraitName(String traitName) {
        this.traitName = traitName;
    }

    public String getTraitDescription() {
        return traitDescription;
    }

    public void setTraitDescription(String traitDescription) {
        this.traitDescription = traitDescription;
    }

    public Integer getTnstat() {
        return tnstat;
    }

    public void setTnstat(Integer tnstat) {
        this.tnstat = tnstat;
    }

    public String getTraitGroup() {
        return traitGroup;
    }

    public void setTraitGroup(String traitGroup) {
        this.traitGroup = traitGroup;
    }
}
