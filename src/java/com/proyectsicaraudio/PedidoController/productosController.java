/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.EstadoProductoFacadeLocal;
import com.proyectsicaraudio.EJB.MarcaProductoFacadeLocal;
import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.EstadoProducto;
import com.proyectsicaraudio.model.MarcaProducto;
import com.proyectsicaraudio.model.Producto;
import com.proyectsicaraudio.usuarioController.SubirArchivos;
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
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author nina garcia
 */
@Named
@ViewScoped
public class productosController implements Serializable {

    @EJB
    private ProductoFacadeLocal productoLocal;
    @EJB
    private EstadoProductoFacadeLocal estLocal;
    @EJB
    private MarcaProductoFacadeLocal marcaEjb;
    @Inject
    private Producto producto;
    private List<Producto> productos;
    @Inject
    private EstadoProducto est;
    @Inject
    private MarcaProducto marca;
    private List<MarcaProducto> marcas;
    
    private FacesContext facesContext;
    private ResourceBundle rb;
    
    @PostConstruct
    public void init() {
        marcas = marcaEjb.findAll();
        productos = productoLocal.findAll();
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjPed");
    }

    public MarcaProducto getMarca() {
        return marca;
    }

    public void setMarca(MarcaProducto marca) {
        this.marca = marca;
    }

    public List<MarcaProducto> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<MarcaProducto> marcas) {
        this.marcas = marcas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ProductoFacadeLocal getProductoLocal() {
        return productoLocal;
    }

    public void setProductoLocal(ProductoFacadeLocal productoLocal) {
        this.productoLocal = productoLocal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void registrarProducto(Part imagen) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            SubirArchivos subir = new SubirArchivos();
            subir.setP(producto);
            String d = subir.SubirArchivo("catalogo", imagen);
            est = estLocal.find(1);
            producto.setIdEstPro(est);
            producto.setIdMarca(marca);
            producto.setImagen(d+subir.extension(subir.nombreArchivo(imagen)));
            productoLocal.create(producto);
            context.execute("swal('Registro','Exitoso','success')");
        } catch (Exception e) {
            context.execute("swal('Lo sentimos','Intentalo nuevamene, en otro momento','error')");
        }

    }
    
    public Producto verProducto(Producto pr){
        Producto prod=productoLocal.find(pr.getIdProducto());
        return producto=prod;
    }
    
    public void cambiar(){
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            productoLocal.edit(producto);
            context.execute("swal('Se cambiaron','Los datos de manera correcta','success')");
        } catch (Exception e) {
            context.execute("swal('Lo sentimos','Ha ocurrido un error','error')");
        }
    }
}
