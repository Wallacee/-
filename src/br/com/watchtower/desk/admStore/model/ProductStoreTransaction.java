/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.watchtower.desk.admStore.model;

/**
 *
 * @author Wallace
 */
public class ProductStoreTransaction {

    private Product product;
    private float buyValue;
    private float saleValue;
    private int amount;

    public ProductStoreTransaction(Product product, float buyValue, float saleValue, int amount) {
        this.product = product;
        this.buyValue = buyValue;
        this.saleValue = saleValue;
        this.amount = amount;
    }

    public ProductStoreTransaction() {
    }

    public Product getProduct() {
        return product;
    }

    public String getshortProductName() {
        return getProduct().getProductCategoryId().getCategory() + " - "
                + getProduct().getProductCoverId().getCover() + " "
                + getProduct().getProductTypeId().getType() + " "
                + getProduct().getProductBrandId().getBrandName() + " "
                + getProduct().getProductValueMeasureUnitId().getValue() + " "
                + getProduct().getProductMeasureUnitId().getNameMeasure() + " (" + getProduct().getProductMeasureUnitId().getShortNameMeasure() + ") "
                + getProduct().getProductDetailId().getDetail();
    }

    public float getUnitariLiquidAmount() {
        return (getSaleValue() - getBuyValue());
    }

    public float getTotalLiquidAmount() {
        return getAmount() * getUnitariLiquidAmount();
    }

    public float getTotalBuy() {
        return getAmount() * getBuyValue();
    }

    public float getTotalSale() {
        return getAmount() * getSaleValue();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(float buyValue) {
        this.buyValue = buyValue;
    }

    public float getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(float saleValue) {
        this.saleValue = saleValue;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
