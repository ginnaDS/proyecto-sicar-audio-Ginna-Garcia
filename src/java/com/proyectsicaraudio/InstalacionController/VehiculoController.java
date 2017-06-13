/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.CarroFacadeLocal;
import com.proyectsicaraudio.EJB.MarcaFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Carro;
import com.proyectsicaraudio.model.Marca;
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
    @EJB
    private MarcaFacadeLocal marcaEJB;
    @Inject
    private Carro carro;
    private List<Carro> carros;
    @Inject
    private Usuario usuario;
    @Inject
    private Marca marca;
    private List<Marca> marcas;
    private FacesContext facesContext;
    private ResourceBundle rb;
    
    @PostConstruct
    public void init(){
        marcas = marcaEJB.findAll();
        carros = carroLocal.carrosUsuario();
       facesContext = FacesContext.getCurrentInstance();
       rb =facesContext.getApplication().getResourceBundle(facesContext, "msjIns");
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
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
            carro.setIdMarca(marca);
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
     
     public void eliminar(Carro cr){
         try {
         carroLocal.remove(cr);
             System.out.println("se elimino"); 
         } catch (Exception e) {
             System.out.println("no se elimino "+e.getMessage()+e.getCause());
         }
     }
     
     public void ver(Carro ca){
         this.carro=ca;
     }
     
     public void editar(){
         carroLocal.edit(carro);
     }
 
}
