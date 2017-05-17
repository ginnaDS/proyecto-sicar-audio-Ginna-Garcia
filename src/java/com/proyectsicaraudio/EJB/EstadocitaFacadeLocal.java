/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Estadocita;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface EstadocitaFacadeLocal {

    void create(Estadocita estadocita);

    void edit(Estadocita estadocita);

    void remove(Estadocita estadocita);

    Estadocita find(Object id);

    List<Estadocita> findAll();

    List<Estadocita> findRange(int[] range);

    int count();
    
}
