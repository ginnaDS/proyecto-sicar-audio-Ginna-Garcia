/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nina garcia
 */
@Entity
@Table(name = "detallespedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallespedido.findAll", query = "SELECT d FROM Detallespedido d"),
    @NamedQuery(name = "Detallespedido.findByIdDetPedido", query = "SELECT d FROM Detallespedido d WHERE d.idDetPedido = :idDetPedido"),
    @NamedQuery(name = "Detallespedido.findByCantidadProducto", query = "SELECT d FROM Detallespedido d WHERE d.cantidadProducto = :cantidadProducto"),
    @NamedQuery(name = "Detallespedido.findByPrecioProducto", query = "SELECT d FROM Detallespedido d WHERE d.precioProducto = :precioProducto")})
public class Detallespedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetPedido")
    private Integer idDetPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadProducto")
    private int cantidadProducto;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioProducto")
    private double precioProducto;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto idProducto;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido idPedido;

    public Detallespedido() {
    }

    public Detallespedido(Integer idDetPedido) {
        this.idDetPedido = idDetPedido;
    }

    public Detallespedido(Integer idDetPedido, int cantidadProducto, String descripcion, double precioProducto) {
        this.idDetPedido = idDetPedido;
        this.cantidadProducto = cantidadProducto;
        this.descripcion = descripcion;
        this.precioProducto = precioProducto;
    }

    public Integer getIdDetPedido() {
        return idDetPedido;
    }

    public void setIdDetPedido(Integer idDetPedido) {
        this.idDetPedido = idDetPedido;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetPedido != null ? idDetPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallespedido)) {
            return false;
        }
        Detallespedido other = (Detallespedido) object;
        if ((this.idDetPedido == null && other.idDetPedido != null) || (this.idDetPedido != null && !this.idDetPedido.equals(other.idDetPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.Detallespedido[ idDetPedido=" + idDetPedido + " ]";
    }

}
