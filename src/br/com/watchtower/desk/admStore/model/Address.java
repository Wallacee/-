/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wallace
 */
@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByNumberProperty", query = "SELECT a FROM Address a WHERE a.numberProperty = :numberProperty"),
    @NamedQuery(name = "Address.findByNamePublicPlace", query = "SELECT a FROM Address a WHERE a.namePublicPlace = :namePublicPlace"),
    @NamedQuery(name = "Address.findByNeighborhood", query = "SELECT a FROM Address a WHERE a.neighborhood = :neighborhood"),
    @NamedQuery(name = "Address.findByCodeCep", query = "SELECT a FROM Address a WHERE a.codeCep = :codeCep")})
public class Address implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMBER_PROPERTY")
    private Integer numberProperty;
    @Column(name = "NAME_PUBLIC_PLACE")
    private String namePublicPlace;
    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;
    @Column(name = "CODE_CEP")
    private String codeCep;
    @JoinTable(name = "address_has_phone", joinColumns = {
        @JoinColumn(name = "ADRESS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PHONE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Phone> phoneCollection;
    @JoinTable(name = "supplier_has_address", joinColumns = {
        @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Supplier> supplierCollection;
    @JoinColumn(name = "UF_CODE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UfCode ufCodeId;
    @JoinColumn(name = "PUBLIC_PLACE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PublicPlace publicPlaceId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adressId")
    private Collection<Store> storeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId")
    private Collection<Employee> employeeCollection;

    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumberProperty() {
        return numberProperty;
    }

    public void setNumberProperty(Integer numberProperty) {
        this.numberProperty = numberProperty;
    }

    public String getNamePublicPlace() {
        return namePublicPlace;
    }

    public void setNamePublicPlace(String namePublicPlace) {
        this.namePublicPlace = namePublicPlace;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCodeCep() {
        return codeCep;
    }

    public void setCodeCep(String codeCep) {
        this.codeCep = codeCep;
    }

    @XmlTransient
    public Collection<Phone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    @XmlTransient
    public Collection<Supplier> getSupplierCollection() {
        return supplierCollection;
    }

    public void setSupplierCollection(Collection<Supplier> supplierCollection) {
        this.supplierCollection = supplierCollection;
    }

    public UfCode getUfCodeId() {
        return ufCodeId;
    }

    public void setUfCodeId(UfCode ufCodeId) {
        this.ufCodeId = ufCodeId;
    }

    public PublicPlace getPublicPlaceId() {
        return publicPlaceId;
    }

    public void setPublicPlaceId(PublicPlace publicPlaceId) {
        this.publicPlaceId = publicPlaceId;
    }

    @XmlTransient
    public Collection<Store> getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Collection<Store> storeCollection) {
        this.storeCollection = storeCollection;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.Address[ id=" + id + " ]";
    }
    
}
