/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interface.VentanaCliente;
import Interface.VentanaServidor;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author ramos
 */
public class ChatCtrUser extends Thread
{
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;  
    private DataOutputStream salida;          
    private BufferedReader entrada;           
    final int puerto = 8000;
    private final AtomicBoolean running = new AtomicBoolean(false);
    
    public ChatCtrUser(String ip)
    {
        try{

           s=new Socket(ip,this.puerto);      
           entradaSocket = new InputStreamReader(s.getInputStream());
           entrada = new BufferedReader(entradaSocket);       
           salida = new DataOutputStream(s.getOutputStream());

         }catch(Exception e){
             System.out.println(e);
         };
     }
    public void parar() {
        running.set(false);
    }
     public void run()
     {             
         String texto="";
         running.set(true);
         while(running.get())
         {
            try{
                  texto=entrada.readLine();
                  Interface.VentanaCliente. jTextArea1.setText(Interface.VentanaCliente.jTextArea1.getText()+"\n"+texto);
            }catch(IOException e){
                    System.out.println(e);
            };
         }
     }
     public void enviarMSG(String msg)
     {
        try{
            salida.writeUTF(msg+"\n");
        }catch(IOException e){
            System.out.println(e);
        };
     }
   
     public String leerMSG()
     { 
       try{
            return entrada.readLine();
          }catch(IOException e){
              System.out.println(e);
          };
       return null;
     }
     
     public void desconectar()              
     {  
        try{
            s.close();
           }catch(IOException e){
               System.out.println(e);
           };
     }   
}