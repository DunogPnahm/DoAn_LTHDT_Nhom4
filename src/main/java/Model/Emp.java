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
public class Emp {
    protected int EMP_NUM;
    protected String EMP_LNAME;
    protected String EMP_FNAME;
    protected String EMP_MNAME;
    protected String EMP_EMAIL;
    protected String EMP_JOBCODE;
    protected Date EMP_HIREDATE;
    protected Date EMP_DOB;

    public Emp() {
    }

    public String getEMP_MNAME() {
        return EMP_MNAME;
    }

    public void setEMP_MNAME(String EMP_MNAME) {
        this.EMP_MNAME = EMP_MNAME;
    }

    public String getEMP_EMAIL() {
        return EMP_EMAIL;
    }

    public void setEMP_EMAIL(String EMP_EMAIL) {
        this.EMP_EMAIL = EMP_EMAIL;
    }

    public Emp(int EMP_NUM, String EMP_LNAME, String EMP_FNAME, String EMP_MNAME, String EMP_EMAIL, String EMP_JOBCODE, Date EMP_HIREDATE, Date EMP_DOB) {
        this.EMP_NUM = EMP_NUM;
        this.EMP_LNAME = EMP_LNAME;
        this.EMP_FNAME = EMP_FNAME;
        this.EMP_MNAME = EMP_MNAME;
        this.EMP_EMAIL = EMP_EMAIL;
        this.EMP_JOBCODE = EMP_JOBCODE;
        this.EMP_HIREDATE = EMP_HIREDATE;
        this.EMP_DOB = EMP_DOB;
    }

    public int getEMP_NUM() {
        return EMP_NUM;
    }

    public String getEMP_LNAME() {
        return EMP_LNAME;
    }

    public String getEMP_FNAME() {
        return EMP_FNAME;
    }

    public String getEMP_INITIAL() {
        return EMP_MNAME;
    }

    public String getEMP_JOBCODE() {
        return EMP_JOBCODE;
    }

    public Date getEMP_HIREDATE() {
        return EMP_HIREDATE;
    }

    public Date getEMP_DOB() {
        return EMP_DOB;
    }

    public void setEMP_NUM(int EMP_NUM) {
        this.EMP_NUM = EMP_NUM;
    }

    public void setEMP_LNAME(String EMP_LNAME) {
        this.EMP_LNAME = EMP_LNAME;
    }

    public void setEMP_FNAME(String EMP_FNAME) {
        this.EMP_FNAME = EMP_FNAME;
    }

    public void setEMP_INITIAL(String EMP_MNAME) {
        this.EMP_MNAME = EMP_MNAME;
    }

    public void setEMP_JOBCODE(String EMP_JOBCODE) {
        this.EMP_JOBCODE = EMP_JOBCODE;
    }

    public void setEMP_HIREDATE(Date EMP_HIREDATE) {
        this.EMP_HIREDATE = EMP_HIREDATE;
    }

    public void setEMP_DOB(Date EMP_DOB) {
        this.EMP_DOB = EMP_DOB;
    }

    @Override
    public String toString() {
        return EMP_NUM + " - " + EMP_LNAME + " " + EMP_MNAME + " " + EMP_FNAME;
    }
}
