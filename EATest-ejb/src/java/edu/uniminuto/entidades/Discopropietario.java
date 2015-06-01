/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jhon Gómez
 */
@Entity
@Table(name = "discopropietario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discopropietario.findAll", query = "SELECT d FROM Discopropietario d"),
    @NamedQuery(name = "Discopropietario.findById", query = "SELECT d FROM Discopropietario d WHERE d.id = :id"),
    @NamedQuery(name = "Discopropietario.findByPrecio", query = "SELECT d FROM Discopropietario d WHERE d.precio = :precio"),
    @NamedQuery(name = "Discopropietario.findByVendido", query = "SELECT d FROM Discopropietario d WHERE d.vendido = :vendido")})
public class Discopropietario implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "vendido")
    private boolean vendido;
    @JoinColumn(name = "propietario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona propietario;
    @JoinColumn(name = "disco", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Disco disco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discop")
    private List<Pedidodisco> pedidodiscoList;

    public Discopropietario() {
    }

    public Discopropietario(Integer id) {
        this.id = id;
    }

    public Discopropietario(Integer id, long precio, boolean vendido) {
        this.id = id;
        this.precio = precio;
        this.vendido = vendido;
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

    public boolean getVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    @XmlTransient
    public List<Pedidodisco> getPedidodiscoList() {
        return pedidodiscoList;
    }

    public void setPedidodiscoList(List<Pedidodisco> pedidodiscoList) {
        this.pedidodiscoList = pedidodiscoList;
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
        if (!(object instanceof Discopropietario)) {
            return false;
        }
        Discopropietario other = (Discopropietario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Discopropietario[ id=" + id + " ]";
    }
    
}
