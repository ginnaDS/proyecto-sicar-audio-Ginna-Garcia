/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Detallespedido;
import com.proyectsicaraudio.model.Pedido;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nina garcia
 */
@Stateless
public class DetallespedidoFacade extends AbstractFacade<Detallespedido> implements DetallespedidoFacadeLocal {

    @PersistenceContext(unitName = "proyectsicaraudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallespedidoFacade() {
        super(Detallespedido.class);
    }

    @Override
    public List<Detallespedido> detallesPedido(Pedido pedido) {
        String consulta;
        List<Detallespedido> lista;
        try {
            consulta="FROM Detallespedido d WHERE d.idPedido=?1";
            Query query= getEntityManager().createQuery(consulta);
            query.setParameter(1, pedido);  
            
            lista=query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}

