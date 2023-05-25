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
    private Crs crs;
    private Room room;
    private Sem sem;
    private Prof prof;

    public Cls() {
    }

    public Cls(String CLS_CODE, String CLS_TIME, Crs crs, Room room, Sem sem, Prof prof) {
        this.CLS_CODE = CLS_CODE;
        this.CLS_TIME = CLS_TIME;
        this.crs = crs;
        this.room = room;
        this.sem = sem;
        this.prof = prof;
    }

    public String getCLS_CODE() {
        return CLS_CODE;
    }

    public String getCLS_TIME() {
        return CLS_TIME;
    }

    public Crs getCrs() {
        return crs;
    }

    public Room getRoom() {
        return room;
    }

    public Sem getSem() {
        return sem;
    }

    public Prof getProf() {
        return prof;
    }

    public void setCLS_CODE(String CLS_CODE) {
        this.CLS_CODE = CLS_CODE;
    }

    public void setCLS_TIME(String CLS_TIME) {
        this.CLS_TIME = CLS_TIME;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSem(Sem sem) {
        this.sem = sem;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }
    
}
