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
@Table(name = "product_value_measure_unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductValueMeasureUnit.findAll", query = "SELECT p FROM ProductValueMeasureUnit p"),
    @NamedQuery(name = "ProductValueMeasureUnit.findById", query = "SELECT p FROM ProductValueMeasureUnit p WHERE p.id = :id"),
    @NamedQuery(name = "ProductValueMeasureUnit.findByValue", query = "SELECT p FROM ProductValueMeasureUnit p WHERE p.value = :value"),
    @NamedQuery(name = "ProductValueMeasureUnit.findByDateRegistration", query = "SELECT p FROM ProductValueMeasureUnit p WHERE p.dateRegistration = :dateRegistration")})
public class ProductValueMeasureUnit implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "VALUE")
    private float value;
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productValueMeasureUnitId")
    private Collection<Product> productCollection;

    public ProductValueMeasureUnit() {
    }

    public ProductValueMeasureUnit(Integer id) {
        this.id = id;
    }

    public ProductValueMeasureUnit(Integer id, float value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
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
        if (!(object instanceof ProductValueMeasureUnit)) {
            return false;
        }
        ProductValueMeasureUnit other = (ProductValueMeasureUnit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductValueMeasureUnit[ id=" + id + " ]";
    }
    
}
