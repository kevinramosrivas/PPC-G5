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
import java.awt.event.MouseListener;
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
    public ModificarUsuario modificarui;
    public DefaultTableModel model = new DefaultTableModel();
    public List<ModificateThread> hilos = new ArrayList<ModificateThread>();
    public int id = -1;
    public String nombre = null;
    public String apellido = null;
    public String contrasena = null;
    public String permisos_string = "";
    public ModificarCtr(){
        modificarui = new ModificarUsuario();
        modificarui.btn_buscar.addActionListener(this);
        modificarui.btn_actualizar.addActionListener(this);
        modificarui.btnLlenar.addActionListener(this);
        modificarui.jTable_usuarios = new JTable(model);
        modificarui.jScrollPane1.setViewportView(modificarui.jTable_usuarios);
        model.addColumn("Id");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        
    }
    public void limpiarTabla(DefaultTableModel modelo ,JTable tabla){
        for (int i = 0; i < tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i-=1;
        }
    }
    public void actionPerformed(ActionEvent e) {
        
            if(e.getSource() == modificarui.btn_buscar){
                
                limpiarTabla(model,modificarui.jTable_usuarios);
                int permisos_cmb = modificarui.cmb_niveles.getSelectedIndex()+1 ;
                if (permisos_cmb==1){
                    permisos_string = "Administrador";
                }else if (permisos_cmb==2){
                    permisos_string = "Trabajador";
                }

                if(permisos_string.equalsIgnoreCase("Administrador")){
                    try{
                        System.out.println("Consular usuario administrador");
                        List<Map<String, Object>> resultList = new ArrayList<>();
                        String sql = String.format(
                              //   "SELECT * FROM admins where id_admin=%");
                                "select id_admin, name_admin, ap_admin , pass_admin from admins");
                        System.out.println(sql);

                        try {
                            resultList = new ConnectionPool().makeConsult(sql);

                            for (int i=0;i<resultList.size();i++){
                                model.addRow(new Object[]{String.valueOf(resultList.get(i).get("id_admin")),
                                    String.valueOf(resultList.get(i).get("name_admin")),
                                    String.valueOf(resultList.get(i).get("ap_admin"))
                                });

                            }

                        } catch (SQLException ex) {
                            System.err.println("Error al llenar tabla"+ ex);
                            JOptionPane.showMessageDialog(null, "Error al mostrar información, Contactar al administrador");
                            //System.out.println(ex);
                        }
                    }catch (Exception exc) {
                            System.err.println(exc);
                    }
                }
                else if(permisos_string.equalsIgnoreCase("Trabajador")){
                    try{
                        System.out.println("Consular usuario trabajador");
                        List<Map<String, Object>> resultList = new ArrayList<>();
                        String sql = String.format(
                              //   "SELECT * FROM admins where id_admin=%");
                                "SELECT * FROM `users`");
                        System.out.println(sql);

                        try {
                            resultList = new ConnectionPool().makeConsult(sql);

                            for (int i=0;i<resultList.size();i++){
                                model.addRow(new Object[]{String.valueOf(resultList.get(i).get("id_user")),
                                    String.valueOf(resultList.get(i).get("name_user")),
                                    String.valueOf(resultList.get(i).get("ap_user"))
                                });

                            }

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
            if(e.getSource() == modificarui.btn_actualizar){
                nombre = modificarui.txt_nombre.getText();
                apellido = modificarui.txt_apellido.getText();
                if(hilos.size()<5){
                   hilos.add(new ModificateThread(id,nombre,apellido,permisos_string));
                }
                else{
                    System.out.println("Numero maximo de peticiones");
                }
            }
            if(e.getSource() == modificarui.btnLlenar){
               int row = modificarui.jTable_usuarios.getSelectedRow();
               id = Integer.parseInt(modificarui.jTable_usuarios.getValueAt(row, 0).toString());
               nombre = modificarui.jTable_usuarios.getValueAt(row, 1).toString();
               apellido = modificarui.jTable_usuarios.getValueAt(row, 2).toString();
               modificarui.txt_nombre.setText(nombre);
               modificarui.txt_apellido.setText(apellido);
               
            }
        }

}




