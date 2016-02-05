/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.io.Serializable;
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
@Table(name = "store_pratileira_amount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorePratileiraAmount.findAll", query = "SELECT s FROM StorePratileiraAmount s"),
    @NamedQuery(name = "StorePratileiraAmount.findLastIn", query = "SELECT MAX(s.id) FROM StorePratileiraAmount s"),
    @NamedQuery(name = "StorePratileiraAmount.findAllByMeasureUnit", query = "SELECT s FROM StorePratileiraAmount s WHERE s.productId.measureUnit = :boolean"),
    @NamedQuery(name = "StorePratileiraAmount.findById", query = "SELECT s FROM StorePratileiraAmount s WHERE s.id = :id"),
    @NamedQuery(name = "StorePratileiraAmount.findByProductId", query = "SELECT s FROM StorePratileiraAmount s WHERE s.productId.id = :productId"),
    @NamedQuery(name = "StorePratileiraAmount.findByBuyValueNProductId", query = "SELECT s FROM StorePratileiraAmount s WHERE s.productBuyValueId.valueBuy = :buy AND s.productId.id = :productId"),
    @NamedQuery(name = "StorePratileiraAmount.findBySaleValueNProductId", query = "SELECT s FROM StorePratileiraAmount s WHERE s.productSaleValueId.value = :sale AND s.productId.id = :productId"),
    @NamedQuery(name = "StorePratileiraAmount.findByProductSalledId", query = "SELECT s FROM StorePratileiraAmount s WHERE s.productId.id = :productId AND s.pratileira = :pratileira"),
    @NamedQuery(name = "StorePratileiraAmount.findByPratileira", query = "SELECT s FROM StorePratileiraAmount s WHERE s.pratileira = :pratileira"),
    @NamedQuery(name = "StorePratileiraAmount.findByDateRegistration", query = "SELECT s FROM StorePratileiraAmount s WHERE s.dateRegistration = :dateRegistration")})
public class StorePratileiraAmount implements BaseModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PRATILEIRA")
    private boolean pratileira;
    @Basic(optional = false)
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @Column(name = "DATE_SALE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSale;
    @JoinColumn(name = "PRODUCT_BUY_VALUE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductBuyValue productBuyValueId;
    @JoinColumn(name = "PRODUCT_SALE_VALUE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductSaleValue productSaleValueId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Product productId;

    public StorePratileiraAmount() {
    }

    public StorePratileiraAmount(Integer id) {
        this.id = id;
    }

    public StorePratileiraAmount(Integer id, boolean pratileira, Date dateRegistration) {
        this.id = id;
        this.pratileira = pratileira;
        this.dateRegistration = dateRegistration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getPratileira() {
        return pratileira;
    }

    public void setPratileira(boolean pratileira) {
        this.pratileira = pratileira;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public ProductBuyValue getProductBuyValueId() {
        return productBuyValueId;
    }

    public void setProductBuyValueId(ProductBuyValue productBuyValueId) {
        this.productBuyValueId = productBuyValueId;
    }

    public ProductSaleValue getProductSaleValueId() {
        return productSaleValueId;
    }

    public void setProductSaleValueId(ProductSaleValue productSaleValueId) {
        this.productSaleValueId = productSaleValueId;
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
        if (!(object instanceof StorePratileiraAmount)) {
            return false;
        }
        StorePratileiraAmount other = (StorePratileiraAmount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.StorePratileiraAmount[ id=" + id + " ]";
    }

}
