/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Estadousuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nina garcia
 */
@Stateless
public class EstadousuarioFacade extends AbstractFacade<Estadousuario> implements EstadousuarioFacadeLocal {

    @PersistenceContext(unitName = "proyectsicaraudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadousuarioFacade() {
        super(Estadousuario.class);
    }
    
}
