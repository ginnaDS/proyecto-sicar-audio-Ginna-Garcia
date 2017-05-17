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
@Table(name = "estadocitas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocita.findAll", query = "SELECT e FROM Estadocita e"),
    @NamedQuery(name = "Estadocita.findByIdEstadoCi", query = "SELECT e FROM Estadocita e WHERE e.idEstadoCi = :idEstadoCi"),
    @NamedQuery(name = "Estadocita.findByTipoEstadoC", query = "SELECT e FROM Estadocita e WHERE e.tipoEstadoC = :tipoEstadoC")})
public class Estadocita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadoCi")
    private Integer idEstadoCi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tipoEstadoC")
    private String tipoEstadoC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoCi", fetch = FetchType.LAZY)
    private List<Cita> citaList;

    public Estadocita() {
    }

    public Estadocita(Integer idEstadoCi) {
        this.idEstadoCi = idEstadoCi;
    }

    public Estadocita(Integer idEstadoCi, String tipoEstadoC) {
        this.idEstadoCi = idEstadoCi;
        this.tipoEstadoC = tipoEstadoC;
    }

    public Integer getIdEstadoCi() {
        return idEstadoCi;
    }

    public void setIdEstadoCi(Integer idEstadoCi) {
        this.idEstadoCi = idEstadoCi;
    }

    public String getTipoEstadoC() {
        return tipoEstadoC;
    }

    public void setTipoEstadoC(String tipoEstadoC) {
        this.tipoEstadoC = tipoEstadoC;
    }

    @XmlTransient
    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCi != null ? idEstadoCi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocita)) {
            return false;
        }
        Estadocita other = (Estadocita) object;
        if ((this.idEstadoCi == null && other.idEstadoCi != null) || (this.idEstadoCi != null && !this.idEstadoCi.equals(other.idEstadoCi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectsicaraudio.model.Estadocita[ idEstadoCi=" + idEstadoCi + " ]";
    }
    
}
