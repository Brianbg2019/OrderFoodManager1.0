/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal.ofm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brayan.Barrientos
 */
@Entity
@Table(name = "detalles", catalog = "bd_comandas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalles.findAll", query = "SELECT d FROM Detalles d"),
    @NamedQuery(name = "Detalles.findByIdDetalle", query = "SELECT d FROM Detalles d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detalles.findByCantidad", query = "SELECT d FROM Detalles d WHERE d.cantidad = :cantidad")})
public class Detalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalle", nullable = false)
    private Long idDetalle;
    @JoinColumn(name = "idOrden", referencedColumnName = "idOrden", nullable = false)
    @ManyToOne(optional = false)
    private Ordenes idOrden;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = false)
    @ManyToOne(optional = false)
    private Productos idProducto;

    public Detalles() {
    }

    public Detalles(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Ordenes getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Ordenes idOrden) {
        this.idOrden = idOrden;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalles)) {
            return false;
        }
        Detalles other = (Detalles) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.personal.ofm.entity.Detalles[ idDetalle=" + idDetalle + " ]";
    }
    
}
