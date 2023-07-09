/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Crs {
    private String CRS_CODE;
    private Dept dept;
    private String CRS_TITLE;
    private int CRS_CREDIT;
    private String CRS_DESC;
    private String CRS_COMPULSORY;
    
    public Crs()
    {
        
    }

    public Crs(String CRS_CODE, Dept dept, String CRS_TITLE, int CRS_CREDIT, String CRS_DESC, String CRS_COMPULSORY) {
        this.CRS_CODE = CRS_CODE;
        this.dept = dept;
        this.CRS_TITLE = CRS_TITLE;
        this.CRS_CREDIT = CRS_CREDIT;
        this.CRS_DESC = CRS_DESC;
        this.CRS_COMPULSORY = CRS_COMPULSORY;
    }

    public String getCRS_CODE() {
        return CRS_CODE;
    }

    public Dept getDept() {
        return dept;
    }

    public String getCRS_TITLE() {
        return CRS_TITLE;
    }

    public int getCRS_CREDIT() {
        return CRS_CREDIT;
    }

    public String getCRS_DESC() {
        return CRS_DESC;
    }

    public String getCRS_COMPULSORY() {
        return CRS_COMPULSORY;
    }

    public void setCRS_CODE(String CRS_CODE) {
        this.CRS_CODE = CRS_CODE;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setCRS_TITLE(String CRS_TITLE) {
        this.CRS_TITLE = CRS_TITLE;
    }

    public void setCRS_CREDIT(int CRS_CREDIT) {
        this.CRS_CREDIT = CRS_CREDIT;
    }

    public void setCRS_DESC(String CRS_DESC) {
        this.CRS_DESC = CRS_DESC;
    }

    public void setCRS_COMPULSORY(String CRS_COMPULSORY) {
        this.CRS_COMPULSORY = CRS_COMPULSORY;
    }
    
}
