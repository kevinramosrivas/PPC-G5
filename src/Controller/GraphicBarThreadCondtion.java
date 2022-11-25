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
public class GraphicBarThreadCondtion extends Thread{
    public Graficos grafico;
    public List<Map<String, Object>> datos;
    public int operativo;
    public int defectuosa;
    public int mantenimiento;
    public GraphicBarThreadCondtion(Graficos grafico, List<Map<String, Object>> datos) {
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
        int labo_1Operativo = 0;
        int labo_2Operativo = 0;
        int labo_3Operativo = 0;
        int labo_4Operativo = 0;
        int labo_5Operativo = 0;
        int labo_6Operativo = 0;
        int labo_7Operativo = 0;
        int labo_1Defectuoso = 0;
        int labo_2Defectuoso = 0;
        int labo_3Defectuoso = 0;
        int labo_4Defectuoso = 0;
        int labo_5Defectuoso = 0;
        int labo_6Defectuoso = 0;
        int labo_7Defectuoso = 0;
        int labo_1Mantenimiento = 0;
        int labo_2Mantenimiento = 0;
        int labo_3Mantenimiento = 0;
        int labo_4Mantenimiento = 0;
        int labo_5Mantenimiento = 0;
        int labo_6Mantenimiento = 0;
        int labo_7Mantenimiento = 0;
        for (int i=0;i<datos.size();i++){
            switch (datos.get(i).get("id_lab").toString()) {
                case "1":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_1Operativo++;
                            break;
                        case "defectuosa":
                            labo_1Defectuoso++;
                            break;
                        default:
                            labo_1Mantenimiento++;
                            break;
                    }
                    break;
                case "2":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_2Operativo++;
                            break;
                        case "defectuosa":
                            labo_2Defectuoso++;
                            break;
                        default:
                            break;
                    }
                    break;
                case "3":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_3Operativo++;
                            break;
                        case "defectuosa":
                            labo_3Defectuoso++;
                            break;
                        default:
                            labo_3Mantenimiento++;
                            break;
                    }
                    break;
                case "4":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_4Operativo++;
                            break;
                        case "defectuosa":
                            labo_4Defectuoso++;
                            break;
                        default:
                            labo_4Mantenimiento++;
                            break;
                    }
                    break;
                case "5":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_5Operativo++;
                            break;
                        case "defectuosa":
                            labo_5Defectuoso++;
                            break;
                        default:
                            labo_5Mantenimiento++;
                            break;
                    }
                    break;
                case "6":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_6Operativo++;
                            break;
                        case "defectuosa":
                            labo_6Defectuoso++;
                            break;
                        default:
                            labo_6Mantenimiento++;
                            break;
                    }
                    break;
                case "7":
                    switch (datos.get(i).get("estado").toString()) {
                        case "operativo":
                            labo_7Operativo++;
                            break;
                        case "defectuosa":
                            labo_7Defectuoso++;
                            break;
                        default:
                            labo_7Mantenimiento++;
                            break;
                    }
                    break;                    
            }
            

        }
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        datos.setValue(labo_1Operativo,"PC operativa","Laboratorio 1");
        datos.setValue(labo_1Defectuoso,"PC defectuosa","Laboratorio 1");
        datos.setValue(labo_1Mantenimiento,"PC en mantenimiento","Laboratorio 1");
        datos.setValue(labo_2Operativo,"PC operativa","Laboratorio 2");
        datos.setValue(labo_2Defectuoso,"PC defectuosa","Laboratorio 2");
        datos.setValue(labo_2Mantenimiento,"PC en mantenimiento","Laboratorio 2");
        datos.setValue(labo_3Operativo,"PC operativa","Laboratorio 3");
        datos.setValue(labo_3Defectuoso,"PC defectuosa","Laboratorio 3");
        datos.setValue(labo_3Mantenimiento,"PC en mantenimiento","Laboratorio 3");
        datos.setValue(labo_4Operativo,"PC operativa","Laboratorio 4");
        datos.setValue(labo_4Defectuoso,"PC defectuosa","Laboratorio 4");
        datos.setValue(labo_4Mantenimiento,"PC en mantenimiento","Laboratorio 4");
        datos.setValue(labo_5Operativo,"PC operativa","Laboratorio 5");
        datos.setValue(labo_5Defectuoso,"PC defectuosa","Laboratorio 5");
        datos.setValue(labo_5Mantenimiento,"PC en mantenimiento","Laboratorio 5");
        datos.setValue(labo_6Operativo,"PC operativa","Laboratorio 6");
        datos.setValue(labo_6Defectuoso,"PC defectuosa","Laboratorio 6");
        datos.setValue(labo_6Mantenimiento,"PC en mantenimiento","Laboratorio 6");
        datos.setValue(labo_7Operativo,"PC operativa","Laboratorio 7");
        datos.setValue(labo_7Defectuoso,"PC defectuosa","Laboratorio 7");
        datos.setValue(labo_7Mantenimiento,"PC en mantenimiento","Laboratorio 7");
        JFreeChart grafico_barras = ChartFactory.createBarChart3D("Estado de las PC por laboratorio", 
                "Laboratorio", 
                "Cantidad", datos, 
                PlotOrientation.HORIZONTAL, true, 
                true, false
                );
        ChartPanel panel = new ChartPanel(grafico_barras);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(400,250));
        
        grafico.jPanel2.setLayout(new BorderLayout());
        grafico.jPanel2.add(panel,BorderLayout.NORTH);
        
        grafico.pack();
        // saber cuanto tiempo se demora en ejecutar el codigo
        long tiempoFinal = System.currentTimeMillis();
        System.out.println("Tiempo de ejecucion: " + (tiempoFinal - tiempoInicial) + " milisegundos");
        
        
    }
    
    public void sumarLabo(){
        
    }
        
}
