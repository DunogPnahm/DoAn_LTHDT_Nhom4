/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Pre {
    private Crs pre;
    private Crs crs;

    public Pre() {
    }

    public Pre(Crs pre, Crs crs) {
        this.pre = pre;
        this.crs = crs;
    }

    public Crs getPre() {
        return pre;
    }

    public Crs getCrs() {
        return crs;
    }

    public void setPre(Crs pre) {
        this.pre = pre;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }
    
}
