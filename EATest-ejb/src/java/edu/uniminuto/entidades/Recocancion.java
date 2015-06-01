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
@Table(name = "recocancion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recocancion.findAll", query = "SELECT r FROM Recocancion r"),
    @NamedQuery(name = "Recocancion.findById", query = "SELECT r FROM Recocancion r WHERE r.id = :id")})
public class Recocancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "recopilacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Recopilacion recopilacion;
    @JoinColumn(name = "cancion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cancion cancion;

    public Recocancion() {
    }

    public Recocancion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recopilacion getRecopilacion() {
        return recopilacion;
    }

    public void setRecopilacion(Recopilacion recopilacion) {
        this.recopilacion = recopilacion;
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
        if (!(object instanceof Recocancion)) {
            return false;
        }
        Recocancion other = (Recocancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Recocancion[ id=" + id + " ]";
    }
    
}
