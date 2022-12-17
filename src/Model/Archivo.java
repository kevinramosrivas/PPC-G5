/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramos
 */
public class Archivo {
    public static Archivo archivo = new Archivo("","registro.txt");
    public String path;
    public String name;
    public boolean bandera0 = false;
    public boolean bandera1 = false;
    public int turno;
    public File file;
    public Archivo(String path, String name){
        this.path = path;
        this.name = name;
        file = new File(this.name);
    }
    
    public synchronized void escribir(String texto){
        bandera0 = true;
        turno = 1;
        while(bandera1 && turno==1){
            try{
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        //inicio sección critica
        
        
        //crear un archivo de texto
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
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
            System.out.println(e);
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
            }
        }
        
        //fin sección critica
        bandera0 = false;
        notify();
        
    }
    public synchronized List<String> leer(String nombreEmpleado){
        List<String> coincidencias = new ArrayList<>();
        bandera1 = true;
        turno =0;
        while(bandera0 && turno==0){
            try{
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        //inicio sección critica
        
        FileReader fr = null;
        BufferedReader br = null;
        System.out.println(nombreEmpleado+"solicita su información");
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           fr = new FileReader (file);
           br = new BufferedReader(fr);

           // Lectura del fichero para buscar el nombre del usuario
           String linea;
           while((linea=br.readLine())!=null){
                if(linea.contains(nombreEmpleado)){
                    coincidencias.add(linea);
                }
           }
            System.out.println(coincidencias);
        }
        catch(IOException e){
           e.printStackTrace();
           System.out.println("Hubo un error al abrir el archivo");
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (IOException e2){ 
              e2.printStackTrace();
              System.out.println("Hubo un error al cerrar el archivo");
           }
        }
        
        //fin sección critica
        bandera1 = false;
        notify();
        return coincidencias;
    }
    
}
