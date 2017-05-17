/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectsicaraudio.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nina garcia
 */
@Entity
@Table(name = "prefacturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prefactura.findAll", query = "SELECT p FROM Prefactura p"),
    @NamedQuery(name = "Prefactura.findByIdPrefactura", query = "SELECT p FROM Prefactura p WHERE p.idPrefactura = :idPrefactura"),
    @NamedQuery(name = "Prefactura.findByValorTotal", query = "SELECT p FROM Prefactura p WHERE p.valorTotal = :valorTotal"),
    @NamedQuery(name = "Prefactura.findByFecha", query = "SELECT p FROM Prefactura p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Prefactura.findByFechaEnvio", query = "SELECT p FROM Prefactura p WHERE p.fechaEnvio = :fechaEnvio")})
public class Prefactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrefactura")
    private Integer idPrefactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorTotal")
    private double valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaEnvio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido idPedido;

    public Prefactura() {
    }

    public Prefactura(Integer idPrefactura) {
        this.idPrefactura = idPrefactura;
    }

    public Prefactura(Integer idPrefactura, double valorTotal, Date fecha, Date fechaEnvio) {
        this.idPrefactura = idPrefactura;
        this.valorTotal = valorTotal;
        this.fecha = fecha;
        this.fechaEnvio = fechaEnvio;
    }

    public Integer getIdPrefactura() {
        return idPrefactura;
    }

    public void setIdPrefactura(Integer idPrefactura) {
        this.idPrefactura = idPrefactura;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
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
        hash += (idPrefactura != null ? idPrefactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prefactura)) {
            return false;
        }
        Prefactura other = (Prefactura) object;
        if ((this.idPrefactura == null && other.idPrefactura != null) || (this.idPrefactura != null && !this.idPrefactura.equals(other.idPrefactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.Prefactura[ idPrefactura=" + idPrefactura + " ]";
    }
    
}
