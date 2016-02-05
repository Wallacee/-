/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.productBrandId.brandName"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByMeasureUnit", query = "SELECT p FROM Product p WHERE p.measureUnit = :measureUnit"),
    @NamedQuery(name = "Product.findByActive", query = "SELECT p FROM Product p WHERE p.active = :active"),
    @NamedQuery(name = "Product.findByDateRegistration", query = "SELECT p FROM Product p WHERE p.dateRegistration = :dateRegistration"),
    @NamedQuery(name = "Product.findByAll", query = "SELECT p FROM Product p WHERE p.productBrandId.brandName = :brandName AND p.productCategoryId.category = :category AND p.productCoverId.cover = :cover AND p.productDetailId.detail = :detail AND p.productMeasureUnitId.nameMeasure = :nameMeasure AND p.productMeasureUnitId.shortNameMeasure = :shortNameMeasure AND p.productTypeId.type = :type AND p.productValueMeasureUnitId.value = :valueMeasureUnit AND p.measureUnit = :measureUnit AND p.active = :active")})
public class Product implements BaseModel {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "MEASURE_UNIT")
    private boolean measureUnit;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Basic(optional = false)
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @JoinColumn(name = "PRODUCT_MEASURE_UNIT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductMeasureUnit productMeasureUnitId;
    @JoinColumn(name = "PRODUCT_BRAND_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductBrand productBrandId;
    @JoinColumn(name = "PRODUCT_CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductCategory productCategoryId;
    @JoinColumn(name = "PRODUCT_COVER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductCover productCoverId;
    @JoinColumn(name = "PRODUCT_DETAIL_ID", referencedColumnName = "ID")
    @ManyToOne
    private ProductDetail productDetailId;
    @JoinColumn(name = "PRODUCT_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductType productTypeId;
    @JoinColumn(name = "PRODUCT_VALUE_MEASURE_UNIT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductValueMeasureUnit productValueMeasureUnitId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<StorePratileiraAmount> storePratileiraAmountCollection;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, boolean measureUnit, Date dateRegistration) {
        this.id = id;
        this.measureUnit = measureUnit;
        this.dateRegistration = dateRegistration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(boolean measureUnit) {
        this.measureUnit = measureUnit;
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

    public ProductMeasureUnit getProductMeasureUnitId() {
        return productMeasureUnitId;
    }

    public void setProductMeasureUnitId(ProductMeasureUnit productMeasureUnitId) {
        this.productMeasureUnitId = productMeasureUnitId;
    }

    public ProductBrand getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(ProductBrand productBrandId) {
        this.productBrandId = productBrandId;
    }

    public ProductCategory getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(ProductCategory productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public ProductCover getProductCoverId() {
        return productCoverId;
    }

    public void setProductCoverId(ProductCover productCoverId) {
        this.productCoverId = productCoverId;
    }

    public ProductDetail getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(ProductDetail productDetailId) {
        this.productDetailId = productDetailId;
    }

    public ProductType getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(ProductType productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ProductValueMeasureUnit getProductValueMeasureUnitId() {
        return productValueMeasureUnitId;
    }

    public void setProductValueMeasureUnitId(ProductValueMeasureUnit productValueMeasureUnitId) {
        this.productValueMeasureUnitId = productValueMeasureUnitId;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.watchtower.desk.admStore.model.Product[ id=" + id + " ]";
    }

}
