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
    private Bldg bldg;

    public Room() {
    }

    public Room(String ROOM_CODE, String ROOM_TYPE, Bldg bldg) {
        this.ROOM_CODE = ROOM_CODE;
        this.ROOM_TYPE = ROOM_TYPE;
        this.bldg = bldg;
    }

    public String getROOM_CODE() {
        return ROOM_CODE;
    }

    public String getROOM_TYPE() {
        return ROOM_TYPE;
    }

    public Bldg getBldg() {
        return bldg;
    }

    public void setROOM_CODE(String ROOM_CODE) {
        this.ROOM_CODE = ROOM_CODE;
    }

    public void setROOM_TYPE(String ROOM_TYPE) {
        this.ROOM_TYPE = ROOM_TYPE;
    }

    public void setBldg(Bldg bldg) {
        this.bldg = bldg;
    }
    
}
