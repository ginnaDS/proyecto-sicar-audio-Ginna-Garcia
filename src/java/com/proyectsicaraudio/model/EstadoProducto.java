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
@Table(name = "estadosproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoProducto.findAll", query = "SELECT e FROM EstadoProducto e"),
    @NamedQuery(name = "EstadoProducto.findByIdEstPro", query = "SELECT e FROM EstadoProducto e WHERE e.idEstPro = :idEstPro"),
    @NamedQuery(name = "EstadoProducto.findByTipoEstado", query = "SELECT e FROM EstadoProducto e WHERE e.tipoEstado = :tipoEstado")})
public class EstadoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstPro")
    private Integer idEstPro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "tipoEstado")
    private String tipoEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstPro", fetch = FetchType.LAZY)
    private List<Producto> productoList;

    public EstadoProducto() {
    }

    public EstadoProducto(Integer idEstPro) {
        this.idEstPro = idEstPro;
    }

    public EstadoProducto(Integer idEstPro, String tipoEstado) {
        this.idEstPro = idEstPro;
        this.tipoEstado = tipoEstado;
    }

    public Integer getIdEstPro() {
        return idEstPro;
    }

    public void setIdEstPro(Integer idEstPro) {
        this.idEstPro = idEstPro;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstPro != null ? idEstPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProducto)) {
            return false;
        }
        EstadoProducto other = (EstadoProducto) object;
        if ((this.idEstPro == null && other.idEstPro != null) || (this.idEstPro != null && !this.idEstPro.equals(other.idEstPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.EstadoProducto[ idEstPro=" + idEstPro + " ]";
    }
    
}
