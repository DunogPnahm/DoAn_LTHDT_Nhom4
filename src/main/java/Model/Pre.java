/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Pre extends Crs {
    private String PRE_TAKE;
    private String CRS_REQ_PRE;

    public Pre() {
    }

    public Pre(String PRE_TAKE, String CRS_REQ_PRE) {
        this.PRE_TAKE = PRE_TAKE;
        this.CRS_REQ_PRE = CRS_REQ_PRE;
    }

    public Pre(String PRE_TAKE, String CRS_REQ_PRE, String DEPT_CODE, String CRS_TITLE, int CRS_CREDIT, String CRS_DESC, String CRS_COMPULSORY) {
        super(CRS_REQ_PRE, DEPT_CODE, CRS_TITLE, CRS_CREDIT, CRS_DESC, CRS_COMPULSORY);
        this.CRS_REQ_PRE = CRS_CODE;
        this.PRE_TAKE = PRE_TAKE;
    }
    
    public String getPRE_TAKE() {
        return PRE_TAKE;
    }

    public void setPRE_TAKE(String PRE_TAKE) {
        this.PRE_TAKE = PRE_TAKE;
    }

    public String getCRS_REQ_PRE() {
        return CRS_REQ_PRE;
    }

    public void setCRS_REQ_PRE(String CRS_REQ_PRE) {
        this.CRS_REQ_PRE = CRS_REQ_PRE;
    }
    
    
}
