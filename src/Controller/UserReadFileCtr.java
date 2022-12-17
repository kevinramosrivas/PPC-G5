/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interface.ModificationReport;
import Model.User;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mildr
 */
public class UserReadFileCtr {
    ModificationReport reporte;
    String acumulador = "";
    public User usuario = Model.User.usuario;
    public UserReadFileCtr(){
        // Por implementar
        reporte = new ModificationReport();
    }
    public void getData(){
        // se añade la conexion al servidor RMI
        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7777);
            RMI interfaz = (RMI) registro.lookup("RemotoRMI");
            
            List<String> respuesta = interfaz.leerModificaciones(usuario.getName_user());
            for (String linea : respuesta) {
                acumulador = acumulador + linea + "\n";
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        reporte.jTextModification.setText(acumulador);
        
    }
}