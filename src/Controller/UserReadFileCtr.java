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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mildr
 */
public class UserReadFileCtr {
    ModificationReport reporte;
    String acumulador = "";
    public User usuario = Model.User.usuario;
    public DefaultTableModel model = new DefaultTableModel();
    public UserReadFileCtr(){
        // Por implementar
        reporte = new ModificationReport();
        // añadir columnas a la tabla
        model.addColumn("Id PC");
        model.addColumn("Estado");
        model.addColumn("Modificacion");
        model.addColumn("Laboratorio");
        reporte.jTableModifications.setModel(model);
    }
    public void getData(){
        // se añade la conexion al servidor RMI
        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7777);
            RMI interfaz = (RMI) registro.lookup("RemotoRMI");
            
            List<String> respuesta = interfaz.leerModificaciones(usuario.getName_user());
            for (String string : respuesta) {
                String[] datos = string.split(",");
                //consideramos todo excepto el nombre
                model.addRow(new Object[]{datos[1], datos[2], datos[3], datos[4]});
            }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
}