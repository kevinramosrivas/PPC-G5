/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Interface.Graficos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ramos
 */
public class GraphicBarThreadLab extends Thread{
    public Graficos grafico;
    public List<Map<String, Object>> datos;
    public int operativo;
    public int defectuosa;
    public int mantenimiento;
    public GraphicBarThreadLab(Graficos grafico, List<Map<String, Object>> datos) {
        this.grafico = grafico;
        this.datos = datos;
        this.mantenimiento = 0;
        this.defectuosa = 0;
        this.operativo = 0 ;
        
    }
    @Override
    public void run(){
        // ver cuanto tiempo demora en ejecutarse el hilo
        long tiempoInicial = System.currentTimeMillis();
        int numPcLab1 = 0;
        int numPcLab2 = 0;
        int numPcLab3 = 0;
        int numPcLab4 = 0;
        int numPcLab5 = 0;
        int numPcLab6 = 0;
        int numPcLab7 = 0;

        for (int i=0;i<datos.size();i++){
            switch (datos.get(i).get("id_lab").toString()) {
                case "1":
                    numPcLab1++;
                    break;
                case "2":
                    numPcLab2++;
                    break;
                case "3":
                    numPcLab3++;
                    break;
                case "4":
                    numPcLab4++;
                    break;
                case "5":
                    numPcLab5++;
                    break;
                case "6":
                    numPcLab6++;
                    break;
                case "7":
                    numPcLab7++;
                    break;                  
            }
            

        }
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(numPcLab1, "Lab 1", "Laboratorio 1");
        datos.setValue(numPcLab2, "Lab 2", "Laboratorio 2");
        datos.setValue(numPcLab3, "Lab 3", "Laboratorio 3");
        datos.setValue(numPcLab4, "Lab 4", "Laboratorio 4");
        datos.setValue(numPcLab5, "Lab 5", "Laboratorio 5");
        datos.setValue(numPcLab6, "Lab 6", "Laboratorio 6");
        datos.setValue(numPcLab7, "Lab 7", "Laboratorio 7");
        JFreeChart grafico_barras = ChartFactory.createBarChart3D("Cantidad de computadoras por laboratorio",
                "Laboratorio", 
                "Cantidad", datos, 
                PlotOrientation.HORIZONTAL, true, 
                true, false
                );
        ChartPanel panel = new ChartPanel(grafico_barras);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(400,250));
        
        grafico.jPanel3.setLayout(new BorderLayout());
        grafico.jPanel3.add(panel,BorderLayout.NORTH);
        
        grafico.pack();
        // saber cuanto tiempo se demora en ejecutar el codigo
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("Tiempo de ejecucion: " + (tiempoFinal - tiempoInicial) + " milisegundos");
        
        
    }
    
    public void sumarLabo(){
        
    }
        
}
