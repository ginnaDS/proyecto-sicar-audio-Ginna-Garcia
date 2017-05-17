/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Detallespedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface DetallespedidoFacadeLocal {

    void create(Detallespedido detallespedido);

    void edit(Detallespedido detallespedido);

    void remove(Detallespedido detallespedido);

    Detallespedido find(Object id);

    List<Detallespedido> findAll();

    List<Detallespedido> findRange(int[] range);

    int count();
    
}
