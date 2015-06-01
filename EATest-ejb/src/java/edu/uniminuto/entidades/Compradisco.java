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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jhon Gómez
 */
@Entity
@Table(name = "compradisco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compradisco.findAll", query = "SELECT c FROM Compradisco c"),
    @NamedQuery(name = "Compradisco.findById", query = "SELECT c FROM Compradisco c WHERE c.id = :id"),
    @NamedQuery(name = "Compradisco.findByPrecio", query = "SELECT c FROM Compradisco c WHERE c.precio = :precio"),
    @NamedQuery(name = "Compradisco.findByCalificacion", query = "SELECT c FROM Compradisco c WHERE c.calificacion = :calificacion")})
public class Compradisco implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "calificacion")
    private int calificacion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private long precio;
    @JoinColumn(name = "vendedor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona vendedor;
    @JoinColumn(name = "disco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Disco disco;
    @JoinColumn(name = "compra", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Compra compra;

    public Compradisco() {
    }

    public Compradisco(Integer id) {
        this.id = id;
    }

    public Compradisco(Integer id, long precio, int calificacion) {
        this.id = id;
        this.precio = precio;
        this.calificacion = calificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }


    public Persona getVendedor() {
        return vendedor;
    }

    public void setVendedor(Persona vendedor) {
        this.vendedor = vendedor;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
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
        if (!(object instanceof Compradisco)) {
            return false;
        }
        Compradisco other = (Compradisco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Compradisco[ id=" + id + " ]";
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    
}
