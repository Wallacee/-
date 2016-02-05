/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.util.Collection;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wallace
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByCodeRg", query = "SELECT e FROM Employee e WHERE e.codeRg = :codeRg"),
    @NamedQuery(name = "Employee.findByCodeCpf", query = "SELECT e FROM Employee e WHERE e.codeCpf = :codeCpf")})
public class Employee implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODE_RG")
    private String codeRg;
    @Column(name = "CODE_CPF")
    private String codeCpf;
    @JoinTable(name = "employee_has_phone", joinColumns = {
        @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PHONE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Phone> phoneCollection;
    @JoinTable(name = "store_has_employee", joinColumns = {
        @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "STORE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Store> storeCollection;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Address addressId;
    @JoinColumn(name = "E_CONTACT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EContact eContactId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userId;

    public Employee() {
    }

    public Employee(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeRg() {
        return codeRg;
    }

    public void setCodeRg(String codeRg) {
        this.codeRg = codeRg;
    }

    public String getCodeCpf() {
        return codeCpf;
    }

    public void setCodeCpf(String codeCpf) {
        this.codeCpf = codeCpf;
    }

    @XmlTransient
    public Collection<Phone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    @XmlTransient
    public Collection<Store> getStoreCollection() {
        return storeCollection;
    }

    public void setStoreCollection(Collection<Store> storeCollection) {
        this.storeCollection = storeCollection;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public EContact getEContactId() {
        return eContactId;
    }

    public void setEContactId(EContact eContactId) {
        this.eContactId = eContactId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.Employee[ id=" + id + " ]";
    }
    
}
