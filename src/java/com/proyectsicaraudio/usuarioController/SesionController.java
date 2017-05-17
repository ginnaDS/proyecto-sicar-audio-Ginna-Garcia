/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.EJB.ClienteFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Cliente;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
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
@Named
@ViewScoped
public class SesionController implements Serializable{
    @EJB
    private ClienteFacadeLocal clienteLocal;
    @EJB
    private UsuarioFacadeLocal usuarioLocal;
    @Inject
    private Usuario usuario;
    @Inject
    private Cliente cliente;
    
    @PostConstruct
    public void init(){
      usuario= usuarioActivo();
    }
    public UsuarioFacadeLocal getUsuarioLocal() {
        return usuarioLocal;
    }

    public ClienteFacadeLocal getClienteLocal() {
        return clienteLocal;
    }

    public void setClienteLocal(ClienteFacadeLocal clienteLocal) {
        this.clienteLocal = clienteLocal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setUsuarioLocal(UsuarioFacadeLocal usuarioLocal) {
        this.usuarioLocal = usuarioLocal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void verificarSesion(){
        try {
            
         FacesContext context = FacesContext.getCurrentInstance(); 
         Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("us");
         
            if (us == null) {
                
                context.getExternalContext().redirect("../../index.xhtml");
                
            }
        } catch (Exception e) {
            
           //log para guardar algun registro de algun error
        }
    }
    
    public Usuario usuarioActivo(){
           FacesContext context=FacesContext.getCurrentInstance();
           Usuario us=(Usuario) context.getExternalContext().getSessionMap().get("us");
           return us;
    }
    
    public void seguridad(){
        Usuario user = usuarioActivo();
        try {
            if (user==null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../../permiso.xhtml");
        }
        } catch (Exception e) {
        }
        
    }
    
   public void guardarCambios(){
       try {
           usuarioLocal.edit(usuario);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso"
                    , "Se actualizo correctamente"));
       } catch (Exception e) {
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", e.getCause().getMessage()));
       }
   }
}
