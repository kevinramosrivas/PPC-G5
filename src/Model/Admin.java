/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ramos
 */
public class Admin {
    private int id_admin;
    private String name_admin;
    private String ap_admin;
    private String pass_admin;
    public static Admin administrador = new Admin();

    public int getId_admin() {
        return id_admin;
    }

    public String getName_admin() {
        return name_admin;
    }

    public String getAp_admin() {
        return ap_admin;
    }

    public String getPass_admin() {
        return pass_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setName_admin(String name_admin) {
        this.name_admin = name_admin;
    }

    public void setAp_admin(String ap_admin) {
        this.ap_admin = ap_admin;
    }

    public void setPass_admin(String pass_admin) {
        this.pass_admin = pass_admin;
    }
    
    
    
    
}
