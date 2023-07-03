/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Room {
    private String ROOM_CODE;
    private String ROOM_TYPE;
    private String BLDG_CODE;
    private Bldg bldg;

    public Room() {
    }

    public Room(String ROOM_CODE, String ROOM_TYPE, String BLDG_CODE) {
        this.ROOM_CODE = ROOM_CODE;
        this.ROOM_TYPE = ROOM_TYPE;
        this.BLDG_CODE = BLDG_CODE;
    }

    public String getROOM_CODE() {
        return ROOM_CODE;
    }

    public void setROOM_CODE(String ROOM_CODE) {
        this.ROOM_CODE = ROOM_CODE;
    }

    public String getROOM_TYPE() {
        return ROOM_TYPE;
    }

    public void setROOM_TYPE(String ROOM_TYPE) {
        this.ROOM_TYPE = ROOM_TYPE;
    }

    public String getBLDG_CODE() {
        return BLDG_CODE;
    }

    public void setBLDG_CODE(String BLDG_CODE) {
        this.BLDG_CODE = BLDG_CODE;
    }

    public Bldg getBldg() {
        return bldg;
    }

    public void setBldg(Bldg bldg) {
        this.bldg = bldg;
    }
    
}
