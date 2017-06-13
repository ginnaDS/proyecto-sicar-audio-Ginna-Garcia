/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.EJB.ClienteFacadeLocal;
import com.proyectsicaraudio.EJB.EmailFacadeLocal;
import com.proyectsicaraudio.EJB.EstadousuarioFacadeLocal;
import com.proyectsicaraudio.EJB.RolFacadeLocal;
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Cliente;
import com.proyectsicaraudio.model.Email;
import com.proyectsicaraudio.model.Estadousuario;
import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
public class RegistroUsuario implements Serializable{
    @EJB
    private UsuarioFacadeLocal usuariofl;
    @EJB
    private RolFacadeLocal rolfl;
    @EJB
    private EmailFacadeLocal emailfl;
    @EJB
    private EstadousuarioFacadeLocal estadoLocal;
    @EJB
    private ClienteFacadeLocal clienteEJB;
    
    private boolean bloquear;
    
    @Inject
    private Rol rol;
    @Inject
    private Estadousuario estado;
    private Email email;
    @Inject
    private Usuario usuario;
    private List<Usuario> usuarios;
    @Inject
    private Cliente cliente;
    
    private FacesContext facesContext;
    private ResourceBundle rb;
    @PostConstruct
    public void init(){
        usuarios = usuariofl.findAll();
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjUsua");
    }

    public boolean isBloquear() {
        return bloquear;
    }
    
    public boolean inactivo(){
        return bloquear = true;
    }

    public void setBloquear(boolean bloquear) {
        this.bloquear = bloquear;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public EstadousuarioFacadeLocal getEstadoLocal() {
        return estadoLocal;
    }

    public void setEstadoLocal(EstadousuarioFacadeLocal estadoLocal) {
        this.estadoLocal = estadoLocal;
    }

    public Estadousuario getEstado() {
        return estado;
    }

    public void setEstado(Estadousuario estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
     String claveAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')){
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }
    
    public void registarUsuario(){
        RequestContext context = RequestContext.getCurrentInstance();
        try{
            estado.setIdEstadoUs(1);
            usuario.setIdEstadoUs(estado);
            rol.setIdRol(1);
            usuario.setIdRol(rol);
            usuariofl.create(usuario);
            cliente.setIdUsuario(usuario);
            clienteEJB.create(cliente);
            context.execute("swal('Bienvenido','Te has registrado exitosamente','success')");
            emailfl.enviarEmailRegistro(usuario);
        }catch(Exception e){
            context.execute("swal('Lo sentimos','Intentalo nuevamente','error')");
        }
}
    
      public void registrarAdmin(){
          RequestContext context = RequestContext.getCurrentInstance();
      try {
        String cadena = claveAleatoria(6);
        usuario.setContrasenia(cadena);
        estado.setIdEstadoUs(1);
        usuario.setIdEstadoUs(estado);
        rol.setIdRol(4);
        usuario.setIdRol(rol);
        usuariofl.create(usuario); 
        emailfl.envEmailRegistroT(usuario);
         context.execute("swal('Bienvenido','Te has registrado exitosamente','success')");
    } catch (Exception e) {
            context.execute("swal('Lo sentimos','Intentalo nuevamente','error')");
        }
       
    }
      
      
      public void registrarTecnico(){
          RequestContext context = RequestContext.getCurrentInstance();
       try {
        String cadena = claveAleatoria(6);
        usuario.setContrasenia(cadena);
        estado.setIdEstadoUs(1);
        usuario.setIdEstadoUs(estado);
        rol.setIdRol(3);
        usuario.setIdRol(rol);
        usuariofl.create(usuario);  
        emailfl.envEmailRegistroT(usuario);
        context.execute("swal('Bienvenido','Te has registrado exitosamente','success')");
    } catch (Exception e) {
         context.execute("swal('Lo sentimos','Intentalo nuevamente','error')");
        }
       
    }
}
