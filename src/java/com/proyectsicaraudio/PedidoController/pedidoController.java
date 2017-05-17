/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.DetallespedidoFacadeLocal;
import com.proyectsicaraudio.EJB.PedidoFacadeLocal;
import com.proyectsicaraudio.EJB.PrefacturaFacadeLocal;
import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.Cliente;
import com.proyectsicaraudio.model.Detallespedido;
import com.proyectsicaraudio.model.Estadopedido;
import com.proyectsicaraudio.model.Pedido;
import com.proyectsicaraudio.model.Prefactura;
import com.proyectsicaraudio.model.Producto;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
public class pedidoController implements Serializable {

    @EJB
    private ProductoFacadeLocal productoFl;

    @EJB
    private PedidoFacadeLocal pedidoFl;
    
    @EJB
    private PrefacturaFacadeLocal prefacturaLocal;
    
    private boolean bloqueo;
            
    @Inject
    private Prefactura prefactura;
    @Inject
    private Producto producto;

    @Inject
    private Cliente cliente;

    @Inject
    private Detallespedido detallePedido;

    @EJB
    private DetallespedidoFacadeLocal detalleFl;

    
    private Date fecha;
    
    private List<Producto> productosSel;

    private List<Producto> productos;
    private List<Detallespedido> detallespedido;

    public PrefacturaFacadeLocal getPrefacturaLocal() {
        return prefacturaLocal;
    }

    public void setPrefacturaLocal(PrefacturaFacadeLocal prefacturaLocal) {
        this.prefacturaLocal = prefacturaLocal;
    }

    public Prefactura getPrefactura() {
        return prefactura;
    }

    public void setPrefactura(Prefactura prefactura) {
        this.prefactura = prefactura;
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

    public List<Producto> getProductosSel() {
        return productosSel;
    }

    public void setProductosSel(List<Producto> productosSel) {
        this.productosSel = productosSel;
    }

    public Detallespedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallespedido(Detallespedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    public List<Detallespedido> getDetallespedido() {
        return detallespedido;
    }

    public void setDetallespedido(List<Detallespedido> detallespedido) {
        this.detallespedido = detallespedido;
    }
    
     public boolean inactivo(){
            return bloqueo = true;
    }
    
    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    

    @PostConstruct
    public void init() {
        productos = productoFl.findAll();
        productosSel = new LinkedList<>();
        detallespedido = new LinkedList<>();
    }


    //Hice un metodo que se llama calcular
    //Ese metodo tiene dos variables una Double precio, osea que se llama precio y es de tipo Double
    //Tambien una tipo producto, que va a ser igual al metodo del EJB llamado find, el cual retorna un tipo Producto, poniendole un id especificado
    //ese producto, se le pone a detallePedido
    //Cree otra variable double  llamada PrecioP que sera igual a tomar el producto previamente puesto en detallepedido, se toma el precio de ese producto
    //luego cree un int cantidad que sera igual a tomar la cantidad de detallePedido (La que se puso en el formulario)
    //luego dije que precio sera igual a multiplicar precioP pot cantidad
    //Luego retornamos precio
    //En el formulario se tiene un outputlabel cuyo value sera este metodo(calcular---- Osea este de debajito)
    //Se le asigno este metodo tambien a el boton calcular a manera de actionListener
    //Usando la propiedad update que pertenece a primefaces el cual usa ajax se actualizo el formulario 
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
            System.out.println(producto.getIdProducto()+"///////////////:............");
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
        registrarPre(fecha, pedido);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
           ("PedidoRegistradoExitosamente")));  
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error"),
            ("NoSeHaRegistrado")));
        }
       
        
    }
    
    public double calcularT(Pedido pedido){
        double total=0.0;
        for (Detallespedido d : pedido.getDetallespedidoList()) {
            total+=d.getPrecioProducto();
        }
        return total;
    }
    
    public void registrarPre(Date feh,Pedido pedido){
        try {
        prefactura.setFechaEnvio(fecha);
        prefactura.setFecha(feh);
           double total= calcularT(pedido);
        prefactura.setIdPedido(pedido);
        prefactura.setValorTotal(total);
        prefacturaLocal.create(prefactura);
        } catch (Exception e) {
        }
    }
    

    
    
}
