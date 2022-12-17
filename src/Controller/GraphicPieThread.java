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
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ramos
 */
public class GraphicPieThread extends Thread{
    // ver cuanto tiempo demora en ejecutarse el hilo
    long tiempoInicial = System.currentTimeMillis();
    public Graficos grafico;
    public List<Map<String, Object>> datos;
    public int operativo;
    public int defectuosa;
    public int mantenimiento;
    public GraphicPieThread(Graficos grafico, List<Map<String, Object>> datos) {
        this.grafico = grafico;
        this.datos = datos;
        this.mantenimiento = 0;
        this.defectuosa = 0;
        this.operativo = 0 ;
        
    }
    @Override
    public void run(){
        for (int i=0;i<datos.size();i++){
            switch (datos.get(i).get("estado").toString()) {
                case "operativo":
                    operativo++;
                    break;
                case "defectuosa":
                    defectuosa++;
                    break;
                default:
                    mantenimiento++;
                    break;
            }
            

        }
        DefaultPieDataset pieGraphic = new DefaultPieDataset();
        pieGraphic.setValue("PC operativa",operativo);
        pieGraphic.setValue("PC defectuosa",defectuosa);
        pieGraphic.setValue("PC en mantenimiento",mantenimiento);

        JFreeChart grafico_circular = ChartFactory.createPieChart(
                "Estados de las PC",
                pieGraphic,
                true,
               true,
                true
        );

        ChartPanel panel = new ChartPanel(grafico_circular);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(400,200));
        grafico.jPanel1.setLayout(new BorderLayout());
        grafico.jPanel1.add(panel,BorderLayout.NORTH);
        grafico.pack();
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("Tiempo de ejecucion: " + (tiempoFinal - tiempoInicial) + " milisegundos");
        
    }
    
}
