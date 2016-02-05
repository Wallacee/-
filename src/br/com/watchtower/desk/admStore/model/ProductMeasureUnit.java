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
@Table(name = "product_measure_unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductMeasureUnit.findAll", query = "SELECT p FROM ProductMeasureUnit p ORDER BY p.nameMeasure"),
    @NamedQuery(name = "ProductMeasureUnit.findById", query = "SELECT p FROM ProductMeasureUnit p WHERE p.id = :id"),
    @NamedQuery(name = "ProductMeasureUnit.findByNameMeasure", query = "SELECT p FROM ProductMeasureUnit p WHERE p.nameMeasure = :nameMeasure"),
    @NamedQuery(name = "ProductMeasureUnit.findByShortNameMeasure", query = "SELECT p FROM ProductMeasureUnit p WHERE p.shortNameMeasure = :shortNameMeasure"),
    @NamedQuery(name = "ProductMeasureUnit.findByNameMeasureNShortNameMeasure", query = "SELECT p FROM ProductMeasureUnit p WHERE p.nameMeasure = :nameMeasure AND p.shortNameMeasure = :shortNameMeasure"),
    @NamedQuery(name = "ProductMeasureUnit.findByActive", query = "SELECT p FROM ProductMeasureUnit p WHERE p.active = :active"),
    @NamedQuery(name = "ProductMeasureUnit.findByDateRegistration", query = "SELECT p FROM ProductMeasureUnit p WHERE p.dateRegistration = :dateRegistration")})
public class ProductMeasureUnit implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME_MEASURE")
    private String nameMeasure;
    @Column(name = "SHORT_NAME_MEASURE")
    private String shortNameMeasure;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productMeasureUnitId")
    private Collection<Product> productCollection;

    public ProductMeasureUnit() {
    }

    public ProductMeasureUnit(Integer id) {
        this.id = id;
    }

    public ProductMeasureUnit(Integer id, String nameMeasure) {
        this.id = id;
        this.nameMeasure = nameMeasure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameMeasure() {
        return nameMeasure;
    }

    public void setNameMeasure(String nameMeasure) {
        this.nameMeasure = nameMeasure;
    }

    public String getShortNameMeasure() {
        return shortNameMeasure;
    }

    public void setShortNameMeasure(String shortNameMeasure) {
        this.shortNameMeasure = shortNameMeasure;
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
        if (!(object instanceof ProductMeasureUnit)) {
            return false;
        }
        ProductMeasureUnit other = (ProductMeasureUnit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductMeasureUnit[ id=" + id + " ]";
    }
    
}
