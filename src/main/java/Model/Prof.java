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
public class Prof extends Emp{
    private int PROF_NUM;
    private String PROF_SPECIALTY;
    private String DEPT_CODE;
    private String PROF_RANK;
    private Dept dept;

    public Prof() {
    }

    public Prof(int PROF_NUM, String PROF_SPECIALTY, String DEPT_CODE, String PROF_RANK) {
        this.PROF_NUM = PROF_NUM;
        this.PROF_SPECIALTY = PROF_SPECIALTY;
        this.DEPT_CODE = DEPT_CODE;
        this.PROF_RANK = PROF_RANK;
    }
    
    public Prof(int PROF_NUM, String PROF_SPECIALTY, String DEPT_CODE, String PROF_RANK, String EMP_LNAME, String EMP_FNAME, String EMP_MNAME, String EMP_JOBCODE, Date EMP_HIREDATE, Date EMP_DOB, String EMP_EMAIL) {
        super(PROF_NUM, EMP_LNAME, EMP_FNAME, EMP_MNAME, EMP_EMAIL, EMP_JOBCODE, EMP_HIREDATE, EMP_DOB);
        this.PROF_NUM = EMP_NUM;
        this.PROF_SPECIALTY = PROF_SPECIALTY;
        this.DEPT_CODE = DEPT_CODE;
        this.PROF_RANK = PROF_RANK;
    }

    public int getPROF_NUM() {
        return PROF_NUM;
    }

    public void setPROF_NUM(int PROF_NUM) {
        this.PROF_NUM = PROF_NUM;
    }

    public String getPROF_SPECIALTY() {
        return PROF_SPECIALTY;
    }

    public void setPROF_SPECIALTY(String PROF_SPECIALTY) {
        this.PROF_SPECIALTY = PROF_SPECIALTY;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public String getPROF_RANK() {
        return PROF_RANK;
    }

    public void setPROF_RANK(String PROF_RANK) {
        this.PROF_RANK = PROF_RANK;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

}
