/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionPool;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author ramos
 */
public class ModificateThread extends Thread {
    public int id;
    public String name;
    public String ap;
    public String tipo;
    public ModificateThread(int id, String name, String ap, String tipo){
        this.id = id;
        this.name = name;
        this.ap = ap;
        this.tipo = tipo;
        start();
    }
    public void run(){
        System.out.println("hilo iniciado");
        try{
            if(tipo.equalsIgnoreCase("Administrador")){
                String sql = String.format("UPDATE `admins` SET `name_admin` = '%s', `ap_admin` = '%s' WHERE (`id_admin` = '%s');",
                        this.name,this.ap,this.id);
                System.out.println("consulta del admin:"+sql);
                try {
                    new ConnectionPool().makeUpdate(sql);

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                JOptionPane.showMessageDialog(null, "Usuario con id: "+this.id+" actualizado con exito");
                
            }
            else if(tipo.equalsIgnoreCase("Trabajador")){
                
                String sql = String.format("UPDATE `users` SET `name_user` = '%s', `ap_user` = '%s' WHERE (`id_user` = '%s');",
                        this.name,this.ap,this.id);
                System.out.println("consulta del admin:"+sql);
                try {
                    new ConnectionPool().makeUpdate(sql);

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                JOptionPane.showMessageDialog(null, "Usuario con id: "+this.id+" actualizado con exito");
                
 
            }

        }catch(Exception exc){
            System.out.println(exc);
        }
    }
    
    
}
