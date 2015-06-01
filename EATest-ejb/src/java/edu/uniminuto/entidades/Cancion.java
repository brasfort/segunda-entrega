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
@Table(name = "cancion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c"),
    @NamedQuery(name = "Cancion.findById", query = "SELECT c FROM Cancion c WHERE c.id = :id"),
    @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cancion.findByAnhio", query = "SELECT c FROM Cancion c WHERE c.anhio = :anhio"),
    @NamedQuery(name = "Cancion.findByDuracion", query = "SELECT c FROM Cancion c WHERE c.duracion = :duracion"),
    @NamedQuery(name = "Cancion.findByPeso", query = "SELECT c FROM Cancion c WHERE c.peso = :peso"),
    @NamedQuery(name = "Cancion.findByCalidad", query = "SELECT c FROM Cancion c WHERE c.calidad = :calidad"),
    @NamedQuery(name = "Cancion.findByPrecio", query = "SELECT c FROM Cancion c WHERE c.precio = :precio")})
public class Cancion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "anhio")
    private String anhio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "peso")
    private String peso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "calidad")
    private String calidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancion")
    private List<Recocancion> recocancionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancion")
    private List<Compracancion> compracancionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancion")
    private List<Pedidocancion> pedidocancionList;
    @JoinColumn(name = "interprete", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Interprete interprete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancion")
    private List<Discocancion> discocancionList;

    public Cancion() {
    }

    public Cancion(Long id) {
        this.id = id;
    }

    public Cancion(Long id, String nombre, String anhio, Date duracion, String peso, String calidad, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.anhio = anhio;
        this.duracion = duracion;
        this.peso = peso;
        this.calidad = calidad;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnhio() {
        return anhio;
    }

    public void setAnhio(String anhio) {
        this.anhio = anhio;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Recocancion> getRecocancionList() {
        return recocancionList;
    }

    public void setRecocancionList(List<Recocancion> recocancionList) {
        this.recocancionList = recocancionList;
    }

    @XmlTransient
    public List<Compracancion> getCompracancionList() {
        return compracancionList;
    }

    public void setCompracancionList(List<Compracancion> compracancionList) {
        this.compracancionList = compracancionList;
    }

    @XmlTransient
    public List<Pedidocancion> getPedidocancionList() {
        return pedidocancionList;
    }

    public void setPedidocancionList(List<Pedidocancion> pedidocancionList) {
        this.pedidocancionList = pedidocancionList;
    }

    public Interprete getInterprete() {
        return interprete;
    }

    public void setInterprete(Interprete interprete) {
        this.interprete = interprete;
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
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.uniminuto.entidades.Cancion[ id=" + id + " ]";
    }
    
}
