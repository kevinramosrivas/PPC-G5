/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ramos
 */
public class User {
    private int id_user;
    private String name_user;
    private String ap_user;
    private String pass_user;
    public static User usuario = new User();

    public int getId_user() {
        return id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public String getAp_user() {
        return ap_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setAp_user(String ap_user) {
        this.ap_user = ap_user;
    }

    public void setPass_user(String pass_user) {
        this.pass_user = pass_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }
    

    
}
