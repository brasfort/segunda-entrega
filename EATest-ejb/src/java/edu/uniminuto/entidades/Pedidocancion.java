/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.entidades;

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
 * @author Jhon Gómez
 */
@Entity
@Table(name = "pedidocancion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidocancion.findAll", query = "SELECT p FROM Pedidocancion p"),
    @NamedQuery(name = "Pedidocancion.findById", query = "SELECT p FROM Pedidocancion p WHERE p.id = :id")})
public class Pedidocancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "pedido", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "cancion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cancion cancion;

    public Pedidocancion() {
    }

    public Pedidocancion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
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
        if (!(object instanceof Pedidocancion)) {
            return false;
        }
        Pedidocancion other = (Pedidocancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Pedidocancion[ id=" + id + " ]";
    }
    
}
