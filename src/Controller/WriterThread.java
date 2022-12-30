/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import Connection.ConnectionPool;
import com.itextpdf.text.Paragraph;
import javax.swing.JOptionPane;
/**
 *
 * @author ramos
 */
public class WriterThread extends Thread{
    public String nameLab;
    public WriterThread(String nameLab){
        this.nameLab = nameLab;
    }
    @Override
    public void run(){
        Document documento = new Document();
        long s = System.currentTimeMillis();
        try{
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta+
                    "/Downloads/ReporteLaboratorio_"+this.nameLab+".pdf"));
            documento.open();
            PdfPTable tabla = new PdfPTable(5);
            Paragraph p = new Paragraph();
            p.add("Reporte de laboratorio "+nameLab);
            tabla.addCell("id_pc");
            tabla.addCell("estado");
            tabla.addCell("id_lab");
            tabla.addCell("fecha_mod");
            tabla.addCell("obs");
            List<Map<String, Object>> resultList = new ArrayList<>();
            String sql = String.format("SELECT * FROM computer where id_lab=%s",
                    this.nameLab);
            try {
                resultList = new ConnectionPool().makeConsult(sql);
                for (int i=0;i<resultList.size();i++){
                    tabla.addCell(String.valueOf(resultList.get(i).get("id_pc")));
                    tabla.addCell(String.valueOf(resultList.get(i).get("estado")));
                    tabla.addCell(String.valueOf(resultList.get(i).get("id_lab")));
                    tabla.addCell(String.valueOf(resultList.get(i).get("fecha_mod")));
                    tabla.addCell(String.valueOf(resultList.get(i).get("obs")));
                }
                //añadir el parrafo al documento centrado y con un espacio de 20
                p.setAlignment(Paragraph.ALIGN_CENTER);
                p.setSpacingAfter(20);
                documento.add(p);
                documento.add(tabla);

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Informe del laboratorio: "+this.nameLab+" fue creado con exito");

        }catch(Exception exc){
            System.out.println(exc);
        }
        System.out.println( "Tiempo: " + (System.currentTimeMillis() - s) + " ms. "  );
    }
    
}
