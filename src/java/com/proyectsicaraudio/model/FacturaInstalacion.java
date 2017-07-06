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
@Table(name = "facturainstalacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaInstalacion.findAll", query = "SELECT f FROM FacturaInstalacion f"),
    @NamedQuery(name = "FacturaInstalacion.findByIdFcIns", query = "SELECT f FROM FacturaInstalacion f WHERE f.idFcIns = :idFcIns"),
    @NamedQuery(name = "FacturaInstalacion.findByTotal", query = "SELECT f FROM FacturaInstalacion f WHERE f.total = :total"),
    @NamedQuery(name = "FacturaInstalacion.findByFecha", query = "SELECT f FROM FacturaInstalacion f WHERE f.fecha = :fecha")})
public class FacturaInstalacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFcIns")
    private Integer idFcIns;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido idPedido;
    @JoinColumn(name = "idCita", referencedColumnName = "idCita")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cita idCita;

    public FacturaInstalacion() {
    }

    public FacturaInstalacion(Integer idFcIns) {
        this.idFcIns = idFcIns;
    }

    public FacturaInstalacion(Integer idFcIns, double total, Date fecha) {
        this.idFcIns = idFcIns;
        this.total = total;
        this.fecha = fecha;
    }

    public Integer getIdFcIns() {
        return idFcIns;
    }

    public void setIdFcIns(Integer idFcIns) {
        this.idFcIns = idFcIns;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Cita getIdCita() {
        return idCita;
    }

    public void setIdCita(Cita idCita) {
        this.idCita = idCita;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFcIns != null ? idFcIns.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaInstalacion)) {
            return false;
        }
        FacturaInstalacion other = (FacturaInstalacion) object;
        if ((this.idFcIns == null && other.idFcIns != null) || (this.idFcIns != null && !this.idFcIns.equals(other.idFcIns))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.FacturaInstalacion[ idFcIns=" + idFcIns + " ]";
    }
    
}
