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
import javax.ejb.Local;

/**
 *
 * @author nina garcia
 */
@Local
public interface PedidoFacadeLocal {

    void create(Pedido pedido);

    void edit(Pedido pedido);

    void remove(Pedido pedido);

    Pedido find(Object id);

    List<Pedido> findAll();

    List<Pedido> findRange(int[] range);
    
    public Cliente pedidosCliente(Usuario usuario);

    List<Pedido> pedidoUsuario();
    
    int count();
    
    boolean reducirStock(Detallespedido d);
    
    List<Pedido> traerPed(Estadopedido e);
}
