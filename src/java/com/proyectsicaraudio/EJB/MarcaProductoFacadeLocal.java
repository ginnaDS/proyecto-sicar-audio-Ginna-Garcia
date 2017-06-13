/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.MarcaProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface MarcaProductoFacadeLocal {

    void create(MarcaProducto marcaProducto);

    void edit(MarcaProducto marcaProducto);

    void remove(MarcaProducto marcaProducto);

    MarcaProducto find(Object id);

    List<MarcaProducto> findAll();

    List<MarcaProducto> findRange(int[] range);

    int count();
    
}
