/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.EJB.InstalacionFacadeLocal;
import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Instalacion;
import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.Date;
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
public class instalacionController implements Serializable {
    @EJB
    private UsuarioFacadeLocal usuarioLocal;
    @EJB
    private InstalacionFacadeLocal instalacionLocal;
    @EJB
    private RolFacadeLocal rolLocal;
    @EJB
    private CitaFacadeLocal citaLocal;
    @Inject
    private Usuario usuario;
    private List<Usuario> usuarios;
    @Inject
    private Instalacion inta;
    @Inject
    private Cita cita;
    private List<Cita> citas;
    @Inject
    private Rol rol;
    
    private FacesContext facesContext;
    private ResourceBundle rb;
    @PostConstruct
    public void init(){
        usuarios = usuarioLocal.findAll();
        citas = citaLocal.citasTecnico();   
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
    }


    public RolFacadeLocal getRolLocal() {
        return rolLocal;
    }

    public void setRolLocal(RolFacadeLocal rolLocal) {
        this.rolLocal = rolLocal;
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

    public InstalacionFacadeLocal getInstalacionLocal() {
        return instalacionLocal;
    }

    public void setInstalacionLocal(InstalacionFacadeLocal instalacionLocal) {
        this.instalacionLocal = instalacionLocal;
    }

    public CitaFacadeLocal getCitaLocal() {
        return citaLocal;
    }

    public void setCitaLocal(CitaFacadeLocal citaLocal) {
        this.citaLocal = citaLocal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Instalacion getInta() {
        return inta;
    }

    public void setInta(Instalacion inta) {
        this.inta = inta;
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
    
    public void registrarInsta(){
        try {
            Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
            inta.setIdUsuario(u);
            Date fecha = new Date();
            inta.setFecha(fecha);
            System.out.println("fecha "+inta.getFecha());
            Cita ct = citaLocal.find(cita.getIdCita());
            inta.setIdCita(ct);
            instalacionLocal.create(inta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
            ("RegistroExitoso")));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error"),
            ("NoSeHaRegistrado"+e.getMessage())));
        }
    }
}
