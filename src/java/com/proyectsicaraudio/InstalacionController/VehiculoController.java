/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CarroFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Carro;
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
public class VehiculoController implements Serializable {
     @EJB
    private CarroFacadeLocal carroLocal;
    @EJB
    private UsuarioFacadeLocal usuarioLocal;
    @Inject
    private Carro carro;
    private List<Carro> carros;
    @Inject
    private Usuario usuario;
    
    private FacesContext facesContext;
    private ResourceBundle rb;
    
    @PostConstruct
    public void init(){
       facesContext = FacesContext.getCurrentInstance();
       rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
    }

    public CarroFacadeLocal getCarroLocal() {
        return carroLocal;
    }

    public void setCarroLocal(CarroFacadeLocal carroLocal) {
        this.carroLocal = carroLocal;
    }

    public UsuarioFacadeLocal getUsuarioLocal() {
        return usuarioLocal;
    }

    public void setUsuarioLocal(UsuarioFacadeLocal usuarioLocal) {
        this.usuarioLocal = usuarioLocal;
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
    
     public void registrarVehiculo(){
        try {  
            
            Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
            System.out.println(u.getIdUsuario());
            carro.setIdUsuario(u);
            carroLocal.create(carro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
            ("NuevoVehiculo")));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error"),
            ("NoSeHaRegitrado")));
        }
    }
    
    
    
}
