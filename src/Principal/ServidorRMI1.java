
package Principal;
import Connection.ConnectionPool;
import Controller.RMI;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ServidorRMI1 extends UnicastRemoteObject implements Controller.RMI{

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
            System.out.println("Consulta realizada por"+usuario);
        } catch (SQLException ex) {
            Logger.getLogger(ServidorRMI1.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        //guardar las modificaciones en un archivo de texto
        String texto = String.format("Lab: %s, PC: %s, Estado: %s, Observaciones: %s, Usuario: %s",
        lab,comp,estado,obs,usuario);
        
        //crear un archivo de texto
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File("registro.txt");
            // Si el archivo no existe, se crea!
            if (!file.exists()) {
                file.createNewFile();
            }
            // flag true, indica adjuntar información al archivo.
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write("\n"+texto);
            System.out.println("información agregada!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }



}
