/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Cls {
    private String CLS_CODE;
    private String CLS_TIME;
    private String CRS_CODE;
    private String ROOM_CODE;
    private String SEM_CODE;
    private int PROF_NUM;
    private Crs crs;
    private Room room;
    private Prof prof;
    private Sem sem;

    public Cls() {
    }

    public Cls(String CLS_CODE, String CLS_TIME, String CRS_CODE, String ROOM_CODE, String SEM_CODE, int PROF_NUM) {
        this.CLS_CODE = CLS_CODE;
        this.CLS_TIME = CLS_TIME;
        this.CRS_CODE = CRS_CODE;
        this.ROOM_CODE = ROOM_CODE;
        this.SEM_CODE = SEM_CODE;
        this.PROF_NUM = PROF_NUM;
    }

    public String getCLS_CODE() {
        return CLS_CODE;
    }

    public void setCLS_CODE(String CLS_CODE) {
        this.CLS_CODE = CLS_CODE;
    }

    public String getCLS_TIME() {
        return CLS_TIME;
    }

    public void setCLS_TIME(String CLS_TIME) {
        this.CLS_TIME = CLS_TIME;
    }

    public String getCRS_CODE() {
        return CRS_CODE;
    }

    public void setCRS_CODE(String CRS_CODE) {
        this.CRS_CODE = CRS_CODE;
    }

    public String getROOM_CODE() {
        return ROOM_CODE;
    }

    public void setROOM_CODE(String ROOM_CODE) {
        this.ROOM_CODE = ROOM_CODE;
    }

    public String getSEM_CODE() {
        return SEM_CODE;
    }

    public void setSEM_CODE(String SEM_CODE) {
        this.SEM_CODE = SEM_CODE;
    }

    public int getPROF_NUM() {
        return PROF_NUM;
    }

    public void setPROF_NUM(int PROF_NUM) {
        this.PROF_NUM = PROF_NUM;
    }

    public Crs getCrs() {
        return crs;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Sem getSem() {
        return sem;
    }

    public void setSem(Sem sem) {
        this.sem = sem;
    }
    
    
    
}
