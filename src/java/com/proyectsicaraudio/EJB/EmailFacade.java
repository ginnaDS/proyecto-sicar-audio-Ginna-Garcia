/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.EJB;

import com.proyectsicaraudio.model.Cita;
import com.proyectsicaraudio.model.Email;
import com.proyectsicaraudio.model.Usuario;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nina garcia
 */
@Stateless
public class EmailFacade implements EmailFacadeLocal{

    @Override
    public void enviarEmailRegistro(Usuario us) {
        try {
            Email e=new Email();
            e.setAsunto("Confirmacion de Registro");
            e.setMensaje("Cordial bienvenida a su empresa Milenium Audio, gracias por elegirnos a nosotros. "+" Su nombre de usuario es "+us.getNombreUsuario()+" y su contraseña sera "+us.getContrasenia());
            Session session = e.getSession();
            Message msj =new MimeMessage(session);
            msj.setFrom(new InternetAddress(Email.getREMITENTE()));
            msj.setSubject(e.getAsunto());
            msj.setContent(e.getMensaje(),"text/html");
            msj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(us.getCorreo()));

            Transport.send(msj);
        } catch (AddressException ex) {
            System.out.print("La direccion del correo ");
        }catch (MessagingException ex){
            System.out.print("se ha presentado un error");
            ex.printStackTrace();
        }
        
    }

    @Override
    public void envEmailRegistroT(Usuario usuario) {
         try {
            Email e=new Email();
            e.setAsunto("Confirmacion registro");
            e.setMensaje("Cordial bienvenida al sistema. "+usuario.getPrimerNombre()+" "+usuario.getPrimerApellido()+
            "\n Sus datos de usuario son: \n Nombre de usuario: "+usuario.getNombreUsuario()+" \n Contraseña: "+usuario.getContrasenia()
            );
            Session session = e.getSession();
            Message msj =new MimeMessage(session);
            msj.setFrom(new InternetAddress(Email.getREMITENTE()));
            msj.setSubject(e.getAsunto());
            msj.setContent(e.getMensaje(),"text/html");
            msj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getCorreo()));

            Transport.send(msj);
        } catch (AddressException ex) {
            System.out.print("La direccion del correo ");
        }catch (MessagingException ex){
            System.out.print("se ha presentado un error "+ex.getMessage());
         
        }}

    @Override
    public void envNotfCita(Cita cita, Usuario use) {
        try {
            Email e=new Email();
            e.setAsunto("Nueva Compra");
            System.out.println("////////nueva");
            e.setMensaje("Señor(a). "+use.getPrimerNombre()+" "+use.getPrimerApellido()+
            "\n Su compra numero :"+cita.getIdCita()+"\n Se encuentra en estado "+cita.getIdEstadoCi().getTipoEstadoC()+" \n y esta reservada para la fecha: "+cita.getFecha()
            );
            System.out.println(cita.getIdCita());
            System.out.println(use.getCc());
            Session session = e.getSession();
            Message msj =new MimeMessage(session);
            msj.setFrom(new InternetAddress(Email.getREMITENTE()));
            msj.setSubject(e.getAsunto());
            msj.setContent(e.getMensaje(),"text/html");
        
            msj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(use.getCorreo()));

            Transport.send(msj);
        } catch (AddressException ex) {
            System.out.print("La direccion del correo ");
        }catch (MessagingException ex){
            System.out.print("se ha presentado un error "+ex.getMessage());
         
        }
    }

    @Override
    public void envNotfCompra(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }