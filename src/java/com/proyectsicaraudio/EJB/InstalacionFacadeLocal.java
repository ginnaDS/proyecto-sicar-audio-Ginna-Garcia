/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Instalacion;
import com.proyectsicaraudio.model.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface InstalacionFacadeLocal {

    void create(Instalacion instalacion);

    void edit(Instalacion instalacion);

    void remove(Instalacion instalacion);

    Instalacion find(Object id);

    List<Instalacion> findAll();

    List<Instalacion> findRange(int[] range);

    int count();
}
