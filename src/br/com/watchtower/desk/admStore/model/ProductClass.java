/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "product_class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductClass.findAll", query = "SELECT p FROM ProductClass p"),
    @NamedQuery(name = "ProductClass.findById", query = "SELECT p FROM ProductClass p WHERE p.id = :id"),
    @NamedQuery(name = "ProductClass.findByNameClass", query = "SELECT p FROM ProductClass p WHERE p.nameClass = :nameClass")})
public class ProductClass implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME_CLASS")
    private String nameClass;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productClassId")
    private Collection<Product> productCollection;

    public ProductClass() {
    }

    public ProductClass(Integer id) {
        this.id = id;
    }

    public ProductClass(Integer id, String nameClass) {
        this.id = id;
        this.nameClass = nameClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
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
        if (!(object instanceof ProductClass)) {
            return false;
        }
        ProductClass other = (ProductClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductClass[ id=" + id + " ]";
    }
    
}
