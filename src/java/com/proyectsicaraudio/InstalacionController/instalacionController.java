/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.EJB.EstadocitaFacadeLocal;
import com.proyectsicaraudio.EJB.InstalacionFacadeLocal;
import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Estadocita;
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
import org.primefaces.context.RequestContext;

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
    @EJB
    private EstadocitaFacadeLocal estadoLocal;
    @Inject
    private Usuario usuario;
    private List<Usuario> usuarios;
    @Inject
    private Instalacion inta;
    private List<Instalacion> instalaciones;
    private List<Instalacion> instalaciones1;
    @Inject
    private Cita cita;
    private List<Cita> citas;
    @Inject
    private Rol rol;
    @Inject
    private Estadocita estado;
    private FacesContext facesContext;
    private ResourceBundle rb;

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(List<Instalacion> instalaciones) {
        this.instalaciones = instalaciones;
    }

    public Estadocita getEstado() {
        return estado;
    }

    public void setEstado(Estadocita estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public List<Instalacion> getInstalaciones1() {
        return instalaciones1;
    }

    public void setInstalaciones1(List<Instalacion> instalaciones1) {
        this.instalaciones1 = instalaciones1;
    }
    
    @PostConstruct
    public void init(){
        instalaciones = instalacionLocal.findAll();
        instalaciones1 = instalacionLocal.instalacionesTecnico();
        usuarios = usuarioLocal.findAll();
        citas = citaLocal.citasTecnico(); 
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
    }
    
    public void actualizar(Cita item) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            cita=citaLocal.find(item.getIdCita());
            cita.setIdEstadoCi(estadoLocal.find(4));
            citaLocal.edit(cita);
            context.execute("swal('Actualizacion','Exitosa','success')");            
        } catch (Exception e) {
            context.execute("swal('Lo sentimos','Intentalo nuevamente','error')");
            System.out.println(e.getMessage());
        }        
    }
    
    public Cita mostrar(Cita citas){
        return citas;
    }
    
    public void capturarCita(Cita c){
        cita=c;
    }
    
    public void editar(){
        actualizar(cita);
        registrarInsta();
    }
    public void registrarInsta(){
        try {
            Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
            inta.setIdUsuario(u);
            Date fecha = new Date();
            inta.setFecha(fecha);
            System.out.println("fecha "+inta.getFecha());
            Cita ct = citaLocal.find(cita.getIdCita());
            System.out.println("id cita "+ct.getIdCita());
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
