/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.InstalacionController;

import com.proyectsicaraudio.EJB.DetallespedidoFacadeLocal;
import com.proyectsicaraudio.EJB.FacturaInstalacionFacadeLocal;
import com.proyectsicaraudio.EJB.PedidoFacadeLocal;
import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Cliente;
import com.proyectsicaraudio.model.Detallespedido;
import com.proyectsicaraudio.model.Estadopedido;
import com.proyectsicaraudio.model.FacturaInstalacion;
import com.proyectsicaraudio.model.Pedido;
import com.proyectsicaraudio.model.Prefactura;
import com.proyectsicaraudio.model.Producto;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class pedidoTecnico implements Serializable{
    @EJB
    private ProductoFacadeLocal productoFl;
    @EJB
    private PedidoFacadeLocal pedidoFl;
    @EJB
    private FacturaInstalacionFacadeLocal facturaLocal;
    @EJB
    private DetallespedidoFacadeLocal detalleFl;
    @Inject
    private FacturaInstalacion factura;
    @Inject
    private Producto producto;
    @Inject
    private Cliente cliente;
    @Inject
    private Detallespedido detallePedido;

    private Date fecha;

    private List<Producto> productosSel;

    private List<Producto> productos;
    private List<Detallespedido> detallespedido;

    public FacturaInstalacion getFactura() {
        return factura;
    }

    public void setFactura(FacturaInstalacion factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Detallespedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(Detallespedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductosSel() {
        return productosSel;
    }

    public void setProductosSel(List<Producto> productosSel) {
        this.productosSel = productosSel;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Detallespedido> getDetallespedido() {
        return detallespedido;
    }

    public void setDetallespedido(List<Detallespedido> detallespedido) {
        this.detallespedido = detallespedido;
    }
    
    @PostConstruct
    public void init() {
        productos = productoFl.findAll();
        productosSel = new LinkedList<>();
        detallespedido = new LinkedList<>();
    }
    
    public Double calcular() {

        Double precio = 0.0;
        Producto p;
        try {
            p = productoFl.find(producto.getIdProducto());
            detallePedido.setIdProducto(p);
            Double precioP = detallePedido.getIdProducto().getPrecio();
            int cantidad = detallePedido.getCantidadProducto();

            precio = precioP * cantidad;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return precio;
    }

    public void añadir() {
        Producto p;
        p = productoFl.find(producto.getIdProducto());
        System.out.println(producto.getIdProducto() + "///////////////:............");
        productosSel.add(p);
        System.out.println(productosSel.size());
    }

    public List<Detallespedido> registrar() {

        Double precio;

        try {

            precio = calcular();
            if (precio <= 0.0) {
                System.out.println("Debe calcular el precio para crear detalle");
            } else {
                detallePedido.setPrecioProducto(precio);
                Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
                cliente = pedidoFl.pedidosCliente(user);
                detallespedido.add(detallePedido);
                detallePedido = new Detallespedido();
                precio = 0.0;

            }
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        return detallespedido;
    }

    public void registrarPedido() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            //Se crea una variable tipo pedido
            Pedido pedido = new Pedido();
            //Se crea una variable date
            Date fecha = new Date();
            //Se le agrega la fecha al pedido con la variable tipo date
            pedido.setFecha(fecha);

            pedido.setIdCliente(cliente);

            Estadopedido estadoPedido = new Estadopedido();
            estadoPedido.setIdEstadoPe(14);
            pedido.setIdEstadoPe(estadoPedido);

            for (Detallespedido d : detallespedido) {
                d.setIdPedido(pedido);
            }
            pedido.setDetallespedidoList(detallespedido);
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
            cliente = pedidoFl.pedidosCliente(user);
            pedido.setIdCliente(cliente);
            pedidoFl.create(pedido);
            regFc(pedido);
            detallespedido = new ArrayList();
            context.execute("swal('Registro exitoso','Tu pedido esta en proceso','success')");
        } catch (Exception e) {
            context.execute("swal('Ha ocurrido un error','Intentalo mas tarde','error')");
        }

    }

    public double calcularT(Pedido pedido) {
        double total = 0.0;
        for (Detallespedido d : pedido.getDetallespedidoList()) {
            total += d.getPrecioProducto();
        }
        return total;
    }
    
    public void regFc(Pedido pdo){
        Cita cita = new Cita();
        try {
            Date fec= new Date();
            factura.setFecha(fec);
            double total = calcularT(pdo);
            factura.setIdCita(cita);
            factura.setIdPedido(pdo);
            factura.setTotal(total);
            facturaLocal.create(factura);
        } catch (Exception e) {
        }
        
    }
 
}
