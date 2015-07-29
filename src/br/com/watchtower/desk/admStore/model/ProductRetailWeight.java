/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wallace
 */
@Entity
@Table(name = "product_retail_weight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductRetailWeight.findAll", query = "SELECT p FROM ProductRetailWeight p"),
    @NamedQuery(name = "ProductRetailWeight.findById", query = "SELECT p FROM ProductRetailWeight p WHERE p.id = :id"),
    @NamedQuery(name = "ProductRetailWeight.findByWeightValue", query = "SELECT p FROM ProductRetailWeight p WHERE p.weightValue = :weightValue"),
    @NamedQuery(name = "ProductRetailWeight.findByDateRegistration", query = "SELECT p FROM ProductRetailWeight p WHERE p.dateRegistration = :dateRegistration")})
public class ProductRetailWeight implements BaseModel {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "WEIGHT_VALUE")
    private float weightValue;
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Product productId;

    public ProductRetailWeight() {
    }

    public ProductRetailWeight(Integer id) {
        this.id = id;
    }

    public ProductRetailWeight(Integer id, float weightValue) {
        this.id = id;
        this.weightValue = weightValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(float weightValue) {
        this.weightValue = weightValue;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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
        if (!(object instanceof ProductRetailWeight)) {
            return false;
        }
        ProductRetailWeight other = (ProductRetailWeight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductRetailWeight[ id=" + id + " ]";
    }
    
}
