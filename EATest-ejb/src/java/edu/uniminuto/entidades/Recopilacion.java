/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.entidades;

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
 * @author Jhon Gómez
 */
@Entity
@Table(name = "recopilacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recopilacion.findAll", query = "SELECT r FROM Recopilacion r"),
    @NamedQuery(name = "Recopilacion.findById", query = "SELECT r FROM Recopilacion r WHERE r.id = :id"),
    @NamedQuery(name = "Recopilacion.findByNombre", query = "SELECT r FROM Recopilacion r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Recopilacion.findByFecha", query = "SELECT r FROM Recopilacion r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Recopilacion.findByPublica", query = "SELECT r FROM Recopilacion r WHERE r.publica = :publica")})
public class Recopilacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publica")
    private boolean publica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recopilacion")
    private List<Recocancion> recocancionList;
    @JoinColumn(name = "propietario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona propietario;

    public Recopilacion() {
    }

    public Recopilacion(Integer id) {
        this.id = id;
    }

    public Recopilacion(Integer id, String nombre, Date fecha, boolean publica) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.publica = publica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    @XmlTransient
    public List<Recocancion> getRecocancionList() {
        return recocancionList;
    }

    public void setRecocancionList(List<Recocancion> recocancionList) {
        this.recocancionList = recocancionList;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
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
        if (!(object instanceof Recopilacion)) {
            return false;
        }
        Recopilacion other = (Recopilacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Recopilacion[ id=" + id + " ]";
    }
    
}
