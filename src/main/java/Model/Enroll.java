/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author DunogPnahm
 */
public class Enroll {
    private int STU_NUM;
    private String CLS_CODE;
    private Date ENROLL_DATE;
    private float ENROLL_GRADE;
    
    public Enroll(){
    }

    public Enroll(int STU_NUM, String CLS_CODE, Date ENROLL_DATE, float ENROLL_GRADE) {
        this.STU_NUM = STU_NUM;
        this.CLS_CODE = CLS_CODE;
        this.ENROLL_DATE = ENROLL_DATE;
        this.ENROLL_GRADE = ENROLL_GRADE;
    }

    public int getSTU_NUM() {
        return STU_NUM;
    }

    public String getCLS_CODE() {
        return CLS_CODE;
    }

    public Date getENROLL_DATE() {
        return ENROLL_DATE;
    }

    public float getENROLL_GRADE() {
        return ENROLL_GRADE;
    }

    public void setSTU_NUM(int STU_NUM) {
        this.STU_NUM = STU_NUM;
    }

    public void setCLS_CODE(String CLS_CODE) {
        this.CLS_CODE = CLS_CODE;
    }

    public void setENROLL_DATE(Date ENROLL_DATE) {
        this.ENROLL_DATE = ENROLL_DATE;
    }

    public void setENROLL_GRADE(float ENROLL_GRADE) {
        this.ENROLL_GRADE = ENROLL_GRADE;
    }
    
}
