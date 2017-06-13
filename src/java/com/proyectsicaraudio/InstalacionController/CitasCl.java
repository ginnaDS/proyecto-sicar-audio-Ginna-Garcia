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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author nina garcia
 */
@Named
@ViewScoped
public class CitasCl implements Serializable{
    @EJB
    private CitaFacadeLocal citaLocal;
    private Cita cita;
    private List<Cita> citasCli;
    

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Cita> getCitas() {
        return citasCli;
    }

    public void setCitas(List<Cita> citas) {
        this.citasCli = citas;
    }
            
    @PostConstruct
    public void init(){
        citasCli = citaLocal.citasCliente();
    }
}
