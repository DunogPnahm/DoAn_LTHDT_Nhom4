/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class Task {
    private String TASK_CODE;
    private String TASK_DESC;
    private String CLS_CODE;
    private Cls cls;

    public Task() {
    }

    public Task(String TASK_CODE, String TASK_DESC, String CLS_CODE) {
        this.TASK_CODE = TASK_CODE;
        this.TASK_DESC = TASK_DESC;
        this.CLS_CODE = CLS_CODE;
    }

    public String getTASK_CODE() {
        return TASK_CODE;
    }

    public void setTASK_CODE(String TASK_CODE) {
        this.TASK_CODE = TASK_CODE;
    }

    public String getTASK_DESC() {
        return TASK_DESC;
    }

    public void setTASK_DESC(String TASK_DESC) {
        this.TASK_DESC = TASK_DESC;
    }

    public String getCLS_CODE() {
        return CLS_CODE;
    }

    public void setCLS_CODE(String CLS_CODE) {
        this.CLS_CODE = CLS_CODE;
    }

    public Cls getCls() {
        return cls;
    }

    public void setCls(Cls cls) {
        this.cls = cls;
    }
    
    
}
