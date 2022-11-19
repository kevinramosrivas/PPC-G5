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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author USUARIO
 */
public class ModificarCtr implements ActionListener {
    
    public static String user_update = "";
    ModificarUsuario modificarui;
    public ModificarCtr(){
        modificarui = new ModificarUsuario();
        modificarui.jButton3.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
            DefaultTableModel model = new DefaultTableModel();
            int permisos_cmb = modificarui.cmb_niveles.getSelectedIndex()+1 ;
            String permisos_string = "";
            
            if (permisos_cmb==1){
                permisos_string = "Administrador";
            }else if (permisos_cmb==2){
                permisos_string = "Trabajador";
            }
                        
            if(permisos_string.equalsIgnoreCase("Administrador")){
                    
                try{
                    System.out.println("Consular usuario administrador");
                    
                    modificarui.jTable_usuarios = new JTable(model);
                    modificarui.jScrollPane1.setViewportView(modificarui.jTable_usuarios);
                        
                    model.addColumn("Id");
                    model.addColumn("Nombre");
                    model.addColumn("Apellidos");
                    model.addColumn("Contraseña");
                    List<Map<String, Object>> resultList = new ArrayList<>();
                    String sql = String.format(
                          //   "SELECT * FROM admins where id_admin=%");
                            "select id_admin, name_admin, ap_admin , pass_admin from admins");
                    System.out.println(sql);
                    
                    try {
                        resultList = new ConnectionPool().makeConsult(sql);
                        
                        for (int i=0;i<resultList.size();i++){
                            model.addColumn(String.valueOf(resultList.get(i).get("id_admin")));
                            model.addColumn(String.valueOf(resultList.get(i).get("name_admin")));
                            model.addColumn(String.valueOf(resultList.get(i).get("ap_admin")));
                            model.addColumn(String.valueOf(resultList.get(i).get("pass_admin")));
                           
                        }    
                        
                        //model.addRow(model);
                       
                        
                    } catch (SQLException ex) {
                        System.err.println("Error al llenar tabla"+ ex);
                        JOptionPane.showMessageDialog(null, "Error al mostrar información, Contactar al administrador");
                        //System.out.println(ex);
                    }
                }catch (Exception exc) {
                        System.err.println(exc);
                }
            }
        }
    }




