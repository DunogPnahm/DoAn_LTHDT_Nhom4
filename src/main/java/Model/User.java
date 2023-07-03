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
    private Prof prof;
    private Emp emp;
    private Stu stu;
    
    static private User uniqueInstance;

    protected User() {
    }

    static public void setNull()
    {
        uniqueInstance = null;
    }
    
    protected User(String USER_ACC, String USER_PSWD, String USER_ROLE, int USER_ID) {
        this.USER_ACC = USER_ACC;
        this.USER_PSWD = USER_PSWD;
        this.USER_ROLE = USER_ROLE;
        this.USER_ID = USER_ID;
    }
    
    static public User getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new User();
        }
        return uniqueInstance;
    }
    
    static public User getInstance(String USER_ACC, String USER_PSWD, String USER_ROLE, int USER_ID){
        if(uniqueInstance == null){
            uniqueInstance = new User(USER_ACC, USER_PSWD, USER_ROLE, USER_ID);
        }
        return uniqueInstance;
    }

    public String getUSER_ACC() {
        return USER_ACC;
    }

    public void setUSER_ACC(String USER_ACC) {
        this.USER_ACC = USER_ACC;
    }

    public String getUSER_PSWD() {
        return USER_PSWD;
    }

    public void setUSER_PSWD(String USER_PSWD) {
        this.USER_PSWD = USER_PSWD;
    }

    public String getUSER_ROLE() {
        return USER_ROLE;
    }

    public void setUSER_ROLE(String USER_ROLE) {
        this.USER_ROLE = USER_ROLE;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Stu getStu() {
        return stu;
    }

    public void setStu(Stu stu) {
        this.stu = stu;
    }

    
}
