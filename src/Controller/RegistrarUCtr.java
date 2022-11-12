/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionPool;
import Interface.RegistrarUsuarios;
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
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


/**
 *
 * @author USUARIO
 */
public class RegistrarUCtr implements ActionListener{
    RegistrarUsuarios registraru;
    static public int adminId = 0;
    public Admin administrador = Model.Admin.administrador;
    public RegistrarUCtr(){
        registraru = new RegistrarUsuarios();
        registraru.jButton1.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int permisos_cmb,validación =0;
        String nombre,apellido, pass, permisos_string;
        
        nombre = RegistrarUsuarios.txt_nombre.getText().trim();
        apellido = RegistrarUsuarios.txt_apellido.getText().trim();
        pass = RegistrarUsuarios.txt_contraseña.getText().trim();
        permisos_cmb = RegistrarUsuarios.cmb_niveles.getSelectedIndex()+1 ;
        
        //Validación de campos
        
        if(nombre.isEmpty()||apellido.isEmpty()||pass.isEmpty()){
            JOptionPane.showMessageDialog(null,"Completar los datos faltantes");
            validación++;
        } else { 
                if(permisos_cmb.equalsIgnoreCase("Seleccionar")){
                    
                }
     
        
        if (permisos_cmb==1){
            permisos_string = "Administrador";
        }else if (permisos_cmb==2){
            permisos_string = "Trabajador";
        }
        
        
        
        
        
        this.registraru.prob.setText("");
        int id = Integer.parseInt(this.registraru.id.getText());
        int pass = Integer.parseInt(new String(this.registraru.pass.getPassword()));
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
    
    
    
}

