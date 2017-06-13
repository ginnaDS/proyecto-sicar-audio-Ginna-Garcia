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
import com.proyectsicaraudio.EJB.UsuarioFacadeLocal;
import com.proyectsicaraudio.model.Cliente;
import com.proyectsicaraudio.model.Detallespedido;
import com.proyectsicaraudio.model.Pedido;
import com.proyectsicaraudio.model.Prefactura;
import com.proyectsicaraudio.model.Producto;
import com.proyectsicaraudio.model.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author nina garcia
 */
@Named
@ViewScoped
public class prefacturaController implements Serializable {
    @EJB
    private PrefacturaFacadeLocal preLocal;
    @EJB
    private PedidoFacadeLocal pedidoLocal;
    @EJB
    private DetallespedidoFacadeLocal detallesP;
    @EJB
    private ProductoFacadeLocal productoLoc;
    @EJB
    private UsuarioFacadeLocal usuL;
    @Inject
    private Producto produc;
    private List<Producto> productos;
    @Inject
    private Prefactura pref;
    private List<Prefactura> prefacturas;
    @Inject
    private Pedido pedido;
    private List<Pedido> pedidos;
    @Inject
    private Detallespedido detalleP;
    private List<Detallespedido> detalles;
    @Inject 
    private Cliente cl;
    @Inject 
    private Usuario user;
    
    @PostConstruct
    public void init(){
      //detalles=detallesP.findAll();
     pedidos = pedidoLocal.pedidoUsuario();
      //prefacturas = preLocal.findAll();
     productos = productoLoc.findAll();
    }

    public ProductoFacadeLocal getProductoLoc() {
        return productoLoc;
    }

    public void setProductoLoc(ProductoFacadeLocal productoLoc) {
        this.productoLoc = productoLoc;
    }

    public Producto getProduc() {
        return produc;
    }

    public void setProduc(Producto produc) {
        this.produc = produc;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Prefactura> getPrefacturas() {
        return prefacturas;
    }

    public void setPrefacturas(List<Prefactura> prefacturas) {
        this.prefacturas = prefacturas;
    }

    public DetallespedidoFacadeLocal getDetallesP() {
        return detallesP;
    }

    public void setDetallesP(DetallespedidoFacadeLocal detallesP) {
        this.detallesP = detallesP;
    }

    public Detallespedido getDetalleP() {
        return detalleP;
    }

    public void setDetalleP(Detallespedido detalleP) {
        this.detalleP = detalleP;
    }

    public List<Detallespedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detallespedido> detalles) {
        this.detalles = detalles;
    }

    public PrefacturaFacadeLocal getPreLocal() {
        return preLocal;
    }

    public void setPreLocal(PrefacturaFacadeLocal preLocal) {
        this.preLocal = preLocal;
    }

    public PedidoFacadeLocal getPedidoLocal() {
        return pedidoLocal;
    }

    public void setPedidoLocal(PedidoFacadeLocal pedidoLocal) {
        this.pedidoLocal = pedidoLocal;
    }

    public Prefactura getPref() {
        return pref;
    }

    public void setPref(Prefactura pref) {
        this.pref = pref;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public void registrar(){
       
        
        
    }
    
    public void exportarPdf() throws JRException, IOException{
        Map<String,Object> parametros= new HashMap<String,Object>();
        //parametros.put("txtProductos", u.getPrimerNombre());
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJR.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getProductos()));
        
       HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       response.addHeader("Content-disposition", "attachment; filename=CatalogoMA.pdf");
       ServletOutputStream stream = response.getOutputStream();
     
        JasperExportManager.exportReportToPdfStream(jasperPrint,stream);
        
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void verPdf() throws JRException, IOException{
         File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/rptJR.jasper"));
         
         byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getProductos()));
         HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
         response.setContentType("application/pdf");
         response.setContentLength(bytes.length);
         ServletOutputStream stream = response.getOutputStream();
         stream.write(bytes,0,bytes.length);
         stream.flush();
         stream.close();
         
         FacesContext.getCurrentInstance().responseComplete();
    }
}
