/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
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
public class LoginController implements Serializable{
    @EJB
    private UsuarioFacadeLocal usuarioLocal;
    @EJB
    private RolFacadeLocal rolLocal;
    @Inject
    private Rol rol;
    @Inject
    private Usuario usuario;
    private FacesContext facesContext;
    private ResourceBundle rb;
    @PostConstruct
    public void init(){
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjUsua");
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public UsuarioFacadeLocal getUsuarioLocal() {
        return usuarioLocal;
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
    public String iniciarSesion(){
        Usuario user;
        String contrasenia;
         String Redireccion=null;
         try {
             user = usuarioLocal.iniciarSesion(usuario);
             System.out.println("Iniciar");
             if (user!=null) {
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("us", user);
              Redireccion="/protegido/"+user.getIdRol().getTipoRol()+"/principal?faces-redirect=true";
             }else{
                RequestContext context = RequestContext.getCurrentInstance();
        context.execute("swal('Error!','Usuario o contraseña incorrecta','warning')");
              
             }
         } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Aviso"),("Error ")+e.getMessage()));
    }
    
           return Redireccion;
    }
    
    public  void cerrarSesion() throws IOException{
         usuario= null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("./../../index.xhtml");
//        return "/faces/index.xhtml";
         
    }
    
  
}
