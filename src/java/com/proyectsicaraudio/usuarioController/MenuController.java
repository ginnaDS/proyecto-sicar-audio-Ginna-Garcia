/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.usuarioController;

import com.proyectsicaraudio.EJB.PermisoFacadeLocal;
import com.proyectsicaraudio.model.Permiso;
import com.proyectsicaraudio.model.Rol;
import com.proyectsicaraudio.model.Usuario;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author nina garcia
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    @EJB
    private PermisoFacadeLocal permisoLocal;
    private List<Permiso> permisos;
    private MenuModel model;

    @PostConstruct
    public void init() {
        //this.establecerPermisos();
    }

    public PermisoFacadeLocal getPermisoLocal() {
        return permisoLocal;
    }

    public void setPermisoLocal(PermisoFacadeLocal permisoLocal) {
        this.permisoLocal = permisoLocal;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public List<Permiso> listarPermisos() {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("us");
        Rol rol = us.getIdRol();
        List<Permiso> permisos2 = new LinkedList<>();
        permisos2 = rol.getPermisoList();

        return permisos2;
    }

    /*  
    public void establecerPermisos(){
        FacesContext context = FacesContext.getCurrentInstance(); 
         Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("us");
        for (Permiso p: permisos){
                if (p.getUrl().equals(model) && p.get) {
                   DefaultSubMenu firstSubmenu = new DefaultSubMenu(p.getNombre()); 
                    for (Permiso i : permisos) {
                        Permiso subMenu = i.getSubmenu();
                        if (subMenu != null) {
                            if (submenu.getCodigo() == p.getIdPermiso()) {
                              DefaultMenuItem item = new DefaultMenuItem(p.getNombre());
                              firstSubMenu.addElement(item);
                            }
                            
                        }
                    }
                   model.addElement(firstSubmenu);
                }else{
                    if (p.getSubMenu() == null) {
                         DefaultMenuItem item = new DefaultMenuItem(p.getNombre());
                         model.addElement(item);
                    }
                
            }
           
            
            
        }
    }*/
}
