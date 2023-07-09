/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Bldg {
    private String BLDG_CODE;
    private String BLDG_LOCATION;

    public Bldg() {
    }

    public Bldg(String BLDG_CODE, String BLDG_LOCATION) {
        this.BLDG_CODE = BLDG_CODE;
        this.BLDG_LOCATION = BLDG_LOCATION;
    }

    public String getBLDG_CODE() {
        return BLDG_CODE;
    }

    public String getBLDG_LOCATION() {
        return BLDG_LOCATION;
    }

    public void setBLDG_CODE(String BLDG_CODE) {
        this.BLDG_CODE = BLDG_CODE;
    }

    public void setBLDG_LOCATION(String BLDG_LOCATION) {
        this.BLDG_LOCATION = BLDG_LOCATION;
    }
    
}
