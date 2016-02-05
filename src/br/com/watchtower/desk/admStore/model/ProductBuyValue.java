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
@Table(name = "product_buy_value")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductBuyValue.findAll", query = "SELECT p FROM ProductBuyValue p"),
    @NamedQuery(name = "ProductBuyValue.findById", query = "SELECT p FROM ProductBuyValue p WHERE p.id = :id"),
    @NamedQuery(name = "ProductBuyValue.findByStorePratileiraAmountId", query = "SELECT p FROM ProductBuyValue p JOIN p.storePratileiraAmountCollection v WHERE v.id = :id"),
    @NamedQuery(name = "ProductBuyValue.findByValueBuy", query = "SELECT p FROM ProductBuyValue p WHERE p.valueBuy = :valueBuy"),
    @NamedQuery(name = "ProductBuyValue.findByBiggestValueBuy", query = "SELECT p FROM ProductBuyValue p WHERE p.valueBuy >= :valueBuy"),
    @NamedQuery(name = "ProductBuyValue.findBySmallestValueBuy", query = "SELECT p FROM ProductBuyValue p WHERE p.valueBuy <= :valueBuy"),
    @NamedQuery(name = "ProductBuyValue.findByProductId", query = "SELECT p FROM ProductBuyValue p JOIN p.storePratileiraAmountCollection v WHERE v.productId.id = :productId ORDER BY p.dateRegistration"),
    @NamedQuery(name = "ProductBuyValue.findByDateRegistration", query = "SELECT p FROM ProductBuyValue p WHERE p.dateRegistration = :dateRegistration")})
public class ProductBuyValue implements BaseModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "VALUE_BUY")
    private float valueBuy;
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productBuyValueId")
    private Collection<StorePratileiraAmount> storePratileiraAmountCollection;

    public ProductBuyValue() {
    }

    public ProductBuyValue(Integer id) {
        this.id = id;
    }

    public ProductBuyValue(Integer id, float valueBuy) {
        this.id = id;
        this.valueBuy = valueBuy;
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

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @XmlTransient
    public Collection<StorePratileiraAmount> getStorePratileiraAmountCollection() {
        return storePratileiraAmountCollection;
    }

    public void setStorePratileiraAmountCollection(Collection<StorePratileiraAmount> storePratileiraAmountCollection) {
        this.storePratileiraAmountCollection = storePratileiraAmountCollection;
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
        if (!(object instanceof ProductBuyValue)) {
            return false;
        }
        ProductBuyValue other = (ProductBuyValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.ProductBuyValue[ id=" + id + " ]";
    }

}
