/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Prof extends Emp{
    private Emp emp;
    private int PROF_NUM;
    private String PROF_SPECIALTY;
    private String PROF_EMAIL;
    private Dept dept;
    private String PROF_RANK;

    public Prof() {
    }

    public Prof(Emp emp, String PROF_SPECIALTY, String PROF_EMAIL, Dept dept, String PROF_RANK) {
        this.emp = emp;
        PROF_NUM = emp.getEMP_NUM();
        this.PROF_SPECIALTY = PROF_SPECIALTY;
        this.PROF_EMAIL = PROF_EMAIL;
        this.dept = dept;
        this.PROF_RANK = PROF_RANK;
    }

    public Emp getEmp() {
        return emp;
    }

    public String getPROF_SPECIALTY() {
        return PROF_SPECIALTY;
    }

    public String getPROF_EMAIL() {
        return PROF_EMAIL;
    }

    public Dept getDept() {
        return dept;
    }

    public String getPROF_RANK() {
        return PROF_RANK;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
        PROF_NUM = emp.getEMP_NUM();
    }

    public void setPROF_SPECIALTY(String PROF_SPECIALTY) {
        this.PROF_SPECIALTY = PROF_SPECIALTY;
    }

    public void setPROF_EMAIL(String PROF_EMAIL) {
        this.PROF_EMAIL = PROF_EMAIL;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setPROF_RANK(String PROF_RANK) {
        this.PROF_RANK = PROF_RANK;
    }
    
}
