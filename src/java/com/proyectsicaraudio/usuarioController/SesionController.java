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
import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

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
            System.out.println(e.getMessage());
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
        RequestContext context = RequestContext.getCurrentInstance();
       try {
           
           usuarioLocal.edit(usuario);
           context.execute("swal('Datos','actualizados correctamente','success')");
       } catch (Exception e) {
           System.out.println(usuario);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso", e.getCause().getMessage()));
       }
   }
   
   
   public String userFoto(){
       String ruta = null;
       FacesContext context = FacesContext.getCurrentInstance(); 
         Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("us");
         
         switch(us.getGenero()){
             case "F": ruta= "mujer.png";
             break;
             case "M": ruta= "hombre2.png";
             break;
             case "O": ruta= "lgbti.png";
             break;
             
         }
         
         return ruta;
         
   }
   
   public String cambiarColor(){
         String color = null;
       FacesContext context = FacesContext.getCurrentInstance(); 
         Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("us");
         
         switch(us.getGenero()){
             case "F": color= "#ffffff";
             break;
             case "M": color= "#ffffff";
             break;
             case "O": color= "#ffffff";
             break;
             
         }
         
         return color;
   }
}
