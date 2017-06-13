package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CarroFacadeLocal;
import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.EJB.EmailFacadeLocal;
import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Carro;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Email;
import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author nina garcia
 */
@Named
@RequestScoped
public class AsignacionController implements Serializable{
  @EJB
  private CitaFacadeLocal citaLocal;
  @EJB
  private UsuarioFacadeLocal userLocal;
  @EJB
  private RolFacadeLocal rolLocal;
  @EJB
  private CarroFacadeLocal carroLocal;
  @EJB
  private EmailFacadeLocal emailfl;
  private Email email;
  @Inject
  private Carro carro;
  private List<Carro> carros;
  @Inject
  private Cita cita;
  @Inject
  private Rol rol;
  @Inject
  private Usuario usua;
  
  private FacesContext facesContext;
  private ResourceBundle rb;

  
  private List<Usuario> usuarios;
  @PostConstruct
  public void init(){
    usuarios = userLocal.traerUsuarios(rol);
    carros = carroLocal.findAll();
    facesContext = FacesContext.getCurrentInstance();
    rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
  } 

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
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
    
  public List<Usuario> TraerUser(){
    Rol r=new Rol(); 
    r.setIdRol(3);
    List<Usuario> usuarios= userLocal.traerUsuarios(r);
    return usuarios;
  }
  
 /* public Cita verCita(Cita c){
      Cita ci=citaLocal.find(c.getIdCita());
  return  cita= ci;
  }*/
  
  public void verCita(Cita c){
      this.cita = c;
  }
  
  public void asignarTecnico(Cita item){
      RequestContext context = RequestContext.getCurrentInstance();
      try {
         citaLocal.edit(cita);
         emailfl.envNotfCita(cita, usua);
         context.execute("swal('El tecnico'"+cita.getIdUsuario().getNombreUsuario()+",'Fue asignado exitosamente','success')");
      
      } catch (Exception e) {
      context.execute("swal('Lo sentimos','No se ha podido asignar','error')");
      }
  }
}
