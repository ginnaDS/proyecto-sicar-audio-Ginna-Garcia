/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.EstadopedidoFacadeLocal;
import com.proyectsicaraudio.EJB.PedidoFacadeLocal;
import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.Estadopedido;
import com.proyectsicaraudio.model.Pedido;
import com.proyectsicaraudio.model.Producto;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author nina garcia
 */
@Named
@ViewScoped
public class StockController implements Serializable{
    @EJB
    private ProductoFacadeLocal prodLocal;
    @EJB
    private EstadopedidoFacadeLocal estPedLocal;
    @EJB
    private PedidoFacadeLocal pedidoLocal;
    @Inject
    private Producto producto;
    @Inject
    private Pedido pedido;
    private List<Pedido> pedidos;
    @Inject
    private Estadopedido estado;
    
    @PostConstruct
    public void init(){
//        pedidos = descontarCantidad();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Estadopedido getEstado() {
        return estado;
    }

    public void setEstado(Estadopedido estado) {
        this.estado = estado;
    }

    public List<Pedido> pedidosLista(){
        List<Pedido> lista= new LinkedList<>();
        
        try {
            Estadopedido e = new Estadopedido(14);
            lista = pedidoLocal.traerPed(e);
            for (Pedido pedido1 : lista){
                
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    
}
