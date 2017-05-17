/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Prefactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface PrefacturaFacadeLocal {

    void create(Prefactura prefactura);

    void edit(Prefactura prefactura);

    void remove(Prefactura prefactura);

    Prefactura find(Object id);

    List<Prefactura> findAll();

    List<Prefactura> findRange(int[] range);

    int count();
    
}
