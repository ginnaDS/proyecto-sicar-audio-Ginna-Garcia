/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nina garcia
 */

public class Email{
   private final static String HOST="smtp.gmail.com";
   private final static String PORT="587";//25 O 587
   private final static String REMITENTE="sicaraudiocol@gmail.com";
   private final static String REMITENTE_PASS="sicaraudio2234";
   
   private String asunto;
   private String mensaje;
   private String destinatario;

    public Email() {
        inicializarPropiedades();
    }
    
    private Properties propiedades;
 
    public Email (String asunto, String mensaje, String destinatario){
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
        inicializarPropiedades();
        
    }
   
     private void inicializarPropiedades() {
        propiedades =new Properties();
        propiedades.put("mail.smtp.auth","true");
        propiedades.put("mail.smtp.starttls.enable","true");
        propiedades.put("mail.smtp.host",HOST);
        propiedades.put("mail.smtp.port",PORT);
    }
     
     public Session getSession(){
         Authenticator a = new Authenticator() {
              @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, REMITENTE_PASS);
            }
         };
     return Session.getInstance(propiedades, a);
     }
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Properties getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(Properties propiedades) {
        this.propiedades = propiedades;
    }

    public static String getHOST() {
        return HOST;
    }

    public static String getPORT() {
        return PORT;
    }

    public static String getREMITENTE() {
        return REMITENTE;
    }

    public static String getREMITENTE_PASS() {
        return REMITENTE_PASS;
    }
     
     
}

