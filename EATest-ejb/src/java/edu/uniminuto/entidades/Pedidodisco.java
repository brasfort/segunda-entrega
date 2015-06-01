/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon Gómez
 */
@Entity
@Table(name = "pedidodisco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidodisco.findAll", query = "SELECT p FROM Pedidodisco p"),
    @NamedQuery(name = "Pedidodisco.findById", query = "SELECT p FROM Pedidodisco p WHERE p.id = :id"),
    @NamedQuery(name = "Pedidodisco.findByComentario", query = "SELECT p FROM Pedidodisco p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "Pedidodisco.findByEnviado", query = "SELECT p FROM Pedidodisco p WHERE p.enviado = :enviado"),
    @NamedQuery(name = "Pedidodisco.findByFechaenvio", query = "SELECT p FROM Pedidodisco p WHERE p.fechaenvio = :fechaenvio"),
    @NamedQuery(name = "Pedidodisco.findByRechazado", query = "SELECT p FROM Pedidodisco p WHERE p.rechazado = :rechazado")})
public class Pedidodisco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enviado")
    private boolean enviado;
    @Column(name = "fechaenvio")
    @Temporal(TemporalType.DATE)
    private Date fechaenvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rechazado")
    private boolean rechazado;
    @JoinColumn(name = "pedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "discop", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Discopropietario discop;

    public Pedidodisco() {
    }

    public Pedidodisco(Integer id) {
        this.id = id;
    }

    public Pedidodisco(Integer id, boolean enviado, boolean rechazado) {
        this.id = id;
        this.enviado = enviado;
        this.rechazado = rechazado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public Date getFechaenvio() {
        return fechaenvio;
    }

    public void setFechaenvio(Date fechaenvio) {
        this.fechaenvio = fechaenvio;
    }

    public boolean getRechazado() {
        return rechazado;
    }

    public void setRechazado(boolean rechazado) {
        this.rechazado = rechazado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Discopropietario getDiscop() {
        return discop;
    }

    public void setDiscop(Discopropietario discop) {
        this.discop = discop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidodisco)) {
            return false;
        }
        Pedidodisco other = (Pedidodisco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Pedidodisco[ id=" + id + " ]";
    }
    
}
