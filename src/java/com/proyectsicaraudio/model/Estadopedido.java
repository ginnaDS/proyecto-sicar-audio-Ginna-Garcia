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
@Table(name = "estadopedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadopedido.findAll", query = "SELECT e FROM Estadopedido e"),
    @NamedQuery(name = "Estadopedido.findByIdEstadoPe", query = "SELECT e FROM Estadopedido e WHERE e.idEstadoPe = :idEstadoPe"),
    @NamedQuery(name = "Estadopedido.findByTipoEstadoP", query = "SELECT e FROM Estadopedido e WHERE e.tipoEstadoP = :tipoEstadoP")})
public class Estadopedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoPe")
    private Integer idEstadoPe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tipoEstadoP")
    private String tipoEstadoP;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoPe", fetch = FetchType.LAZY)
    private List<Pedido> pedidoList;

    public Estadopedido() {
    }

    public Estadopedido(Integer idEstadoPe) {
        this.idEstadoPe = idEstadoPe;
    }

    public Estadopedido(Integer idEstadoPe, String tipoEstadoP) {
        this.idEstadoPe = idEstadoPe;
        this.tipoEstadoP = tipoEstadoP;
    }

    public Integer getIdEstadoPe() {
        return idEstadoPe;
    }

    public void setIdEstadoPe(Integer idEstadoPe) {
        this.idEstadoPe = idEstadoPe;
    }

    public String getTipoEstadoP() {
        return tipoEstadoP;
    }

    public void setTipoEstadoP(String tipoEstadoP) {
        this.tipoEstadoP = tipoEstadoP;
    }

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPe != null ? idEstadoPe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadopedido)) {
            return false;
        }
        Estadopedido other = (Estadopedido) object;
        if ((this.idEstadoPe == null && other.idEstadoPe != null) || (this.idEstadoPe != null && !this.idEstadoPe.equals(other.idEstadoPe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.Estadopedido[ idEstadoPe=" + idEstadoPe + " ]";
    }
    
}
