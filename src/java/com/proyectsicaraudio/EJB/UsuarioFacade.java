/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "proyectsicaraudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(Usuario usuario) {
        Usuario us = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.nombreUsuario=?1 and u.contrasenia=?2";
            Query query = getEntityManager().createQuery(consulta);
            query.setParameter(1, usuario.getNombreUsuario());
            query.setParameter(2, usuario.getContrasenia());

            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                us = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {

        }
        return us;
    }

    @Override
    public List<Usuario> traerUsuarios(Rol rol) {
        Usuario use = null;
        List<Usuario> lista = null;
        String Consulta;
        try {
            Consulta = "FROM Usuario u WHERE u.idRol=?1";
            Query query = getEntityManager().createQuery(Consulta);
            query.setParameter(1, rol);
            lista = query.getResultList();

        } catch (Exception e) {
            throw e;
        } finally {

        }
        return lista;
    }
}
