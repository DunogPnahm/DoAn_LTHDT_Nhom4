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
public class Enroll {
    private Stu stu;
    private Cls cls;
    private Date ENROLL_DATE;
    private float ENROLL_GRADE;
    
    public Enroll(){
    }

    public Enroll(Stu stu, Cls cls, Date ENROLL_DATE, float ENROLL_GRADE) {
        this.stu = stu;
        this.cls = cls;
        this.ENROLL_DATE = ENROLL_DATE;
        this.ENROLL_GRADE = ENROLL_GRADE;
    }

    public Stu getStu() {
        return stu;
    }

    public Cls getCls() {
        return cls;
    }

    public Date getENROLL_DATE() {
        return ENROLL_DATE;
    }

    public float getENROLL_GRADE() {
        return ENROLL_GRADE;
    }

    public void setStu(Stu stu) {
        this.stu = stu;
    }

    public void setCls(Cls cls) {
        this.cls = cls;
    }

    public void setENROLL_DATE(Date ENROLL_DATE) {
        this.ENROLL_DATE = ENROLL_DATE;
    }

    public void setENROLL_GRADE(float ENROLL_GRADE) {
        this.ENROLL_GRADE = ENROLL_GRADE;
    }
    
}
