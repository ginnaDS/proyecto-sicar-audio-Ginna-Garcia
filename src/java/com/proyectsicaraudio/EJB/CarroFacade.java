/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Carro;
import com.proyectsicaraudio.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nina garcia
 */
@Stateless
public class CarroFacade extends AbstractFacade<Carro> implements CarroFacadeLocal {

    @PersistenceContext(unitName = "proyectsicaraudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarroFacade() {
        super(Carro.class);
    }

    @Override
    public List<Carro> carrosUsuario() {
        String consulta;
        List<Carro> lista;
        Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
        try {
            consulta="FROM Carro c WHERE c.idUsuario=?1";
            Query query= getEntityManager().createQuery(consulta);
            query.setParameter(1, u);
            
            lista=query.getResultList();
           
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}