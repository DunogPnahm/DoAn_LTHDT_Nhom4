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
    protected String CRS_CODE;
    protected String DEPT_CODE;
    protected String CRS_TITLE;
    protected int CRS_CREDIT;
    protected String CRS_DESC;
    protected String CRS_COMPULSORY;
    protected Dept dept;
    
    public Crs()
    {
        
    }

    public Crs(String CRS_CODE, String DEPT_CODE, String CRS_TITLE, int CRS_CREDIT, String CRS_DESC, String CRS_COMPULSORY) {
        this.CRS_CODE = CRS_CODE;
        this.DEPT_CODE = DEPT_CODE;
        this.CRS_TITLE = CRS_TITLE;
        this.CRS_CREDIT = CRS_CREDIT;
        this.CRS_DESC = CRS_DESC;
        this.CRS_COMPULSORY = CRS_COMPULSORY;
    }

    public String getCRS_CODE() {
        return CRS_CODE;
    }

    public void setCRS_CODE(String CRS_CODE) {
        this.CRS_CODE = CRS_CODE;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public String getCRS_TITLE() {
        return CRS_TITLE;
    }

    public void setCRS_TITLE(String CRS_TITLE) {
        this.CRS_TITLE = CRS_TITLE;
    }

    public int getCRS_CREDIT() {
        return CRS_CREDIT;
    }

    public void setCRS_CREDIT(int CRS_CREDIT) {
        this.CRS_CREDIT = CRS_CREDIT;
    }

    public String getCRS_DESC() {
        return CRS_DESC;
    }

    public void setCRS_DESC(String CRS_DESC) {
        this.CRS_DESC = CRS_DESC;
    }

    public String getCRS_COMPULSORY() {
        return CRS_COMPULSORY;
    }

    public void setCRS_COMPULSORY(String CRS_COMPULSORY) {
        this.CRS_COMPULSORY = CRS_COMPULSORY;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return CRS_CODE + " - " + CRS_TITLE;
    }
    
}
