/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.EJB.EventoFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Evento;
import com.proyectsicaraudio.model.Usuario;
import java.io.File;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author nina garcia
 */
@Named
@ViewScoped
public class EventoController implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuariofl;
    
    @EJB
    private EventoFacadeLocal eventofl;
    @Inject 
    private Usuario usuario;
    @Inject
    private Evento evento;
    private FacesContext facesContext;
    private ResourceBundle rb;
    @PostConstruct
    public void init(){
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjUsua");
        
    }

    public UsuarioFacadeLocal getUsuariofl() {
        return usuariofl;
    }

    public void setUsuariofl(UsuarioFacadeLocal usuariofl) {
        this.usuariofl = usuariofl;
    }

    public EventoFacadeLocal getEventofl() {
        return eventofl;
    }

    public void setEventofl(EventoFacadeLocal eventofl) {
        this.eventofl = eventofl;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    
    
   public void asignarEvento(Part fotoEvento){
       RequestContext context = RequestContext.getCurrentInstance();
       try{
           SubirArchivos subir= new SubirArchivos();
           Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
           evento.setIdUsuario(u);
           subir.setE(evento);
           String d=subir.SubirArchivo("eventos", fotoEvento);
           
           System.out.println("Accaaa");
           System.out.println("File "+d);
           evento.setPoster(d+subir.extension(subir.nombreArchivo(fotoEvento)));
           System.out.println("Evento "+evento.getPoster());
           eventofl.create(evento);
           
           context.execute("swal('Se ha registrado','Un nuevo evento','success')");
       }catch(Exception e){ 
           context.execute("swal('Lo sentimos','se ha producido un error, intentalo nuevamente','error')");
       }
               
   }
   
   
   
}
  

    

