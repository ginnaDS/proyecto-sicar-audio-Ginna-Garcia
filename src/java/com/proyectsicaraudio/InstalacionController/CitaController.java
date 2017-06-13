/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CarroFacadeLocal;
import com.proyectsicaraudio.EJB.CitaFacadeLocal;
import com.proyectsicaraudio.EJB.EstadocitaFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Carro;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Estadocita;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.List;
//import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author nina garcia
 */
@Named (value = "citaControl")
@ViewScoped
public class CitaController implements Serializable{
 
    
    @EJB
    private CitaFacadeLocal citaLocal;
    @EJB
    private CarroFacadeLocal carroLocal;
    @EJB
    private UsuarioFacadeLocal usuarioLocal;
    @EJB
    private EstadocitaFacadeLocal estCiLocal;
    @Inject
    private Cita cita;
    @Inject
    private Carro carro;
    private List<Carro> carros;
    @Inject
    private Usuario usuario;
    @Inject
    private Estadocita estCita;
    
//    private FacesContext facesContext;
//  private ResourceBundle rb;
     @PostConstruct
    public void init(){
       
        carros = carroLocal.carrosUsuario();
//        facesContext = FacesContext.getCurrentInstance();
//        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
     //   citas = citaLocal.citasTecnico();
    }

   
    public CitaFacadeLocal getCitaLocal() {
        return citaLocal;
    }

    public void setCitaLocal(CitaFacadeLocal citaLocal) {
        this.citaLocal = citaLocal;
    }

    public CarroFacadeLocal getCarroLocal() {
        return carroLocal;
    }

    public void setCarroLocal(CarroFacadeLocal carroLocal) {
        this.carroLocal = carroLocal;
    }

    public UsuarioFacadeLocal getUsuarioLocall() {
        return usuarioLocal;
    }

    public void setUsuarioLocal(UsuarioFacadeLocal usuarioLocal) {
        this.usuarioLocal = usuarioLocal;
        
    }

    public EstadocitaFacadeLocal getEstCiLocal() {
        return estCiLocal;
    }

    public void setEstCiLocal(EstadocitaFacadeLocal estCiLocal) {
        this.estCiLocal = estCiLocal;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estadocita getEstCita() {
        return estCita;
    }

    public void setEstCita(Estadocita estCita) {
        this.estCita = estCita;
    }
    
    public void registrarCita(){
        System.out.println("registrar");
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            cita.setIdCarro(carro);
            estCita.setIdEstadoCi(3);
            cita.setIdEstadoCi(estCita);
            citaLocal.create(cita);
        context.execute("swal('Tu cita','Se registro exitosamente','success')");

        } catch (Exception e) {
            System.out.println(e.getMessage()+"");
        context.execute("swal('Lo sentimos','Intentalo nuevamene','error')");
        }
            
    }
    //p: message id="mensaje" autoupdate="false" severity="info, fatal" showSummary="true" showDetail="true"
    public void eliminar(Cita ct){
        try {
        citaLocal.remove(ct);
            RequestContext context = RequestContext.getCurrentInstance();
        context.execute("swal('Eliminada!', 'con exito','success')");
        } catch (Exception e) {
        }
    }
}
  

