/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Usuario;

/**
 *
 * @author nina garcia
 */
public interface EmailFacadeLocal {
    void enviarEmailRegistro(Usuario us);
    void envEmailRegistroT(Usuario usuario);
    void envNotfCompra(Usuario user);
    void envNotfCita(Cita cita, Usuario use);
}
