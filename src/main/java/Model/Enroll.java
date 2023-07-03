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
    private float ENROLL_FINAL;
    private float ENROLL_ASSESS;
    private float ENROLL_MIDTERM;
    private Stu stu;
    private Cls cls;
    
    private int no;
    private float AVG;
    
    public Enroll(){
    }

    public Enroll(int STU_NUM, String CLS_CODE, Date ENROLL_DATE, float ENROLL_FINAL, float ENROLL_ASSESS, float ENROLL_MIDTERM) {
        this.STU_NUM = STU_NUM;
        this.CLS_CODE = CLS_CODE;
        this.ENROLL_DATE = ENROLL_DATE;
        this.ENROLL_FINAL = ENROLL_FINAL;
        this.ENROLL_ASSESS = ENROLL_ASSESS;
        this.ENROLL_MIDTERM = ENROLL_MIDTERM;
    }

    public int getSTU_NUM() {
        return STU_NUM;
    }

    public void setSTU_NUM(int STU_NUM) {
        this.STU_NUM = STU_NUM;
    }

    public String getCLS_CODE() {
        return CLS_CODE;
    }

    public void setCLS_CODE(String CLS_CODE) {
        this.CLS_CODE = CLS_CODE;
    }

    public Date getENROLL_DATE() {
        return ENROLL_DATE;
    }

    public void setENROLL_DATE(Date ENROLL_DATE) {
        this.ENROLL_DATE = ENROLL_DATE;
    }

    public Stu getStu() {
        return stu;
    }

    public void setStu(Stu stu) {
        this.stu = stu;
    }

    public Cls getCls() {
        return cls;
    }

    public void setCls(Cls cls) {
        this.cls = cls;
    }

    public float getENROLL_FINAL() {
        return ENROLL_FINAL;
    }

    public void setENROLL_FINAL(float ENROLL_FINAL) {
        this.ENROLL_FINAL = ENROLL_FINAL;
    }

    public float getENROLL_ASSESS() {
        return ENROLL_ASSESS;
    }

    public void setENROLL_ASSESS(float ENROLL_ASSESS) {
        this.ENROLL_ASSESS = ENROLL_ASSESS;
    }

    public float getENROLL_MIDTERM() {
        return ENROLL_MIDTERM;
    }

    public void setENROLL_MIDTERM(float ENROLL_MIDTERM) {
        this.ENROLL_MIDTERM = ENROLL_MIDTERM;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public float getAVG() {
        return AVG;
    }

    public void setAVG(float AVG) {
        this.AVG = AVG;
    }

    
    
}
