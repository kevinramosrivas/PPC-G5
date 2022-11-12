/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Interface.PrintReport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author ramos
 */
public class PrintReportCtr implements ActionListener{
    PrintReport printnui;
    public PrintReportCtr(){
        printnui = new PrintReport();
        printnui.btnImprimir.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.printnui.btnImprimir){
            if(this.printnui.jCheckBox1.isSelected()){
              WriterThread hilo1 = new WriterThread("1");
              hilo1.start();
            }
            if(this.printnui.jCheckBox2.isSelected()){
              WriterThread hilo2 = new WriterThread("2");
              hilo2.start();
            }
            if(this.printnui.jCheckBox3.isSelected()){
              WriterThread hilo3 = new WriterThread("3");
              hilo3.start();
            }
            if(this.printnui.jCheckBox4.isSelected()){
              WriterThread hilo4 = new WriterThread("4");
              hilo4.start();
            }
            if(this.printnui.jCheckBox5.isSelected()){
              WriterThread hilo5 = new WriterThread("5");
              hilo5.start();
            } 
        }
        
    }
    
}
