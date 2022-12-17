package Controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMI extends Remote {

    public boolean modificarPc(String lab,String comp,String estado,String obs,String usuario) throws RemoteException;
    public List<String> leerModificaciones(String nombreEmpleado)throws RemoteException;

}
