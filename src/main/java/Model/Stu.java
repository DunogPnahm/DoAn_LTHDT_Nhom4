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
public class Stu {
    private int STU_NUM;
    private String STU_LNAME;
    private String STU_FNAME;
    private String STU_INITIAL;
    private String STU_EMAIL;
    private Date STU_DOB;
    private String STU_GENDER;
    private String DEPT_CODE;
    private String MGTCLS_CODE;

    public Stu() {
    }

    public Stu(int STU_NUM, String STU_LNAME, String STU_FNAME, String STU_INITIAL, String STU_EMAIL, Date STU_DOB, String STU_GENDER, String DEPT_CODE, String MGTCLS_CODE) {
        this.STU_NUM = STU_NUM;
        this.STU_LNAME = STU_LNAME;
        this.STU_FNAME = STU_FNAME;
        this.STU_INITIAL = STU_INITIAL;
        this.STU_EMAIL = STU_EMAIL;
        this.STU_DOB = STU_DOB;
        this.STU_GENDER = STU_GENDER;
        this.DEPT_CODE = DEPT_CODE;
        this.MGTCLS_CODE = MGTCLS_CODE;
    }

    public int getSTU_NUM() {
        return STU_NUM;
    }

    public String getSTU_LNAME() {
        return STU_LNAME;
    }

    public String getSTU_FNAME() {
        return STU_FNAME;
    }

    public String getSTU_INITIAL() {
        return STU_INITIAL;
    }

    public String getSTU_EMAIL() {
        return STU_EMAIL;
    }

    public Date getSTU_DOB() {
        return STU_DOB;
    }

    public String getSTU_GENDER() {
        return STU_GENDER;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public String getMGTCLS_CODE() {
        return MGTCLS_CODE;
    }

    public void setSTU_NUM(int STU_NUM) {
        this.STU_NUM = STU_NUM;
    }

    public void setSTU_LNAME(String STU_LNAME) {
        this.STU_LNAME = STU_LNAME;
    }

    public void setSTU_FNAME(String STU_FNAME) {
        this.STU_FNAME = STU_FNAME;
    }

    public void setSTU_INITIAL(String STU_INITIAL) {
        this.STU_INITIAL = STU_INITIAL;
    }

    public void setSTU_EMAIL(String STU_EMAIL) {
        this.STU_EMAIL = STU_EMAIL;
    }

    public void setSTU_DOB(Date STU_DOB) {
        this.STU_DOB = STU_DOB;
    }

    public void setSTU_GENDER(String STU_GENDER) {
        this.STU_GENDER = STU_GENDER;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public void setMGTCLS_CODE(String MGTCLS_CODE) {
        this.MGTCLS_CODE = MGTCLS_CODE;
    }
    
}
