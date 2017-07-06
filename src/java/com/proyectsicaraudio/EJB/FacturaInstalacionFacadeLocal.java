/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.FacturaInstalacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface FacturaInstalacionFacadeLocal {

    void create(FacturaInstalacion facturaInstalacion);

    void edit(FacturaInstalacion facturaInstalacion);

    void remove(FacturaInstalacion facturaInstalacion);

    FacturaInstalacion find(Object id);

    List<FacturaInstalacion> findAll();

    List<FacturaInstalacion> findRange(int[] range);

    int count();
    
}
