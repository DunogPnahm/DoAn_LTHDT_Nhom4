/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DunogPnahm
 */
public class User {
    private String USER_ACC;
    private String USER_PSWD;
    private String USER_ROLE;
    private int USER_ID;

    public User() {
    }

    public User(String USER_ACC, String USER_PSWD, String USER_ROLE, int USER_ID) {
        this.USER_ACC = USER_ACC;
        this.USER_PSWD = USER_PSWD;
        this.USER_ROLE = USER_ROLE;
        this.USER_ID = USER_ID;
    }

    public User(String USER_ACC, String USER_PSWD, String USER_ROLE, Emp emp) {
        this.USER_ACC = USER_ACC;
        this.USER_PSWD = USER_PSWD;
        this.USER_ROLE = emp.getEMP_JOBCODE();
        this.USER_ID = emp.getEMP_NUM();
    }
    
    public User(String USER_ACC, String USER_PSWD, String USER_ROLE, Prof prof) {
        this.USER_ACC = USER_ACC;
        this.USER_PSWD = USER_PSWD;
        this.USER_ROLE = prof.getEmp().getEMP_JOBCODE();
        this.USER_ID = prof.getEmp().getEMP_NUM();
    }
    
    public User(String USER_ACC, String USER_PSWD, String USER_ROLE, Stu stu) {
        this.USER_ACC = USER_ACC;
        this.USER_PSWD = USER_PSWD;
        this.USER_ROLE = "STU";
        this.USER_ID = stu.getSTU_NUM();
    }

    public String getUSER_ACC() {
        return USER_ACC;
    }

    public String getUSER_PSWD() {
        return USER_PSWD;
    }

    public String getUSER_ROLE() {
        return USER_ROLE;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ACC(String USER_ACC) {
        this.USER_ACC = USER_ACC;
    }

    public void setUSER_PSWD(String USER_PSWD) {
        this.USER_PSWD = USER_PSWD;
    }

    public void setUSER_ROLE(String USER_ROLE) {
        this.USER_ROLE = USER_ROLE;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }
    
}
