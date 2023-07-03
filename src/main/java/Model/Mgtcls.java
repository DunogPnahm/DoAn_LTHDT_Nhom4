/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Mgtcls {
    private String MGTCLS_CODE;
    private int PROF_NUM;
    private String SEM_CODE;
    private Prof prof;
    private Sem sem;

    public Mgtcls() {

    }

    public Mgtcls(String MGTCLS_CODE, int PROF_NUM, String SEM_CODE) {
        this.MGTCLS_CODE = MGTCLS_CODE;
        this.PROF_NUM = PROF_NUM;
        this.SEM_CODE = SEM_CODE;
    }

    public String getMGTCLS_CODE() {
        return MGTCLS_CODE;
    }

    public void setMGTCLS_CODE(String MGTCLS_CODE) {
        this.MGTCLS_CODE = MGTCLS_CODE;
    }

    public Prof getPROF() {
        return prof;
    }

    public void setPROF(Prof prof) {
        this.prof = prof;
    }

    public Sem getSEM() {
        return sem;
    }

    public void setSEM(Sem sem) {
        this.sem = sem;
    }

    public int getPROF_NUM() {
        return PROF_NUM;
    }

    public void setPROF_NUM(int PROF_NUM) {
        this.PROF_NUM = PROF_NUM;
    }

    public String getSEM_CODE() {
        return SEM_CODE;
    }

    public void setSEM_CODE(String SEM_CODE) {
        this.SEM_CODE = SEM_CODE;
    }
   
    @Override
    public String toString() {
        return MGTCLS_CODE;
    }
    
}
