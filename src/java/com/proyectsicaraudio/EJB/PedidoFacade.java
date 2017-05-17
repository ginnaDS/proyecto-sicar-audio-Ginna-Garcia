/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Cliente;
import com.proyectsicaraudio.model.Detallespedido;
import com.proyectsicaraudio.model.Estadopedido;
import com.proyectsicaraudio.model.Pedido;
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
public class PedidoFacade extends AbstractFacade<Pedido> implements PedidoFacadeLocal {

    @PersistenceContext(unitName = "proyectsicaraudioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    @Override
    public Cliente pedidosCliente(Usuario usuario) {
        Pedido ped=null;
        String consulta;
        List<Cliente> clie;
        Cliente cliente=null;
        
        try {
            consulta="FROM Cliente c WHERE c.idUsuario=?1";
            Query query= getEntityManager().createQuery(consulta);
            query.setParameter(1, usuario);
            
            clie=query.getResultList();
            if (!clie.isEmpty()) {
                
            cliente=clie.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        
        return cliente; 
        
    }

    @Override
    public List<Pedido> pedidoUsuario() {
         String consulta;
        List<Pedido> pedUsuario;
        Usuario u=(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
        Cliente c= pedidosCliente(u);
;        try {
            consulta="FROM Pedido p WHERE p.idCliente=?1";
            Query query= getEntityManager().createQuery(consulta);
            query.setParameter(1, c);
            
            pedUsuario=query.getResultList();
            
            System.out.println(pedUsuario.get(0));
            System.out.println(u.getIdUsuario()+"//////////carroFacade 49");
            pedidosCliente(u);
        } catch (Exception e) {
            throw e;
        }
        return pedUsuario;}

    @Override
    public boolean reducirStock(Detallespedido d) {
        boolean confirmacion = false;
        try {
            Query consulta=getEntityManager().createNativeQuery("call descontar_stock(?1,?2)");
            consulta.setParameter(1, d.getIdProducto());
            System.out.println("id "+d.getIdPedido());
            consulta.setParameter(2, d.getCantidadProducto());
            System.out.println("cantidad "+d.getCantidadProducto());
            confirmacion=true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return confirmacion;
    }

    @Override
    public List<Pedido> traerPed(Estadopedido e) {
        String consulta;
        List<Pedido> lista;
        try {
            consulta="from Pedido p where p.idEstadoPe=?1";
            Query query= getEntityManager().createQuery(consulta);
            query.setParameter(1, e);
            
            lista=query.getResultList();
            
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }
    }
