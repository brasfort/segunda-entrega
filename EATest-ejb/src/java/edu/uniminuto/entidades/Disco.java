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
@Table(name = "disco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disco.findAll", query = "SELECT d FROM Disco d"),
    @NamedQuery(name = "Disco.findById", query = "SELECT d FROM Disco d WHERE d.id = :id"),
    @NamedQuery(name = "Disco.findByNombre", query = "SELECT d FROM Disco d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Disco.findByImagen", query = "SELECT d FROM Disco d WHERE d.imagen = :imagen"),
    @NamedQuery(name = "Disco.findByAnhio", query = "SELECT d FROM Disco d WHERE d.anhio = :anhio")})
public class Disco implements Serializable {
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
    @Size(min = 1, max = 200)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anhio")
    @Temporal(TemporalType.DATE)
    private Date anhio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disco")
    private List<Discopropietario> discopropietarioList;
    @JoinColumn(name = "interprete", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Interprete interprete;
    @JoinColumn(name = "genero", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Genero genero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disco")
    private List<Compradisco> compradiscoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disco")
    private List<Discocancion> discocancionList;

    public Disco() {
    }

    public Disco(Integer id) {
        this.id = id;
    }

    public Disco(Integer id, String nombre, String imagen, Date anhio) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.anhio = anhio;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getAnhio() {
        return anhio;
    }

    public void setAnhio(Date anhio) {
        this.anhio = anhio;
    }

    @XmlTransient
    public List<Discopropietario> getDiscopropietarioList() {
        return discopropietarioList;
    }

    public void setDiscopropietarioList(List<Discopropietario> discopropietarioList) {
        this.discopropietarioList = discopropietarioList;
    }

    public Interprete getInterprete() {
        return interprete;
    }

    public void setInterprete(Interprete interprete) {
        this.interprete = interprete;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @XmlTransient
    public List<Compradisco> getCompradiscoList() {
        return compradiscoList;
    }

    public void setCompradiscoList(List<Compradisco> compradiscoList) {
        this.compradiscoList = compradiscoList;
    }

    @XmlTransient
    public List<Discocancion> getDiscocancionList() {
        return discocancionList;
    }

    public void setDiscocancionList(List<Discocancion> discocancionList) {
        this.discocancionList = discocancionList;
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
        if (!(object instanceof Disco)) {
            return false;
        }
        Disco other = (Disco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Disco[ id=" + id + " ]";
    }
    
}
