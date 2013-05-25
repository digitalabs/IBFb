package ibfb.lists.report;

/**
 *
 * @author tmsg
 */
public class ReportBean {
    public static final int BARCODE = 0;
    public static final int LABEL_1 = 1;
    public static final int LABEL_2 = 2;
    public static final int LABEL_3 = 3;
    public static final int LABEL_4 = 4;
    public static final int LABEL_5 = 5;
    public static final int LABEL_6 = 6;
    public static final int LABEL_7 = 7;
    public static final int LABEL_8 = 8;    

    private String lotid;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String label5;
    private String label6;
    private String label7;
    private String label8;

    public ReportBean() {
        this.label1 = "";
        this.label2 = "";
        this.label3 = "";
        this.label4 = "";
        this.label5 = "";
        this.label6 = "";
        this.label7 = "";
        this.label8 = "";
    }

    
    
    public ReportBean(String lotid, String label1, String label2, String label3, String label4, String label5, String label6, String label7, String label8) {
        this.lotid = lotid;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.label5 = label5;
        this.label6 = label6;
        this.label7 = label7;
        this.label8 = label8;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public String getLabel4() {
        return label4;
    }

    public void setLabel4(String label4) {
        this.label4 = label4;
    }

    public String getLabel5() {
        return label5;
    }

    public void setLabel5(String label5) {
        this.label5 = label5;
    }

    public String getLabel6() {
        return label6;
    }

    public void setLabel6(String label6) {
        this.label6 = label6;
    }

    public String getLabel7() {
        return label7;
    }

    public void setLabel7(String label7) {
        this.label7 = label7;
    }

    public String getLabel8() {
        return label8;
    }

    public void setLabel8(String label8) {
        this.label8 = label8;
    }

    public String getLotid() {
        return lotid;
    }

    public void setLotid(String lotid) {
        this.lotid = lotid;
    }
    
    
    
}
