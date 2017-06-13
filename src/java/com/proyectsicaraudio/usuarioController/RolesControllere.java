/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.model.Rol;
import java.io.Serializable;
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
@Named
@ViewScoped
public class RolesControllere implements Serializable{
@EJB
private RolFacadeLocal rolLocal;
@Inject
private Rol rol;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void registraRol(){
        try {
            rolLocal.create(rol);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
                   ("SeCreoUnNuevoRol")));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,("Error"),
                   ("NoSeHaPodidoRegistrar")));
        }
    }
}
