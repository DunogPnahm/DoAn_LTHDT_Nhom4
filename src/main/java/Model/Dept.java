/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Dept {
    private String DEPT_CODE;
    private String DEPT_NAME;
    private int PROF_NUM;
    private Prof prof;

    public Dept() {
    }

    public Dept(String DEPT_CODE, String DEPT_NAME, int PROF_NUM) {
        this.DEPT_CODE = DEPT_CODE;
        this.DEPT_NAME = DEPT_NAME;
        this.PROF_NUM = PROF_NUM;
    }

    public String getDEPT_CODE() {
        return DEPT_CODE;
    }

    public void setDEPT_CODE(String DEPT_CODE) {
        this.DEPT_CODE = DEPT_CODE;
    }

    public String getDEPT_NAME() {
        return DEPT_NAME;
    }

    public void setDEPT_NAME(String DEPT_NAME) {
        this.DEPT_NAME = DEPT_NAME;
    }

    public int getPROF_NUM() {
        return PROF_NUM;
    }

    public void setPROF_NUM(int PROF_NUM) {
        this.PROF_NUM = PROF_NUM;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }
    
    @Override
    public String toString() {
        return DEPT_CODE + " - " + DEPT_NAME;
    }
}
