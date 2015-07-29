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
@Table(name = "e_contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EContact.findAll", query = "SELECT e FROM EContact e"),
    @NamedQuery(name = "EContact.findById", query = "SELECT e FROM EContact e WHERE e.id = :id"),
    @NamedQuery(name = "EContact.findByContact", query = "SELECT e FROM EContact e WHERE e.contact = :contact")})
public class EContact implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CONTACT")
    private String contact;
    @JoinTable(name = "supplier_has_e_contact", joinColumns = {
        @JoinColumn(name = "E_CONTACT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Supplier> supplierCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eContactId")
    private Collection<Employee> employeeCollection;

    public EContact() {
    }

    public EContact(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @XmlTransient
    public Collection<Supplier> getSupplierCollection() {
        return supplierCollection;
    }

    public void setSupplierCollection(Collection<Supplier> supplierCollection) {
        this.supplierCollection = supplierCollection;
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
        if (!(object instanceof EContact)) {
            return false;
        }
        EContact other = (EContact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.EContact[ id=" + id + " ]";
    }
    
}
