/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionPool;
import Interface.Login;
import Interface.admin_menu;
import Model.Admin;
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
    static public int adminId = 0;
    public Admin administrador = Model.Admin.administrador;
    public LoginCtr(){
        loginui = new Login();
        loginui.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.loginui.prob.setText("");
        int id = Integer.parseInt(this.loginui.id.getText());
        int pass = Integer.parseInt(new String(this.loginui.pass.getPassword()));
        boolean empty = searchAdmin(id,pass);
        if (!empty){
            admin_menu admin = new admin_menu();
            loginui.dispose();
        }
    }
    public boolean searchAdmin(int id, int pass){
        List<Map<String, Object>> resultList = new ArrayList<>();
        String sql = String.format("select * from admins where id_admin=%s and pass_admin=%s",
                id,pass);
        try {
            resultList = new ConnectionPool().makeConsult(sql);
            if (resultList.isEmpty()) {
                this.loginui.prob.setText("Datos incorrectos");
                this.loginui.prob.setForeground(Color.red);
            } else{
                this.adminId = (int) resultList.get(0).get("id_admin");
                this.administrador.setId_admin((int) resultList.get(0).get("id_admin"));
                System.out.println(resultList);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return resultList.isEmpty();
    }
    public Integer getAdminId(){
        return this.adminId;
    } 
}
