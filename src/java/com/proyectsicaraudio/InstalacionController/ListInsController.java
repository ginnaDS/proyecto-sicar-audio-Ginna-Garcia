/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.EJB.EstadocitaFacadeLocal;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Estadocita;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author nina garcia
 */
@Named (value="listaController")
@ViewScoped
public class ListInsController implements Serializable{
    
    @EJB
    private CitaFacadeLocal citaLocal;
    @EJB
    private EstadocitaFacadeLocal estCitLocal;
    @Inject
    private Cita cita;
    private List<Cita> citas;
    @Inject
    private Estadocita estCit;
    private List<Estadocita> estados;
    
    private FacesContext facesContext;
  private ResourceBundle rb;
    @PostConstruct
    public void init(){
        citas = citaLocal.findAll();
        estados = estCitLocal.findAll();
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
    }

    public CitaFacadeLocal getCitaLocal() {
        return citaLocal;
    }

    public void setCitaLocal(CitaFacadeLocal citaLocal) {
        this.citaLocal = citaLocal;
    }

    public EstadocitaFacadeLocal getEstCitLocal() {
        return estCitLocal;
    }

    public void setEstCitLocal(EstadocitaFacadeLocal estCitLocal) {
        this.estCitLocal = estCitLocal;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Estadocita getEstCit() {
        return estCit;
    }

    public void setEstCit(Estadocita estCit) {
        this.estCit = estCit;
    }

    public List<Estadocita> getEstados() {
        return estados;
    }

    public void setEstados(List<Estadocita> estados) {
        this.estados = estados;
    }
    
   public void cambiarEstado(){
       try {
           citaLocal.edit(cita);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
            ("CambiosGuardados")));
       } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error"),
            ("CambiosNoGuardados")));
       }
       
   }
    }