/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author DunogPnahm
 */
public class Sem {
    private String SEM_CODE;
    private int SEM_YEAR;
    private Date SEM_START_DATE;
    private Date SEM_END_DATE;

    public Sem() {

    }

    public Sem(String SEM_CODE, int SEM_YEAR, Date SEM_START_DATE, Date SEM_END_DATE) {
        this.SEM_CODE = SEM_CODE;
        this.SEM_YEAR = SEM_YEAR;
        this.SEM_START_DATE = SEM_START_DATE;
        this.SEM_END_DATE = SEM_END_DATE;
    }

    public String getSEM_CODE() {
        return SEM_CODE;
    }

    public void setSEM_CODE(String SEM_CODE) {
        this.SEM_CODE = SEM_CODE;
    }

    public int getSEM_YEAR() {
        return SEM_YEAR;
    }

    public void setSEM_YEAR(int SEM_YEAR) {
        this.SEM_YEAR = SEM_YEAR;
    }

    public Date getSEM_START_DATE() {
        return SEM_START_DATE;
    }

    public void setSEM_START_DATE(Date SEM_START_DATE) {
        this.SEM_START_DATE = SEM_START_DATE;
    }

    public Date getSEM_END_DATE() {
        return SEM_END_DATE;
    }

    public void setSEM_END_DATE(Date SEM_END_DATE) {
        this.SEM_END_DATE = SEM_END_DATE;
    }
    
}
