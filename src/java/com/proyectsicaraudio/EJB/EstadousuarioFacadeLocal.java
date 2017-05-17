/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Estadousuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface EstadousuarioFacadeLocal {

    void create(Estadousuario estadousuario);

    void edit(Estadousuario estadousuario);

    void remove(Estadousuario estadousuario);

    Estadousuario find(Object id);

    List<Estadousuario> findAll();

    List<Estadousuario> findRange(int[] range);

    int count();
    
}
