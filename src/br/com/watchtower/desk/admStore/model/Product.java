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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByMeasureUnit", query = "SELECT p FROM Product p WHERE p.measureUnit = :measureUnit"),
    @NamedQuery(name = "Product.findByDateRegistration", query = "SELECT p FROM Product p WHERE p.dateRegistration = :dateRegistration")})
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
    @Basic(optional = false)
    @Column(name = "DATE_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistration;
    @JoinTable(name = "product_has_product_value_measure_unit", joinColumns = {
        @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PRODUCT_VALUE_MEASURE_UNIT_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<ProductValueMeasureUnit> productValueMeasureUnitCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ProductRetailWeight> productRetailWeightCollection;
    @JoinColumn(name = "FINANCIAL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductFinancial financialId;
    @JoinColumn(name = "MEASURE_UNIT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductMeasureUnit measureUnitId;
    @JoinColumn(name = "PRODUCT_BRAND_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductBrand productBrandId;
    @JoinColumn(name = "PRODUCT_CLASS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductClass productClassId;
    @JoinColumn(name = "PRODUCT_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ProductType productTypeId;

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

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @XmlTransient
    public Collection<ProductValueMeasureUnit> getProductValueMeasureUnitCollection() {
        return productValueMeasureUnitCollection;
    }

    public void setProductValueMeasureUnitCollection(Collection<ProductValueMeasureUnit> productValueMeasureUnitCollection) {
        this.productValueMeasureUnitCollection = productValueMeasureUnitCollection;
    }

    @XmlTransient
    public Collection<ProductRetailWeight> getProductRetailWeightCollection() {
        return productRetailWeightCollection;
    }

    public void setProductRetailWeightCollection(Collection<ProductRetailWeight> productRetailWeightCollection) {
        this.productRetailWeightCollection = productRetailWeightCollection;
    }

    public ProductFinancial getFinancialId() {
        return financialId;
    }

    public void setFinancialId(ProductFinancial financialId) {
        this.financialId = financialId;
    }

    public ProductMeasureUnit getMeasureUnitId() {
        return measureUnitId;
    }

    public void setMeasureUnitId(ProductMeasureUnit measureUnitId) {
        this.measureUnitId = measureUnitId;
    }

    public ProductBrand getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(ProductBrand productBrandId) {
        this.productBrandId = productBrandId;
    }

    public ProductClass getProductClassId() {
        return productClassId;
    }

    public void setProductClassId(ProductClass productClassId) {
        this.productClassId = productClassId;
    }

    public ProductType getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(ProductType productTypeId) {
        this.productTypeId = productTypeId;
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
