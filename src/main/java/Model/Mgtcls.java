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
    private Prof prof;
    private Sem sem;

    public Mgtcls() {

    }
    
    public Mgtcls(String MGTCLS_CODE, Prof prof, Sem sem) {
        this.MGTCLS_CODE = MGTCLS_CODE;
        this.prof = prof;
        this.sem = sem;
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
   
    
}
