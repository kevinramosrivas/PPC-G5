/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interface.RegisterUser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Connection.ConnectionPool;
import java.sql.SQLException;

/**
 *
 * @author ramos
 */
public class RegisterCtr implements ActionListener{
    RegisterUser registerui;
    public RegisterCtr(){
        registerui = new RegisterUser();
        registerui.btnRegister.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.registerui.btnRegister){
            int permisos_cmb;
            boolean isValidate;
            String nombre,apellido, pass, permisos_string = "";
            nombre = this.registerui.txt_nombre.getText().trim();
            apellido = this.registerui.txt_apellido.getText().trim();
            pass = this.registerui.txt_contraseña.getText().trim();
            permisos_cmb = registerui.cmb_niveles.getSelectedIndex()+1 ;
            if (permisos_cmb==1){
                permisos_string = "Administrador";
            }else if (permisos_cmb==2){
                permisos_string = "Trabajador";
            }
            isValidate = this.validar(nombre, apellido, pass);
            if(isValidate){
                if(permisos_string.equalsIgnoreCase("Administrador")){
                    System.out.println("hola soy un admin validado");
                    String sql = String.format("INSERT INTO `admins`(`name_admin`, `ap_admin`, `pass_admin`) VALUES ('%s','%s','%s')",nombre,apellido,pass);
                    System.out.println(sql);
                    try {
                        new ConnectionPool().makeUpdate(sql);

                    }catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
                if(permisos_string.equalsIgnoreCase("Trabajador")){
                    System.out.println("hola soy un trabajador validado");
                    String sql = String.format("INSERT INTO `users`(`name_user`, `ap_user`, `pass_user`) VALUES ('%s','%s','%s')",nombre,apellido,pass);
                    try {
                        new ConnectionPool().makeUpdate(sql);

                    }catch (SQLException ex) {
                        System.out.println(ex);
                    }
                }
            }
            
        }
    }
    public boolean validar(String nombre,String apellido,String pass){
        int numValidate =0;
        if(nombre.equals("")){
            this.registerui.txt_nombre.setBackground(Color.red);
            numValidate++;
        }
        
        if(apellido.equals("")){
            this.registerui.txt_apellido.setBackground(Color.red);
            numValidate++;
        }
        
        if(pass.equals("")){
            this.registerui.txt_contraseña.setBackground(Color.red);
            numValidate++;
        }
        return numValidate <= 0;
            
    }
        
    
}
