/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nina garcia
 */
@Entity
@Table(name = "estadousuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadousuario.findAll", query = "SELECT e FROM Estadousuario e"),
    @NamedQuery(name = "Estadousuario.findByIdEstadoUs", query = "SELECT e FROM Estadousuario e WHERE e.idEstadoUs = :idEstadoUs"),
    @NamedQuery(name = "Estadousuario.findByTipoEstado", query = "SELECT e FROM Estadousuario e WHERE e.tipoEstado = :tipoEstado")})
public class Estadousuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoUs", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoUs")
    private Integer idEstadoUs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipoEstado")
    private String tipoEstado;

    public Estadousuario() {
    }

    public Estadousuario(Integer idEstadoUs) {
        this.idEstadoUs = idEstadoUs;
    }

    public Estadousuario(Integer idEstadoUs, String tipoEstado) {
        this.idEstadoUs = idEstadoUs;
        this.tipoEstado = tipoEstado;
    }

    public Integer getIdEstadoUs() {
        return idEstadoUs;
    }

    public void setIdEstadoUs(Integer idEstadoUs) {
        this.idEstadoUs = idEstadoUs;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoUs != null ? idEstadoUs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadousuario)) {
            return false;
        }
        Estadousuario other = (Estadousuario) object;
        if ((this.idEstadoUs == null && other.idEstadoUs != null) || (this.idEstadoUs != null && !this.idEstadoUs.equals(other.idEstadoUs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.Estadousuario[ idEstadoUs=" + idEstadoUs + " ]";
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
}
