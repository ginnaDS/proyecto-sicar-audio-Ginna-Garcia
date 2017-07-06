/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.DetallespedidoFacadeLocal;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author nina garcia
 */
@Named
@ViewScoped
public class PedController implements Serializable {

    @EJB
    private PedidoFacadeLocal pedidolocal;
    @EJB
    private EstadopedidoFacadeLocal estLocal;
    @EJB
    private DetallespedidoFacadeLocal detLocal;
    @Inject
    private Detallespedido detalle;
    private List<Detallespedido> detalles;
    @Inject
    private Estadopedido estado;
    private List<Estadopedido> estados;
    @Inject
    private Pedido pedido;
    private List<Pedido> pedidos;
    
    private FacesContext facesContext;
    private ResourceBundle rb;

    @PostConstruct
    public void init() {
        pedidos = pedidolocal.findAll();
        estados = estLocal.findAll();
        detalles = detLocal.detallesPedido(pedido);
        facesContext = FacesContext.getCurrentInstance();
        rb = facesContext.getApplication().getResourceBundle(facesContext, "msjPed");
    }

    public Detallespedido getDetalle() {
        return detalle;
    }

    public void setDetalle(Detallespedido detalle) {
        this.detalle = detalle;
    }

    public List<Detallespedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detallespedido> detalles) {
        this.detalles = detalles;
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
    
    public void cambiarEs(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public List<Pedido> listaPedidos() {
        return pedidolocal.findAll();
    }
    
    public void mostrarCamb(Pedido pd) {
        pedido = pd;
    }
    
    public void actualizar(Pedido item) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            pedido=pedidolocal.find(item.getIdPedido());
            pedido.setIdEstadoPe(estLocal.find(17));
            pedidolocal.edit(pedido);
            context.execute("swal('Actualizacion','Exitosa','success')");            
        } catch (Exception e) {
            context.execute("swal('Lo sentimos','Intentalo nuevamente','error')");
            System.out.println(e.getMessage());
        }        
    }
}
