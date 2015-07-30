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
@Table(name = "financial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Financial.findAll", query = "SELECT f FROM Financial f"),
    @NamedQuery(name = "Financial.findById", query = "SELECT f FROM Financial f WHERE f.id = :id"),
    @NamedQuery(name = "Financial.findByValueBuy", query = "SELECT f FROM Financial f WHERE f.valueBuy = :valueBuy"),
    @NamedQuery(name = "Financial.findByValueEstimatedSale", query = "SELECT f FROM Financial f WHERE f.valueEstimatedSale = :valueEstimatedSale")})
public class ProductFinancial implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "VALUE_BUY")
    private float valueBuy;
    @Basic(optional = false)
    @Column(name = "VALUE_ESTIMATED_SALE")
    private float valueEstimatedSale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "financialId")
    private Collection<Product> productCollection;

    public ProductFinancial() {
    }

    public ProductFinancial(Integer id) {
        this.id = id;
    }

    public ProductFinancial(Integer id, float valueBuy, float valueEstimatedSale) {
        this.id = id;
        this.valueBuy = valueBuy;
        this.valueEstimatedSale = valueEstimatedSale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValueBuy() {
        return valueBuy;
    }

    public void setValueBuy(float valueBuy) {
        this.valueBuy = valueBuy;
    }

    public float getValueEstimatedSale() {
        return valueEstimatedSale;
    }

    public void setValueEstimatedSale(float valueEstimatedSale) {
        this.valueEstimatedSale = valueEstimatedSale;
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
        if (!(object instanceof ProductFinancial)) {
            return false;
        }
        ProductFinancial other = (ProductFinancial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.Financial[ id=" + id + " ]";
    }
    
}
