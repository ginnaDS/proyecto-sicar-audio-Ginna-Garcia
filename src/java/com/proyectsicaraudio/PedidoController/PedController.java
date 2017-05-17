/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.EstadopedidoFacadeLocal;
import com.proyectsicaraudio.EJB.PedidoFacadeLocal;
import com.proyectsicaraudio.model.Detallespedido;
import com.proyectsicaraudio.model.Estadopedido;
import com.proyectsicaraudio.model.Pedido;
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
public class PedController implements Serializable{
    @EJB
    private PedidoFacadeLocal pedidolocal;
    @EJB
    private EstadopedidoFacadeLocal estLocal;
    @Inject
    private Estadopedido estado;
    private List<Estadopedido> estados;
    @Inject
    private Pedido pedido;
    private List<Pedido> pedidos;
    
    private FacesContext facesContext;
    private ResourceBundle rb;
    @PostConstruct
    public void init(){
        pedidos = pedidolocal.findAll();
        estados = estLocal.findAll();
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjPed");
    }

    public Estadopedido getEstado() {
        return estado;
    }

    public void setEstado(Estadopedido estado) {
        this.estado = estado;
    }

    public List<Estadopedido> getEstados() {
        return estados;
    }

    public void setEstados(List<Estadopedido> estados) {
        this.estados = estados;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void cambiarEs(Pedido pedido){
            this.pedido = pedido;
    }
    
     public List<Pedido> listaPedidos(){
     return pedidolocal.findAll();
     }
     
     public Pedido mostrarCamb(Pedido pd){
         return pedido=pd;
     }
     
     public void actualizar(){
         try {
             Estadopedido et = estLocal.find(estado.getIdEstadoPe());
             pedido.setIdEstadoPe(et);
             for (Detallespedido d: pedido.getDetallespedidoList()) {
                 System.out.println("cantidad antes "+d.getCantidadProducto());
                 pedidolocal.reducirStock(d);
                 System.out.println("cantidad despues "+d.getCantidadProducto());
             }
             pedidolocal.edit(pedido);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso")
                    , ("HaCambiadoElEstadoDelPedido"))); 
         } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error")
                    , ("NoSeHaCambiadoElEstado"))); 
             System.out.println(e.getMessage());
         }        
     }
}