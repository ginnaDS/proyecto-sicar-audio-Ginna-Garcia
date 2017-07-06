/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.EstadopedidoFacadeLocal;
import com.proyectsicaraudio.EJB.EventoFacadeLocal;
import com.proyectsicaraudio.EJB.PedidoFacadeLocal;
import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.Estadopedido;
import com.proyectsicaraudio.model.Evento;
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
    private EventoFacadeLocal eventLocal;
    @Inject
    private Evento evento;
    private List<Evento> eventos;
    @Inject
    private Producto producto;
    private List<Producto> productos;
    
    @PostConstruct
    public void init(){
        eventos = eventLocal.findAll();
        productos = prodLocal.findAll();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
