/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.model.Cita;
import java.io.Serializable;
import java.util.List;
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
@Named (value = "citControl")
@ViewScoped
public class CitasController implements Serializable{
     @EJB
    private CitaFacadeLocal citaLocal;
      @Inject
    private Cita cita;
    private List<Cita> listaCitas;
    
    @PostConstruct
    public void init(){
        listaCitas = citaLocal.citasCliente();
        
     //   citas = citaLocal.citasTecnico();
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }
    
    public void citat(Cita ci){
        this.cita=ci;
    }
    
        public void cancelarCita(Cita item){
        try{
            int estado = item.getIdEstadoCi().getIdEstadoCi();
            
            if(estado==2 || estado==3){
                System.out.println("por aqiooo");
                Cita c=citaLocal.find(item.getIdCita());
                System.out.println("id "+c.getIdCita());
                citaLocal.edit(c);
                
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
                         ("SehanGuardadoLosCambios")));
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error"),
                         ("NoSeHaPodidoGuardar")+e.getMessage()));
            System.out.println("errooooooooooooooor "+e.getMessage()+e.getCause());
            
            
        }
    }
        
        
}
