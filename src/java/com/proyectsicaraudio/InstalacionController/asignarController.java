/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.EJB.EmailFacadeLocal;
import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Email;
import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
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
@Named
@ViewScoped
public class asignarController implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuarioLocal;
    @EJB
    private RolFacadeLocal rolLocal;
    
  private EmailFacadeLocal emailfl;
  private Email email;
     @EJB
  private CitaFacadeLocal citaLocal;
    @Inject
    private AsignacionController asignacionController;
    @Inject
    private Usuario usua;
    private List<Usuario> usuarios;
    @Inject
    private Rol rol;
    @Inject
    private Cita c;

    private FacesContext facesContext;
    private ResourceBundle rb;
    @PostConstruct
    public void init() {
        this.c = asignacionController.getCita();
        rol.setIdRol(3);
//        Email email= new Email();
        usuarios = usuarioLocal.traerUsuarios(rol);
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
    }

    public RolFacadeLocal getRolLocal() {
        return rolLocal;
    }

    public void setRolLocal(RolFacadeLocal rolLocal) {
        this.rolLocal = rolLocal;
    }

    public Usuario getUsua() {
        return usua;
    }

    public void setUsua(Usuario usua) {
        this.usua = usua;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Cita getC() {
        return c;
    }

    public void setC(Cita c) {
        this.c = c;
    }
    
    public Cita mostrarAEditar(Cita ci){
    return c=ci;
    }
    
    public void asignarTecnico(){
      try {
         Usuario us= usuarioLocal.find(usua.getIdUsuario());
         c.setIdUsuario(us);
         Cita cit= citaLocal.find(c.getIdCita());
          System.out.println(cit.getIdCita());
         citaLocal.edit(cit); 
//        emailfl.envNotfCita(cit, us);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso")
                    ,("CambiosGuardados"))); 
      } catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error")
                    , ("CambiosNoGuardados"+e.getMessage())));
      }
      
      
  }
        

}
