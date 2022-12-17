/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionPool;
import Interface.ModificarComp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author mildr
 */
public class ModificarCompCtr implements ActionListener{
    private ModificarComp modificarui;
    private List<Map<String, Object>> resultList, resultByLab;
    private List<String> labsUniques, compUniques, estUniques;
    private Map<String, Object> selectedComp;
    
    public ModificarCompCtr() {
        this.getData();
        
        modificarui = new ModificarComp();
        modificarui.cmb_labId.addActionListener(this);
        modificarui.btn_mod.addActionListener(this);
    }
    private List getUniquesItems(List<Map<String, Object>> dataset, String key, boolean sort){
        List<String> list= new ArrayList<>();
        for (int i = 0; i < dataset.size(); i++) {
            String data = String.valueOf(dataset.get(i).get(key));
            if(!list.contains(data)) {
                list.add(data);
            }
        }
        if (sort) {
            Collections.sort(list);
        }
        return list;
    }
    private List<Map<String, Object>> getFilterBy (List<Map<String, Object>> dataset, String key, String value){
        List<Map<String, Object>> byKey = new ArrayList<>();
        for (var d:dataset) {
            if (d.get(key).equals(Integer.parseInt(value))) {
                byKey.add(d);
            }
        }
        return byKey;
    }
    private void setDataByLab (){
        String labId = String.valueOf(modificarui.cmb_labId.getSelectedItem());
        resultByLab = getFilterBy(this.resultList, "id_lab", labId);
        this.compUniques = getUniquesItems(resultByLab, "id_pc", true);
        this.estUniques = getUniquesItems(resultList, "estado", false);
        //System.out.println(resultByLab.toString());
        //System.out.println(compUniques.toString());
        //System.out.println(estUniques.toString());
        
        int countCom = this.modificarui.cmb_comId.getItemCount();
        for(int i = 0; i < countCom; i++){
            this.modificarui.cmb_comId.removeItemAt(0);
        }
        this.modificarui.cmb_comId.addItem("");
        for (int i = 0; i < compUniques.size(); i++) {
            this.modificarui.cmb_comId.addItem(compUniques.get(i));
        }
        this.modificarui.cmb_comId.setEnabled(true);
        
        int countNewLab = this.modificarui.cmb_newLabId.getItemCount();
        for(int i = 0; i < countNewLab; i++){
            this.modificarui.cmb_newLabId.removeItemAt(0);
        }
        
        this.modificarui.cmb_newLabId.addItem("");
        for (int i = 0; i < labsUniques.size(); i++) {
            if (i != this.labsUniques.indexOf(labId)) {
                this.modificarui.cmb_newLabId.addItem(labsUniques.get(i));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modificarui.cmb_labId) {
            modificarui.cmb_comId.removeActionListener(this);
            setDataByLab();
            modificarui.cmb_comId.addActionListener(this);
        }
        if (e.getSource() == modificarui.cmb_comId) {
            modificarui.cmb_estado.removeActionListener(this);
            setCompInfo();
            modificarui.cmb_estado.addActionListener(this);
        }
        if (e.getSource() == modificarui.btn_mod){
            sendModComp();
        }
    }
    
    private void getData(){
        this.resultList = new ArrayList<>();
        String sql = "select id_pc, estado, id_lab, fecha_mod, obs from computer";
        try {
            this.resultList = new ConnectionPool().makeConsult(sql);
            this.labsUniques = getUniquesItems(resultList, "id_lab", true);
            for (int i = 0; i < labsUniques.size(); i++) {
                this.modificarui.cmb_labId.addItem(labsUniques.get(i));
            }
            this.modificarui.cmb_labId.setEnabled(true);
            
            this.modificarui.cmb_estado.addItem("");
            for (int i = 0; i < estUniques.size(); i++) {
                this.modificarui.cmb_estado.addItem(estUniques.get(i));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    private void setCompInfo(){
        selectedComp = new java.util.HashMap<>();
        var value = this.modificarui.cmb_comId.getSelectedItem();
        for (var d:this.resultByLab){
            boolean isPc = d.get("id_pc").equals(Integer.parseInt(String.valueOf(value)));
            if (isPc) {
                selectedComp = d;
            }
        }
        //System.out.println(selectedComp.toString());
        int boxIndex = this.estUniques.indexOf(selectedComp.get("estado"))+1;
        this.modificarui.cmb_estado.setSelectedIndex(boxIndex);
        
        this.modificarui.labEst.setEnabled(true);
        this.modificarui.cmb_estado.setEnabled(true);
        this.modificarui.do_modLab.setEnabled(true);
        this.modificarui.do_obs.setEnabled(true);
        this.modificarui.btn_mod.setEnabled(true);
        
        modificarui.cmb_newLabId.removeActionListener(this);
        modificarui.do_modLab.addItemListener(checkList);
        modificarui.cmb_newLabId.addActionListener(this);
        
        modificarui.do_obs.addItemListener(checkObs);
    }
    ItemListener checkList = (ItemEvent e) -> {
        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            this.modificarui.cmb_newLabId.setEnabled(true);
        } else {//checkbox has been deselected
            this.modificarui.cmb_newLabId.setSelectedIndex(0);
            this.modificarui.cmb_newLabId.setEnabled(false);
        }
    };
    ItemListener checkObs = (ItemEvent e) -> {
        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
            this.modificarui.obs.setEnabled(true);
        } else {//checkbox has been deselected
            this.modificarui.obs.setEnabled(false);
        }
    };
    
    private void sendModComp(){
        String lab, comp, estado, obs;
        comp = String.valueOf(modificarui.cmb_comId.getSelectedItem());
        estado = String.valueOf(modificarui.cmb_estado.getSelectedItem());
        if (this.modificarui.do_obs.isSelected()) {
            obs = modificarui.obs.getText();
        } else {
            obs = String.valueOf(this.selectedComp.get("obs"));
        }
        if (this.modificarui.do_modLab.isSelected() && !String.valueOf(modificarui.cmb_newLabId.getSelectedItem()).isEmpty()) {
            lab = String.valueOf(modificarui.cmb_newLabId.getSelectedItem());
        } else {
            lab = String.valueOf(this.selectedComp.get("id_lab"));
        }
        String sql = String.format("update computer set id_lab='%s', estado='%s',"
                + "fecha_mod=CURRENT_TIMESTAMP, obs='%s' where id_pc='%s'", 
                Integer.parseInt(lab),estado,obs,Integer.parseInt(comp));
        try {
            new ConnectionPool().makeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Computadora con id: "+comp+" actualizado con exito");
        } catch (SQLException e) {
            System.out.println(e);
        }
        //System.out.println(sql);
    }
}
