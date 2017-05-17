/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Carro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface CarroFacadeLocal {

    void create(Carro carro);

    void edit(Carro carro);

    void remove(Carro carro);

    Carro find(Object id);

    List<Carro> findAll();

    List<Carro> findRange(int[] range);
    
    List<Carro> carrosUsuario();

    int count();
    
}
