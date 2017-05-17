/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.PedidoController;

import com.proyectsicaraudio.EJB.ProductoFacadeLocal;
import com.proyectsicaraudio.model.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImagesView {
     
    private List<String> images;
    private List<Producto> productos;
    
    @EJB
    private ProductoFacadeLocal productoEJB;
     
    @PostConstruct
    public void init() {
        productos=productoEJB.findAll();
                
        images = new ArrayList<String>();
        for (Producto p : productos) {
            images.add(p.getImagen());
        }
    }
 
    public List<String> getImages() {
        return images;
    }
    
   
}