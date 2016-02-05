/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;


import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wallace
 */
@Entity
@Table(name = "product_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductType.findAll", query = "SELECT p FROM ProductType p ORDER BY p.type"),
    @NamedQuery(name = "ProductType.findById", query = "SELECT p FROM ProductType p WHERE p.id = :id"),
    @NamedQuery(name = "ProductType.findByType", query = "SELECT p FROM ProductType p WHERE p.type = :type"),
    @NamedQuery(name = "ProductType.findByActive", query = "SELECT p FROM ProductType p WHERE p.active = :active"),
    @NamedQuery(name = "ProductType.findByDateRegistration", query = "SELECT p FROM ProductType p WHERE p.dateRegistration = :dateRegistration")})
public class ProductType implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productTypeId")
    private Collection<Product> productCollection;

    public ProductType() {
    }

    public ProductType(Integer id) {
        this.id = id;
    }

    public ProductType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
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
        if (!(object instanceof ProductType)) {
            return false;
        }
        ProductType other = (ProductType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductType[ id=" + id + " ]";
    }
    
}
