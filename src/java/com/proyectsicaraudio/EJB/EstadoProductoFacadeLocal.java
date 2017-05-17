/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.EstadoProducto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface EstadoProductoFacadeLocal {

    void create(EstadoProducto estadoProducto);

    void edit(EstadoProducto estadoProducto);

    void remove(EstadoProducto estadoProducto);

    EstadoProducto find(Object id);

    List<EstadoProducto> findAll();

    List<EstadoProducto> findRange(int[] range);

    int count();
    
}
