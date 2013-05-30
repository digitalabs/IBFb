package ibfb.domain.core;

import java.io.Serializable;

/**
 * Class to manage germplasm template
 * @author tmsg
 */
public class ListFields  implements Serializable {
    private String listName;
    private String listdDesc;
    private String listType;
    private Integer listDate;

    public ListFields() {
    }

    public ListFields(String listName, String listdDesc, String listType, Integer listDate) {
        this.listName = listName;
        this.listdDesc = listdDesc;
        this.listType = listType;
        this.listDate = listDate;
    }

    public Integer getListDate() {
        return listDate;
    }

    public void setListDate(Integer listDate) {
        this.listDate = listDate;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getListdDesc() {
        return listdDesc;
    }

    public void setListdDesc(String listdDesc) {
        this.listdDesc = listdDesc;
    }
    
    
}
