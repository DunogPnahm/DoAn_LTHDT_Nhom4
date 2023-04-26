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
    private char STU_INTIAL;
    private String STU_EMAIL;
    private Date STU_DOB;
    private char STU_GENDER;
    private Dept dept;
    private Mgtcls mgtcls;

    public Stu() {
    }

    public Stu(int STU_NUM, String STU_LNAME, String STU_FNAME, char STU_INTIAL, String STU_EMAIL, Date STU_DOB, char STU_GENDER, Dept dept, Mgtcls mgtcls) {
        this.STU_NUM = STU_NUM;
        this.STU_LNAME = STU_LNAME;
        this.STU_FNAME = STU_FNAME;
        this.STU_INTIAL = STU_INTIAL;
        this.STU_EMAIL = STU_EMAIL;
        this.STU_DOB = STU_DOB;
        this.STU_GENDER = STU_GENDER;
        this.dept = dept;
        this.mgtcls = mgtcls;
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

    public char getSTU_INTIAL() {
        return STU_INTIAL;
    }

    public String getSTU_EMAIL() {
        return STU_EMAIL;
    }

    public Date getSTU_DOB() {
        return STU_DOB;
    }

    public char getSTU_GENDER() {
        return STU_GENDER;
    }

    public Dept getDept() {
        return dept;
    }

    public Mgtcls getMgtcls() {
        return mgtcls;
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

    public void setSTU_INTIAL(char STU_INTIAL) {
        this.STU_INTIAL = STU_INTIAL;
    }

    public void setSTU_EMAIL(String STU_EMAIL) {
        this.STU_EMAIL = STU_EMAIL;
    }

    public void setSTU_DOB(Date STU_DOB) {
        this.STU_DOB = STU_DOB;
    }

    public void setSTU_GENDER(char STU_GENDER) {
        this.STU_GENDER = STU_GENDER;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setMgtcls(Mgtcls mgtcls) {
        this.mgtcls = mgtcls;
    }
    
}
