/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.personal.ofm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bryan.cabrerafgkah
 */
@Entity
@Table(name = "ordenes", catalog = "bd_comandas", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenes.findAll", query = "SELECT o FROM Ordenes o"),
    @NamedQuery(name = "Ordenes.findByIdOrden", query = "SELECT o FROM Ordenes o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Ordenes.findByEstado", query = "SELECT o FROM Ordenes o WHERE o.estado = :estado"),
    @NamedQuery(name = "Ordenes.findByTotalPago", query = "SELECT o FROM Ordenes o WHERE o.totalPago = :totalPago"),
    @NamedQuery(name = "Ordenes.findByFecha", query = "SELECT o FROM Ordenes o WHERE o.fecha = :fecha")})
public class Ordenes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrden", nullable = false)
    private Long idOrden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalPago", nullable = false)
    private float totalPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", nullable = false)
    @ManyToOne(optional = false)
    private Clientes idCliente;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrden")
    private List<Detalles> detallesList;

    public Ordenes() {
    }

    public Ordenes(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Ordenes(Long idOrden, String estado, float totalPago, Date fecha) {
        this.idOrden = idOrden;
        this.estado = estado;
        this.totalPago = totalPago;
        this.fecha = fecha;
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(float totalPago) {
        this.totalPago = totalPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public List<Detalles> getDetallesList() {
        return detallesList;
    }

    public void setDetallesList(List<Detalles> detallesList) {
        this.detallesList = detallesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenes)) {
            return false;
        }
        Ordenes other = (Ordenes) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.personal.ofm.entity.Ordenes[ idOrden=" + idOrden + " ]";
    }
    
}
