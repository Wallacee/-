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
@Table(name = "product_brand")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductBrand.findAll", query = "SELECT p FROM ProductBrand p ORDER BY p.brandName"),
    @NamedQuery(name = "ProductBrand.findById", query = "SELECT p FROM ProductBrand p WHERE p.id = :id"),
    @NamedQuery(name = "ProductBrand.findByBrandName", query = "SELECT p FROM ProductBrand p WHERE p.brandName = :brandName"),
    @NamedQuery(name = "ProductBrand.findByActive", query = "SELECT p FROM ProductBrand p WHERE p.active = :active"),
    @NamedQuery(name = "ProductBrand.findByDateRegistration", query = "SELECT p FROM ProductBrand p WHERE p.dateRegistration = :dateRegistration")})
public class ProductBrand implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "BRAND_NAME")
    private String brandName;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productBrandId")
    private Collection<Product> productCollection;

    public ProductBrand() {
    }

    public ProductBrand(Integer id) {
        this.id = id;
    }

    public ProductBrand(Integer id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
        if (!(object instanceof ProductBrand)) {
            return false;
        }
        ProductBrand other = (ProductBrand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductBrand[ id=" + id + " ]";
    }
    
}
