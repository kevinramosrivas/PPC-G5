
package Principal;
import Connection.ConnectionPool;
import Controller.RMI;
import Model.Archivo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ServidorRMI1 extends UnicastRemoteObject implements Controller.RMI{
    public Archivo archivo = Model.Archivo.archivo;
    public ServidorRMI1() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("RemotoRMI", new ServidorRMI1());
            System.out.println("Servidor Activo !!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean modificarPc(String lab, String comp, String estado, String obs, String usuario) throws RemoteException {
        String sql = String.format("update computer set id_lab='%s', estado='%s',"
            + "fecha_mod=CURRENT_TIMESTAMP, obs='%s' where id_pc='%s'", 
        Integer.parseInt(lab),estado,obs,Integer.parseInt(comp));
        try {
            new ConnectionPool().makeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorRMI1.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        //guardar las modificaciones en un archivo de texto
        String texto = String.format("%s, PC: %s, Estado: %s, Observaciones: %s, Lab: %s",
        usuario,comp,estado,obs,lab);
        archivo.escribir(texto);
        return true;
    }

    @Override
    public List<String> leerModificaciones(String nombreEmpleado) throws RemoteException {
        List<String> leer = archivo.leer(nombreEmpleado);
        return leer;
    }



}
