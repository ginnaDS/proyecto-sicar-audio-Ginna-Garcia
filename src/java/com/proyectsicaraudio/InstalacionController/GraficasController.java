/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author nina garcia
 */

@ManagedBean
public class GraficasController{
    private PieChartModel model;
    private Integer mes;

    public GraficasController() {
        model = new PieChartModel();
    }
    
    public String showVentas(){
        model = new PieChartModel();
        if (getMes() == 1) {
            model.set("Semana 1", 1024.56);
            model.set("Semana 2", 890.56);
            model.set("Semana 3", 2345.90);
            model.set("Semana 4", 4000);
            return "";
        }
        if (getMes() == 2) {
            model.set("Semana 1", 3454.56);
            model.set("Semana 2", 234.56);
            model.set("Semana 3", 2342.90);
            model.set("Semana 4", 2343);
            return "";
        }
        if (getMes() == 3) {
            model.set("Semana 1", 9766.56);
            model.set("Semana 2", 12345.56);
            model.set("Semana 3", 3454.90);
            model.set("Semana 4", 950.12);
            return "";
        }
        return "";
    }
    
    
//    @PostConstruct
//    public void init(){
//        try {
//        createPieModels();
//            
//        } catch (Exception e) {
//            Logger.getLogger(GraficasController)
//        }
//    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public PieChartModel getModel() {
        return model;
    }

    public void setModel(PieChartModel model) {
        this.model = model;
    }
    
}
