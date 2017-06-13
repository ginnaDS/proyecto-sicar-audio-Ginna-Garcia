/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.PrefacturaFacadeLocal;
import com.proyectsicaraudio.model.Prefactura;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.BarChartModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
/**
 *
 * @author nina garcia
 */
@ManagedBean
@ViewScoped
public class GraficaController implements Serializable{
    
    @EJB
    private PrefacturaFacadeLocal prefl;
    
    private BarChartModel barmodel;
    private int valorMax;
    
    @PostConstruct
    public void init(){
        try {
            createBarmodels();
        } catch(ParseException ex) {
            Logger.getLogger(GraficaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BarChartModel getBarmodel() {
        return barmodel;
    }

    public void setBarmodel(BarChartModel barmodel) {
        this.barmodel = barmodel;
    }
    
private BarChartModel initBarModel() throws ParseException {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Prueba");
        for (Prefactura v : prefl.findAll()) {
            DateFormat dateFormat = new SimpleDateFormat("YYYY");
            Date date = new Date();
            String nuevo = dateFormat.format(v.getFechaEnvio());
            date = dateFormat.parse(nuevo);
            boys.set(nuevo, v.getValorTotal());
            if (v.getValorTotal() > valorMax) {
                valorMax = (int) v.getValorTotal() + 10000;
            }
        }

        model.addSeries(boys);

        return model;
    }

 private void createBarmodels() throws ParseException {
        createBarModel();
    }
    

    private void createBarModel() throws ParseException {
        barmodel = initBarModel();

        barmodel.setTitle("Reporte");

        Axis xAxis = barmodel.getAxis(AxisType.X);
        xAxis.setLabel("Fecha");

        Axis yAxis = barmodel.getAxis(AxisType.Y);
        yAxis.setLabel("Precio");
        yAxis.setMin(0);
        yAxis.setMax(valorMax);

        barmodel.setSeriesColors("337ab7,FF0000");
    }
        
}
