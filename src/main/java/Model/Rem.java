/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Rem {
    private int REM_NUM;
    private String REM_DESC;
    private int EMP_NUM;
    private Emp emp;

    public Rem() {
    }

    public Rem(int REM_NUM, String REM_DESC, int EMP_NUM) {
        this.REM_NUM = REM_NUM;
        this.REM_DESC = REM_DESC;
        this.EMP_NUM = EMP_NUM;
    }

    public int getREM_NUM() {
        return REM_NUM;
    }

    public void setREM_NUM(int REM_NUM) {
        this.REM_NUM = REM_NUM;
    }

    public String getREM_DESC() {
        return REM_DESC;
    }

    public void setREM_DESC(String REM_DESC) {
        this.REM_DESC = REM_DESC;
    }

    public int getEMP_NUM() {
        return EMP_NUM;
    }

    public void setEMP_NUM(int EMP_NUM) {
        this.EMP_NUM = EMP_NUM;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }
    
    
}
