/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Cita;
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
public class CitaFacade extends AbstractFacade<Cita> implements CitaFacadeLocal {

    @PersistenceContext(unitName = "proyectsicaraudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaFacade() {
        super(Cita.class);
    }

    @Override
    public List<Cita> citasTecnico() {
        String consulta;
        List<Cita> listaCitas;
        Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
        try {
            consulta="FROM Cita c WHERE c.idUsuario=?1";
            Query query = getEntityManager().createQuery(consulta);
            query.setParameter(1, u);
            
            listaCitas=query.getResultList();
        } catch (Exception e) {
            
            throw e;
        }
        return listaCitas;
    }

    @Override
    public List<Cita> citasCliente() {
    String Consulta;
    List<Cita> listaCita;
    Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
        try {
            Consulta="FROM Cita c WHERE c.idCarro=in(SELECT Carro c where c.idUsuario=?1)";
            Query query= getEntityManager().createQuery(Consulta);
            query.setParameter(1, u);
            
            listaCita=query.getResultList();
        } catch (Exception e) {
         throw e;
        }return listaCita;
    }
    
}
