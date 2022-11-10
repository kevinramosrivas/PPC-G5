/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionPool;
import Interface.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginCtr implements ActionListener{
    Login loginui;
    private int adminId = 0;
    public LoginCtr(){
        loginui = new Login();
        loginui.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = Integer.parseInt(this.loginui.id.getText());
        searchAdmin(id);
        loginui.dispose();
    }
    public void searchAdmin(int id){
        List<Map<String, Object>> resultList = new ArrayList<>();
        String sql = String.format("select * from admins where id_admin=%s",id);
        try {
            resultList = new ConnectionPool().makeConsult(sql);
            this.adminId = (int) resultList.get(0).get("id_admin");
            System.out.println(resultList);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public Integer getAdminId(){
        return this.adminId;
    } 
}
