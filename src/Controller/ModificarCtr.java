/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interface.ModificarUsuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Connection.ConnectionPool;
import Model.Admin;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;


/**
 *
 * @author USUARIO
 */
public class ModificarCtr implements ActionListener {
    
    public static String user_update = "";
    ModificarUsuario modificarui;
    public ModificarCtr(){
        modificarui = new ModificarUsuario();
        modificarui.btn_actualizar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()== this.modificarui.btn_actualizar){
            DefaultTableModel model = new DefaultTableModel();
            boolean isValidate;
            int permisos_cmb = modificarui.cmb_niveles.getSelectedIndex()+1 ;
            String permisos_string = "";
            
            if (permisos_cmb==1){
                permisos_string = "Administrador";
            }else if (permisos_cmb==2){
                permisos_string = "Trabajador";
            }
            
            
            if(permisos_string.equalsIgnoreCase("Administrador")){
                    System.out.println("Consular usuario administrador");
                    String sql = String.format(
                            "select id_admin, name_admin, ap_admin , pass_admin from admins");
                    System.out.println(sql);
                    try {
                        new ConnectionPool().makeUpdate(sql);
                        modificarui.jTable_usuarios = new JTable();
                        modificarui.
                    }catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    
                    
            
            
            }
        }
    }        


