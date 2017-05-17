/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.model.Evento;
import com.proyectsicaraudio.model.Producto;
import com.proyectsicaraudio.model.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author nina garcia
 */
@Named
@RequestScoped
public class SubirArchivos implements Serializable {

    private Part carpeta;

    private String ruta;

    private List<Part> lista;

    private Evento e;

    private Producto p;
    
    private String extension;

    public Part getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Part carpeta) {
        this.carpeta = carpeta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<Part> getLista() {
        return lista;
    }

    public void setLista(List<Part> lista) {
        this.lista = lista;
    }

    public Evento getE() {
        return e;
    }

    public void setE(Evento e) {
        this.e = e;
    }

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }
    
    

    public String path() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        return path;
    }

    public String nombreArchivo(Part part) {
        String[] partesurl = part.getSubmittedFileName().split("\\\\");
        return partesurl[partesurl.length - 1];

    }
    
    public String extension(String nombre){
    int index= nombre.lastIndexOf(".");
    return nombre.substring(index);
    }

    private File tomararchivo(Part part, String tipo, String nombre) {
        try {
            System.out.println("tomar archivo 1");
            InputStream inStream = part.getInputStream();

            File ruta = new File(path() + "/resources/" + tipo);
            File f = new File(ruta, nombre+extension(nombreArchivo(part)));
            
            System.out.println("tomar archivo 2");
            OutputStream outStream = new FileOutputStream(f);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inStream.read(buf)) > 0) {
                outStream.write(buf, 0, len);
            }
            outStream.close();
            inStream.close();
            System.out.println("Conversion realizada");
            return f;
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }

    public String SubirArchivo(String tipo, Part part) {

        String dato=null;
        File f= null;
        switch (tipo) {
            case "eventos":
                dato= tipoEvento(e);
        System.out.println("Subir archivo");
                break;
                
            case "catalogo":
                dato=tipoProducto(p);
                break;
                
            case "usuarios":
                dato=tipoUsuario();
                break;        
        }
        
        if (dato!=null) {
          f= tomararchivo(part, tipo, dato);
        }else{
            System.out.println("Dato es nulo");
        }
            
        return dato;
    }

    public String tipoEvento(Evento e) {
        return e.getNombre().replace(" ", "_") + e.getFecha().getMinutes();
    }

    public String tipoProducto(Producto p) {
        return "catalogo"+ p.getIdProducto();
    }

    public String tipoUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("us");
        return us.getPrimerApellido() + us.getCc();
    }
}
