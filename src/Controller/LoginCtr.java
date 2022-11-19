/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionPool;
import Interface.Login;
import Interface.admin_menu;
import Interface.user_menu;
import Model.Admin;
import Model.User;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginCtr implements ActionListener{
    Login loginui;
    private String type="";
    public Admin administrador = Model.Admin.administrador;
    public User usuario = Model.User.usuario;
    public LoginCtr(String type){
        this.type=type;
        loginui = new Login();
        loginui.btnLogin.addActionListener(this);
        loginui.tituloLogin.setText("LOGIN DE "+type.toUpperCase());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.loginui.prob.setText("");
        int id = Integer.parseInt(this.loginui.id.getText());
        String pass = new String(this.loginui.pass.getPassword());
        boolean empty = searchAdmin(id,pass);
        if (!empty){
            loginui.dispose();
        }
        if ("admins".equals(this.type)){
            admin_menu admin = new admin_menu();
        }
        if("users".equals(this.type)){
            user_menu user = new user_menu();
        }
    }
    public boolean searchAdmin(int id, String pass){
        String type2 = this.type.substring(0, this.type.length()-1); //Substring user o admin
        List<Map<String, Object>> resultList = new ArrayList<>();
        String sql = String.format("select * from %s where id_%s=%s and pass_%s=%s",
                this.type,type2,id, 
                type2,pass);
        try {
            resultList = new ConnectionPool().makeConsult(sql);
            if (resultList.isEmpty()) {
                this.loginui.prob.setText("Datos incorrectos");
                this.loginui.prob.setForeground(Color.red);
            } else{
                if ("admins".equals(this.type)){
                    this.administrador.setName_admin(String.valueOf(resultList.get(0).get("name_"+type2)));
                    this.administrador.setAp_admin(String.valueOf(resultList.get(0).get("ap_"+type2)));
                    this.administrador.setPass_admin(String.valueOf(resultList.get(0).get("pass_"+type2)));
                    this.administrador.setId_admin((int) resultList.get(0).get("id_"+type2));
                    System.out.println(resultList);
                }
                else if("users".equals(this.type)){
                    this.usuario.setName_user(String.valueOf(resultList.get(0).get("name_"+type2)));
                    this.usuario.setAp_user(String.valueOf(resultList.get(0).get("ap_"+type2)));
                    this.usuario.setPass_user(String.valueOf(resultList.get(0).get("pass_"+type2)));
                    this.usuario.setId_user((int) resultList.get(0).get("id_"+type2));
                    System.out.println(resultList);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultList.isEmpty();
    }
}
