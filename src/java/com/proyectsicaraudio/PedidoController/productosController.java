/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.EstadoProductoFacadeLocal;
import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.EstadoProducto;
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
    @Inject
    private Producto producto;
    private List<Producto> productos;
    @Inject
    private EstadoProducto est;

    private FacesContext facesContext;
    private ResourceBundle rb;
    
    @PostConstruct
    public void init() {
        productos = productoLocal.findAll();
        facesContext = FacesContext.getCurrentInstance();
        rb =facesContext.getApplication().getResourceBundle(facesContext, "msjPed");
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
        try {
            SubirArchivos subir = new SubirArchivos();
            subir.setP(producto);
            String d=subir.SubirArchivo("catalogo", imagen);
            
            producto.setImagen(d+subir.extension(subir.nombreArchivo(imagen)));
            est.setIdEstPro(1);
            producto.setIdEstPro(est);
            productoLocal.create(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso"),
            ("LosProductosSeHanGuardadoCorrectamente")));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error"),
           ("OcurrioUnErrorAlRegistrar")));
        }

    }
    
    public Producto verProducto(Producto pr){
        Producto prod=productoLocal.find(pr.getIdProducto());
        return producto=prod;
    }
    
    public void cambiar(){
        try {
            productoLocal.edit(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,("Aviso")
                    , ("LosProductosSeActualizaronCorrectamente")));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,("Error")
                    , ("NoSeHaActualizado")));
        }
    }
}
